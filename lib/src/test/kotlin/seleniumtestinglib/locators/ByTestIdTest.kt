package seleniumtestinglib.locators

import seleniumtestinglib.JsFunction
import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.driver
import seleniumtestinglib.render
import java.util.regex.Pattern
import kotlin.test.Test
import kotlin.test.assertEquals

class ByTestIdTest {

    @Test
    fun `by test id`() {
        driver.render(""" <div data-testid="custom-element" /> """)

        val result = driver.findElement(testId("custom-element"))

        assertEquals("div", result.tagName)
    }

    @Test
    fun regex() {
        driver.render(""" <div data-testid="custom-element" /> """)

        val result = driver.findElement(testId(Pattern.compile("custom")))

        assertEquals("div", result.tagName)
    }

    @Test
    fun function() {
        driver.render(""" <div data-testid="custom-element" /> """)

        val result = driver.findElement(testId(JsFunction("c => c.startsWith('custom')")))

        assertEquals("div", result.tagName)
    }

    @Test
    fun `not exact`() {
        driver.render(
            """
            <div>
                <span data-testid='foobar'>I accept</span>
                <p>I accept</p>
            </div>
        """
        )

        val result = driver.findElement(testId("foo", exact = false))

        assertEquals("span", result.tagName)
    }
}
