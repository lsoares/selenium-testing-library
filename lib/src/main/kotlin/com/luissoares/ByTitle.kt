package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

/**
 * https://testing-library.com/docs/queries/bytitle
 */
data class ByTitle(
    private val title: String,
    private val exact: Boolean = true,
) : By() {
    override fun findElements(context: SearchContext): List<WebElement> =
        getWebDriver(context).waitFor {
            it.findElements(cssSelector("*")).filter { element ->
                when {
                    exact -> title == element.getAttribute("title") ||
                        element.tagName == "title" && element.text == element.text

                    else  -> element.getAttribute("title").orEmpty().contains(title, ignoreCase = true) ||
                        element.tagName == "title" && element.text.contains(title, ignoreCase = true)
                }
            }
        }
}
