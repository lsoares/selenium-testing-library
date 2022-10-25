package com.luissoares

/**
 * https://testing-library.com/docs/queries/bydisplayvalue
 */
class ByDisplayValue(
    value: String,
    valueIsString: Boolean = true,
    exact: Boolean? = null,
) : ByTestingLibrary(
    by = "DisplayValue",
    textMatch = value,
    textMatchIsString = valueIsString,
    options = mapOf("exact" to exact),
)
