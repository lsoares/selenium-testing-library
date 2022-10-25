package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext

/**
 * https://testing-library.com/docs/queries/bylabeltext
 */
data class ByLabelText(
    private val text: String,
    private val exact: Boolean = true,
    private val selector: String = "*",
) : By() {
    override fun findElements(context: SearchContext) =
        getJavascriptExecutor(context).queryAll(
            by = "LabelText",
            arg0 = text,
            options = mapOf(
                "selector" to selector,
                "exact" to exact,
            ),
        )
}
