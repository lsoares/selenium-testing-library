package seleniumtestinglib.interactions

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By.id
import org.openqa.selenium.By.tagName
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role.TEXTBOX
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class UnhoverTest(private val driver: RemoteWebDriver) {

    @Test
    fun unhover() {
        driver.render(
            """
            <input id='inputText' />
            <span id='result'></span>
            <script>
                document.getElementById('inputText').addEventListener('mouseleave', () => {
                   document.getElementById('result').innerText = 'unhovered!'
                })
            </script>
        """
        )
        val input = driver.findElement(ByRole(TEXTBOX))
        driver.user.hover(input)

        driver.user.unhover(input)

        assertEquals("unhovered!", driver.findElement(id("result")).text)
    }

    @Test
    fun `equivalent to unhover`() {
        driver.render(
            """
            <input id='inputText' />
            <span id='result'></span>
            <script>
                document.getElementById('inputText').addEventListener('mouseleave', () => {
                   document.getElementById('result').innerText = 'unhovered!'
                })
            </script>
        """
        )
        val input = driver.findElement(ByRole(TEXTBOX))
        driver.user.hover(input)

        driver.user.pointer(mapOf("target" to driver.findElement(tagName("body"))))

        assertEquals("unhovered!", driver.findElement(id("result")).text)
    }
}
