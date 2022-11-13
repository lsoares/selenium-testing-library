package seleniumtestinglib.jestdom

import org.openqa.selenium.WebElement
import kotlin.test.assertTrue

fun expect(webElement: WebElement) = JestDomExpect(webElement)

class JestDomExpect(private val webElement: WebElement) {
    fun toBeDisabled() = assertTrue(webElement.isDisabled)
}
