package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.*
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.isPartiallyChecked
import seleniumtestinglib.queries.ByType
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.*

@ExtendWith(DriverLifeCycle::class)
class PartiallyCheckedTest(private val driver: RemoteWebDriver) {

    @Test
    fun `partially checked by aria`() {
        driver.render("""<input type="checkbox" aria-checked="mixed" />""")
        val checkbox = driver.getBy(ByType.Role, "checkbox")

        assertTrue(checkbox.isPartiallyChecked)
        expect(checkbox).toBePartiallyChecked()
    }

    @Test
    fun `partially checked by attr`() {
        driver.render("""<input type="checkbox" />""")
        val checkbox = driver.getBy(ByType.Role, "checkbox")

        assertFalse(checkbox.isPartiallyChecked)
        expect(checkbox).not.toBePartiallyChecked()
        driver.executeScript("arguments[0].indeterminate = true", checkbox)
        expect(checkbox).toBePartiallyChecked()
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<input type="checkbox" checked data-testid="x" />""",
            """<input type="checkbox" data-testid="x" />""",
            """<div role="checkbox" aria-checked="true" data-testid="x" />""",
            """<div role="checkbox" aria-checked="false" data-testid="x" />""",
        ]
    )
    fun `not partially checked`(html: String) {
        driver.render(html)

        val element = driver.getBy(TestId, "x")
        assertFalse(element.isPartiallyChecked)
        expect(element).not.toBePartiallyChecked()
    }
}
