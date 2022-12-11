package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.isDisabled
import seleniumtestinglib.queries.ByType.Role
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class DisabledTest(private val driver: RemoteWebDriver) {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<button data-testid="test1" type="submit" disabled>submit</button>""",
            """<fieldset disabled><input type="text" data-testid="test1" /></fieldset>""",
        ]
    )
    fun disabled(html: String) {
        driver.render(html)
        val element = driver.getBy(TestId, "test1")

        assertTrue(element.isDisabled)
        expect(element).toBeDisabled()
    }

    @Test
    fun `links cant be disabled`() {
        driver.render("""<a href="/" disabled>link</a>""")
        val element = driver.getBy(Role, "link")

        assertFalse(element.isDisabled)
        expect(element).not.toBeDisabled()
    }
}
