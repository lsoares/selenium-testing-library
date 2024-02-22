package seleniumtestinglib.queries

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.JavascriptException
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.locators.ByText
import seleniumtestinglib.queries.ByType.AltText
import seleniumtestinglib.queries.JsType.Companion.asJsString
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

        val ex = result.exceptionOrNull() as JavascriptException
        assertTrue(ex.message?.contains("Unable to find an element with the alt text: will not work")!!, ex.message)
    }


    @Test
    fun `get but none found - findElements`() {
        driver.render("<input alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = runCatching {
            driver.findElement(ByText("as df".asJsString()))
        }

        val ex = result.exceptionOrNull() as NoSuchElementException
        assertTrue(
            ex.message!!.contains("Cannot locate an element using ByText(text=as df, selector=null, exact=null, ignore=null, normalizer=null)"),
            ex.message
        )
    }

    @Test
    fun `get all but none found`() {
        driver.render("<input alt='Incredibles 2 Poster' src='/incredibles-2.png' />")

        val result = runCatching {
            driver.getAllBy(AltText, "will not work")
        }

        val ex = result.exceptionOrNull() as JavascriptException
        assertTrue(ex.message!!.contains("Unable to find an element with the alt text: will not work"), ex.message)
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
