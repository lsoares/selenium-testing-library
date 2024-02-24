package seleniumtestinglib.locators

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import seleniumtestinglib.queries.LocatorType
import seleniumtestinglib.queries.TextMatch
import seleniumtestinglib.queries.TextMatch.Companion.asJsString
import seleniumtestinglib.queries.TextMatch.JsFunction
import seleniumtestinglib.queries.asJsExpression
import seleniumtestinglib.queries.executeTLQuery

/**
 * https://testing-library.com/docs/queries/bydisplayvalue
 */
data class ByDisplayValue(
    private val value: TextMatch,
    private val exact: Boolean? = null,
    private val normalizer: JsFunction? = null,
) : By() {

    constructor(
        value: String,
        exact: Boolean? = null,
        normalizer: JsFunction? = null,
    ) : this(
        value = value.asJsString(),
        exact = exact,
        normalizer = normalizer,
    )

    constructor(
        value: Regex,
        exact: Boolean? = null,
        normalizer: JsFunction? = null,
    ) : this(
        value = value.asJsExpression(),
        exact = exact,
        normalizer = normalizer,
    )

    fun inexact() = copy(exact = false)

    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as JavascriptExecutor).executeTLQuery(
            by = LocatorType.DisplayValue,
            textMatch = value,
            options = mapOf(
                "exact" to exact,
                "normalizer" to normalizer,
            ),
        )
}
