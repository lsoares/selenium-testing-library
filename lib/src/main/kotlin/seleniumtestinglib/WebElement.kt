package seleniumtestinglib

import org.openqa.selenium.By.id
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement

val WebElement.value: String?
    get() = getAttribute("value")

val WebElement.isChecked: Boolean
    get() = isSelected

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
