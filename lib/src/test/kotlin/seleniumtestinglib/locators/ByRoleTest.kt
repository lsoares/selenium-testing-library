package seleniumtestinglib.locators

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.locators.Role.*
import seleniumtestinglib.locators.ByRole.Value
import seleniumtestinglib.queries.JsType.Companion.asJsFunction
import seleniumtestinglib.queries.JsType.Companion.asJsRegex
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(DriverLifeCycle::class)
class ByRoleTest(private val driver: RemoteWebDriver) {

    private fun examples() = setOf(
        of(TextBox, """<input type="text" placeholder="5-digit zipcode" id="txtbox" />"""),
        of(TextBox, """<textarea id="txtboxMultiline" required></textarea>"""),
        of(Button, """"<div id="saveChanges" tabindex="0" role="button" aria-pressed="false">Save</div>"""),
        of(Button, """<button type="button" id="saveChanges">Save</button>"""),
        of(Form, """<div role="form"></div>"""),
        of(Img, """<img></img>"""),
        of(Form, """<form aria-label="xyz">test</form>"""),
    )

    @ParameterizedTest
    @MethodSource("examples")
    fun `by role`(role: Role, content: String) {
        driver.render(content)

        val result = driver.findElement(ByRole(role))

        assertNotNull(result)
        // assertEquals(role, result.ariaRole)
    }


    @Test
    fun `role with regex in name parameter`() {
        driver.render("""<h1>something as a user something</h1>""")

        val result = driver.findElements(ByRole(Heading, name = "/as a user/i".asJsRegex()))

        assertEquals("something as a user something", result.single().accessibleName)
    }

    @Test
    fun `aria-selected`() {
        driver.render(
            """
              <div role="tablist">
                <button role="tab" aria-selected="false">React</button>
                <button role="tab" aria-selected="true">Native</button>
                <button role="tab" aria-selected="false">Cypress</button>
              </div>
        """
        )

        val result = driver.findElements(ByRole(Tab, selected = true))

        assertEquals("Native", result.single().text)
    }

    @Test
    fun `with name`() {
        driver.render(
            """
                <label for="email">Email address</label>
                <input />
                <input
                  type="email"
                  id="email"
                  aria-describedby="email-help"
                  placeholder="Enter email"
                />
                <input />
        """
        )

        val result = driver.findElements(ByRole(TextBox, name = "Email address"))

        assertEquals("input", result.single().tagName)
    }

    private fun `hidden values`() = setOf(
        of(false, listOf("Close dialog")),
        of(true, listOf("Open dialog", "Close dialog")),
    )

    @ParameterizedTest
    @MethodSource("hidden values")
    fun hidden(hidden: Boolean, expectedButtonsFound: List<String>) {
        driver.render(
            """<main aria-hidden="true">
                <button>Open dialog</button>
              </main>
              <div role="dialog">
                <button>Close dialog</button>
              </div>"""
        )

        val result = driver.findElements(ByRole(Button, hidden = hidden))

        assertEquals(expectedButtonsFound, result.map { it.text })
    }

    @Test
    fun description() {
        driver.render(
            """<ul>
                      <li role="alertdialog" aria-describedby="notification-id-1">
                        <div><button>Close</button></div>
                        <div id="notification-id-1">You have unread emails</div>
                      </li>
                      <li role="alertdialog" aria-describedby="notification-id-2">
                        <div><button>Close</button></div>
                        <div id="notification-id-2">Your session is about to expire!</div>
                      </li>
                    </ul>"""
        )

        val result =
            driver.findElements(ByRole(AlertDialog, description = "Your session is about to expire!"))

        assertEquals("Your session is about to expire!", result.single().text.substringAfter("Close\n"))
    }

    @Test
    fun `description with regex`() {
        driver.render(
            """<ul>
                      <li role="alertdialog" aria-describedby="notification-id-1">
                        <div><button>Close</button></div>
                        <div id="notification-id-1">You have unread emails</div>
                      </li>
                      <li role="alertdialog" aria-describedby="notification-id-2">
                        <div><button>Close</button></div>
                        <div id="notification-id-2">Your session is about to expire!</div>
                      </li>
                    </ul>"""
        )

        val result = driver.findElements(
            ByRole(AlertDialog, description = "/your session/i".asJsRegex())
        )

        assertEquals("Your session is about to expire!", result.single().text.substringAfter("Close\n"))
    }

    @Test
    fun `description with function`() {
        driver.render(
            """<ul>
                      <li role="alertdialog" aria-describedby="notification-id-1">
                        <div><button>Close</button></div>
                        <div id="notification-id-1">You have unread emails</div>
                      </li>
                      <li role="alertdialog" aria-describedby="notification-id-2">
                        <div><button>Close</button></div>
                        <div id="notification-id-2">Your session is about to expire!</div>
                      </li>
                    </ul>"""
        )

        val result = driver.findElements(
            ByRole(AlertDialog, description = "content => content.endsWith('!')".asJsFunction())
        )

        assertEquals("Your session is about to expire!", result.single().text.substringAfter("Close\n"))
    }

    private fun `heading level values`() = setOf(
        of(2, listOf("h2", "div")),
        of(null, listOf("h1", "h2", "h3", "div"))
    )

    @ParameterizedTest
    @MethodSource("heading level values")
    fun `heading level`(level: Int?, expectedResults: List<String>) {
        driver.render(
            """<h1>Heading Level One</h1>
                <h2>First Heading Level Two</h2>
                <h3>Heading Level Three</h3>
                <div role="heading" aria-level="2">Second Heading Level Two</div>"""
        )

        val result = driver.findElements(ByRole(Heading, level = level))

        assertEquals(expected = expectedResults, result.map(WebElement::getTagName))
    }

    @Test
    fun checked() {
        driver.render(
            """<button role="checkbox" aria-checked="false">Gummy bears</button>
                    <button role="checkbox" aria-checked="true">Sugar</button>
                    <button role="checkbox" aria-checked="false">Whipped cream</button>"""
        )

        val result = driver.findElements(ByRole(CheckBox, checked = true))

        assertEquals("Sugar", result.single().text)
    }

    @Test
    fun pressed() {
        driver.render(
            """<button aria-pressed="true">👍</button>
                     <button aria-pressed="false">👎</button>"""
        )

        val result = driver.findElements(ByRole(Button, pressed = true))

        assertEquals("👍", result.single().text)
    }

    @Test
    fun current() {
        driver.render(
            """<nav>
                        <a href="current/page" aria-current="true">👍</a>
                        <a href="another/page">👎</a>
                      </nav>"""
        )

        val result = driver.findElements(ByRole(Link, current = true))

        assertEquals("👍", result.single().text)
    }

    @Test
    fun expanded() {
        driver.render(
            """
            <a href="x"></a>
            <a aria-expanded="false" href="x">expanded</a>
            <a href="x"></a>
        """
        )

        val result = driver.findElements(ByRole(Link, expanded = false))

        assertEquals("expanded", result.single().text)
    }

    private fun `test cases query fallbacks`() = setOf(
        of(false, 0),
        of(null, 0),
        of(true, 1),
    )

    @ParameterizedTest
    @MethodSource("test cases query fallbacks")
    fun `query fallbacks`(queryFallbacks: Boolean?, expectedCount: Int) {
        driver.render(
            """ <div role="switch checkbox" /> """
        )

        val result = driver.findElements(ByRole(CheckBox, queryFallbacks = queryFallbacks))

        assertEquals(expectedCount, result.size)
    }

    @Test
    fun busy() {
        driver.render(
            """
             <section>
                <div role="alert" aria-busy="false">Login failed</div>
                <div role="alert" aria-busy="true">Error: Loading message...</div>
              </section>
            """
        )

        val result = driver.findElements(ByRole(Alert, busy = false))

        assertEquals("Login failed", result.single().text)
    }

    private fun `testing value filter`() = setOf(
        of(Value(now = 5), listOf("Volume")),
        of(Value(min = 0), listOf("Volume", "Pitch")),
        of(Value(max = 10), listOf("Volume", "Pitch")),
        of(Value(text = "/med/".asJsRegex()), listOf("Volume", "Pitch")),
    )

    @ParameterizedTest
    @MethodSource("testing value filter")
    fun value(value: Value, expectedSpinButtons: List<String>) {
        driver.render(
            """
             <section>
                <div
                  role="spinbutton"
                  aria-valuenow="5"
                  aria-valuemin="0"
                  aria-valuemax="10"
                  aria-valuetext="medium"
                >
                  Volume
                </div>
                <div
                  role="spinbutton"
                  aria-valuenow="3"
                  aria-valuemin="0"
                  aria-valuemax="10"
                  aria-valuetext="medium"
                >
                  Pitch
                </div>
              </section>
        """
        )

        val result = driver.findElements(ByRole(SpinButton, value = value))

        assertEquals(expectedSpinButtons, result.map(WebElement::getText))
    }
}
