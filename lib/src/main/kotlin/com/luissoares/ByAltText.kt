package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

/**
 * https://testing-library.com/docs/queries/byalttext
 */
data class ByAltText(
    private val text: String,
) : By() {
    override fun findElements(context: SearchContext): List<WebElement> =
        getWebDriver(context).waitFor {
            it.findElements(ByCssSelector("[alt='$text']"))
                .filter(::canHaveAlt)
        }

    private fun canHaveAlt(element: WebElement) =
        element.tagName in listOf("img", "area", "input") ||
            Regex(".+-.+").find(element.tagName) != null
}
