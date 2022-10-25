package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

data class ByRole(
    private val role: String,
) : By() {
    override fun findElements(context: SearchContext): List<WebElement> =
        context.findElements(
            cssSelector("input, textarea, [role=textbox]")
        )
}
