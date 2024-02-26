package seleniumtestinglib.interactions

import seleniumtestinglib.driver
import seleniumtestinglib.isFocused
import seleniumtestinglib.locators.TL.By.role
import seleniumtestinglib.locators.RoleType.Radio
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertTrue

class TabTest {

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

        assertTrue(driver.findElement(role(Radio)).isFocused)
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

        assertTrue(driver.findElement(role(Radio)).isFocused)
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

        assertTrue(driver.findElement(role(Radio)).isFocused)
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

        assertTrue(driver.findElement(role(Radio)).isFocused)
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

        assertTrue(driver.findElement(role(Radio)).isFocused)
    }
}
