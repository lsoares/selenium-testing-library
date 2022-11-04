package com.luissoares.userinteractions

import com.luissoares.DriverLifeCycle
import com.luissoares.interactions.deselectOptions
import com.luissoares.interactions.user
import com.luissoares.locators.ByRole
import com.luissoares.render
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class DeselectOptionsTest(private val driver: RemoteWebDriver) {

    @Test
    fun `deselect options`() {
        driver.render(
            """<select multiple>
                      <option value="1">A</option>
                      <option value="2">B</option>
                      <option value="3" selected>C</option>
                    </select>"""
        )
        assertTrue(driver.findElement(ByRole("option", name = "C")).isSelected)

        driver.user.deselectOptions(driver.findElement(ByRole("listbox")), listOf("3"))

        assertFalse(driver.findElement(ByRole("option", name = "C")).isSelected)
    }
}
