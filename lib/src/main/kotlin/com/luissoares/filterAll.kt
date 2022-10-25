package com.luissoares

import org.openqa.selenium.By.cssSelector
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration.ofMillis
import java.time.Duration.ofSeconds

fun WebDriver.filterAll(isTrue: (WebElement) -> Boolean) =
    waitFor {
        findElements(cssSelector("*")).filter(isTrue)
    }

fun <T> WebDriver.waitFor(fn: (WebDriver) -> T): T {
    val implicitWaitTimeout = manage().timeouts().implicitWaitTimeout
    val timeout = implicitWaitTimeout.seconds.takeIf { it > 0 } ?: 5
    return WebDriverWait(this, ofSeconds(timeout))
        .pollingEvery(ofMillis(100))
        .until(fn)
}
