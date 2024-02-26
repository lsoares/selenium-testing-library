package seleniumtestinglib.interactions

import seleniumtestinglib.driver
import seleniumtestinglib.locators.TL.By.role
import seleniumtestinglib.locators.RoleType.ListBox
import seleniumtestinglib.locators.RoleType.Option
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SelectOptionsTest {

    @Test
    fun `select options by string`() {
        driver.render(
            """<select multiple>
                      <option value="1">A</option>
                      <option value="2">B</option>
                      <option value="3">C</option>
                    </select>"""
        )
        val select = driver.findElement(role(ListBox))

        driver.user.selectOptions(select, "1", "3")

        assertTrue(select.findElement(role(Option, name = "A")).isSelected)
        assertFalse(select.findElement(role(Option, name = "B")).isSelected)
        assertTrue(select.findElement(role(Option, name = "C")).isSelected)
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
        val select = driver.findElement(role(ListBox))

        driver.user.selectOptions(
            select,
            select.findElement(role(Option, name = "A")),
            select.findElement(role(Option, name = "C")),
        )

        assertTrue(select.findElement(role(Option, name = "A")).isSelected)
        assertFalse(select.findElement(role(Option, name = "B")).isSelected)
        assertTrue(select.findElement(role(Option, name = "C")).isSelected)
    }
}
