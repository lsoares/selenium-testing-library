package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

data class ByRole(
    private val role: String,
) : By() {
    override fun findElements(context: SearchContext): List<WebElement> {
        val selectors = when (role) {
            "textbox"           -> setOf("input, textarea")
            "heading"           -> setOf("h1", "h2", "h3", "h4", "h5", "h6")
            "banner"            -> setOf("header")
            "button", "article" -> setOf(role)
            else                -> emptySet()
        } + "[role=$role]"
        return context.findElements(cssSelector(selectors.joinToString(",")))
    }
}
