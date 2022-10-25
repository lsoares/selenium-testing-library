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
    private val ignore: String = "script, style",
) : By() {

    override fun findElements(context: SearchContext) =
        getJavascriptExecutor(context).queryAll(
            by = "Text",
            arg0 = text,
            options = mapOf(
                "exact" to exact,
                "selector" to selector,
                "ignore" to (ignore.takeIf(String::isNotBlank) ?: false),
            ),
        )
}
