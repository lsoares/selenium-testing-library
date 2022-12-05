package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.*

@ExtendWith(DriverLifeCycle::class)
class AccessibleDescriptionTest(private val driver: RemoteWebDriver) {

    @Test
    fun `accessible description`() {
        driver.render(
            """
            <a data-testid="link" href="/" aria-label="Home page"
               title="A link to start over">Start</a>
            <a data-testid="extra-link" href="/about" aria-label="About page">About</a>
        """
        )

        expect(driver.getBy(TestId, "link")).toHaveAccessibleDescription()
        expect(driver.getBy(TestId, "link")).toHaveAccessibleDescription("A link to start over")
        expect(driver.getBy(TestId, "extra-link")).not.toHaveAccessibleDescription()
        expect(driver.getBy(TestId, "link")).not.toHaveAccessibleDescription("Home page")
    }
}
