package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.render

@ExtendWith(DriverLifeCycle::class)
class EmptyDomElementTest(private val driver: RemoteWebDriver) {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<span data-testid="empty"></span>""",
            """<span data-testid="with-comment"><!-- comment --></span>""",
        ]
    )
    fun empty(html: String) {
        driver.render(html)

        expect(driver.findElement(By.tagName("span"))).toBeEmptyDomElement()
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<span data-testid="not-empty"><span data-testid="empty"></span></span>""",
            """<span data-testid="with-whitespace"> </span>""",
        ]
    )
    fun `not empty`(html: String) {
        driver.render(html)

        expect(driver.findElement(By.tagName("span"))).not.toBeEmptyDomElement()
    }
}
