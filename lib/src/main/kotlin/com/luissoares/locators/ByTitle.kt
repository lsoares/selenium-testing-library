package com.luissoares.locators

/**
 * https://testing-library.com/docs/queries/bytitle
 */
class ByTitle(
    title: String,
    matchTextBy: TextMatchType = TextMatchType.STRING,
    exact: Boolean? = null,
    normalizer: String? = null,
) : ByTestingLibrary(
    by = "Title",
    textMatch = title,
    matchTextBy = matchTextBy,
    options = mapOf("exact" to exact, "normalizer" to normalizer),
)
