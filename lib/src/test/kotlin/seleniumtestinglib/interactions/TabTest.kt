package seleniumtestinglib.interactions

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.isFocused
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role.RADIO
import seleniumtestinglib.render
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

        assertTrue(driver.findElement(ByRole(RADIO)).isFocused)
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

        assertTrue(driver.findElement(ByRole(RADIO)).isFocused)
    }

    @Test
    fun `equivalent to tab`() {
        driver
            .render(
                """
                <div>
                  <input type="checkbox" />
                  <input type="radio" />
                  <input type="number" />
                </div> 
            """
            )
            .tab()
            .keyboard("{Tab}")

        assertTrue(driver.findElement(ByRole(RADIO)).isFocused)
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

        assertTrue(driver.findElement(ByRole(RADIO)).isFocused)
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

        assertTrue(driver.findElement(ByRole(RADIO)).isFocused)
    }
}
