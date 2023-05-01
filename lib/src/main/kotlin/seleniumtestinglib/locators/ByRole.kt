package seleniumtestinglib.locators

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.queries.ByType
import seleniumtestinglib.queries.JsType
import seleniumtestinglib.queries.JsType.Companion.asJsString
import seleniumtestinglib.queries.executeTLQuery

/**
 * https://testing-library.com/docs/queries/byrole
 */
data class ByRole(
    private val role: String,
    private val hidden: Boolean? = null,
    private val name: JsType? = null,
    private val description: JsType? = null,
    private val normalizer: String? = null,
    private val selected: Boolean? = null,
    private val checked: Boolean? = null,
    private val pressed: Boolean? = null,
    private val current: Boolean? = null,
    private val expanded: Boolean? = null,
    private val queryFallbacks: Boolean? = null,
    private val level: Int? = null,
) : By() {

    @Suppress("UNCHECKED_CAST")
    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as RemoteWebDriver).executeTLQuery(
            by = ByType.Role,
            textMatch = role.asJsString(),
            options = mapOf(
                "hidden" to hidden,
                "name" to name,
                "description" to description,
                "selected" to selected,
                // TODO: busy
                "checked" to checked,
                "pressed" to pressed,
                // TODO: suggest
                "current" to current,
                "expanded" to expanded,
                "queryFallbacks" to queryFallbacks,
                "level" to level,
                // TODO: value
            ),
        ) as List<WebElement>
}
