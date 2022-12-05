package seleniumtestinglib.jestdom

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/**
 * https://testing-library.com/docs/ecosystem-jest-dom/
 */
fun expect(element: WebElement?) = JestDomMatcher(element)

class JestDomMatcher(
    private val webElement: WebElement?,
    private val requireTrue: Boolean = true,
) {

    val not get() = JestDomMatcher(webElement, requireTrue.not())

    private fun assertTrue(condition: Boolean) {
        require(condition xor requireTrue.not())
    }

    fun toBeDisabled() {
        assertTrue(webElement?.isEnabled == false)
    }

    fun toBeEnabled() {
        assertTrue(webElement?.isEnabled == true)
    }

    fun toBeEmptyDomElement() {
        assertTrue(webElement?.text?.isEmpty() == true)
    }

    fun toBeInvalid() {
        assertTrue(
            webElement?.getAttribute("aria-invalid") in setOf("", "true")
        )
    }

    fun toBeInTheDocument() {
        requireNotNull(webElement)
    }

    fun toBeRequired() {
        assertTrue(
            webElement?.getAttribute("required") in setOf("", "true") ||
                webElement?.getAttribute("aria-required") in setOf("", "true")
        )
    }

    fun toBeValid() {
        val ariaInvalid = webElement?.getAttribute("aria-invalid")
        assertTrue(ariaInvalid == null || ariaInvalid == "false")
    }

    fun toBeVisible() {
        assertTrue(webElement?.isDisplayed == true)
    }

    fun toContainElement(ancestor: WebElement?) {
        assertTrue(webElement?.findElements(By.xpath(".//*"))?.contains(ancestor) == true)
    }

    fun toContainHtml(htmlText: String) {
        assertTrue(webElement?.getAttribute("innerHTML")?.contains(htmlText) == true)
    }

    fun toHaveAccessibleDescription(description: String? = null) {
        val accessibleDescription = webElement?.getAttribute("aria-describedby") ?: webElement?.getAttribute("title")
        if (description == null)
            assertTrue(accessibleDescription != null)
        else
            assertTrue(description == accessibleDescription)
    }

    fun toHaveAccessibleName() {
        assertTrue(webElement?.accessibleName?.isNotBlank() == true)
    }

    fun toHaveAttribute(attribute: String, value: String) {
        assertTrue(value == webElement?.getAttribute(attribute))
    }

    fun toHaveClass(className: String) {
        assertTrue(webElement?.getAttribute("class")?.contains(className) == null)
    }

    fun toHaveFocus() {
    }

    fun toHaveFormValues(values: Map<String, String>) {
        assertTrue(values.all { webElement?.getAttribute(it.key) == it.value })
    }

    fun toHaveStyle(styles: Map<String, String>) {
        assertTrue(styles.all { webElement?.getCssValue(it.key) == it.value })
    }

    fun toHaveTextContent(text: String, normalizeWhitespace: Boolean = false) {
        assertTrue(
            text == if (normalizeWhitespace) webElement?.text?.replace(
                "\\s+".toRegex(),
                " "
            ) else webElement?.text
        )
    }

    fun toHaveValue(value: String) {
        assertTrue(value == webElement?.getAttribute("value"))
    }

    fun toHaveDisplayValue(value: String) {
        assertTrue(value == webElement?.getAttribute("value"))
    }

    fun toBeChecked() {
        assertTrue(webElement?.getAttribute("checked") == "true")
    }

    fun toBePartiallyChecked() {
        assertTrue("true" == webElement?.getAttribute("indeterminate"))
    }

    fun toHaveErrorMessage(message: String) {
        assertTrue(message == webElement?.getAttribute("aria-errormessage"))
    }
}
