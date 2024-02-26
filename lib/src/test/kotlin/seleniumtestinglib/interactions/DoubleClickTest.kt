package seleniumtestinglib.interactions

import seleniumtestinglib.driver
import seleniumtestinglib.isChecked
import seleniumtestinglib.locators.TL.By.role
import seleniumtestinglib.locators.RoleType.CheckBox
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse

class DoubleClickTest {

    @Test
    fun `double click`() {
        driver.render("<input type='checkbox' />")
        val checkbox = driver.findElement(role(CheckBox))

        driver.user.dblClick(checkbox)

        assertFalse(checkbox.isChecked)
    }

    @Test
    fun `equivalent to double click`() {
        driver.render("<input type='checkbox' />")
        val checkbox = driver.findElement(role(CheckBox))

        driver.user.pointer(
            mapOf("target" to checkbox),
            mapOf("keys" to "[MouseLeft][MouseLeft]", "target" to checkbox),
        )

        assertFalse(checkbox.isChecked)
    }
}
