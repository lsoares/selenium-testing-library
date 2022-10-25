package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

/**
ByPlaceholderText: https://testing-library.com/docs/queries/byplaceholdertext
 */
data class ByPlaceholderText(
    private val text: String? = null,
    private val regexText: Regex? = null,
    private val exact: Boolean = true,
) : By() {
    override fun findElements(context: SearchContext): List<WebElement> =
        text?.let {
            when (exact) {
                true  -> context.findElements(cssSelector("[placeholder='$text']"))
                false -> getWebDriver(context).filterAll { element ->
                    element.getAttribute("placeholder")
                        ?.contains(text, ignoreCase = true)
                        ?: false
                }
            }
        } ?: regexText?.let {
            getWebDriver(context).filterAll { element ->
                element.getAttribute("placeholder")
                    ?.let(regexText::matches)
                    ?: false
            }
        }
        ?: error("You nee to provide text or regexText")
}
