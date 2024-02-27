package seleniumtestinglib.locators

import seleniumtestinglib.driver
import seleniumtestinglib.TL.By.labelText
import seleniumtestinglib.asJsFunction
import seleniumtestinglib.render
import java.util.regex.Pattern
import java.util.regex.Pattern.CASE_INSENSITIVE
import kotlin.test.Test
import kotlin.test.assertEquals

class ByLabelTextTest {

    @Test
    fun `by for`() {
        driver.render(
            """
               <label for="username-input">Username</label>
               <input id="username-input" /> 
            """
        )

        val result = driver.findElement(labelText("Username"))

        assertEquals("input", result.tagName)
    }

    @Test
    fun `by aria-labeled-by`() {
        driver.render(
            """
               <label id="username-label">Username</label>
               <input aria-labelledby="username-label" />
            """
        )

        val result = driver.findElement(labelText("Username"))

        assertEquals("input", result.tagName)
    }

    @Test
    fun `by wrapping label`() {
        driver.render(
            """
                <label>
                  <span>Username</span>
                  <input />
                </label>
            """
        )

        val result = driver.findElement(labelText("Username"))

        assertEquals("input", result.tagName)
    }

    @Test
    fun `aria label`() {
        driver.render("<input aria-label='Username' />")

        val result = driver.findElement(labelText("Username"))

        assertEquals("input", result.tagName)
    }

    @Test
    fun `not exact with selector`() {
        driver.render(
            """
            <input aria-label='Username' />
            <span><div class="x"><input aria-label='Username' /></div></span>
            """
        )

        val result = driver.findElement(labelText("user", exact = false, selector = "span .x input"))

        assertEquals("input", result.tagName)
    }

    @Test
    fun regex() {
        driver.render("<input aria-label='Username' />")

        val result = driver.findElement(labelText(Pattern.compile("user", CASE_INSENSITIVE)))

        assertEquals("input", result.tagName)
    }

    @Test
    fun function() {
        driver.render("<input aria-label='Username' />")

        val result = driver.findElement(labelText("c => c.startsWith('User')".asJsFunction()))

        assertEquals("input", result.tagName)
    }
}
