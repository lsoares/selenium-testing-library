package seleniumtestinglib.jestdom

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.By.id
import seleniumtestinglib.driver
import seleniumtestinglib.isValid
import seleniumtestinglib.render
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<input id="x" />""",
            """<input id="x" aria-invalid="false" />""",
            """<form id="x">
                  <input />
                </form>"""
        ]
    )
    fun valid(html: String) {
        driver.render(html)
        val element = driver.findElement(id("x"))

        assertTrue(element.isValid)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<input id="x" aria-invalid />""",
            """<input id="x" aria-invalid="true" />""",
            """<form id="x">
                  <input required />
                </form>"""
        ]
    )
    fun `not valid`(html: String) {
        driver.render(html)
        val element = driver.findElement(id("x"))

        assertFalse(element.isValid)
    }
}
