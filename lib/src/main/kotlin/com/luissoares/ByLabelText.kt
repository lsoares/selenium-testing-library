package com.luissoares

/**
 * https://testing-library.com/docs/queries/bylabeltext
 */
class ByLabelText(
    text: String,
    exact: Boolean? = null,
    selector: String? = null,
) : ByTestingLibrary(
    by = "LabelText",
    arg0 = text,
    options = mapOf(
        "selector" to selector,
        "exact" to exact,
    ),
)
