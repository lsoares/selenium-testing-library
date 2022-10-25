package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

/**
 * https://testing-library.com/docs/queries/bydisplayvalue
 */
data class ByDisplayValue(
    private val value: String,
) : By() {
    override fun findElements(context: SearchContext): List<WebElement> =
        getWebDriver(context).waitUntil {
            it.findElements(cssSelector("*")).filter { element ->
                when (element.tagName) {
                    "input", "textarea" -> element.getAttribute("value") == value
                    "select"            ->
                        value in Select(element)
                            .allSelectedOptions.map(WebElement::getText)

                    else                -> false
                }
            }
        }
}
