package com.luissoares

import org.openqa.selenium.By.cssSelector
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration.ofSeconds

fun SearchContext.filterAll(driver: WebDriver, predicate: (WebElement)->Boolean): List<WebElement> {
        val implicitWaitTimeout = driver.manage().timeouts().implicitWaitTimeout
        val timeout = implicitWaitTimeout.seconds.takeIf { it > 0 } ?: 5
        return WebDriverWait(driver, ofSeconds(timeout))
            .until {
                findElements(cssSelector("*")).filter(predicate)
            }
    }
