package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class ValidTest(private val driver: RemoteWebDriver) {

    @Test
    fun invalid() {
        driver.render(
            """
            <input data-testid="no-aria-invalid" />
            <input data-testid="aria-invalid" aria-invalid /> 
        """
        )

        val invalid = driver.getBy(TestId, "aria-invalid")
        assertFalse(invalid.isValid)
        expect(invalid).not.toBeValid()
        val valid = driver.getBy(TestId, "no-aria-invalid")
        assertTrue(valid.isValid)
        expect(valid).toBeValid()
    }
}
