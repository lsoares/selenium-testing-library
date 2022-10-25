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
        with(TestingLibraryScript) {
            getJavascriptExecutor(context).queryAllBy(
                by = "TestId",
                mainArgument = value,
            )
        }
}
