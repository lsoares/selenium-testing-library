package seleniumtestinglib.locators

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import seleniumtestinglib.Current.Page
import seleniumtestinglib.JsFunction
import seleniumtestinglib.Role.SpinButton
import seleniumtestinglib.TL.By.altText
import seleniumtestinglib.TL.By.placeholderText
import seleniumtestinglib.TL.By.role
import seleniumtestinglib.TL.By.text
import seleniumtestinglib.Value
import seleniumtestinglib.driver
import seleniumtestinglib.render
import java.util.regex.Pattern.CASE_INSENSITIVE
import java.util.regex.Pattern.compile
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NotFoundTest {

    private fun `findElement error cases`() = setOf(
        of(
            text(compile("a", CASE_INSENSITIVE)),
            "ByText(/a/i)"
        ),
        of(
            placeholderText(JsFunction("el => true")),
            "ByPlaceholderText(el => true)"
        ),
        of(
            role(SpinButton, current = Page, value = Value(min = 1)),
            "ByRole(spinbutton, value: {min=1}, current: page)"
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
    fun `query all but none found`() {
        driver.render(
            """
            <input alt='Incredibles 1' src='/incredibles-2.png' />
            <input alt='Incredibles 2' src='/incredibles-2.png' />
        """
        )

        val result = driver.findElements(altText("incredibles", exact = true))

        assertEquals(emptyList(), result)
    }
}
