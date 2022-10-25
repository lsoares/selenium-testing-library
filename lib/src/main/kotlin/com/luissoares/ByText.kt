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
    // TODO:
    // ignore?: string|boolean = 'script, style',
) : By() {

    override fun findElements(context: SearchContext) =
        with(TestingLibraryScript) {
            getWebDriver(context).findAllBy(
                by = "Text",
                mainArgument = text,
                options = mapOf("exact" to exact, "selector" to selector),
            )
        }
}
