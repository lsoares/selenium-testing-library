package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.text.RegexOption.IGNORE_CASE

@ExtendWith(DriverLifeCycle::class)
class AccessibleNameTest(private val driver: RemoteWebDriver) {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<img data-testid="x" src="" alt="accessible name" />""",
            """<svg data-testid="x"><title>accessible name</title></svg>""",
            """<input data-testid="x" title="accessible name" />""",
            """<button data-testid="x"><img src="" alt="accessible name" /></button>""",
            """<button data-testid="x"><svg><title>accessible name</title></svg></p>""",
        ]
    )
    fun `accessible name`(html: String) {
        driver.render(html)

        assertEquals("accessible name", driver.getBy(TestId, "x").accessibleName)
        expect(driver.getBy(TestId, "x")).toHaveAccessibleName()
        expect(driver.getBy(TestId, "x")).toHaveAccessibleName("accessible name")
        expect(driver.getBy(TestId, "x")).not.toHaveAccessibleName("abc")
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<img data-testid="x" src="" alt="" />""",
            """<p><img data-testid="x" src="" alt="" /> Test content</p>""",
            """<div><svg data-testid="x"></svg></div>""",
        ]
    )
    fun `not accessible name`(html: String) {
        driver.render(html)

        assertEquals("", driver.getBy(TestId, "x").accessibleName)
        expect(driver.getBy(TestId, "x")).not.toHaveAccessibleName()
        expect(driver.getBy(TestId, "x")).not.toHaveAccessibleName("abc")
    }

    @Test
    fun regex() {
        driver.render(
            """<span data-testid="x" aria-description="Accessible description"></span>"""

        )

        expect(driver.getBy(TestId, "x")).toHaveAccessibleName(Regex("accessible", IGNORE_CASE))
        expect(driver.getBy(TestId, "x")).not.toHaveAccessibleName(Regex("nope", IGNORE_CASE))
    }

    @Test
    fun function() {
        driver.render("""<img data-testid="x" src="" alt="accessible name" />""")

        expect(driver.getBy(TestId, "x")).toHaveAccessibleName {
            it.endsWith("name")
        }
        expect(driver.getBy(TestId, "x")).not.toHaveAccessibleName {
            it.endsWith("NAME")
        }
    }
}
