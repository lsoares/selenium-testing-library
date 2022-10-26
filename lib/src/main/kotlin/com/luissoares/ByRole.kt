package com.luissoares

/**
 * https://testing-library.com/docs/queries/byrole
 */
class ByRole(
    role: String,
    matchTextBy: TextMatchType = TextMatchType.STRING,
    exact: Boolean? = null,
    hidden: Boolean? = null,
    name: String? = null,
    description: String? = null,
    matchDescriptionBy: TextMatchType = TextMatchType.STRING,
    normalizer: String? = null,
    selected: Boolean? = null,
    checked: Boolean? = null,
    pressed: Boolean? = null,
    current: Boolean? = null,
    expanded: Boolean? = null,
    queryFallbacks: Boolean? = null,
    level: Int? = null,
) : ByTestingLibrary(
    by = "Role",
    textMatch = role,
    matchTextBy = matchTextBy,
    matchDescriptionBy = matchDescriptionBy,
    options = mapOf(
        "exact" to exact,
        "name" to name,
        "hidden" to hidden,
        "description" to description,
        "normalizer" to normalizer,
        "selected" to selected,
        "checked" to checked,
        "pressed" to pressed,
        "current" to current,
        "expanded" to expanded,
        "queryFallbacks" to queryFallbacks,
        "level" to level,
    ),
)
