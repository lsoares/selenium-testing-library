package seleniumtestinglib.interactions

import org.openqa.selenium.By.id
import org.openqa.selenium.By.tagName
import seleniumtestinglib.*
import seleniumtestinglib.PointerOption.Target
import seleniumtestinglib.RoleType.TextBox
import seleniumtestinglib.TL.By.role
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
        val input = driver.findElement(role(TextBox))
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
        val input = driver.findElement(role(TextBox))
        driver.user.hover(input)

        driver.user.pointer(mapOf(Target to driver.findElement(tagName("body"))))

        assertEquals("unhovered!", driver.findElement(id("result")).text)
    }
}
