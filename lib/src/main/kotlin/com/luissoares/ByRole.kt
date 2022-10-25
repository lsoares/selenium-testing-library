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
    private val checked: Boolean? = null,
    private val pressed: Boolean? = null,
    private val current: Boolean? = null,
    private val expanded: Boolean? = null,
    private val queryFallbacks: Boolean? = null,
    private val level: Int? = null,
) : By() {
    override fun findElements(context: SearchContext) =
        getJavascriptExecutor(context).queryAll(
            by = "Role",
            arg0 = role,
            options = mapOf(
                "exact" to exact,
                "name" to name,
                "hidden" to hidden,
                "description" to description,
                "selected" to selected,
                "checked" to checked,
                "pressed" to pressed,
                "current" to current,
                "expanded" to expanded,
                "queryFallbacks" to queryFallbacks,
                "level" to level,
            ).filterValues { it != null },
        )
}
