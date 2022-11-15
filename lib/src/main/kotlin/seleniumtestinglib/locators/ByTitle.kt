package seleniumtestinglib.locators

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.coreapi.ByType
import seleniumtestinglib.coreapi.TextMatch
import seleniumtestinglib.coreapi.TextMatch.Companion.asJsFunction
import seleniumtestinglib.coreapi.TextMatch.Companion.asJsString
import seleniumtestinglib.coreapi.executeTLQuery

/**
 * https://testing-library.com/docs/queries/bytitle
 */
class ByTitle(
    private val title: TextMatch,
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

    @Suppress("UNCHECKED_CAST")
    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as RemoteWebDriver).executeTLQuery(
            by = ByType.Title,
            textMatch = title,
            options = mapOf(
                "exact" to exact,
                "normalizer" to normalizer?.asJsFunction(),
            ),
        ) as List<WebElement>
}
