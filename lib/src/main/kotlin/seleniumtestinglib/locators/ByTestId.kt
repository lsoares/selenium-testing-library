package seleniumtestinglib.locators

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import seleniumtestinglib.queries.LocatorType
import seleniumtestinglib.queries.JsType
import seleniumtestinglib.queries.JsType.Companion.asJsExpression
import seleniumtestinglib.queries.JsType.Companion.asJsString
import seleniumtestinglib.queries.executeTLQuery

/**
 * https://testing-library.com/docs/queries/bytestid
 */
data class ByTestId(
    private val text: JsType,
    private val exact: Boolean? = null,
    private val normalizer: String? = null,
) : By() {

    constructor(
        text: String,
        exact: Boolean? = null,
        normalizer: String? = null,
    ) : this(
        text = text.asJsString(),
        normalizer = normalizer,
        exact = exact,
    )

    fun inexact() = copy(exact = false)

    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as JavascriptExecutor).executeTLQuery(
            by = LocatorType.TestId,
            textMatch = text,
            options = mapOf(
                "normalizer" to normalizer?.asJsExpression(),
                "exact" to exact,
            ),
        )
}
