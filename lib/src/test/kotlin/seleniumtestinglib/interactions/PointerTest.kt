package seleniumtestinglib.interactions

import org.openqa.selenium.By
import seleniumtestinglib.*
import seleniumtestinglib.RoleType.Banner
import seleniumtestinglib.RoleType.ContentInfo
import seleniumtestinglib.TL.By.role
import seleniumtestinglib.PointerOption.*
import seleniumtestinglib.PointerOption.Target
import kotlin.test.Test
import kotlin.test.assertEquals

class PointerTest {

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
            mapOf(Target to driver.findElement(By.tagName("div")), Offset to 2, Keys to "[MouseLeft>]"),
            mapOf(Offset to 5)
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
            mapOf(Keys to "[TouchA>]", Target to driver.findElement(role(Banner))),
            mapOf(PointerName to "TouchA", Target to driver.findElement(role(ContentInfo))),
            mapOf(Keys to "[/TouchA]"),
        )

        assertEquals(
            """[{"keys":"[TouchA>]","target":{}},{"pointerName":"TouchA","target":{}},{"keys":"[/TouchA]"}]""",
            driver.findElement(By.id("spy")).text,
        )
    }
}
