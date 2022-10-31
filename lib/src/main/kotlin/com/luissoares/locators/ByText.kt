package com.luissoares.locators

import com.luissoares.ByTestingLibrary
import com.luissoares.TextMatchType

/**
 * https://testing-library.com/docs/queries/bytext
 */
class ByText(
    text: String,
    matchTextBy: TextMatchType = TextMatchType.STRING,
    selector: String? = null,
    exact: Boolean? = null,
    ignore: String? = null,
    normalizer: String? = null
) : ByTestingLibrary(
    by = "Text",
    textMatch = text,
    matchTextBy = matchTextBy,
    options = mapOf(
        "exact" to exact,
        "selector" to selector,
        "ignore" to ignore,
        "normalizer" to normalizer,
    ),
)
