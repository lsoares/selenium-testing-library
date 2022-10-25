package com.luissoares

/**
 * https://testing-library.com/docs/queries/bytestid
 */
class ByTestId(value: String) : ByTestingLibrary(
    by = "TestId",
    textMatch = value,
)
