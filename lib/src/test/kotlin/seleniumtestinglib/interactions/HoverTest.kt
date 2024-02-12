package seleniumtestinglib.interactions

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role.TEXTBOX
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class HoverTest(private val driver: RemoteWebDriver) {

    @Test
    fun hover() {
        driver.render(
            """
            <input id='inputText' />
            <span id='result'></span>
            <script>
                document.getElementById('inputText').addEventListener('mouseenter', () => {
                   document.getElementById('result').innerText = 'hovered!'
                })
            </script>
        """
        )
        val input = driver.findElement(ByRole(TEXTBOX))

        driver.user.hover(input)

        assertEquals("hovered!", driver.findElement(By.id("result")).text)
    }

    @Test
    fun `equivalent to hover`() {
        driver.render(
            """
            <input id='inputText' />
            <span id='result'></span>
            <script>
                document.getElementById('inputText').addEventListener('mouseenter', () => {
                   document.getElementById('result').innerText = 'hovered!'
                })
            </script>
        """
        )
        val input = driver.findElement(ByRole(TEXTBOX))

        driver.user.pointer(mapOf("target" to input))

        assertEquals("hovered!", driver.findElement(By.id("result")).text)
    }
}
