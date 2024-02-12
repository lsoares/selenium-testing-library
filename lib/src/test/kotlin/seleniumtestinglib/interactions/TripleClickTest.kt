package seleniumtestinglib.interactions

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.isChecked
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role.CheckBox
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class TripleClickTest(private val driver: RemoteWebDriver) {

    @Test
    fun `triple click`() {
        driver.render("<input type='checkbox' />")
        val checkbox = driver.findElement(ByRole(CheckBox))

        driver.user.tripleClick(checkbox)

        assertTrue(checkbox.isChecked)
    }

    @Test
    fun `equivalent to triple click`() {
        driver.render("<input type='checkbox' />")
        val checkbox = driver.findElement(ByRole(CheckBox))

        driver.user.pointer(
            mapOf("target" to checkbox),
            mapOf("keys" to "[MouseLeft]".repeat(3), "target" to checkbox),
        )

        assertTrue(checkbox.isChecked)
    }
}
