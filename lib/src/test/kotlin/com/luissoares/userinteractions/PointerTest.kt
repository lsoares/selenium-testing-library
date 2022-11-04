package com.luissoares.userinteractions

import com.luissoares.DriverLifeCycle
import com.luissoares.interactions.pointer
import com.luissoares.interactions.user
import com.luissoares.locators.ByRole
import com.luissoares.render
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
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
    fun another() {
        driver.render(
            """
              <header>header</header>
              <footer>footer</footer>
        """
        )
        val user = driver.user()
        driver.executeScript("""
           const userPointer = user.pointer
           calls = []
           user.pointer = (a) => {
              calls.push(a)
              userPointer(a)
           }
        """)

        user.pointer(
            mapOf("keys" to "[TouchA>]", "target" to driver.findElement(ByRole("banner"))),
            mapOf("pointerName" to "TouchA", "target" to driver.findElement(ByRole("contentinfo"))),
            mapOf("keys" to "[/TouchA]"),
        )

        println(driver.executeScript("return calls"))
    }
}
