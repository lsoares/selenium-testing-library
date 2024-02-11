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
    private val name: JsType? = null,
    private val description: JsType? = null,
    private val hidden: Boolean? = null,
    private val normalizer: String? = null,
    private val selected: Boolean? = null,
    private val busy: Boolean? = null,
    private val checked: Boolean? = null,
    private val pressed: Boolean? = null,
    private val suggest: Boolean? = null,
    private val current: Boolean? = null,
    private val expanded: Boolean? = null,
    private val queryFallbacks: Boolean? = null,
    private val level: Int? = null,
    private val value: Value? = null,
) : By() {

    constructor(
        role: String,
        name: String? = null,
        description: String? = null,
    ) : this(
        role = role,
        name = name?.asJsString(),
        description = description?.asJsString(),
    )

    fun with(
        hidden: Boolean? = null,
        normalizer: String? = null,
        selected: Boolean? = null,
        busy: Boolean? = null,
        checked: Boolean? = null,
        pressed: Boolean? = null,
        suggest: Boolean? = null,
        current: Boolean? = null,
        expanded: Boolean? = null,
        queryFallbacks: Boolean? = null,
        level: Int? = null,
        value: Value? = null,
    ) = copy(
        hidden = hidden,
        normalizer = normalizer,
        selected = selected,
        busy = busy,
        checked = checked,
        pressed = pressed,
        suggest = suggest,
        current = current,
        expanded = expanded,
        queryFallbacks = queryFallbacks,
        level = level,
        value = value,
    )


    override fun findElements(context: SearchContext): List<WebElement> =
        (getWebDriver(context) as RemoteWebDriver).executeTLQuery(
            by = ByType.Role,
            textMatch = role.asJsString(),
            options = mapOf(
                "hidden" to hidden,
                "name" to name,
                "description" to description,
                "selected" to selected,
                "busy" to busy,
                "checked" to checked,
                "pressed" to pressed,
                "suggest" to suggest,
                "current" to current,
                "expanded" to expanded,
                "queryFallbacks" to queryFallbacks,
                "level" to level,
                "value" to value?.toMap(),
            ),
        )

    data class Value(val min: Int? = null, val max: Int? = null, val now: Int? = null, val text: JsType? = null)

    private fun Value.toMap() =
        mapOf("min" to min, "max" to max, "now" to now, "text" to text).filterValues { it != null }
}
