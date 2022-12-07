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

internal val WebElement.wrappedDriver get() = (this as RemoteWebElement).wrappedDriver as RemoteWebDriver
