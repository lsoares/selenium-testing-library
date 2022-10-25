package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

data class ByTitle(private val title: String) : By() {
    override fun findElements(context: SearchContext): List<WebElement> =
        context.filterAll(getWebDriver(context)) {
            title == it.getAttribute("title") ||
                it.tagName == "title" && it.text == it.text
        }
}
