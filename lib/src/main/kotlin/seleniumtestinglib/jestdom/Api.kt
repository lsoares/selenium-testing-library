package seleniumtestinglib.jestdom

import org.openqa.selenium.WebElement

/**
 * https://testing-library.com/docs/ecosystem-jest-dom/
 */
fun expect(webElement: WebElement?) = JestDomExpect(webElement)

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

    fun toBeInvalid() {
        assert(webElement?.isInvalid)
    }

    fun toBeRequired() {
        assert(webElement?.isRequired)
    }

    fun toBeValid() {
        assert(webElement?.isValid)
    }

    fun toBeVisible() {
        assert(webElement?.isVisible)
    }

    fun toContainElement(element: WebElement?) {
        assert(webElement?.toContainElement(element))
    }
}
