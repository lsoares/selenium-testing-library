package seleniumtestinglib.locators

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.coreapi.ByType
import seleniumtestinglib.coreapi.MatchType
import seleniumtestinglib.coreapi.executeTLQuery

/**
 * https://testing-library.com/docs/queries/byrole
 */
data class ByRole(
    private val role: String,
    private val matchTextBy: MatchType = MatchType.STRING,
    private val exact: Boolean? = null,
    private val hidden: Boolean? = null,
    private val name: String? = null,
    private val description: String? = null,
    private val matchDescriptionBy: MatchType = MatchType.STRING,
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
            textMatch = role,
            matchTextBy = matchTextBy,
            matchDescriptionBy = matchDescriptionBy,
            options = mapOf(
                "exact" to exact,
                "name" to name,
                "hidden" to hidden,
                "description" to description,
                "normalizer" to normalizer,
                "selected" to selected,
                "checked" to checked,
                "pressed" to pressed,
                "current" to current,
                "expanded" to expanded,
                "queryFallbacks" to queryFallbacks,
                "level" to level,
            ),
        ) as List<WebElement>
}
