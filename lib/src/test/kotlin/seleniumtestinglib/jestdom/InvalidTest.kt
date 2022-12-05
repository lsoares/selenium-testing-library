package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.Test

@ExtendWith(DriverLifeCycle::class)
class InvalidTest(private val driver: RemoteWebDriver) {

    @Test
    fun invalid() {
        driver.render(
            """
            <input data-testid="no-aria-invalid" />
            <input data-testid="aria-invalid" aria-invalid /> 
            <input data-testid="aria-invalid2" aria-invalid="true" /> 
        """
        )

        expect(driver.getBy(TestId, "aria-invalid")).toBeInvalid()
        expect(driver.getBy(TestId, "aria-invalid2")).toBeInvalid()
    }
}
