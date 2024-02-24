package seleniumtestinglib.locators

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import seleniumtestinglib.queries.LocatorType
import seleniumtestinglib.queries.TextMatch
import seleniumtestinglib.queries.TextMatch.Companion.asJsString
import seleniumtestinglib.queries.TextMatch.JsFunction
import seleniumtestinglib.queries.asJsRegex
import seleniumtestinglib.queries.executeTLQuery

/**
 * https://testing-library.com/docs/queries/byplaceholdertext
 */
data class ByPlaceholderText(
    private val text: TextMatch,
    private val exact: Boolean? = null,
    private val normalizer: JsFunction? = null,
) : By() {

    constructor(
        text: String,
        exact: Boolean? = null,
        normalizer: JsFunction? = null,
    ) : this(
        text = text.asJsString(),
        exact = exact,
        normalizer = normalizer,
    )

    constructor(
        text: Regex,
        exact: Boolean? = null,
        normalizer: JsFunction? = null,
    ) : this(
        text = text.asJsRegex(),
        exact = exact,
        normalizer = normalizer,
    )

    fun inexact() = copy(exact = false)

    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as JavascriptExecutor).executeTLQuery(
            by = LocatorType.PlaceholderText,
            textMatch = text,
            options = mapOf(
                "exact" to exact,
                "normalizer" to normalizer,
            ),
        )
}
