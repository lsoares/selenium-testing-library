package seleniumtestinglib.jestdom

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.By
import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.accessibleDescription
import seleniumtestinglib.driver
import seleniumtestinglib.expect
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.text.RegexOption.IGNORE_CASE

class AccessibleDescriptionTest {

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

        assertEquals("accessible description", driver.findElement(testId("x")).accessibleDescription)
        expect(driver.findElement(testId("x"))).toHaveAccessibleDescription()
        expect(driver.findElement(testId("x"))).toHaveAccessibleDescription("accessible description")
        expect(driver.findElement(testId("x"))).not.toHaveAccessibleDescription("not this one")
    }

    @Test
    fun regex() {
        driver.render(
            """<span data-testid="x" aria-description="Accessible description"></span>"""

        )

        expect(driver.findElement(testId("x"))).toHaveAccessibleDescription(Regex("accessible", IGNORE_CASE))
        expect(driver.findElement(testId("x"))).not.toHaveAccessibleDescription(Regex("nope", IGNORE_CASE))
    }

    @Test
    fun function() {
        driver.render("""<span data-testid="x" aria-description="Accessible description"></span>""")

        expect(driver.findElement(testId("x"))).toHaveAccessibleDescription {
            it.startsWith("access", ignoreCase = true)
        }
        expect(driver.findElement(testId("x"))).not.toHaveAccessibleDescription {
            it.startsWith("access", ignoreCase = false)
        }
    }

    @Test
    fun `no accessible description`() {
        driver.render("""<img src="avatar.jpg" alt="User profile pic" />""")

        expect(driver.findElement(By.tagName("img"))).not.toHaveAccessibleDescription()
    }
}
