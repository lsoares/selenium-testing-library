package seleniumtestinglib.jestdom

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.driver
import seleniumtestinglib.expect
import seleniumtestinglib.isChecked
import seleniumtestinglib.render
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CheckedTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<input type="checkbox" checked data-testid="x" />""",
            """<div role="checkbox" aria-checked="true" data-testid="x" />""",
            """<input type="radio" checked value="foo" data-testid="x" />""",
            """<div role="radio" aria-checked="true" data-testid="x" />""",
            """<div role="switch" aria-checked="true" data-testid="x" />""",
        ]
    )
    fun checked(html: String) {
        driver.render(html)
        val checkbox = driver.findElement(testId("x"))

        assertTrue(checkbox.isChecked)
        expect(checkbox).toBeChecked()
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<input type="checkbox" data-testid="x" />""",
            """<div role="checkbox" aria-checked="false" data-testid="x" />""",
            """<input type="radio" value="foo" data-testid="x" />""",
            """<div role="radio" aria-checked="false" data-testid="x" />""",
            """<div role="switch" aria-checked="false" data-testid="x" />""",
        ]
    )
    fun `not checked`(html: String) {
        driver.render(html)
        val checkbox = driver.findElement(testId("x"))

        assertFalse(checkbox.isChecked)
        expect(checkbox).not.toBeChecked()
    }
}
