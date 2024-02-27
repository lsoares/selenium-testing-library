package seleniumtestinglib

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import seleniumtestinglib.*

/**
 * https://testing-library.com/docs/ecosystem-jest-dom/
 */
fun expect(element: WebElement?) = JestDomMatcher(element)

data class JestDomMatcher(
    private val element: WebElement?,
    private val requireTrue: Boolean = true,
) {

    val not get() = JestDomMatcher(element, requireTrue.not())

    fun toBeDisabled() {
        requireNotNull(element)
        validate(element.isDisabled)
    }

    fun toBeEnabled() {
        requireNotNull(element)
        validate(element.isEnabled)
    }

    fun toBeEmptyDomElement() {
        requireNotNull(element)
        val innerHtml = element.getAttribute("innerHTML").replace("<!--.*?-->".toRegex(), "")
        validate(innerHtml.isEmpty())
    }

    fun toBeInvalid() {
        requireNotNull(element)
        validate(element.isInvalid)
    }

    fun toBeInTheDocument() {
        validate(element != null)
    }

    fun toBeRequired() {
        requireNotNull(element)
        validate(element.isRequired)
    }

    fun toBeValid() {
        requireNotNull(element)
        validate(element.isValid)
    }

    fun toBeVisible() {
        requireNotNull(element)
        validate(element.isVisible)
    }

    fun toContainElement(ancestor: WebElement?) {
        requireNotNull(element)
        validate(element.findElements(By.xpath(".//*")).contains(ancestor))
    }

    fun toContainHtml(htmlText: String) {
        requireNotNull(element)
        val normalizedHtmlText = element.wrappedDriver.executeScript(
            """
            const parser = new DOMParser()
            const htmlDoc = parser.parseFromString(arguments[0], "text/html")
            return htmlDoc.querySelector("body").innerHTML
        """, htmlText
        ) as String
        validate(element.innerHtml.contains(normalizedHtmlText))
    }

    fun toHaveAccessibleDescription() {
        requireNotNull(element)
        validate(element.accessibleDescription.isNotBlank())
    }

    fun toHaveAccessibleDescription(expectedAccessibleDescription: String) {
        requireNotNull(element)
        compare(expectedAccessibleDescription, element.accessibleDescription)
    }

    fun toHaveAccessibleDescription(expectedAccessibleDescription: Regex) {
        requireNotNull(element)
        validate(expectedAccessibleDescription.find(element.accessibleDescription) != null)
    }

    fun toHaveAccessibleDescription(expectedAccessibleDescription: (String) -> Boolean) {
        requireNotNull(element)
        validate(expectedAccessibleDescription(element.accessibleDescription))
    }

    fun toHaveAccessibleName() {
        requireNotNull(element)
        validate(element.accessibleName.isNotBlank())
    }

    fun toHaveAccessibleName(expectedAccessibleName: String) {
        requireNotNull(element)
        compare(expectedAccessibleName, element.accessibleName)
    }

    fun toHaveAccessibleName(expectedAccessibleName: Regex) {
        requireNotNull(element)
        validate(expectedAccessibleName.find(element.accessibleDescription) != null)
    }

    fun toHaveAccessibleName(expectedAccessibleName: (String) -> Boolean) {
        requireNotNull(element)
        validate(expectedAccessibleName(element.accessibleName))
    }

    fun toHaveAttribute(attribute: String) {
        requireNotNull(element)
        validate(element.getAttribute(attribute)?.isNotBlank() == true)
    }

    fun toHaveAttribute(attribute: String, value: String) {
        requireNotNull(element)
        compare(value, element.getAttribute(attribute))
    }

    fun toHaveAttribute(attribute: String, value: (String) -> Boolean) {
        requireNotNull(element)
        validate(value(element.getAttribute(attribute)))
    }

    fun toHaveClass(vararg classNames: String, exact: Boolean = false) {
        requireNotNull(element)
        val expectedClasses = classNames.map { it.split(Regex("\\s+")) }.flatten().toSet()
        if (expectedClasses.isEmpty()) {
            validate(element.classes.isNotEmpty())
            return
        }
        when (exact) {
            false -> validate(element.classes.containsAll(expectedClasses), mapOf("elementClasses" to element.classes))
            true  -> compare(expectedClasses, element.classes)
        }
    }

    fun toHaveFocus() {
        requireNotNull(element)
        validate(element.isFocused)
    }

    fun toHaveFormValues(vararg values: Pair<String, Any?>) {
        requireNotNull(element)
        val formValues = element.formValues
        validate(
            values.toMap().all { formValues[it.key] == it.value },
            mapOf("expected values" to values.toMap(), "form values" to formValues)
        )
    }

    fun toHaveStyle(css: String) {
        requireNotNull(element)
        val expectedCss = css.split(";")
            .filter(String::isNotBlank)
            .associate {
                it.split(":").let { it.first().trim().normalizeCssProp() to it.last().trim() }
            }
        val existingCss = expectedCss.keys
            .associateWith { element.getCssValue(it) }
        compare(expectedCss, existingCss)
    }

    fun toHaveStyle(css: Map<String, String>) {
        requireNotNull(element)
        val expectedCss = css.mapKeys { it.key.normalizeCssProp() }
        val existingCss = expectedCss.keys
            .associateWith { element.getCssValue(it) }
        compare(expectedCss, existingCss)
    }

    private fun String.normalizeCssProp() = "(?<=[a-zA-Z])[A-Z]".toRegex().replace(this) { "-${it.value}" }.lowercase()

    fun toHaveTextContent(text: String) {
        requireNotNull(element)
        validate(element.text?.contains(text) == true, mapOf("text" to element.text))
    }

    fun toHaveTextContent(text: Regex) {
        requireNotNull(element)
        validate(element.text?.let { text.find(it) } != null, mapOf("text" to element.text))
    }

    fun toHaveValue() {
        requireNotNull(element)
        compare(element.value, null)
    }

    fun toHaveValue(value: String) {
        requireNotNull(element)
        compare(element.value, value)
    }

    fun toHaveValue(value: Int) {
        requireNotNull(element)
        compare(element.value, value)
    }

    fun toHaveValue(value: List<String>) {
        requireNotNull(element)
        compare(element.value, value)
    }

    fun toHaveDisplayValue(value: String) {
        requireNotNull(element)
        compare(value, element.displayValue)
    }

    fun toHaveDisplayValue(value: Regex) {
        requireNotNull(element)
        validate(element.displayValue?.let {
            if (it is List<*>) it.filter { value.find(it.toString()) != null }.takeIf(List<Any?>::isNotEmpty)
            else value.find(it.toString())
        } != null, mapOf("text" to element.text))
    }

    fun toHaveDisplayValue(value: List<String>) {
        requireNotNull(element)
        compare(value, element.displayValue)
    }

    fun toBeChecked() {
        requireNotNull(element)
        validate(element.isChecked)
    }

    fun toBePartiallyChecked() {
        requireNotNull(element)
        validate(element.isPartiallyChecked)
    }

    fun toHaveErrorMessage(text: String) {
        requireNotNull(element)
        compare(text, element.errorMessage)
    }

    fun toHaveErrorMessage(text: Regex) {
        requireNotNull(element)
        validate(
            text.find(element.errorMessage.orEmpty()) != null,
            mapOf("text" to text, "error message" to element.errorMessage)
        )
    }

    fun toHaveErrorMessage(text: (String?) -> Boolean) {
        requireNotNull(element)
        validate(text(element.errorMessage))
    }

    private fun validate(condition: Boolean, debug: Map<String, Any?> = emptyMap()) {
        check(condition xor requireTrue.not()) {
            "condition: $condition, requireTrue: $requireTrue\ndebug: ${debug.toList()}"
        }
    }

    private fun compare(valueA: Any?, valueB: Any?) {
        check((valueA == valueB) xor requireTrue.not()) { "value A: $valueA\nvalue B: $valueB\nmust be equal? $requireTrue" }
    }
}
