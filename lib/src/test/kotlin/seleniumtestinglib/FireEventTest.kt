package seleniumtestinglib

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.Event.Change
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role.TextBox
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class FireEventTest(private val driver: RemoteWebDriver) {

    @Test
    fun `fire event`() {
        driver.render("""<input />""")
        val input = driver.findElement(ByRole(TextBox))

        input.fireEvent(Change, mapOf("target" to mapOf("value" to "2020-05-24")))

        assertEquals("2020-05-24", input.value)
    }
}
