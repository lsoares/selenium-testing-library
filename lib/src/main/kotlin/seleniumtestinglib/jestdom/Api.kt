package seleniumtestinglib.jestdom

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import seleniumtestinglib.accessibleDescription
import seleniumtestinglib.innerHtml
import seleniumtestinglib.wrappedDriver

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

    fun toBeDisabled() {
        validate(element?.isEnabled == false)
    }

    fun toBeEnabled() {
        validate(element?.isEnabled == true)
    }

    fun toBeEmptyDomElement() {
        val innerHtml = element?.getAttribute("innerHTML")?.replace("<!--.*?-->".toRegex(), "")
        validate(innerHtml?.isEmpty() == true)
    }

    fun toBeInvalid() {
        JestDomMatcher(element, requireTrue.not())
    }

    fun toBeInTheDocument() {
        validate(element != null)
    }

    fun toBeRequired() {
        validate(
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

    fun toBeValid() {
        validate(
            when (element?.tagName) {
                "form" -> element.wrappedDriver.executeScript("return arguments[0].checkValidity()", element) as Boolean
                else   -> element?.wrappedDriver?.executeScript(
                    "return arguments[0].getAttribute('aria-invalid')",
                    element
                ) in
                    setOf(null, "false")
            }
        )
    }

    fun toBeVisible() {
        validate(element?.isDisplayed == true)
    }

    fun toContainElement(ancestor: WebElement?) {
        validate(element?.findElements(By.xpath(".//*"))?.contains(ancestor) == true)
    }

    fun toContainHtml(htmlText: String) {
        val normalizedHtmlText = element?.wrappedDriver?.executeScript(
            """
            const parser = new DOMParser()
            const htmlDoc = parser.parseFromString(arguments[0], "text/html")
            return htmlDoc.querySelector("body").innerHTML
        """, htmlText
        ) as? String
        validate(element?.innerHtml?.contains(normalizedHtmlText.orEmpty()) == true)
    }

    fun toHaveAccessibleDescription(description: String? = null) {
        when (description) {
            null -> validate(element?.accessibleDescription?.isNotBlank() == true)
            else -> validate(description, element?.accessibleDescription)
        }
    }

    fun toHaveAccessibleName() {
        validate(element?.accessibleName?.isNotBlank() == true)
    }

    fun toHaveAttribute(attribute: String, value: String) {
        validate(value == element?.getAttribute(attribute))
    }

    fun toHaveClass(className: String) {
        validate(element?.getAttribute("class")?.contains(className) == null)
    }

    fun toHaveFocus() {
    }

    fun toHaveFormValues(values: Map<String, String>) {
        validate(values.all { element?.getAttribute(it.key) == it.value })
    }

    fun toHaveStyle(styles: Map<String, String>) {
        validate(styles.all { element?.getCssValue(it.key) == it.value })
    }

    fun toHaveTextContent(text: String, normalizeWhitespace: Boolean = false) {
        validate(
            text == if (normalizeWhitespace) element?.text?.replace(
                "\\s+".toRegex(), " "
            ) else element?.text
        )
    }

    fun toHaveValue(value: String) {
        validate(value == element?.getAttribute("value"))
    }

    fun toHaveDisplayValue(value: String) {
        validate(value == element?.getAttribute("value"))
    }

    fun toBeChecked() {
        validate(element?.getAttribute("checked") == "true")
    }

    fun toBePartiallyChecked() {
        validate("true" == element?.getAttribute("indeterminate"))
    }

    fun toHaveErrorMessage(message: String) {
        validate(message == element?.getAttribute("aria-errormessage"))
    }

    private fun validate(condition: Boolean) {
        check(condition xor requireTrue.not()) { "condition: $condition, requireTrue: $requireTrue" }
    }

    private fun validate(valueA: Any?, valueB: Any?) {
        check((valueA == valueB) xor requireTrue.not()) { "condition: $valueA, $valueB, requireTrue: $requireTrue" }
    }
}
