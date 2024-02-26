package seleniumtestinglib.queries

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptException
import org.openqa.selenium.NoSuchElementException
import seleniumtestinglib.driver
import seleniumtestinglib.locators.CurrentType.Page
import seleniumtestinglib.locators.RoleType.SpinButton
import seleniumtestinglib.locators.TL.By.placeholderText
import seleniumtestinglib.locators.TL.By.role
import seleniumtestinglib.locators.TL.By.text
import seleniumtestinglib.locators.Value
import seleniumtestinglib.queries.LocatorType.AltText
import seleniumtestinglib.queries.TextMatch.Companion.asJsFunction
import seleniumtestinglib.render
import java.util.regex.Pattern.CASE_INSENSITIVE
import java.util.regex.Pattern.compile
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class NotFoundTest {

    @Test
    fun `get but none found`() {
        driver.render("<div></div>")

        val result = kotlin.runCatching {
            driver.getBy(AltText, "will not work")
        }

        val ex = result.exceptionOrNull() as JavascriptException
        assertTrue(ex.message?.contains("Unable to find an element with the alt text: will not work")!!, ex.message)
    }

    private fun `findElement error cases`() = setOf(
        of(
            text(compile("a", CASE_INSENSITIVE)),
            "ByText(/a/i)"
        ),
        of(
            placeholderText("el => true".asJsFunction()),
            "ByPlaceholderText(el => true)"
        ),
        of(
            role(SpinButton, current = Page, value = Value(min = 1)),
            "ByRole(spinbutton, value: {min=1}, current: page)"
        ),
    )

    @ParameterizedTest
    @MethodSource("findElement error cases")
    fun `findElement not found`(locator: By, error: String) {
        driver.render("<div></div>")

        val result = runCatching {
            driver.findElement(locator)
        }

        val ex = result.exceptionOrNull() as NoSuchElementException
        assertTrue(ex.message!!.contains("Cannot locate an element using $error"), """Actual: ${ex.message}""")
    }

    @Test
    fun `get all but none found`() {
        driver.render("<div></div>")

        val result = runCatching {
            driver.getAllBy(AltText, "will not work")
        }

        val ex = result.exceptionOrNull() as JavascriptException
        assertTrue(ex.message!!.contains("Unable to find an element with the alt text: will not work"), ex.message)
    }

    @Test
    fun `query returns none`() {
        driver.render("<div></div>")

        val result = driver.queryBy(AltText, "will not work")

        assertNull(result?.tagName)
    }

    @Test
    fun `query all but none found`() {
        driver.render(
            """
            <input alt='Incredibles 1' src='/incredibles-2.png' />
            <input alt='Incredibles 2' src='/incredibles-2.png' />
        """
        )

        val result = driver.queryAllBy(AltText, "incredibles", mapOf("exact" to true))

        assertEquals(emptyList(), result)
    }
}
