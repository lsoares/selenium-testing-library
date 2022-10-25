package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

data class ByPlaceholderText(
    private val text: String,
    private val exact: Boolean = true,
) : By() {
    override fun findElements(context: SearchContext): List<WebElement> = when (exact) {
        true  -> context.findElements(cssSelector("[placeholder='$text']"))
        false -> getWebDriver(context).filterAll(getWebDriver(context)) {
            it.getAttribute("placeholder")?.contains(text, ignoreCase = true) ?: false
        }
    }
}
