package seleniumtestinglib.jestdom

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.driver
import seleniumtestinglib.isInvalid
import seleniumtestinglib.render
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class InvalidTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<input data-testid="invalid" aria-invalid />""",
            """<input data-testid="invalid" aria-invalid="true" />""",
            """<form data-testid="invalid">
                  <input required />
              </form>""",
        ]
    )
    fun invalid(html: String) {
        driver.render(html)
        val element = driver.findElement(testId("invalid"))

        assertTrue(element.isInvalid)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<input data-testid="not-invalid" />""",
            """<input data-testid="not-invalid" aria-invalid="false" />""",
            """<form data-testid="not-invalid">
                    <input />
                </form>""",
        ]
    )
    fun `not invalid`(html: String) {
        driver.render(html)
        val element = driver.findElement(testId("not-invalid"))

        assertFalse(element.isInvalid)
    }
}
