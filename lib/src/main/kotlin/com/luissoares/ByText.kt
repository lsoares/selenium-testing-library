package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext

/**
 * https://testing-library.com/docs/queries/bytext
 */
data class ByText(
    private val text: String,
    private val selector: String = "*",
    private val exact: Boolean = true,
) : By() {

    override fun findElements(context: SearchContext) =
        with(TestingLibraryScript) {
            getWebDriver(context).findAllBy(
                "Text", text,
                mapOf("exact" to exact, "selector" to selector)
            )
        }
}
