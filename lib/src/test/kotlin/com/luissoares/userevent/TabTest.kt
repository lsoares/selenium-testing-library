package com.luissoares.userevent

import com.luissoares.DriverLifeCycle
import com.luissoares.isFocused
import com.luissoares.locators.ByRole
import com.luissoares.render
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
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
        driver.userEvent.tab()

        driver.userEvent.tab()

        val radio = driver.findElement(ByRole("radio"))
        assertTrue(radio.isFocused(driver))
    }

    @Test
    fun `tab with shift`() {
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
            driver.userEvent.tab()
        }

        driver.userEvent.tab(true)

        val radio = driver.findElement(ByRole("radio"))
        assertTrue(radio.isFocused(driver))
    }
}
