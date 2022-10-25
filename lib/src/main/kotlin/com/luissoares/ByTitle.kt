package com.luissoares

/**
 * https://testing-library.com/docs/queries/bytitle
 */
class ByTitle(
    title: String,
    titleIsString: Boolean = true,
    exact: Boolean? = null,
) : ByTestingLibrary(
    by = "Title",
    textMatch = title,
    isString = titleIsString,
    options = mapOf("exact" to exact),
)
