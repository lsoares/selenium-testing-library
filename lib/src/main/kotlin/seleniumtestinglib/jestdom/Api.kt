package seleniumtestinglib.jestdom

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement

/**
 * https://testing-library.com/docs/ecosystem-jest-dom/
 */
fun expect(element: WebElement?) = JestDomMatcher(element)

// TODO: test nulls
class JestDomMatcher(
    private val element: WebElement?,
    private val requireTrue: Boolean = true,
) {

    val not get() = JestDomMatcher(element, requireTrue.not())

    private fun assertTrue(condition: Boolean) {
        require(condition xor requireTrue.not())
    }

    fun toBeDisabled() {
        assertTrue(element?.isEnabled == false)
    }

    fun toBeEnabled() {
        assertTrue(element?.isEnabled == true)
    }

    fun toBeEmptyDomElement() {
        val innerHtml = element?.getAttribute("innerHTML")?.replace("<!--.*?-->".toRegex(), "")
        assertTrue(innerHtml?.isEmpty() == true)
    }

    fun toBeInvalid() {
        JestDomMatcher(element, requireTrue.not())
    }

    fun toBeInTheDocument() {
        assertTrue(element != null)
    }

    fun toBeRequired() {
        assertTrue(
            ((element?.tagName == "input") and (element?.getAttribute("type") == "file") or (element?.ariaRole?.lowercase() in setOf(
                "textbox",
                "checkbox",
                "radio",
                "email",
                "spinbutton",
                "combobox",
                "listbox",
                "date",
            ))) and (element?.getAttribute("required") in setOf(
                "",
                "true"
            )) or (element?.getAttribute("aria-required") in setOf("", "true"))
        )
    }

    private val driver get() = ((element as? RemoteWebElement)?.wrappedDriver) as? RemoteWebDriver

    fun toBeValid() {
        assertTrue(
            when (element?.tagName) {
                "form" -> driver?.executeScript("return arguments[0].checkValidity()", element) as Boolean
                else   -> driver?.executeScript("return arguments[0].getAttribute('aria-invalid')", element) in
                    setOf(null, "false")
            }
        )
    }

    fun toBeVisible() {
        assertTrue(element?.isDisplayed == true)
    }

    fun toContainElement(ancestor: WebElement?) {
        assertTrue(element?.findElements(By.xpath(".//*"))?.contains(ancestor) == true)
    }

    fun toContainHtml(htmlText: String) {
        val normalizedHtmlText = driver?.executeScript(
            """
            const parser = new DOMParser()
            const htmlDoc = parser.parseFromString(arguments[0], "text/html")
            return htmlDoc.querySelector("body").innerHTML
        """, htmlText
        ) as String
        assertTrue(element?.getAttribute("innerHTML")?.contains(normalizedHtmlText) == true)
    }

    fun toHaveAccessibleDescription(description: String? = null) {
        val accessibleDescription = element?.getAttribute("aria-describedby") ?: element?.getAttribute("title")
        if (description == null) assertTrue(accessibleDescription?.isNotBlank() == true)
        else assertTrue(description == accessibleDescription)
    }

    fun toHaveAccessibleName() {
        assertTrue(element?.accessibleName?.isNotBlank() == true)
    }

    fun toHaveAttribute(attribute: String, value: String) {
        assertTrue(value == element?.getAttribute(attribute))
    }

    fun toHaveClass(className: String) {
        assertTrue(element?.getAttribute("class")?.contains(className) == null)
    }

    fun toHaveFocus() {
    }

    fun toHaveFormValues(values: Map<String, String>) {
        assertTrue(values.all { element?.getAttribute(it.key) == it.value })
    }

    fun toHaveStyle(styles: Map<String, String>) {
        assertTrue(styles.all { element?.getCssValue(it.key) == it.value })
    }

    fun toHaveTextContent(text: String, normalizeWhitespace: Boolean = false) {
        assertTrue(
            text == if (normalizeWhitespace) element?.text?.replace(
                "\\s+".toRegex(), " "
            ) else element?.text
        )
    }

    fun toHaveValue(value: String) {
        assertTrue(value == element?.getAttribute("value"))
    }

    fun toHaveDisplayValue(value: String) {
        assertTrue(value == element?.getAttribute("value"))
    }

    fun toBeChecked() {
        assertTrue(element?.getAttribute("checked") == "true")
    }

    fun toBePartiallyChecked() {
        assertTrue("true" == element?.getAttribute("indeterminate"))
    }

    fun toHaveErrorMessage(message: String) {
        assertTrue(message == element?.getAttribute("aria-errormessage"))
    }
}
