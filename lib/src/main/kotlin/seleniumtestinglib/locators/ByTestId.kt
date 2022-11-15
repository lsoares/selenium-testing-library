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
 * https://testing-library.com/docs/queries/bytestid
 */
data class ByTestId(
    private val text: TextMatch,
    private val normalizer: String? = null,
) : By() {

    constructor(
        text: String,
        normalizer: String? = null,
    ) : this(
        text = text.asJsString(),
        normalizer = normalizer,
    )

    @Suppress("UNCHECKED_CAST")
    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as RemoteWebDriver).executeTLQuery(
            by = ByType.TestId,
            textMatch = text,
            options = mapOf(
                "normalizer" to normalizer?.asJsFunction(),
            ),
        ) as List<WebElement>
}
