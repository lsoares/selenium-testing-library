package seleniumtestinglib.jestdom

import seleniumtestinglib.driver
import seleniumtestinglib.interactions.tab
import seleniumtestinglib.interactions.user
import seleniumtestinglib.isFocused
import seleniumtestinglib.locators.TL.By.testId
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
        driver.user.tab()
        val element = driver.findElement(testId("element-to-focus"))
        assertTrue(element.isFocused)
        expect(element).toHaveFocus()

        driver.user.tab()
        assertFalse(element.isFocused)
        expect(element).not.toHaveFocus()
    }
}
