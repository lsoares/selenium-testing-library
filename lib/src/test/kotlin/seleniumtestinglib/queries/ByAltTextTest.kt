package seleniumtestinglib.queries

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.AltText
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
    fun `get all by alt text`() {
        driver.render(
            """
            <input alt='Incredibles 1' src='/incredibles-1.png' />
            <input alt='Incredibles 2' src='/incredibles-2.png' />
        """
        )

        val result = driver.getAllBy(AltText, "incredibles", mapOf("exact" to false))

        assertEquals(2, result.count { it.tagName == "input" })
    }

    @Test
    fun `query by alt text`() {
        driver.render("<input alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = driver.queryBy(AltText, "incredibles", mapOf("exact" to false))

        assertEquals("input", result?.tagName)
    }

    @Test
    fun `query all by alt text`() {
        driver.render(
            """
            <input alt='Incredibles 1' src='/incredibles-2.png' />
            <input alt='Incredibles 2' src='/incredibles-2.png' />
        """
        )

        val result = driver.queryAllBy(AltText, "incredibles", mapOf("exact" to false))

        assertEquals(2, result.count { it.tagName == "input" })
    }
}
