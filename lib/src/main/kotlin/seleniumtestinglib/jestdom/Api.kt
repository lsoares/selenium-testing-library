package seleniumtestinglib.jestdom

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/**
 * https://testing-library.com/docs/ecosystem-jest-dom/
 */
fun expect(element: WebElement?) = JestDomMatcher(element)

class JestDomMatcher(
    private val webElement: WebElement?,
    private val positiveAssert: Boolean = true,
) {

    val not get() = JestDomMatcher(webElement, positiveAssert.not())

    private fun requireEquals(expected: Any?, actual: Any?) {
        if (positiveAssert) require(expected == actual) { "$expected is not $actual" }
//        TODO
    }

    fun toBeDisabled() {
        JestDomMatcher(webElement, positiveAssert.not()).toBeEnabled()
    }

    fun toBeEnabled() {
        requireEquals(true, webElement?.isEnabled)
    }

    fun toBeEmptyDomElement() {
        requireEquals("", webElement?.text)
    }

    fun toBeInvalid() {
        requireEquals(positiveAssert, webElement?.getAttribute("aria-invalid") == "true")
    }

    fun toBeInTheDocument() {
        TODO("Not yet implemented")
    }

    fun toBeRequired() {
        requireEquals(positiveAssert, webElement?.getAttribute("aria-required") == "true")
    }

    fun toBeValid() {
        requireEquals(false, webElement?.getAttribute("aria-invalid") == "false")
    }

    fun toBeVisible() {
        requireEquals(positiveAssert, webElement?.isDisplayed)
    }

    fun toContainElement(ancestor: WebElement?) {
        requireEquals(positiveAssert, webElement?.findElements(By.xpath(".//*"))?.contains(ancestor))
    }

    fun toContainHtml(s: String) {
        val regex = Regex(s)
        requireEquals(positiveAssert, webElement?.getAttribute("innerHTML")?.matches(regex))
    }

    fun toHaveAccessibleDescription(description: String? = null) {
        val accessibleDescription = webElement?.getAttribute("aria-describedby") ?: webElement?.getAttribute("title")
        if (description == null)
            require(accessibleDescription != null)
        else
            requireEquals(description, accessibleDescription)
    }

    fun toHaveAccessibleName() {
        requireEquals(null, webElement?.getAttribute("aria-label"))
    }

    fun toHaveAttribute(attribute: String, value: String) {
        requireEquals(value, webElement?.getAttribute(attribute))
    }

    fun toHaveClass(className: String) {
        requireEquals(positiveAssert, webElement?.getAttribute("class")?.contains(className))
    }

    fun toHaveFocus() {
//        assert(true, webElement?.equals(webElement?.driver()?.switchTo()?.activeElement()))
    }

    fun toHaveFormValues(values: Map<String, String>) {
        requireEquals(true, values.all { webElement?.getAttribute(it.key) == it.value })
    }

    fun toHaveStyle(styles: Map<String, String>) {
        requireEquals(true, styles.all { webElement?.getCssValue(it.key) == it.value })
    }

    fun toHaveTextContent(text: String, normalizeWhitespace: Boolean = false) {
        val actual = if (normalizeWhitespace) webElement?.text?.replace("\\s+".toRegex(), " ") else webElement?.text
        requireEquals(text, actual)
    }

    fun toHaveValue(value: String) {
        requireEquals(value, webElement?.getAttribute("value"))
    }

    fun toHaveDisplayValue(value: String) {
        requireEquals(value, webElement?.getAttribute("value"))
    }

    fun toBeChecked() {
        requireEquals(positiveAssert, webElement?.getAttribute("checked") == "true")
    }

    fun toBePartiallyChecked() {
        requireEquals("true", webElement?.getAttribute("indeterminate"))
    }

    fun toHaveErrorMessage(message: String) {
        requireEquals(message, webElement?.getAttribute("aria-errormessage"))
    }
}
