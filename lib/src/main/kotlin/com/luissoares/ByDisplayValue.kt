package com.luissoares

/**
 * https://testing-library.com/docs/queries/bydisplayvalue
 */
class ByDisplayValue(
    value: String,
    exact: Boolean? = null,
) : ByTestingLibrary(
    by = "DisplayValue",
    arg0 = value,
    options = mapOf("exact" to exact),
)
