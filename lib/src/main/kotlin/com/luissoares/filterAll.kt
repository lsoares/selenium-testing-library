package com.luissoares

import org.openqa.selenium.By.cssSelector
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration.ofSeconds

fun WebDriver.filterAll(predicate: (WebElement) -> Boolean): List<WebElement> {
        val implicitWaitTimeout = manage().timeouts().implicitWaitTimeout
        val timeout = implicitWaitTimeout.seconds.takeIf { it > 0 } ?: 5
        return WebDriverWait(this, ofSeconds(timeout))
            .until {
                findElements(cssSelector("*")).filter(predicate)
            }
    }
