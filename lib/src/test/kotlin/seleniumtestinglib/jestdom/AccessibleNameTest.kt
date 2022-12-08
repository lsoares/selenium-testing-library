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

        expect(driver.getBy(TestId, "x")).toHaveAccessibleName()
        expect(driver.getBy(TestId, "x")).toHaveAccessibleName("accessible name")
        assertEquals("accessible name", driver.getBy(TestId, "x").accessibleName)
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

        expect(driver.getBy(TestId, "x")).not.toHaveAccessibleName()
        expect(driver.getBy(TestId, "x")).not.toHaveAccessibleName("abc")
        assertEquals("", driver.getBy(TestId, "x").accessibleName)
    }

    @Test
    fun `wrong accessible name`() {
        driver.render("""<img data-testid="x" src="" alt="accessible name" />""")

        expect(driver.getBy(TestId, "x")).not.toHaveAccessibleName("abc")
    }

    // TODO: receive regex
}
