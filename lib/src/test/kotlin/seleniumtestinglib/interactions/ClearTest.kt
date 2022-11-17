package seleniumtestinglib.interactions

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.render
import seleniumtestinglib.value
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class ClearTest(private val driver: RemoteWebDriver) {

    @Test
    fun clear() {
        driver.render("<textarea>Hello, World!</textarea>")
        val input = driver.findElement(ByRole("textbox"))
        assertEquals("Hello, World!", input.value)

        driver.user.clear(input)

        assertEquals("", input.value)
    }
}
