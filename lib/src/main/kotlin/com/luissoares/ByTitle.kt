package com.luissoares

/**
 * https://testing-library.com/docs/queries/bytitle
 */
class ByTitle(
    title: String,
    exact: Boolean? = null,
) : ByTestingLibrary(
    by = "Title",
    textMatch = title,
    options = mapOf("exact" to exact),
)
