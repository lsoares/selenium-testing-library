package com.luissoares

/**
 * https://testing-library.com/docs/queries/bytext
 */
class ByText(
    text: String,
    textIsString: Boolean = true,
    selector: String? = null,
    exact: Boolean? = null,
    ignore: String? = null,
    normalizer: String? = null
) : ByTestingLibrary(
    by = "Text",
    textMatch = text,
    textMatchIsString = textIsString,
    options = mapOf(
        "exact" to exact,
        "selector" to selector,
        "ignore" to ignore,
        "normalizer" to normalizer,
    ),
)
