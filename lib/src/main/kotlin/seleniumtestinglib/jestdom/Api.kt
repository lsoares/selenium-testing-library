package seleniumtestinglib.jestdom

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import seleniumtestinglib.*

/**
 * https://testing-library.com/docs/ecosystem-jest-dom/
 */
fun expect(element: WebElement?) = JestDomMatcher(element)

// TODO: test nulls
data class JestDomMatcher(
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
        copy(requireTrue = requireTrue.not()).toBeValid()
    }

    fun toBeInTheDocument() {
        validate(element != null)
    }

    fun toBeRequired() {
        validate(element?.isRequired == true)
    }

    fun toBeValid() {
        validate(element?.isValid == true)
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

    fun toHaveAccessibleDescription(expectedAccessibleDescription: String? = null) {
        when (expectedAccessibleDescription) {
            null -> validate(element?.accessibleDescription?.isNotBlank() == true)
            else -> compare(expectedAccessibleDescription, element?.accessibleDescription)
        }
    }

    fun toHaveAccessibleName(expectedAccessibleName: String? = null) {
        when (expectedAccessibleName) {
            null -> validate(element?.accessibleName?.isNotBlank() == true)
            else -> compare(expectedAccessibleName, element?.accessibleName)
        }
    }

    fun toHaveAttribute(attribute: String, value: String? = null) {
        when (value) {
            null -> validate(element?.getAttribute(attribute)?.isNotBlank() == true)
            else -> compare(value, element?.getAttribute(attribute))
        }
    }

    fun toHaveClass(vararg classNames: String, exact: Boolean = false) {
        val expectedClasses = classNames.map { it.split(Regex("\\s+")) }.flatten().toSet()
        val elementClasses = element?.classList ?: emptySet()
        if (expectedClasses.isEmpty()) {
            validate(elementClasses.isNotEmpty())
            return
        }
        when (exact) {
            false -> validate(elementClasses.containsAll(expectedClasses), mapOf("elementClasses" to elementClasses))
            true  -> compare(expectedClasses, elementClasses)
        }
    }

    fun toHaveFocus() {
        validate(element?.isFocused == true)
    }

    fun toHaveFormValues(vararg values: Pair<String, Any?>) {
        val formValues = element?.formValues ?: emptyMap()
        validate(
            values.toMap().all { formValues[it.key] == it.value },
            mapOf("expected values" to values.toMap(), "form values" to formValues)
        )
    }

    fun toHaveStyle(css: String) {
        val expectedCss = css.split(";")
            .filter(String::isNotBlank)
            .associate {
                it.split(":").let { it.first().trim().normalizeCssProp() to it.last().trim() }
            }
        val existingCss = expectedCss.keys
            .map { it.normalizeCssProp() }
            .associateWith { element?.getCssValue(it) }
        compare(expectedCss, existingCss)
    }

    fun toHaveStyle(css: Map<String, String>) {
        val expectedCss = css.mapKeys { it.key.normalizeCssProp() }
        val existingCss = expectedCss.keys
            .associateWith { element?.getCssValue(it) }
        compare(expectedCss, existingCss)
    }

    private fun String.normalizeCssProp() = "(?<=[a-zA-Z])[A-Z]".toRegex().replace(this) { "-${it.value}" }.lowercase()

    fun toHaveTextContent(text: String) {
        validate(element?.text?.contains(text) == true, mapOf("text" to element?.text))
    }

    fun toHaveTextContent(text: Regex) {
        validate(element?.text?.let { text.find(it) } != null, mapOf("text" to element?.text))
    }

    fun toHaveValue(vararg value: String) {
    }

    fun toHaveValue(value: Int) {
    }

    fun toHaveDisplayValue(value: String) {
        validate(value == element?.getAttribute("value"))
    }

    fun toBeChecked() {
        validate(element?.getAttribute("checked") == "true")
    }

    fun toBePartiallyChecked() {
        validate(element?.isPartiallyChecked == true)
    }

    fun toHaveErrorMessage(text: String) {
        compare(text, element?.errorMessage)
    }

    fun toHaveErrorMessage(text: Regex) {
        validate(
            text.find(element?.errorMessage.orEmpty()) != null,
            mapOf("text" to text, "error message" to element?.errorMessage)
        )
    }

    private fun validate(condition: Boolean, debug: Map<String, Any?> = emptyMap()) {
        check(condition xor requireTrue.not()) {
            "condition: $condition, requireTrue: $requireTrue, debug: ${debug.toList()}"
        }
    }

    private fun compare(valueA: Any?, valueB: Any?) {
        check((valueA == valueB) xor requireTrue.not()) { "value A: $valueA\nvalue B: $valueB\nmust be equal? $requireTrue" }
    }
}
