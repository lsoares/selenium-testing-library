package seleniumtestinglib.jestdom

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import seleniumtestinglib.RoleType.CheckBox
import seleniumtestinglib.TL.By.role
import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.driver
import seleniumtestinglib.expect
import seleniumtestinglib.isPartiallyChecked
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PartiallyCheckedTest {

    @Test
    fun `partially checked by aria`() {
        driver.render("""<input type="checkbox" aria-checked="mixed" />""")
        val checkbox = driver.findElement(role(CheckBox))

        assertTrue(checkbox.isPartiallyChecked)
        expect(checkbox).toBePartiallyChecked()
    }

    @Test
    fun `partially checked by attr`() {
        driver.render("""<input type="checkbox" />""")
        val checkbox = driver.findElement(role(CheckBox))

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

        val element = driver.findElement(testId("x"))
        assertFalse(element.isPartiallyChecked)
        expect(element).not.toBePartiallyChecked()
    }
}
