package seleniumtestinglib.jestdom

import seleniumtestinglib.RoleType.TextBox
import seleniumtestinglib.TL.By.role
import seleniumtestinglib.driver
import seleniumtestinglib.expect
import seleniumtestinglib.render
import kotlin.test.Test

class EnabledTest {

    @Test
    fun enabled() {
        driver.render("<div><input type='text' /></div>")

        expect(driver.findElement(role(TextBox))).toBeEnabled()
    }

    @Test
    fun `not enabled`() {
        driver.render("<div><input type='text' disabled /></div>")

        expect(driver.findElement(role(TextBox))).not.toBeEnabled()
    }
}
