package com.luissoares.userevent

import com.luissoares.DriverLifeCycle
import com.luissoares.SelectValue.ByValue
import com.luissoares.SelectValue.ByWebElement
import com.luissoares.locators.ByRole
import com.luissoares.render
import com.luissoares.userEvent
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
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

        driver.userEvent.selectOptions(
            select,
            listOf(ByValue("1"), ByValue("3")),
        )

        assertTrue(driver.findElement(ByRole("option", name = "A")).isSelected)
        assertFalse(driver.findElement(ByRole("option", name = "B")).isSelected)
        assertTrue(driver.findElement(ByRole("option", name = "C")).isSelected)
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

        driver.userEvent.selectOptions(
            select,
            listOf(
                ByWebElement(driver.findElement(ByRole("option", name = "A"))),
                ByWebElement(driver.findElement(ByRole("option", name = "C"))),
            ),
        )

        assertTrue(driver.findElement(ByRole("option", name = "A")).isSelected)
        assertFalse(driver.findElement(ByRole("option", name = "B")).isSelected)
        assertTrue(driver.findElement(ByRole("option", name = "C")).isSelected)
    }
}
