package seleniumtestinglib.interactions

import seleniumtestinglib.*
import seleniumtestinglib.PointerOption.Keys
import seleniumtestinglib.PointerOption.Target
import seleniumtestinglib.TL.By.labelText
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
        val checkbox = driver.findElement(labelText("Check"))

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
        val checkbox = driver.findElement(labelText("Check"))

        driver.user.pointer(
            mapOf(Target to checkbox),
            mapOf(Keys to "[MouseLeft]", Target to checkbox),
        )

        assertTrue(checkbox.isChecked)
    }
}
