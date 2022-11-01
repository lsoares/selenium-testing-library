package com.luissoares

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

val WebElement.value: String?
    get() = getAttribute("value")

val WebElement.isChecked: Boolean
    get() = isSelected

fun WebElement.isFocused(driver: RemoteWebDriver): Boolean =
    equals(driver.switchTo().activeElement())
