package com.luissoares

/**
 * https://testing-library.com/docs/queries/bytestid
 */
class ByTestId(value: String, valueIsString: Boolean = true) : ByTestingLibrary(
    by = "TestId",
    textMatch = value,
    textMatchIsString = valueIsString,
)
