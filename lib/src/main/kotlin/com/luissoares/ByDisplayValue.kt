package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

/**
 * https://testing-library.com/docs/queries/bydisplayvalue
 */
data class ByDisplayValue(
    private val value: String,
) : By() {
    override fun findElements(context: SearchContext): List<WebElement> =
        getWebDriver(context).waitFor {
            it.findElements(cssSelector("*")).filter { element ->
                element.getAttribute("value") == value
            }
        }
}
