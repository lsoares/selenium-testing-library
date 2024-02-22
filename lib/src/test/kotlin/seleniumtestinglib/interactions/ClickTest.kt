package seleniumtestinglib.interactions

import seleniumtestinglib.driver
import seleniumtestinglib.isChecked
import seleniumtestinglib.locators.ByLabelText
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertTrue

class ClickTest {

    @Test
    fun click() {
        driver.render(
            """ <div>
                        <label for="checkbox">Check</label>
                        <input id="checkbox" type="checkbox" />
                      </div> """
        )
        val checkbox = driver.findElement(ByLabelText("Check"))

        driver.user.click(checkbox)

        assertTrue(checkbox.isChecked)
    }

    @Test
    fun `equivalent to click`() {
        driver.render(
            """ <div>
                        <label for="checkbox">Check</label>
                        <input id="checkbox" type="checkbox" />
                      </div> """
        )
        val checkbox = driver.findElement(ByLabelText("Check"))

        driver.user.pointer(
            mapOf("target" to checkbox),
            mapOf("keys" to "[MouseLeft]", "target" to checkbox),
        )

        assertTrue(checkbox.isChecked)
    }
}
