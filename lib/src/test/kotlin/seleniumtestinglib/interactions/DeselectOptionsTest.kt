package seleniumtestinglib.interactions

import seleniumtestinglib.*
import seleniumtestinglib.TL.By.role
import seleniumtestinglib.Role.ListBox
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
        assertTrue(driver.findElement(role(Role.Option, name = "C")).isSelected)

        driver.user.deselectOptions(driver.findElement(role(ListBox)), "3")

        assertFalse(driver.findElement(role(Role.Option, name = "C")).isSelected)
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
        val select = driver.findElement(role(ListBox))

        driver.user.deselectOptions(
            select,
            driver.findElement(role(Role.Option, name = "C")),
        )

        assertFalse(driver.findElement(role(Role.Option, name = "C")).isSelected)
    }
}
