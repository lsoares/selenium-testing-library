package seleniumtestinglib.jestdom

import seleniumtestinglib.driver
import seleniumtestinglib.queries.LocatorType.Role
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.Test

class EnabledTest {

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
