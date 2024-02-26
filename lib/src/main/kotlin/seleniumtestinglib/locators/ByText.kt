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
import java.util.regex.Pattern

/**
 * https://testing-library.com/docs/queries/bytext
 */
data class ByText(
    private val text: TextMatch,
    private val selector: String? = null,
    private val exact: Boolean? = null,
    private val ignore: String? = null,
    private val normalizer: JsFunction? = null,
) : By() {

    fun disableIgnore() = copy(ignore = "")
    fun inexact() = copy(exact = false)

    constructor(
        text: String,
        selector: String? = null,
        exact: Boolean? = null,
        ignore: String? = null,
        normalizer: JsFunction? = null,
    ) : this(
        text = text.asJsString(),
        selector = selector,
        exact = exact,
        ignore = ignore,
        normalizer = normalizer,
    )

    constructor(
        text: Pattern,
        selector: String? = null,
        exact: Boolean? = null,
        ignore: String? = null,
        normalizer: JsFunction? = null,
    ) : this(
        text = text.asJsExpression(),
        selector = selector,
        exact = exact,
        ignore = ignore,
        normalizer = normalizer,
    )

    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as JavascriptExecutor).executeTLQuery(
            by = LocatorType.Text,
            textMatch = text,
            options = mapOf(
                "exact" to exact,
                "selector" to selector,
                "ignore" to ignore,
                "normalizer" to normalizer,
            ),
        )
}
