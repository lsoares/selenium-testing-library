package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

/**
ByTitle: https://testing-library.com/docs/queries/bytitle
 */
data class ByTitle(
    private val title: String,
    private val exact: Boolean = true,
) : By() {
    override fun findElements(context: SearchContext): List<WebElement> =
        getWebDriver(context).filterAll {
            when {
                exact -> title == it.getAttribute("title") ||
                    it.tagName == "title" && it.text == it.text
                else  -> it.getAttribute("title").orEmpty().contains(title, ignoreCase = true) ||
                    it.tagName == "title" && it.text.contains(title, ignoreCase = true)
            }
        }
}
