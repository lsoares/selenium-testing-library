package seleniumtestinglib.locators

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.NoSuchElementException
import seleniumtestinglib.driver
import seleniumtestinglib.TL.By.placeholderText
import seleniumtestinglib.asJsFunction
import seleniumtestinglib.render
import java.util.regex.Pattern
import java.util.regex.Pattern.CASE_INSENSITIVE
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ByPlaceholderTextTest {

    @Test
    fun exact() {
        driver.render("<input placeholder='Username' />")

        val result = driver.findElement(placeholderText("Username", exact = true))

        assertEquals("input", result.tagName)
    }


    @ParameterizedTest
    @ValueSource(strings = ["Username", "USERNAME", "user"])
    fun `not exact`(text: String) {
        driver.render("<input placeholder='Username' />")

        val result = driver.findElement(placeholderText(text, exact = false))

        assertEquals("input", result.tagName)
    }

    @Test
    fun regex() {
        driver.render("<input placeholder='Username' />")

        val result = driver.findElement(placeholderText(Pattern.compile("user", CASE_INSENSITIVE)))

        assertEquals("input", result.tagName)
    }

    @Test
    fun function() {
        driver.render("<input placeholder='Username' />")

        val result = driver.findElement(placeholderText("c => c.startsWith('User')".asJsFunction()))

        assertEquals("input", result.tagName)
    }

    @Test
    fun `exact not found`() {
        driver.render("<input placeholder='Username' />")

        val result = runCatching {
            driver.findElement(placeholderText("username", exact = true))
        }

        assertTrue(result.exceptionOrNull() is NoSuchElementException)
    }
}
