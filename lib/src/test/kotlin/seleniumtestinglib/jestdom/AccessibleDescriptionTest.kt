package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.*
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.accessibleDescription
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.*
import kotlin.text.RegexOption.IGNORE_CASE

@ExtendWith(DriverLifeCycle::class)
class AccessibleDescriptionTest(private val driver: RemoteWebDriver) {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<span data-testid="x" aria-description="accessible description"></span>""",
            """<img src="logo.jpg" data-testid="x" alt="Company logo" aria-describedby="t1" />
                <span id="t1" role="presentation">accessible description</span>""",
            """<a data-testid="x" href="/" aria-label="Home page" 
                  title="accessible description">Start</a>""",
        ]
    )
    fun `accessible description`(html: String) {
        driver.render(html)

        assertEquals("accessible description", driver.getBy(TestId, "x").accessibleDescription)
        expect(driver.getBy(TestId, "x")).toHaveAccessibleDescription()
        expect(driver.getBy(TestId, "x")).toHaveAccessibleDescription("accessible description")
        expect(driver.getBy(TestId, "x")).not.toHaveAccessibleDescription("not this one")
    }

    @Test
    fun regex() {
        driver.render(
            """<span data-testid="x" aria-description="Accessible description"></span>"""

        )

        expect(driver.getBy(TestId, "x")).toHaveAccessibleDescription(Regex("accessible", IGNORE_CASE))
        expect(driver.getBy(TestId, "x")).not.toHaveAccessibleDescription(Regex("nope", IGNORE_CASE))
    }

    @Test
    fun function() {
        driver.render("""<span data-testid="x" aria-description="Accessible description"></span>""")

        expect(driver.getBy(TestId, "x")).toHaveAccessibleDescription {
            it.startsWith("access", ignoreCase = true)
        }
        expect(driver.getBy(TestId, "x")).not.toHaveAccessibleDescription {
            it.startsWith("access", ignoreCase = false)
        }
    }

    @Test
    fun `no accessible description`() {
        driver.render("""<img src="avatar.jpg" alt="User profile pic" />""")

        expect(driver.findElement(By.tagName("img"))).not.toHaveAccessibleDescription()
    }
}
