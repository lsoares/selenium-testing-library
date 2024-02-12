package seleniumtestinglib.interactions

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.isChecked
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role.CHECKBOX
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse

@ExtendWith(DriverLifeCycle::class)
class DoubleClickTest(private val driver: RemoteWebDriver) {

    @Test
    fun `double click`() {
        driver.render("<input type='checkbox' />")
        val checkbox = driver.findElement(ByRole(CHECKBOX))

        driver.user.dblClick(checkbox)

        assertFalse(checkbox.isChecked)
    }

    @Test
    fun `equivalent to double click`() {
        driver.render("<input type='checkbox' />")
        val checkbox = driver.findElement(ByRole(CHECKBOX))

        driver.user.pointer(
            mapOf("target" to checkbox),
            mapOf("keys" to "[MouseLeft][MouseLeft]", "target" to checkbox),
        )

        assertFalse(checkbox.isChecked)
    }
}
