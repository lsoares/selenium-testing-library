package seleniumtestinglib.coreapi

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.coreapi.ByType.AltText
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class ByAltTextTest(private val driver: RemoteWebDriver) {

    @Test
    fun `get by alt text`() {
        driver.render("<input alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = driver.getBy(AltText, "incredibles", mapOf("exact" to false))

        assertEquals("input", result.tagName)
    }

    @Test
    fun `query by alt text`() {
        driver.render("<input alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = driver.queryBy(AltText, "incredibles", mapOf("exact" to false))

        driver.executeScript("console.log(arguments[0])", "() => throw new Exception()")

        assertEquals("input", result?.tagName)
    }
}
