package seleniumtestinglib.interactions

import seleniumtestinglib.driver
import seleniumtestinglib.isChecked
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role.CheckBox
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse

class DoubleClickTest {

    @Test
    fun `double click`() {
        driver.render("<input type='checkbox' />")
        val checkbox = driver.findElement(ByRole(CheckBox))

        driver.user.dblClick(checkbox)

        assertFalse(checkbox.isChecked)
    }

    @Test
    fun `equivalent to double click`() {
        driver.render("<input type='checkbox' />")
        val checkbox = driver.findElement(ByRole(CheckBox))

        driver.user.pointer(
            mapOf("target" to checkbox),
            mapOf("keys" to "[MouseLeft][MouseLeft]", "target" to checkbox),
        )

        assertFalse(checkbox.isChecked)
    }
}
