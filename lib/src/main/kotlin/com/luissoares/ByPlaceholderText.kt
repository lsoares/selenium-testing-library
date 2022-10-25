package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

/**
 * https://testing-library.com/docs/queries/byplaceholdertext
 */
data class ByPlaceholderText(
    private val text: String? = null,
    private val regexText: Regex? = null,
    private val exact: Boolean = true,
) : By() {
    override fun findElements(context: SearchContext): List<WebElement> {
        text ?: regexText ?: error("missing")
        return with(TestingLibraryScript) {
            getWebDriver(context).findAllBy(
                "PlaceholderText",
                text ?: regexText.toString(),
                mapOf("exact" to exact),
            )
        }
    }
}
