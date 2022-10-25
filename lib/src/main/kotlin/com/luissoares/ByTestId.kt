package com.luissoares

/**
 * https://testing-library.com/docs/queries/bytestid
 */
class ByTestId(
    value: String,
    valueIsString: Boolean = true,
    normalizer: String? = null,
) : ByTestingLibrary(
    by = "TestId",
    textMatch = value,
    textMatchIsString = valueIsString,
    options = mapOf("normalizer" to normalizer),
)
