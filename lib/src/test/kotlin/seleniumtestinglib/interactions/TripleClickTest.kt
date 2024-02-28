package seleniumtestinglib.interactions

import seleniumtestinglib.*
import seleniumtestinglib.PointerOption.Keys
import seleniumtestinglib.PointerOption.Target
import seleniumtestinglib.Role.CheckBox
import seleniumtestinglib.TL.By.role
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
            mapOf(Target to checkbox),
            mapOf(Keys to "[MouseLeft]".repeat(3), Target to checkbox),
        )

        assertTrue(checkbox.isChecked)
    }
}
