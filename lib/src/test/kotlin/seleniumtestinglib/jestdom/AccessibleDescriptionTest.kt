package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.*
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.*

@ExtendWith(DriverLifeCycle::class)
class AccessibleDescriptionTest(private val driver: RemoteWebDriver) {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<span data-testid="x" aria-description="accessible description"></span>""",
            """<img src="logo.jpg" data-testid="x" alt="Company logo" aria-describedby="t1" />
                <span id="t1" role="presentation">accessible description</span>
            """,
            """<a data-testid="x" href="/" aria-label="Home page" 
                  title="accessible description">Start</a>""",
        ]
    )
    fun `accessible description`(html: String) {
        driver.render(html)

        expect(driver.getBy(TestId, "x")).toHaveAccessibleDescription()
        expect(driver.getBy(TestId, "x")).toHaveAccessibleDescription("accessible description")
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<a data-testid="x" href="/" aria-label="Home page">Start</a>""",
            """
                <img src="logo.jpg"
                  data-testid="x"
                  alt="Company logo"
                  aria-describedby="t1"
                />
                <span id="t1" role="presentation">The logo of Our Company</span>
            """,
        ]
    )
    fun `wrong accessible description`(html: String) {
        driver.render(html)

        expect(driver.getBy(TestId, "x")).not.toHaveAccessibleDescription("not this one")
    }

    @Test
    fun `no accessible description`() {
        driver.render("""<img src="avatar.jpg" alt="User profile pic" />""")

        expect(driver.findElement(By.tagName("img"))).not.toHaveAccessibleDescription()
    }
}
