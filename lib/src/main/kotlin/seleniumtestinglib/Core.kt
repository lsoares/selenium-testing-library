package seleniumtestinglib

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import seleniumtestinglib.CurrentValue.AsType
import seleniumtestinglib.TextMatch.JsExpression
import seleniumtestinglib.TextMatch.JsString
import java.util.regex.Pattern
import org.openqa.selenium.By as SeleniumBy


abstract class TL(
    private val by: String,
    private val textMatch: TextMatch,
    private val options: Map<String, Any?> = emptyMap()
) : SeleniumBy() {

    @Suppress("unchecked_cast")
    override fun findElements(context: SearchContext): List<WebElement> {
        context.jsExecutor.ensureScript("testing-library.js", "window.screen?.queryAllByTestId")
        val script = buildString {
            append("return window.screen.queryAllBy$by(")
            append(textMatch.escaped)
            options
                .filterValues { it != null }
                .takeIf(Map<String, Any?>::isNotEmpty)
                ?.escaped
                ?.let { append(", $it") }
            append(")")
        }
        return context.jsExecutor.executeScript(script) as List<WebElement>
    }

    private val SearchContext.jsExecutor get() = (getWebDriver(this) as JavascriptExecutor)

    override fun toString(): String {
        val entries = options.filterValues { it != null }.entries
        val prefix = if (entries.isEmpty()) "" else ", "
        return "By$by($textMatch$prefix${entries.joinToString { "${it.key}: ${it.value}" }})"
    }

    companion object By {
        /**
         * https://testing-library.com/docs/queries/byalttext
         */
        @JvmOverloads
        @JvmStatic
        fun altText(text: String, exact: Boolean? = null, normalizer: JsExpression? = null) =
            object : TL(
                by = "AltText",
                textMatch = text.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun altText(text: Pattern, exact: Boolean? = null, normalizer: JsExpression? = null) =
            object : TL(
                by = "AltText",
                textMatch = text.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun altText(text: JsExpression, exact: Boolean? = null, normalizer: JsExpression? = null) =
            object : TL(
                by = "AltText",
                textMatch = text,
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        /**
         * https://testing-library.com/docs/queries/bydisplayvalue
         */
        @JvmOverloads
        @JvmStatic
        fun displayValue(value: String, exact: Boolean? = null, normalizer: JsExpression? = null) =
            object : TL(
                by = "DisplayValue",
                textMatch = value.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun displayValue(value: Pattern, exact: Boolean? = null, normalizer: JsExpression? = null) =
            object : TL(
                by = "DisplayValue",
                textMatch = value.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun displayValue(value: JsExpression, exact: Boolean? = null, normalizer: JsExpression? = null) =
            object : TL(
                by = "DisplayValue",
                textMatch = value,
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        /**
         * https://testing-library.com/docs/queries/bylabeltext
         */
        @JvmOverloads
        @JvmStatic
        fun labelText(
            text: String,
            exact: Boolean? = null,
            selector: String? = null,
            normalizer: JsExpression? = null
        ) =
            object : TL(
                by = "LabelText",
                textMatch = text.asJsString(),
                options = mapOf(
                    "exact" to exact,
                    "normalizer" to normalizer,
                    "selector" to selector
                ),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun labelText(
            text: Pattern,
            exact: Boolean? = null,
            selector: String? = null,
            normalizer: JsExpression? = null
        ) =
            object : TL(
                by = "LabelText",
                textMatch = text.asJsExpression(),
                options = mapOf(
                    "exact" to exact,
                    "normalizer" to normalizer,
                    "selector" to selector
                ),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun labelText(
            text: JsExpression,
            exact: Boolean? = null,
            selector: String? = null,
            normalizer: JsExpression? = null
        ) =
            object : TL(
                by = "LabelText",
                textMatch = text,
                options = mapOf(
                    "exact" to exact,
                    "normalizer" to normalizer,
                    "selector" to selector
                ),
            ) {}

        /**
         *  https://testing-library.com/docs/queries/byplaceholdertext
         */
        @JvmOverloads
        @JvmStatic
        fun placeholderText(text: String, exact: Boolean? = null, normalizer: JsExpression? = null) =
            object : TL(
                by = "PlaceholderText",
                textMatch = text.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun placeholderText(text: Pattern, exact: Boolean? = null, normalizer: JsExpression? = null) =
            object : TL(
                by = "PlaceholderText",
                textMatch = text.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun placeholderText(text: JsExpression, exact: Boolean? = null, normalizer: JsExpression? = null) =
            object : TL(
                by = "PlaceholderText",
                textMatch = text,
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        /**
         * https://testing-library.com/docs/queries/bytestid
         */
        @JvmOverloads
        @JvmStatic
        fun testId(text: String, exact: Boolean? = null, normalizer: JsExpression? = null) =
            object : TL(
                by = "TestId",
                textMatch = text.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun testId(text: Pattern, exact: Boolean? = null, normalizer: JsExpression? = null) =
            object : TL(
                by = "TestId",
                textMatch = text.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun testId(text: JsExpression, exact: Boolean? = null, normalizer: JsExpression? = null) =
            object : TL(
                by = "TestId",
                textMatch = text,
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}


        /**
         * https://testing-library.com/docs/queries/bytext
         */
        @JvmOverloads
        @JvmStatic
        fun text(
            text: String,
            selector: String? = null,
            exact: Boolean? = null,
            ignore: String? = null,
            normalizer: JsExpression? = null
        ) =
            object : TL(
                by = "Text",
                textMatch = text.asJsString(),
                options = mapOf(
                    "exact" to exact,
                    "normalizer" to normalizer,
                    "selector" to selector,
                    "ignore" to ignore
                ),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun text(
            text: Pattern,
            selector: String? = null,
            exact: Boolean? = null,
            ignore: String? = null,
            normalizer: JsExpression? = null
        ) =
            object : TL(
                by = "Text",
                textMatch = text.asJsExpression(),
                options = mapOf(
                    "exact" to exact,
                    "normalizer" to normalizer,
                    "selector" to selector,
                    "ignore" to ignore
                ),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun text(
            text: JsExpression,
            selector: String? = null,
            exact: Boolean? = null,
            ignore: String? = null,
            normalizer: JsExpression? = null
        ) =
            object : TL(
                by = "Text",
                textMatch = text,
                options = mapOf(
                    "exact" to exact,
                    "normalizer" to normalizer,
                    "selector" to selector,
                    "ignore" to ignore
                ),
            ) {}

        /**
         * https://testing-library.com/docs/queries/bytitle
         */
        @JvmOverloads
        @JvmStatic
        fun title(title: String, exact: Boolean? = null, normalizer: JsExpression? = null) =
            object : TL(
                by = "Title",
                textMatch = title.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun title(title: Pattern, exact: Boolean? = null, normalizer: JsExpression? = null) =
            object : TL(
                by = "Title",
                textMatch = title.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun title(title: JsExpression, exact: Boolean? = null, normalizer: JsExpression? = null) =
            object : TL(
                by = "Title",
                textMatch = title,
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        /**
         * https://testing-library.com/docs/queries/byrole
         */
        @JvmOverloads
        @JvmStatic
        fun role(
            role: Role,
            name: String? = null,
            nameAsRegex: Pattern? = null,
            nameAsFunction: JsExpression? = null,
            description: String? = null,
            descriptionAsRegex: Pattern? = null,
            descriptionAsFunction: JsExpression? = null,
            hidden: Boolean? = null,
            normalizer: JsExpression? = null,
            selected: Boolean? = null,
            busy: Boolean? = null,
            checked: Boolean? = null,
            pressed: Boolean? = null,
            suggest: Boolean? = null,
            current: Current? = null,
            currentAsBoolean: Boolean? = null,
            expanded: Boolean? = null,
            level: Int? = null,
            value: Value? = null,
            queryFallbacks: Boolean? = null,
        ): SeleniumBy {
            require(listOfNotNull(name, nameAsFunction, nameAsRegex).size <= 1) { "Please provide name just once." }
            require(listOfNotNull(description, descriptionAsFunction, descriptionAsRegex).size <= 1) {
                "Please provide description just once."
            }
            val roleOps = roleOptions(role)
            name?.let { roleOps.name(it.asJsString()) }
            nameAsFunction?.let(roleOps::name)
            nameAsRegex?.let { roleOps.name(it.asJsExpression()) }
            description?.let { roleOps.description(it.asJsString()) }
            descriptionAsFunction?.let(roleOps::description)
            descriptionAsRegex?.let { roleOps.description(it.asJsExpression()) }
            hidden?.let(roleOps::hidden)
            normalizer?.let(roleOps::normalizer)
            selected?.let(roleOps::selected)
            busy?.let(roleOps::busy)
            checked?.let(roleOps::checked)
            pressed?.let(roleOps::pressed)
            suggest?.let(roleOps::suggest)
            expanded?.let(roleOps::expanded)
            value?.let(roleOps::value)
            current?.let(roleOps::current)
            currentAsBoolean?.let(roleOps::current)
            level?.let(roleOps::level)
            queryFallbacks?.let(roleOps::queryFallbacks)
            return role(roleOps)
        }

        @JvmStatic
        fun role(options: RoleOptions) = object : TL(
            by = "Role",
            textMatch = options.role.name.lowercase().asJsString(),
            options = options
        ) {}
    }
}

fun roleOptions(role: Role) = RoleOptions(role)

class RoleOptions internal constructor(val role: Role) : MutableMap<String, Any> by mutableMapOf() {
    fun name(name: TextMatch) = apply { this["name"] = name }
    fun description(description: TextMatch) = apply { this["description"] = description }
    fun hidden(hidden: Boolean) = apply { this["hidden"] = hidden }
    fun normalizer(normalizer: JsExpression) = apply { this["normalizer"] = normalizer }
    fun selected(selected: Boolean) = apply { this["selected"] = selected }
    fun busy(busy: Boolean) = apply { this["busy"] = busy }
    fun checked(checked: Boolean) = apply { this["checked"] = checked }
    fun pressed(pressed: Boolean) = apply { this["pressed"] = pressed }
    fun suggest(suggest: Boolean) = apply { this["suggest"] = suggest }
    fun current(current: Current) = apply { this["current"] = AsType(current).value }
    fun current(current: Boolean) = apply { this["current"] = current }
    fun expanded(expanded: Boolean) = apply { this["expanded"] = expanded }
    fun level(level: Int) = apply { this["level"] = level }
    fun value(value: Value) = apply { this["value"] = value.toMap() }
    fun queryFallbacks(queryFallbacks: Boolean) = apply { this["queryFallbacks"] = queryFallbacks }
}

sealed class TextMatch(open val value: String) {
    class JsString(override val value: String) : TextMatch(value)
    class JsExpression(override val value: String) : TextMatch(value)

    override fun toString() = value
}

fun String.asJsExpression() = JsExpression(this)
fun String.asJsString() = JsString(this)

private fun Pattern.asJsExpression(): JsExpression {
    val jsFlags = buildString {
        if (flags() and Pattern.CASE_INSENSITIVE != 0) append('i')
        if (flags() and RegexOption.MULTILINE.value != 0) append('m')
        if (flags() and Pattern.DOTALL != 0) append('s')
        if (flags() and RegexOption.COMMENTS.value != 0) append('x')
        if (flags() and Pattern.UNICODE_CASE != 0) append('u')
    }
    return JsExpression("/${pattern()}/$jsFlags")
}

private val String.quoted get() = "'${replace("'", "\\'")}'"
private val Any?.escaped: Any?
    get() = when (this) {
        is JsString -> value.quoted
        is JsExpression -> value
        is String -> quoted
        is Map<*, *> -> entries.joinToString(prefix = "{ ", postfix = " }") {
            "${it.key}: ${it.value?.escaped}"
        }

        else -> this
    }

class Value(
    private val min: Int? = null,
    private val max: Int? = null,
    private val now: Int? = null,
    private val text: String? = null,
    private val textAsRegex: Pattern? = null,
    private val textAsFunction: JsExpression? = null,
) {

    init {
        require(listOfNotNull(text, textAsRegex, textAsFunction).size <= 1) { "Please provide text just once." }
    }

    internal fun toMap() =
        mapOf(
            "min" to min,
            "max" to max,
            "now" to now,
            "text" to (text ?: textAsRegex?.asJsExpression() ?: textAsFunction?.value?.asJsExpression())
        ).filterValues { it != null }
}

/*
 * https://www.w3.org/TR/wai-aria-1.2/#aria-current
 */
internal sealed class CurrentValue(open val value: Any) {
    override fun toString() = value.toString()
    internal class AsType(value: Current) : CurrentValue(value.name.lowercase().asJsString())
    internal class AsBool(value: Boolean) : CurrentValue(value)
}

@Suppress("UNUSED")
enum class Current {
    Page, Step, Location, Date, Time
}

/*
 * https://www.w3.org/TR/wai-aria-1.2/#role_definitions
 */
@Suppress("UNUSED")
enum class Role {
    Alert, AlertDialog, Application, Article, Banner, Button, Cell, CheckBox, ColumnHeader, ComboBox, Command, Comment,
    Complementary, Composite, ContentInfo, Definition, Dialog, Directory, Document, Feed, Figure, Form, Generic, Grid,
    GridCell, Group, Heading, Img, Input, Landmark, Link, List, ListBox, ListItem, Log, Main, Mark, Marquee, Math, Menu,
    MenuBar, MenuItem, MenuItemCheckBox, MenuItemRadio, Meter, Navigation, None, Note, Option, Presentation,
    ProgressBar, Radio, RadioGroup, Range, Region, RoleType, Row, RowGroup, RowHeader, ScrollBar, Search, SearchBox,
    Section, SectionHead, Select, Separator, Slider, SpinButton, Status, Structure, Suggestion, Switch, Tab, Table,
    TabList, TabPanel, Term, TextBox, Timer, Toolbar, Tooltip, Tree, TreeGrid, TreeItem, Widget, Window
}