package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.interactions.tab
import seleniumtestinglib.interactions.user
import seleniumtestinglib.isFocused
import seleniumtestinglib.locators.ByTestId
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class FocusTest(private val driver: RemoteWebDriver) {

    @Test
    fun focus() {
        driver.render(
            """
                <input type="text" data-testid="element-to-focus" />
                <input type="text" />
            """
        )
        driver.user.tab()
        val element = driver.findElement(ByTestId("element-to-focus"))
        assertTrue(element.isFocused)
        expect(element).toHaveFocus()

        driver.user.tab()
        assertFalse(element.isFocused)
        expect(element).not.toHaveFocus()
    }
}
