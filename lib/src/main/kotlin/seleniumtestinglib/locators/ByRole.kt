package seleniumtestinglib.locators

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.coreapi.ByType
import seleniumtestinglib.coreapi.TextMatch
import seleniumtestinglib.coreapi.executeTLQuery

/**
 * https://testing-library.com/docs/queries/byrole
 */
data class ByRole(
    private val role: TextMatch,
    private val exact: Boolean? = null,
    private val hidden: Boolean? = null,
    private val name: TextMatch? = null,
    private val description: TextMatch? = null,
    private val normalizer: String? = null,
    private val selected: Boolean? = null,
    private val checked: Boolean? = null,
    private val pressed: Boolean? = null,
    private val current: Boolean? = null,
    private val expanded: Boolean? = null,
    private val queryFallbacks: Boolean? = null,
    private val level: Int? = null,
) : By() {

    constructor(
        role: String,
        exact: Boolean? = null,
        hidden: Boolean? = null,
        name: TextMatch? = null,
        description: TextMatch? = null,
        normalizer: String? = null,
        selected: Boolean? = null,
        checked: Boolean? = null,
        pressed: Boolean? = null,
        current: Boolean? = null,
        expanded: Boolean? = null,
        queryFallbacks: Boolean? = null,
        level: Int? = null,
    ) : this(
        role = TextMatch.String(role),
        exact = exact,
        hidden = hidden,
        name = name,
        description = description,
        normalizer = normalizer,
        selected = selected,
        checked = checked,
        pressed = pressed,
        current = current,
        expanded = expanded,
        queryFallbacks = queryFallbacks,
        level = level,
    )

    @Suppress("UNCHECKED_CAST")
    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as RemoteWebDriver).executeTLQuery(
            by = ByType.Role,
            textMatch = role,
            options = mapOf(
                "exact" to exact,
                "name" to name,
                "hidden" to hidden,
                "description" to description,
                "normalizer" to normalizer?.let { TextMatch.Function(normalizer) },
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
