package seleniumtestinglib

import org.openqa.selenium.By.cssSelector
import org.openqa.selenium.By.id
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.support.ui.Select

val WebElement.value: String?
    get() = getAttribute("value")

val WebElement.formValues: Map<String, Any?>
    get() {
        require(tagName == "form") { "this should be a form but it's a $tagName" }
        val formInputs = findElements(cssSelector("input[name], select[name], textarea[name]"))
        val values = mutableMapOf<String, Any?>()
        formInputs.forEach {
            val name = it.getAttribute("name")
            when (it.tagName) {
                "input"    -> when (it.getAttribute("type")) {
                    "number"   -> values[name] = it.value?.toIntOrNull()

                    "checkbox" -> if (findElements(cssSelector("[name=$name]")).size > 1) {
                        values.putIfAbsent(name, mutableListOf<String?>())
                        if (it.isChecked) (values[name] as MutableList<String?>).add(it.value)
                    } else values[name] = it.isChecked

                    "radio"    -> if (it.isSelected) values[name] = it.value
                    else       -> values[name] = it.value
                }

                "select"   -> when (it.getAttribute("multiple")) {
                    null -> values[name] = it.value
                    else -> {
                        values.putIfAbsent(name, mutableListOf<String?>())
                        val select = Select(it)
                        (values[name] as MutableList<String?>).addAll(select.allSelectedOptions.map { it.value })
                    }
                }

                "textarea" -> values[name] = it.value
            }
        }
        return values
    }

val WebElement.isChecked: Boolean
    get() {
        require(ariaRole == "checkbox") { "this is the role $ariaRole but should be a checkbox" }
        return isSelected
    }

val WebElement.isFocused: Boolean
    get() = equals(wrappedDriver.switchTo().activeElement())

val WebElement.isRequired
    get() = ((tagName == "input") and (getAttribute("type") == "file") or (ariaRole?.lowercase() in setOf(
        "textbox",
        "checkbox",
        "radio",
        "email",
        "spinbutton",
        "combobox",
        "listbox",
        "date",
    ))) and (getAttribute("required") in setOf(
        "",
        "true"
    )) or (getAttribute("aria-required") in setOf("", "true"))

val WebElement.isValid
    get() = when (tagName) {
        "form" -> wrappedDriver.executeScript("return arguments[0].checkValidity()", this) as Boolean
        else   -> wrappedDriver.executeScript(
            "return arguments[0].getAttribute('aria-invalid')",
            this
        ) in
            setOf(null, "false")
    }

@Suppress("UNCHECKED_CAST")
val WebElement.files: List<Map<String, Any>>
    get() = wrappedDriver.executeScript(
        "return arguments[0].files",
        this
    ) as? List<Map<String, Any>> ?: emptyList()

val WebElement.innerHtml: String
    get() = getAttribute("innerHTML")

/**
 * https://w3c.github.io/accname
 */
val WebElement.accessibleDescription: String
    get(): String =
        getAttribute("aria-describedby")
            ?.let { wrappedDriver.findElement(id(it)) }?.text
            ?: getAttribute("aria-description")
            ?: getAttribute("title")

val WebElement.classList: Set<String>
    get() = getAttribute("class").takeUnless(String::isNullOrBlank)
        ?.split(Regex("\\s+"))?.toSet() ?: emptySet()

internal val WebElement.wrappedDriver get() = (this as RemoteWebElement).wrappedDriver as RemoteWebDriver
