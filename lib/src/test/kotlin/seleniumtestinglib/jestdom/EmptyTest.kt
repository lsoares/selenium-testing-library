package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.Test

@ExtendWith(DriverLifeCycle::class)
class EmptyTest(private val driver: RemoteWebDriver) {

    @Test
    fun empty() {
        driver.render("<span data-testid=\"not-empty\"><span data-testid=\"empty\"></span></span>")

        expect(driver.getBy(TestId, "empty")).toBeEmptyDomElement()
        expect(driver.getBy(TestId, "not-empty")).not.toBeEmptyDomElement()
    }
}
