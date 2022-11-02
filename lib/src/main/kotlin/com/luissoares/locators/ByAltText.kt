package com.luissoares.locators

/**
 * https://testing-library.com/docs/queries/byalttext
 */
class ByAltText(
    text: String,
    matchTextBy: TextMatchType = TextMatchType.STRING,
    exact: Boolean? = null,
    normalizer: String? = null,
) : ByTestingLibrary(
    by = "AltText",
    textMatch = text,
    matchTextBy = matchTextBy,
    options = mapOf("exact" to exact, "normalizer" to normalizer),
)
