package com.luissoares

/**
 * https://testing-library.com/docs/queries/byplaceholdertext
 */
class ByPlaceholderText(
    text: String,
    textIsString: Boolean = true,
    exact: Boolean? = null,
    normalizer: String? = null,
) : ByTestingLibrary(
    by = "PlaceholderText",
    textMatch = text,
    textMatchIsString = textIsString,
    options = mapOf("exact" to exact, "normalizer" to normalizer),
)
