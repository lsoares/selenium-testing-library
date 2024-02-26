package seleniumtestinglib.locators

import seleniumtestinglib.driver
import seleniumtestinglib.queries.TextMatch.Companion.asJsFunction
import seleniumtestinglib.render
import java.util.regex.Pattern
import kotlin.test.Test
import kotlin.test.assertEquals

class ByTestIdTest {

    @Test
    fun `by test id`() {
        driver.render(""" <div data-testid="custom-element" /> """)

        val result = driver.findElement(ByTestId("custom-element"))

        assertEquals("div", result.tagName)
    }

    @Test
    fun regex() {
        driver.render(""" <div data-testid="custom-element" /> """)

        val result = driver.findElement(ByTestId(Pattern.compile("custom")))

        assertEquals("div", result.tagName)
    }

    @Test
    fun function() {
        driver.render(""" <div data-testid="custom-element" /> """)

        val result = driver.findElement(ByTestId("c => c.startsWith('custom')".asJsFunction()))

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

        val result = driver.findElement(ByTestId("foo", exact = false))

        assertEquals("span", result.tagName)
    }

    @Test
    fun `not exact 2`() {
        driver.render(
            """
            <div>
                <span data-testid='foobar'>I accept</span>
                <p>I accept</p>
            </div>
        """
        )

        val result = driver.findElement(ByTestId("foo").inexact())

        assertEquals("span", result.tagName)
    }
}
