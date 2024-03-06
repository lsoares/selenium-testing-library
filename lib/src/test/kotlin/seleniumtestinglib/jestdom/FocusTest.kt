package seleniumtestinglib.jestdom

import org.openqa.selenium.Keys
import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.driver
import seleniumtestinglib.isFocused
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FocusTest {

    @Test
    fun focus() {
        driver.render(
            """
                <input type="text" data-testid="element-to-focus" />
                <input type="text" />
            """
        )
        driver.switchTo().activeElement().sendKeys(Keys.TAB)
        val element = driver.findElement(testId("element-to-focus"))
        assertTrue(element.isFocused)

        driver.switchTo().activeElement().sendKeys(Keys.TAB)
        assertFalse(element.isFocused)
    }
}
