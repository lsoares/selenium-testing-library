package com.luissoares

/**
 * https://testing-library.com/docs/queries/byalttext
 */
class ByAltText(
    text: String,
    exact: Boolean? = null,
) : ByTestingLibrary(
    by = "AltText",
    arg0 = text,
    options = mapOf("exact" to exact),
)
