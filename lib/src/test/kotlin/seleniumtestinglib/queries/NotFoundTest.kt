package seleniumtestinglib.queries

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptException
import org.openqa.selenium.NoSuchElementException
import seleniumtestinglib.driver
import seleniumtestinglib.locators.*
import seleniumtestinglib.queries.TextMatch.Companion.asJsFunction
import seleniumtestinglib.queries.LocatorType.AltText
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class NotFoundTest {

    @Test
    fun `get but none found`() {
        driver.render("<div></div>")

        val result = kotlin.runCatching {
            driver.getBy(AltText, "will not work")
        }

        val ex = result.exceptionOrNull() as JavascriptException
        assertTrue(ex.message?.contains("Unable to find an element with the alt text: will not work")!!, ex.message)
    }


    private fun `findElement error cases`() = setOf(
        of(
            ByText("a".toRegex()),
            "ByText(text=/a/, selector=null, exact=null, ignore=null, normalizer=null)"
        ),
        of(
            ByPlaceholderText("el => true".asJsFunction()),
            "ByPlaceholderText(text=el => true, exact=null, normalizer=null)"
        ),
        of(
            ByRole(Role.SpinButton, current = Current.Page, value = ByRole.Value(min = 1)),
            "ByRole(role=SpinButton, name=null, description=null, hidden=null, normalizer=null, selected=null, busy=null, checked=null, pressed=null, suggest=null, current=Page, expanded=null, level=null, value=Value(min=1, max=null, now=null, text=null), queryFallbacks=null)"
        ),
    )

    @ParameterizedTest
    @MethodSource("findElement error cases")
    fun `findElement not found`(locator: By, error: String) {
        driver.render("<div></div>")

        val result = runCatching {
            driver.findElement(locator)
        }

        val ex = result.exceptionOrNull() as NoSuchElementException
        assertTrue(ex.message!!.contains("Cannot locate an element using $error"), """Actual: ${ex.message}""")
    }

    @Test
    fun `get all but none found`() {
        driver.render("<div></div>")

        val result = runCatching {
            driver.getAllBy(AltText, "will not work")
        }

        val ex = result.exceptionOrNull() as JavascriptException
        assertTrue(ex.message!!.contains("Unable to find an element with the alt text: will not work"), ex.message)
    }

    @Test
    fun `query returns none`() {
        driver.render("<div></div>")

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
