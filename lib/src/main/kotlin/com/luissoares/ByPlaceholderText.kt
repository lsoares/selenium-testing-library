package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext

/**
 * https://testing-library.com/docs/queries/byplaceholdertext
 */
data class ByPlaceholderText(
    private val text: String,
    private val exact: Boolean = true,
) : By() {
    override fun findElements(context: SearchContext) =
        with(TestingLibraryScript) {
            getWebDriver(context).findAllBy(
                by = "PlaceholderText",
                mainArgument = text,
                options = mapOf("exact" to exact),
            )
        }
}
