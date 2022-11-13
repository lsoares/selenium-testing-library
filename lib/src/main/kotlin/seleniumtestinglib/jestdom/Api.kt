package seleniumtestinglib.jestdom

import org.openqa.selenium.WebElement

fun expect(webElement: WebElement?) = JestDomExpect(webElement)

/**
 * https://testing-library.com/docs/ecosystem-jest-dom/
 */
class JestDomExpect(
    private val webElement: WebElement?,
    private val positiveAssert: Boolean = true,
) {
    val not get() = JestDomExpect(webElement, positiveAssert.not())

    private fun assert(condition: Boolean?) {
        if (positiveAssert) require(condition ?: false)
        else require(condition?.not() ?: true)
    }

    fun toBeDisabled() {
        assert(webElement?.isDisabled)
    }

    fun toBeEnabled() {
        assert(webElement?.isDisabled?.not())
    }

    fun toBeEmptyDomElement() {
        assert(webElement?.isEmptyDomElement)
    }

    fun toBeInTheDocument() {
        assert(webElement?.isInTheDocument)
    }
}
