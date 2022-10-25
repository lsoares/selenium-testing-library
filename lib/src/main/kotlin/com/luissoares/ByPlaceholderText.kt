package com.luissoares

/**
 * https://testing-library.com/docs/queries/byplaceholdertext
 */
class ByPlaceholderText(
    text: String,
    textIsString: Boolean = true,
    exact: Boolean? = null,
) : ByTestingLibrary(
    by = "PlaceholderText",
    textMatch = text,
    textMatchIsString = textIsString,
    options = mapOf("exact" to exact),
)
