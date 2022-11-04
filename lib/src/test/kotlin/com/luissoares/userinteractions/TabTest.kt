package com.luissoares.userinteractions

import com.luissoares.DriverLifeCycle
import com.luissoares.interactions.keyboard
import com.luissoares.interactions.tab
import com.luissoares.interactions.user
import com.luissoares.isFocused
import com.luissoares.locators.ByRole
import com.luissoares.render
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.Test
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class TabTest(private val driver: RemoteWebDriver) {

    @Test
    fun tab() {
        driver.render(
            """
            <div>
              <input type="checkbox" />
              <input type="radio" />
              <input type="number" />
            </div> 
        """
        )
        driver.user.tab()

        driver.user.tab()

        assertTrue(driver.findElement(ByRole("radio")).isFocused(driver))
    }

    @Test
    fun `shift tab`() {
        driver.render(
            """
            <div>
              <input type="checkbox" />
              <input type="radio" />
              <input type="number" />
            </div> 
        """
        )
        repeat(3) {
            driver.user.tab()
        }

        driver.user.tab(shift = true)

        assertTrue(driver.findElement(ByRole("radio")).isFocused(driver))
    }

    @Test
    fun `equivalent to tab`() {
        driver.render(
            """
            <div>
              <input type="checkbox" />
              <input type="radio" />
              <input type="number" />
            </div> 
        """
        )
        driver.user.tab()

        driver.user.keyboard("{Tab}")

        assertTrue(driver.findElement(ByRole("radio")).isFocused(driver))
    }

    @Test
    fun `equivalent to tab(shift=false)`() {
        driver.render(
            """
            <div>
              <input type="checkbox" />
              <input type="radio" />
              <input type="number" />
            </div> 
        """
        )
        driver.user.tab()

        driver.user.keyboard("[/ShiftLeft][/ShiftRight]{Tab}")

        assertTrue(driver.findElement(ByRole("radio")).isFocused(driver))
    }

    @Test
    fun `equivalent to tab(shift=true)`() {
        driver.render(
            """
            <div>
              <input type="checkbox" />
              <input type="radio" />
              <input type="number" />
            </div> 
        """
        )
        repeat(3) {
            driver.user.tab()
        }

        driver.user.keyboard("{Shift>}{Tab}{/Shift}")

        assertTrue(driver.findElement(ByRole("radio")).isFocused(driver))
    }
}
