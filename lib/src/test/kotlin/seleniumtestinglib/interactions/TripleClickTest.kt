package seleniumtestinglib.interactions

import seleniumtestinglib.driver
import seleniumtestinglib.isChecked
import seleniumtestinglib.locators.TL.By.role
import seleniumtestinglib.locators.RoleType.CheckBox
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertTrue

class TripleClickTest {

    @Test
    fun `triple click`() {
        driver.render("<input type='checkbox' />")
        val checkbox = driver.findElement(role(CheckBox))

        driver.user.tripleClick(checkbox)

        assertTrue(checkbox.isChecked)
    }

    @Test
    fun `equivalent to triple click`() {
        driver.render("<input type='checkbox' />")
        val checkbox = driver.findElement(role(CheckBox))

        driver.user.pointer(
            mapOf("target" to checkbox),
            mapOf("keys" to "[MouseLeft]".repeat(3), "target" to checkbox),
        )

        assertTrue(checkbox.isChecked)
    }
}
