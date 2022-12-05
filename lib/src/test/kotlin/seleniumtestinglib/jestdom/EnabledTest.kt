package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.Role
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.Test

@ExtendWith(DriverLifeCycle::class)
class EnabledTest(private val driver: RemoteWebDriver) {

    @Test
    fun disabled() {
        driver.render("<div><input type='text' disabled /></div>")

        expect(driver.getBy(Role, "textbox")).toBeDisabled()
    }

    @Test
    fun `not disabled`() {
        driver.render("<a href=\"...\" disabled>link</a>")

        expect(driver.getBy(Role, "link")).not.toBeDisabled()
    }

    @Test
    fun enabled() {
        driver.render("<div><input type='text' /></div>")

        expect(driver.getBy(Role, "textbox")).toBeEnabled()
    }

    @Test
    fun `not enabled`() {
        driver.render("<div><input type='text' disabled /></div>")

        expect(driver.getBy(Role, "textbox")).not.toBeEnabled()
    }
}
