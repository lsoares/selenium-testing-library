package seleniumtestinglib.interactions

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role.BANNER
import seleniumtestinglib.locators.Role.CONTENTINFO
import seleniumtestinglib.render
import seleniumtestinglib.selection
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class PointerTest(private val driver: RemoteWebDriver) {

    @Test
    fun click() {
        driver.render(
            """
            <article><h1>An article</h1>text</article>
            <script>
                document.getElementsByTagName('body')[0].addEventListener(
                   'click', ev => document.getElementsByTagName('h1')[0].innerText = ev.type
                )
            </script>
        """
        )

        driver.user.pointer("[MouseLeft]")

        assertEquals("click", driver.findElement(By.cssSelector("h1")).text)
    }

    @Test
    fun `two sequential clicks`() {
        driver.render(
            """<span id='left'></span>
                     <span id='right'></span>
                     <script>
                        document.getElementsByTagName('body')[0].addEventListener(
                           'click', ev => document.getElementById('left').innerText = ev.type
                        )
                        document.getElementsByTagName('body')[0].addEventListener(
                           'contextmenu', ev => document.getElementById('right').innerText = ev.type
                        )
                     </script>
                     """
        )

        driver.user.pointer("[MouseLeft]", "[MouseRight]")

        assertEquals("click", driver.findElement(By.id("left")).text)
        assertEquals("contextmenu", driver.findElement(By.id("right")).text)
    }

    @Test
    fun `click with properties`() {
        driver.render("<article><h1>An article</h1>text</article>")
        driver.executeScript(
            "document.getElementsByTagName('body')[0].addEventListener('click', ev => arguments[0].innerText = ev.type)",
            driver.findElement(By.cssSelector("h1")),
        )

        driver.user.pointer("[MouseLeft]")

        assertEquals("click", driver.findElement(By.cssSelector("h1")).text)
    }

    @Test
    fun selection() {
        driver.render("<div><span>foo</span><span>bar</span></div>")

        driver.user.pointer(
            mapOf("target" to driver.findElement(By.tagName("div")), "offset" to 2, "keys" to "[MouseLeft>]"),
            mapOf("offset" to 5)
        )

        assertEquals("oba", driver.selection)
    }

    @Test
    fun `select with touch`() {
        driver.render(
            """
              <header>header</header>
              <footer>footer</footer>
              <span id='spy'></span>
        """
        )
        driver.user()
        driver.executeScript(
            """
           userPointer = user.pointer
           user.pointer = (args) => {
              document.getElementById('spy').innerText = JSON.stringify(args)
           }
        """
        )

        driver.user.pointer(
            mapOf("keys" to "[TouchA>]", "target" to driver.findElement(ByRole(BANNER))),
            mapOf("pointerName" to "TouchA", "target" to driver.findElement(ByRole(CONTENTINFO))),
            mapOf("keys" to "[/TouchA]"),
        )

        assertEquals(
            """[{"keys":"[TouchA>]","target":{}},{"pointerName":"TouchA","target":{}},{"keys":"[/TouchA]"}]""",
            driver.findElement(By.id("spy")).text,
        )
    }
}
