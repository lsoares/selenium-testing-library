package seleniumtestinglib.queries

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.JavascriptException
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.AltText
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class NotFoundTest(private val driver: RemoteWebDriver) {

    @Test
    fun `get but none found`() {
        driver.render("<input alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = kotlin.runCatching {
            driver.getBy(AltText, "will not work")
        }

        assertTrue(result.exceptionOrNull() is JavascriptException)
    }

    @Test
    fun `get all but none found`() {
        driver.render("<input alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = kotlin.runCatching {
            driver.getAllBy(AltText, "will not work")
        }

        assertTrue(result.exceptionOrNull() is JavascriptException)
    }

    @Test
    fun `query  does not exist`() {
        driver.render("<input alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = driver.queryBy(AltText, "will not work")

        assertNull(result?.tagName)
    }

    @Test
    fun `query all but none found`() {
        driver.render(
            """
            <input alt='Incredibles 1' src='/incredibles-2.png' />
            <input alt='Incredibles 2' src='/incredibles-2.png' />
        """
        )

        val result = driver.queryAllBy(AltText, "incredibles", mapOf("exact" to true))

        assertEquals(emptyList(), result)
    }
}
