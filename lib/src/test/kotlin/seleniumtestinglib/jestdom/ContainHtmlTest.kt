package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.queryBy
import seleniumtestinglib.render
import kotlin.test.Test

@ExtendWith(DriverLifeCycle::class)
class ContainHtmlTest(private val driver: RemoteWebDriver) {

    @Test
    fun `contain html`() {
        driver.render(
            """
            <span data-testid="parent"><span data-testid="child"></span></span>
        """
        )

        expect(driver.queryBy(TestId, "parent")).toContainHtml("<span data-testid='child' />")
        expect(driver.queryBy(TestId, "parent")).not.toContainHtml("<br />")
    }
}
