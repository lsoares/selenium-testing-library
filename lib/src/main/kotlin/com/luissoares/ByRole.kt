package com.luissoares

/**
 * https://testing-library.com/docs/queries/byrole
 */
class ByRole(
    role: String,
    exact: Boolean? = null,
    hidden: Boolean? = null,
    name: String? = null,
    description: String? = null,
    selected: Boolean? = null,
    checked: Boolean? = null,
    pressed: Boolean? = null,
    current: Boolean? = null,
    expanded: Boolean? = null,
    queryFallbacks: Boolean? = null,
    level: Int? = null,
) : ByTestingLibrary(
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
        ),
    )
