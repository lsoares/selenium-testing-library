package seleniumtestinglib.locators

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.queries.ByType
import seleniumtestinglib.queries.JsType
import seleniumtestinglib.queries.JsType.Companion.asJsExpression
import seleniumtestinglib.queries.JsType.Companion.asJsString
import seleniumtestinglib.queries.executeTLQuery

/**
 * https://testing-library.com/docs/queries/bylabeltext
 */
data class ByLabelText(
    private val text: JsType,
    private val exact: Boolean? = null,
    private val selector: String? = null,
    private val normalizer: String? = null,
) : By() {

    constructor(
        text: String,
        exact: Boolean? = null,
        selector: String? = null,
        normalizer: String? = null,
    ) : this(
        text = text.asJsString(),
        exact = exact,
        selector = selector,
        normalizer = normalizer,
    )
    fun inexact() = copy(exact = false)

    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as RemoteWebDriver).executeTLQuery(
            by = ByType.LabelText,
            textMatch = text,
            options = mapOf(
                "selector" to selector,
                "exact" to exact,
                "normalizer" to normalizer?.asJsExpression(),
            )
        )
}
