package seleniumtestinglib.jestdom

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.By
import seleniumtestinglib.driver
import seleniumtestinglib.render

class EmptyDomElementTest {

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
