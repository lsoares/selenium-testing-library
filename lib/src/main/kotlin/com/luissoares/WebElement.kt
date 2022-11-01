package com.luissoares

import org.openqa.selenium.WebElement

val WebElement.value: String?
    get() = getAttribute("value")
