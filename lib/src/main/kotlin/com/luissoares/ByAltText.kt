package com.luissoares

/**
 * https://testing-library.com/docs/queries/byalttext
 */
class ByAltText(
    text: String,
    textIsString: Boolean = true,
    exact: Boolean? = null,
    normalizer: String? = null,
) : ByTestingLibrary(
    by = "AltText",
    textMatch = text,
    textMatchIsString = textIsString,
    options = mapOf("exact" to exact, "normalizer" to normalizer),
)
