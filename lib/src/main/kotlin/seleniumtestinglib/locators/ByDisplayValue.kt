package seleniumtestinglib.locators

/**
 * https://testing-library.com/docs/queries/bydisplayvalue
 */
class ByDisplayValue(
    value: String,
    matchTextBy: TextMatchType = TextMatchType.STRING,
    exact: Boolean? = null,
    normalizer: String? = null,
) : CoreApi(
    by = "DisplayValue",
    textMatch = value,
    matchTextBy = matchTextBy,
    options = mapOf("exact" to exact, "normalizer" to normalizer),
)
