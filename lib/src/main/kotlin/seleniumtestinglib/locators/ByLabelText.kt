package seleniumtestinglib.locators

/**
 * https://testing-library.com/docs/queries/bylabeltext
 */
class ByLabelText(
    text: String,
    matchTextBy: TextMatchType = TextMatchType.STRING,
    exact: Boolean? = null,
    selector: String? = null,
    normalizer: String? = null,
) : CoreApi(
    by = "LabelText",
    textMatch = text,
    matchTextBy = matchTextBy,
    options = mapOf(
        "selector" to selector,
        "exact" to exact,
        "normalizer" to normalizer,
    ),
)
