package com.luissoares.locators

import com.luissoares.ByTestingLibrary
import com.luissoares.TextMatchType

/**
 * https://testing-library.com/docs/queries/byplaceholdertext
 */
class ByPlaceholderText(
    text: String,
    matchTextBy: TextMatchType = TextMatchType.STRING,
    exact: Boolean? = null,
    normalizer: String? = null,
) : ByTestingLibrary(
    by = "PlaceholderText",
    textMatch = text,
    matchTextBy = matchTextBy,
    options = mapOf("exact" to exact, "normalizer" to normalizer),
)