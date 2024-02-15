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
 * https://testing-library.com/docs/queries/bytitle
 */
class ByTitle(
    private val title: JsType,
    private val exact: Boolean? = null,
    private val normalizer: String? = null,
) : By() {

    constructor(
        title: String,
        exact: Boolean? = null,
        normalizer: String? = null,
    ) : this(
        title = title.asJsString(),
        exact = exact,
        normalizer = normalizer,
    )

    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as RemoteWebDriver).executeTLQuery(
            by = ByType.Title,
            textMatch = title,
            options = mapOf(
                "exact" to exact,
                "normalizer" to normalizer?.asJsExpression(),
            ),
        )
}
