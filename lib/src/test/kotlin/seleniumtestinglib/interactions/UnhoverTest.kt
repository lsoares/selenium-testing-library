package seleniumtestinglib.interactions

import org.openqa.selenium.By.id
import org.openqa.selenium.By.tagName
import seleniumtestinglib.driver
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role.TextBox
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals

class UnhoverTest {

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
        val input = driver.findElement(ByRole(TextBox))
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
        val input = driver.findElement(ByRole(TextBox))
        driver.user.hover(input)

        driver.user.pointer(mapOf("target" to driver.findElement(tagName("body"))))

        assertEquals("unhovered!", driver.findElement(id("result")).text)
    }
}
