package seleniumtestinglib.interactions

import seleniumtestinglib.*
import seleniumtestinglib.PointerOption.Keys
import seleniumtestinglib.PointerOption.Target
import seleniumtestinglib.Role.CheckBox
import seleniumtestinglib.TL.By.role
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
            mapOf(Target to checkbox),
            mapOf(Keys to "[MouseLeft][MouseLeft]", Target to checkbox),
        )

        assertFalse(checkbox.isChecked)
    }
}
