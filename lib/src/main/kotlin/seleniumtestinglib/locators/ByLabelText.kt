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
 * https://testing-library.com/docs/queries/bylabeltext
 */
data class ByLabelText(
    private val text: TextMatch,
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

    @Suppress("UNCHECKED_CAST")
    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as RemoteWebDriver).executeTLQuery(
            by = ByType.LabelText,
            textMatch = text,
            options = mapOf(
                "selector" to selector,
                "exact" to exact,
                "normalizer" to normalizer?.asJsFunction(),
            )
        ) as List<WebElement>
}
