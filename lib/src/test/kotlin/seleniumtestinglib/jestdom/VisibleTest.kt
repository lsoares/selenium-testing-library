package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.Text
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.Test

@ExtendWith(DriverLifeCycle::class)
class VisibleTest(private val driver: RemoteWebDriver) {

    @Test
    fun invalid() {
        driver.render(
            """
            <div data-testid="zero-opacity" style="opacity: 0">Zero Opacity Example</div>
            <details open>
              <summary>Title of visible text</summary>
              <div>Visible Details Example</div>
            </details>
        """
        )

        expect(driver.getBy(Text, "Zero Opacity Example")).not.toBeVisible()
        expect(driver.getBy(Text, "Visible Details Example")).toBeVisible()
    }
}
