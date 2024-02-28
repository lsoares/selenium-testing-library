package seleniumtestinglib.interactions

import seleniumtestinglib.*
import seleniumtestinglib.TL.By.role
import seleniumtestinglib.Role.TextBox
import kotlin.test.Test
import kotlin.test.assertEquals

class ClearTest {

    @Test
    fun clear() {
        driver.render("<textarea>Hello, World!</textarea>")
        val input = driver.findElement(role(TextBox))
        assertEquals("Hello, World!", input.value)

        driver.user.clear(input)

        assertEquals("", input.value)
    }
}
