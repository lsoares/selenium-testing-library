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
                when (role) {
                    "textbox" -> setOf("input, textarea, [role=textbox]")
                    "heading" -> setOf("h1", "h2", "h3", "h4", "h5", "h6")
                    "button" -> setOf("button")
                    else      -> emptySet()
                }.joinToString(",")
            )
        ) + context.findElements(cssSelector("[role=$role]"))
}
