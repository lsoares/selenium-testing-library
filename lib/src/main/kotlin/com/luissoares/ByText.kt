package com.luissoares

/**
 * https://testing-library.com/docs/queries/bytext
 */
class ByText(
    text: String,
    selector: String? = null,
    exact: Boolean? = null,
    ignore: String? = null,
) : ByTestingLibrary(
    by = "Text",
    arg0 = text,
    options = mapOf(
        "exact" to exact,
        "selector" to selector,
        "ignore" to ignore,
    ),
)
