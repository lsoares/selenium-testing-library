package com.luissoares.locators

/**
 * https://testing-library.com/docs/queries/bytestid
 */
class ByTestId(
    value: String,
    matchTextBy: TextMatchType = TextMatchType.STRING,
    normalizer: String? = null,
) : ByTestingLibrary(
    by = "TestId",
    textMatch = value,
    matchTextBy = matchTextBy,
    options = mapOf("normalizer" to normalizer),
)
