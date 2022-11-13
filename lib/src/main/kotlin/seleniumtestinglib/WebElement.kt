package seleniumtestinglib

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement

val WebElement.value: String?
    get() = getAttribute("value")

val WebElement.isChecked: Boolean
    get() = isSelected

val WebElement.isFocused: Boolean
    get() = equals((this as RemoteWebElement).wrappedDriver.switchTo().activeElement())

@Suppress("UNCHECKED_CAST")
val WebElement.files: List<Map<String, Any>>
    get() = ((this as RemoteWebElement).wrappedDriver as RemoteWebDriver).executeScript(
        "return arguments[0].files",
        this
    ) as? List<Map<String, Any>> ?: emptyList()
