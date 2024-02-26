package seleniumtestinglib.locators

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.NoSuchElementException
import seleniumtestinglib.driver
import seleniumtestinglib.locators.TL.By.altText
import seleniumtestinglib.queries.TextMatch.JsFunction
import seleniumtestinglib.render
import java.util.regex.Pattern
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ByAltTextTest {

    @ParameterizedTest
    @ValueSource(strings = ["img", "input", "area"])
    fun `by alt text`(tagName: String) {
        driver.render("<$tagName alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = driver.findElement(altText("Incredibles 2 Poster"))

        assertEquals(tagName, result.tagName)
    }

    @Test
    fun `not exact`() {
        driver.render("<img alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = driver.findElement(altText("incredibles 2", exact = false))

        assertEquals("img", result.tagName)
    }

    @Test
    fun regex() {
        driver.render("<img alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = driver.findElement(altText(Pattern.compile("incred", Pattern.CASE_INSENSITIVE)))

        assertEquals("img", result.tagName)
    }

    @Test
    fun function() {
        driver.render("<div alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = runCatching {
            driver.findElement(altText(JsFunction("c => c.startsWith('inc')")))
        }

        assertTrue(result.exceptionOrNull() is NoSuchElementException)
    }

    @Test
    fun `by alt text not-valid type`() {
        driver.render("<div alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = runCatching {
            driver.findElement(altText("Incredibles 2 Poster"))
        }

        assertTrue(result.exceptionOrNull() is NoSuchElementException)
    }
}
