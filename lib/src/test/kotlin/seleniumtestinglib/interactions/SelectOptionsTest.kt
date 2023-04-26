package seleniumtestinglib.interactions

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.queries.JsType.Companion.asJsString
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
        val select = driver.findElement(ByRole("listbox"))

        driver.user.selectOptions(select, "1", "3")

        assertTrue(select.findElement(ByRole("option", name = "A".asJsString())).isSelected)
        assertFalse(select.findElement(ByRole("option", name = "B".asJsString())).isSelected)
        assertTrue(select.findElement(ByRole("option", name = "C".asJsString())).isSelected)
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
        val select = driver.findElement(ByRole("listbox"))

        driver.user.selectOptions(
            select,
            select.findElement(ByRole("option", name = "A".asJsString())),
            select.findElement(ByRole("option", name = "C".asJsString())),
        )

        assertTrue(select.findElement(ByRole("option", name = "A".asJsString())).isSelected)
        assertFalse(select.findElement(ByRole("option", name = "B".asJsString())).isSelected)
        assertTrue(select.findElement(ByRole("option", name = "C".asJsString())).isSelected)
    }
}
