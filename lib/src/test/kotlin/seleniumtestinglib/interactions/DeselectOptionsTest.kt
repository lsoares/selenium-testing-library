package seleniumtestinglib.interactions

import seleniumtestinglib.driver
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role
import seleniumtestinglib.locators.Role.ListBox
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DeselectOptionsTest {

    @Test
    fun `deselect options`() {
        driver.render(
            """<select multiple>
                      <option value="1">A</option>
                      <option value="2">B</option>
                      <option value="3" selected>C</option>
                    </select>"""
        )
        assertTrue(driver.findElement(ByRole(Role.Option, name = "C")).isSelected)

        driver.user.deselectOptions(driver.findElement(ByRole(ListBox)), "3")

        assertFalse(driver.findElement(ByRole(Role.Option, name = "C")).isSelected)
    }

    @Test
    fun `deselect options by element`() {
        driver.render(
            """<select multiple>
                      <option value="1" selected>A</option>
                      <option value="2" selected>B</option>
                      <option value="3" selected>C</option>
                    </select>"""
        )
        val select = driver.findElement(ByRole(ListBox))

        driver.user.deselectOptions(
            select,
            driver.findElement(ByRole(Role.Option, name = "C")),
        )

        assertFalse(driver.findElement(ByRole(Role.Option, name = "C")).isSelected)
    }
}
