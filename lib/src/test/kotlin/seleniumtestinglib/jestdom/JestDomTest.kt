package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.coreapi.ByType.Role
import seleniumtestinglib.coreapi.getBy
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class JestDomTest(private val driver: RemoteWebDriver) {

    @Test
    fun disabled() {
        driver.render("<div><input type='text' disabled /></div>")

        val input = driver.getBy(Role, "textbox")
        assertTrue(input.isDisabled)
    }
}
