package seleniumtestinglib.locators

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.coreapi.MatchType
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class ByAltTextTest(private val driver: RemoteWebDriver) {

    @ParameterizedTest
    @ValueSource(strings = ["img", "input", "area"])
    fun `by alt text`(tagName: String) {
        driver.render("<$tagName alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = driver.findElement(ByAltText("Incredibles 2 Poster"))

        assertEquals(tagName, result.tagName)
    }

    @Test
    fun `not exact`() {
        driver.render("<img alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = driver.findElement(ByAltText("incredibles 2", exact = false))

        assertEquals("img", result.tagName)
    }

    @Test
    fun regex() {
        driver.render("<img alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = driver.findElement(ByAltText("/incred/i", matchTextBy = MatchType.REGEX))

        assertEquals("img", result.tagName)
    }

    @Test
    fun `by alt text not-valid type`() {
        driver.render("<div alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = runCatching {
            driver.findElement(ByAltText("Incredibles 2 Poster"))
        }

        assertTrue(result.exceptionOrNull() is NoSuchElementException)
    }
}
