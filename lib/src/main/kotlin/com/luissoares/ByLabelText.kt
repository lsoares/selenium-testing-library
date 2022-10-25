package com.luissoares

/**
 * https://testing-library.com/docs/queries/bylabeltext
 */
class ByLabelText(
    text: String,
    textIsString: Boolean = true,
    exact: Boolean? = null,
    selector: String? = null,
) : ByTestingLibrary(
    by = "LabelText",
    textMatch = text,
    textMatchIsString = textIsString,
    options = mapOf(
        "selector" to selector,
        "exact" to exact,
    ),
)
