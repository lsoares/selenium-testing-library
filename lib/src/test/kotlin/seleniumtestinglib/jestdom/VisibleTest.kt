package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.isVisible
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class VisibleTest(private val driver: RemoteWebDriver) {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<div data-testid="x">Visible Example</div>""",
            """<details open>
                  <summary>Title of visible text</summary>
                  <div data-testid="x">Visible Details Example</div>
               </details>""",
        ]
    )
    fun visible(html: String) {
        driver.render(html)
        val element = driver.getBy(TestId, "x")

        assertTrue(element.isVisible)
        expect(element).toBeVisible()
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<div data-testid="x" style="opacity: 0">Zero Opacity Example</div>""",
            """<div data-testid="x" style="visibility: hidden">
              Visibility Hidden Example
            </div>""",
            """<div data-testid="x" style="display: none"> Display None Example</div>""",
            """<div style="opacity: 0">
                <span data-testid="x"> Hidden Parent Example</span>
            </div>""",
            """<div data-testid="x" hidden>Hidden Attribute Example</div>""",
            """<details>
                  <summary>Title of hidden text</summary>
                  <span data-testid="x">Hidden Details Example</span>
               </details>""",
        ]
    )
    fun `not visible`(html: String) {
        driver.render(html)
        val element = driver.getBy(TestId, "x")

        assertFalse(element.isVisible)
        expect(element).not.toBeVisible()
    }
}
