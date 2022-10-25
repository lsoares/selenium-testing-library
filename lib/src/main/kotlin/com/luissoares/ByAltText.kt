package com.luissoares

/**
 * https://testing-library.com/docs/queries/byalttext
 */
class ByAltText(
    text: String,
    textMatchType: TextType = TextType.STRING,
    exact: Boolean? = null,
) : ByTestingLibrary(
    by = "AltText",
    textMatch = text,
    textMatchType = textMatchType,
    options = mapOf("exact" to exact),
)
