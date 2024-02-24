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
 * https://testing-library.com/docs/queries/bylabeltext
 */
data class ByLabelText(
    private val text: TextMatch,
    private val exact: Boolean? = null,
    private val selector: String? = null,
    private val normalizer: JsFunction? = null,
) : By() {

    constructor(
        text: String,
        exact: Boolean? = null,
        selector: String? = null,
        normalizer: JsFunction? = null,
    ) : this(
        text = text.asJsString(),
        exact = exact,
        selector = selector,
        normalizer = normalizer,
    )

    constructor(
        text: Regex,
        exact: Boolean? = null,
        selector: String? = null,
        normalizer: JsFunction? = null,
    ) : this(
        text = text.asJsExpression(),
        exact = exact,
        selector = selector,
        normalizer = normalizer,
    )

    fun inexact() = copy(exact = false)

    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as JavascriptExecutor).executeTLQuery(
            by = LocatorType.LabelText,
            textMatch = text,
            options = mapOf(
                "selector" to selector,
                "exact" to exact,
                "normalizer" to normalizer,
            )
        )
}
