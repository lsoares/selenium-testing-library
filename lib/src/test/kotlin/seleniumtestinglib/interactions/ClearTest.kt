package seleniumtestinglib.interactions

import seleniumtestinglib.driver
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role.TextBox
import seleniumtestinglib.render
import seleniumtestinglib.value
import kotlin.test.Test
import kotlin.test.assertEquals

class ClearTest {

    @Test
    fun clear() {
        driver.render("<textarea>Hello, World!</textarea>")
        val input = driver.findElement(ByRole(TextBox))
        assertEquals("Hello, World!", input.value)

        driver.user.clear(input)

        assertEquals("", input.value)
    }
}
