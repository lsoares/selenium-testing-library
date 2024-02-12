package seleniumtestinglib.interactions

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role.*
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class SelectOptionsTest(private val driver: RemoteWebDriver) {

    @Test
    fun `select options by string`() {
        driver.render(
            """<select multiple>
                      <option value="1">A</option>
                      <option value="2">B</option>
                      <option value="3">C</option>
                    </select>"""
        )
        val select = driver.findElement(ByRole(LISTBOX))

        driver.user.selectOptions(select, "1", "3")

        assertTrue(select.findElement(ByRole(OPTION, name = "A")).isSelected)
        assertFalse(select.findElement(ByRole(OPTION, name = "B")).isSelected)
        assertTrue(select.findElement(ByRole(OPTION, name = "C")).isSelected)
    }

    @Test
    fun `select options by web element`() {
        driver.render(
            """<select multiple>
                      <option value="1">A</option>
                      <option value="2">B</option>
                      <option value="3">C</option>
                    </select>"""
        )
        val select = driver.findElement(ByRole(LISTBOX))

        driver.user.selectOptions(
            select,
            select.findElement(ByRole(OPTION, name = "A")),
            select.findElement(ByRole(OPTION, name = "C")),
        )

        assertTrue(select.findElement(ByRole(OPTION, name = "A")).isSelected)
        assertFalse(select.findElement(ByRole(OPTION, name = "B")).isSelected)
        assertTrue(select.findElement(ByRole(OPTION, name = "C")).isSelected)
    }
}
