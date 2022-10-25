package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.sam.rosenthal.cssselectortoxpath.utilities.CssElementCombinatorPairsToXpath

data class ByText(
    private val text: String,
    private val selector: String = "*",
    private val exact: Boolean = true,
) : By() {
    override fun findElements(context: SearchContext): List<WebElement> {
        val xPathSelector = CssElementCombinatorPairsToXpath().convertCssSelectorStringToXpathString(selector)
        val xPathFunction = when {
            exact -> "text()='$text'"
            else  -> "contains(text(),'$text')"
        }
        return context.findElements(xpath("$xPathSelector[$xPathFunction]"))
    }
}
