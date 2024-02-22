package seleniumtestinglib.locators

import seleniumtestinglib.driver
import seleniumtestinglib.queries.JsType.Companion.asJsExpression
import seleniumtestinglib.render
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

        val result = driver.findElement(ByTestId("/Custom/i".asJsExpression()))

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
