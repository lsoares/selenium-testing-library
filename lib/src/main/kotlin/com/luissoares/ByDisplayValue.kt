package com.luissoares

/**
 * https://testing-library.com/docs/queries/bydisplayvalue
 */
class ByDisplayValue(
    value: String,
    exact: Boolean? = null,
) : ByTestingLibrary(
    by = "DisplayValue",
    textMatch = value,
    options = mapOf("exact" to exact),
)
