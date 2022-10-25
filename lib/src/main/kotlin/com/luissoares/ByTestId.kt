package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext

/**
 * https://testing-library.com/docs/queries/bytestid
 */
data class ByTestId(
    private val value: String,
) : By() {
    override fun findElements(context: SearchContext) =
        getJavascriptExecutor(context).queryAll(
            by = "TestId",
            arg0 = value,
        )
}
