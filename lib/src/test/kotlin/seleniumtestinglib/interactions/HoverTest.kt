package seleniumtestinglib.interactions

import org.openqa.selenium.By
import seleniumtestinglib.*
import seleniumtestinglib.PointerOption.Target
import seleniumtestinglib.RoleType.TextBox
import seleniumtestinglib.TL.By.role
import kotlin.test.Test
import kotlin.test.assertEquals

class HoverTest {

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
        val input = driver.findElement(role(TextBox))

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
        val input = driver.findElement(role(TextBox))

        driver.user.pointer(mapOf(Target to input))

        assertEquals("hovered!", driver.findElement(By.id("result")).text)
    }
}
