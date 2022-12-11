package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.isInvalid
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class InvalidTest(private val driver: RemoteWebDriver) {

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
        val element = driver.getBy(TestId, "invalid")

        assertTrue(element.isInvalid)
        expect(element).toBeInvalid()
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
        val element = driver.getBy(TestId, "not-invalid")

        assertFalse(element.isInvalid)
        expect(element).not.toBeInvalid()
    }
}
