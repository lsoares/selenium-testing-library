package seleniumtestinglib.locators

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.coreapi.ByType
import seleniumtestinglib.coreapi.MatchType
import seleniumtestinglib.coreapi.executeTLQuery

/**
 * https://testing-library.com/docs/queries/bylabeltext
 */
data class ByLabelText(
    private val text: String,
    private val matchTextBy: MatchType = MatchType.STRING,
    private val exact: Boolean? = null,
    private val selector: String? = null,
    private val normalizer: String? = null,
) : By() {
    @Suppress("UNCHECKED_CAST")
    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as RemoteWebDriver).executeTLQuery(
            by = ByType.LabelText,
            textMatch = text,
            matchTextBy = matchTextBy,
            options = mapOf(
                "selector" to selector,
                "exact" to exact,
                "normalizer" to normalizer,
            )
        ) as List<WebElement>
}
