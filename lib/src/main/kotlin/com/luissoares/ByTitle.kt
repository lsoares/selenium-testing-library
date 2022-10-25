package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration.ofSeconds

data class ByTitle(private val title: String) : By() {
    override fun findElements(context: SearchContext): List<WebElement> {
        val implicitWaitTimeout = getWebDriver(context).manage().timeouts().implicitWaitTimeout
        val timeout = implicitWaitTimeout.seconds.takeIf { it > 0 } ?: 5
        return WebDriverWait(getWebDriver(context), ofSeconds(timeout))
            .until {
                context.findElements(cssSelector("*")).filter {
                    title == it.getAttribute("title") ||
                        it.tagName == "title" && it.text == it.text
                }
            }
    }
}
