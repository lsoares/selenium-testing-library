package seleniumtestinglib.locators

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.coreapi.ByType
import seleniumtestinglib.coreapi.TextMatch
import seleniumtestinglib.coreapi.executeTLQuery

/**
 * https://testing-library.com/docs/queries/bytext
 */
data class ByText(
    private val text: TextMatch,
    private val selector: String? = null,
    private val exact: Boolean? = null,
    private val ignore: String? = null,
    private val normalizer: String? = null,
) : By() {

    constructor(
        text: String,
        selector: String? = null,
        exact: Boolean? = null,
        ignore: String? = null,
        normalizer: String? = null,
    ) : this(
        text = TextMatch.String(text),
        selector = selector,
        exact = exact,
        ignore = ignore,
        normalizer = normalizer,
    )

    @Suppress("UNCHECKED_CAST")
    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as RemoteWebDriver).executeTLQuery(
            by = ByType.Text,
            textMatch = text,
            options = mapOf(
                "exact" to exact,
                "selector" to selector,
                "ignore" to ignore,
                "normalizer" to normalizer?.let { TextMatch.Function(normalizer) },
            ),
        ) as List<WebElement>
}
