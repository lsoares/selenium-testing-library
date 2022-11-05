package seleniumtestinglib

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

val WebElement.value: String?
    get() = getAttribute("value")

val WebElement.isChecked: Boolean
    get() = isSelected

fun WebElement.isFocused(driver: RemoteWebDriver): Boolean =
    equals(driver.switchTo().activeElement())

@Suppress("UNCHECKED_CAST")
fun WebElement.files(driver: RemoteWebDriver): List<Map<String, Any>> =
    driver.executeScript("return arguments[0].files", this) as List<Map<String, Any>>
