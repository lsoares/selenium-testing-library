package seleniumtestinglib.jestdom

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/**
 * https://testing-library.com/docs/ecosystem-jest-dom/
 */
fun expect(element: WebElement?) = JestDomMatcher(element)

class JestDomMatcher(private val webElement: WebElement?) {

    fun toBeDisabled() {
        require(webElement?.isEnabled == false)
    }

    fun toBeEnabled() {
        require(webElement?.isEnabled == true)
    }

    fun toBeEmptyDomElement() {
        require(webElement?.text?.isEmpty() == true)
    }

    fun toBeInvalid() {
        require(
            webElement?.getAttribute("aria-invalid") in setOf("", "true")
        )
    }

    fun toBeInTheDocument() {
        requireNotNull(webElement)
    }

    fun toBeRequired() {
        require(
            webElement?.getAttribute("required") in setOf("", "true") ||
                webElement?.getAttribute("aria-required") in setOf("", "true")
        )
    }

    fun toBeValid() {
        require(webElement?.getAttribute("aria-invalid") == "false")
    }

    fun toBeVisible() {
        require(webElement?.isDisplayed == true)
    }

    fun toContainElement(ancestor: WebElement?) {
        require(webElement?.findElements(By.xpath(".//*"))?.contains(ancestor) == true)
    }

    fun toContainHtml(htmlText: String) {
        require(webElement?.getAttribute("innerHTML")?.contains(htmlText) == true)
    }

    fun toHaveAccessibleDescription(description: String? = null) {
        val accessibleDescription = webElement?.getAttribute("aria-describedby") ?: webElement?.getAttribute("title")
        if (description == null)
            require(accessibleDescription != null)
        else
            require(description == accessibleDescription)
    }

    fun toHaveAccessibleName() {
        requireNotNull(webElement?.getAttribute("aria-label"))
    }

    fun toHaveAttribute(attribute: String, value: String) {
        require(value == webElement?.getAttribute(attribute))
    }

    fun toHaveClass(className: String) {
        require(webElement?.getAttribute("class")?.contains(className) == null)
    }

    fun toHaveFocus() {
    }

    fun toHaveFormValues(values: Map<String, String>) {
        require(values.all { webElement?.getAttribute(it.key) == it.value })
    }

    fun toHaveStyle(styles: Map<String, String>) {
        require(styles.all { webElement?.getCssValue(it.key) == it.value })
    }

    fun toHaveTextContent(text: String, normalizeWhitespace: Boolean = false) {
        require(text == if (normalizeWhitespace) webElement?.text?.replace("\\s+".toRegex(), " ") else webElement?.text)
    }

    fun toHaveValue(value: String) {
        require(value == webElement?.getAttribute("value"))
    }

    fun toHaveDisplayValue(value: String) {
        require(value == webElement?.getAttribute("value"))
    }

    fun toBeChecked() {
        require(webElement?.getAttribute("checked") == "true")
    }

    fun toBePartiallyChecked() {
        require("true" == webElement?.getAttribute("indeterminate"))
    }

    fun toHaveErrorMessage(message: String) {
        require(message == webElement?.getAttribute("aria-errormessage"))
    }
}
