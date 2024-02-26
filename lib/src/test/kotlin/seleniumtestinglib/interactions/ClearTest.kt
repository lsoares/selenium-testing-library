package seleniumtestinglib.interactions

import seleniumtestinglib.driver
import seleniumtestinglib.locators.TL.By.role
import seleniumtestinglib.locators.RoleType.TextBox
import seleniumtestinglib.render
import seleniumtestinglib.value
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
