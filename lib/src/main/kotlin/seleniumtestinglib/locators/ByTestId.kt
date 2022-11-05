package seleniumtestinglib.locators

/**
 * https://testing-library.com/docs/queries/bytestid
 */
class ByTestId(
    value: String,
    matchTextBy: TextMatchType = TextMatchType.STRING,
    normalizer: String? = null,
) : CoreApi(
    by = "TestId",
    textMatch = value,
    matchTextBy = matchTextBy,
    options = mapOf("normalizer" to normalizer),
)
