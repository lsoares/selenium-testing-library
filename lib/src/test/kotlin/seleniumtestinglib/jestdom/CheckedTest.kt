package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.isChecked
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class CheckedTest(private val driver: RemoteWebDriver) {

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
        val checkbox = driver.getBy(TestId, "x")

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
        val checkbox = driver.getBy(TestId, "x")

        assertFalse(checkbox.isChecked)
        expect(checkbox).not.toBeChecked()
    }
}
