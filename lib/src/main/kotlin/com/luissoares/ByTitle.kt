package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext

/**
 * https://testing-library.com/docs/queries/bytitle
 */
data class ByTitle(
    private val title: String,
    private val exact: Boolean = true,
) : By() {
    override fun findElements(context: SearchContext) =
        getJavascriptExecutor(context).queryAll(
            by = "Title",
            arg0 = title,
            options = mapOf("exact" to exact),
        )
}
