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
    textMatchIsString = titleIsString,
    options = mapOf("exact" to exact),
)
