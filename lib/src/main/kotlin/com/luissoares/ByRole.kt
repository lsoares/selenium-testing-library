package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

data class ByRole(
    private val role: String,
) : By() {
    override fun findElements(context: SearchContext): List<WebElement> =
        context.findElements(
            cssSelector(
                (when (role) {
                    "textbox" -> setOf("input, textarea")
                    "heading" -> (1..6).map { "h$it" }
                    "banner" -> setOf("header")
                    "checkbox" -> setOf("input[type=checkbox]")
                    "form" -> setOf("form[aria-label]", "form[aria-labelledby]")
                    "link" -> setOf("a")
                    "button", "article", "figure"
                    -> setOf(role)

                    else -> emptySet()
                } + "[role=$role]").joinToString(",")
            )
        )
}
