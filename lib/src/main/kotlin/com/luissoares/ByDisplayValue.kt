package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext

/**
 * https://testing-library.com/docs/queries/bydisplayvalue
 */
data class ByDisplayValue(
    private val value: String,
    private val exact: Boolean = true,
) : By() {
    override fun findElements(context: SearchContext) =
        with(TestingLibraryScript) {
            getWebDriver(context).findAllBy(
                by = "DisplayValue",
                mainArgument = value,
                options = mapOf(
                    "exact" to exact,
                ),
            )
        }
}
