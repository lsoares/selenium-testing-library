package seleniumtestinglib.interactions

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.locators.ByRole
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
        val input = driver.findElement(ByRole("textbox"))
        driver.user.hover(input)

        driver.user.unhover(input)

        assertEquals("unhovered!", driver.findElement(By.id("result")).text)
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
        val input = driver.findElement(ByRole("textbox"))
        driver.user.hover(input)

        driver.user.pointer(mapOf("target" to driver.findElement(By.tagName("body"))))

        assertEquals("unhovered!", driver.findElement(By.id("result")).text)
    }
}
