package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext

/**
 * https://testing-library.com/docs/queries/byrole
 */
data class ByRole(
    private val role: String,
    private val exact: Boolean = true,
    private val hidden: Boolean = false,
    private val name: String? = null,
    private val description: String? = null,
    private val selected: Boolean? = null,
    // TODO
    // checked?: boolean,
    // pressed?: boolean,
    // current?: boolean | string,
    // expanded?: boolean,
    // queryFallbacks?: boolean,
    // level?: number,
) : By() {
    override fun findElements(context: SearchContext) =
        getJavascriptExecutor(context).queryAll(
            by = "Role",
            mainArgument = role,
            options = mapOf(
                "exact" to exact,
                "name" to name,
                "hidden" to hidden,
                "description" to description,
                "selected" to selected,
            ).filterValues { it != null },
        )
}
