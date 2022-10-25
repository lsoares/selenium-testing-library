package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

data class ByPlaceholderText(
    private val text: String,
    private val exact: Boolean = false,
) : By() {
    override fun findElements(context: SearchContext): List<WebElement> {
        val selector = when (exact) {
            false -> "[placeholder~='$text' i]"
            true  -> "[placeholder='$text']"
        }
        return context.findElements(cssSelector(selector))
    }
}
