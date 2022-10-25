package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

/**
 * https://testing-library.com/docs/queries/byplaceholdertext
 */
data class ByPlaceholderText(
    private val text: String? = null,
    private val regexText: Regex? = null,
    private val exact: Boolean = true,
) : By() {
    override fun findElements(context: SearchContext): List<WebElement> =
        text?.let {
            when {
                exact  -> context.findElements(cssSelector("[placeholder='$text']"))
                else -> {
                    getWebDriver(context).waitFor {
                        it.findElements(cssSelector("*")).filter { element ->
                            element.getAttribute("placeholder")
                                ?.contains(text, ignoreCase = true)
                                ?: false
                        }
                    }
                }
            }
        } ?: regexText?.let {
            getWebDriver(context).waitFor {
                it.findElements(cssSelector("*")).filter { element ->
                    element.getAttribute("placeholder")
                        ?.let(regexText::find) != null
                }
            }
        }
        ?: error("You nee to provide text or regexText")
}
