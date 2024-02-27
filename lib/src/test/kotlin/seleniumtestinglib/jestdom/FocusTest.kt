package seleniumtestinglib.jestdom

import seleniumtestinglib.*
import seleniumtestinglib.TL.By.testId
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
