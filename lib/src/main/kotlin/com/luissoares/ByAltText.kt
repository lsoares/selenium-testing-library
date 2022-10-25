package com.luissoares

/**
 * https://testing-library.com/docs/queries/byalttext
 */
class ByAltText(
    text: String,
    textIsString: Boolean = true,
    exact: Boolean? = null,
) : ByTestingLibrary(
    by = "AltText",
    textMatch = text,
    isString = textIsString,
    options = mapOf("exact" to exact),
)
