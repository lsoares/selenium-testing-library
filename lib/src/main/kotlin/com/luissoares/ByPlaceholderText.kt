package com.luissoares

/**
 * https://testing-library.com/docs/queries/byplaceholdertext
 */
class ByPlaceholderText(
    text: String,
    exact: Boolean? = null,
) : ByTestingLibrary(
    by = "PlaceholderText",
    arg0 = text,
    options = mapOf("exact" to exact),
)
