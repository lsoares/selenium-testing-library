package seleniumtestinglib.jestdom

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.By
import seleniumtestinglib.driver
import seleniumtestinglib.isEmptyDomElement
import seleniumtestinglib.render
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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

        assertTrue(driver.findElement(By.tagName("span")).isEmptyDomElement)
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

        assertFalse(driver.findElement(By.tagName("span")).isEmptyDomElement)
    }
}
