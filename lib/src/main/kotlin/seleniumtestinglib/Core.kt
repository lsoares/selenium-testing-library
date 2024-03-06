package seleniumtestinglib

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import seleniumtestinglib.TextMatch.JsExpression
import seleniumtestinglib.TextMatch.JsString
import java.util.regex.Pattern
import org.openqa.selenium.By as SeleniumBy


class TL {
    companion object By {
        /**
         * https://testing-library.com/docs/queries/byalttext
         */
        @JvmStatic
        fun altText(text: String, exact: Boolean? = null, normalizer: JsExpression? = null) =
            ByAltText(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun altText(text: Pattern, normalizer: JsExpression? = null) =
            ByAltText(text)
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun altText(text: JsExpression, normalizer: JsExpression? = null) =
            ByAltText(text).apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun altText(text: String) = ByAltText(text)

        @JvmStatic
        fun altText(text: Pattern) = ByAltText(text)

        @JvmStatic
        fun altText(text: JsExpression) = ByAltText(text)

        /**
         * https://testing-library.com/docs/queries/bydisplayvalue
         */
        @JvmStatic
        fun displayValue(value: String, exact: Boolean? = null, normalizer: JsExpression? = null) =
            ByDisplayValue(value)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun displayValue(value: Pattern, normalizer: JsExpression? = null) =
            ByDisplayValue(value).apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun displayValue(value: JsExpression, normalizer: JsExpression? = null) =
            ByDisplayValue(value).apply { normalizer?.let(::normalizer) }


        @JvmStatic
        fun displayValue(value: String) = ByDisplayValue(value)

        @JvmStatic
        fun displayValue(value: Pattern) = ByDisplayValue(value)

        @JvmStatic
        fun displayValue(value: JsExpression) = ByDisplayValue(value)

        /**
         * https://testing-library.com/docs/queries/bylabeltext
         */
        @JvmStatic
        fun labelText(
            text: String,
            exact: Boolean? = null,
            selector: String? = null,
            normalizer: JsExpression? = null
        ) =
            ByLabelText(text)
                .apply { exact?.let(::exact) }
                .apply { selector?.let(::selector) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun labelText(
            text: Pattern,
            selector: String? = null,
            normalizer: JsExpression? = null
        ) =
            ByLabelText(text)
                .apply { selector?.let(::selector) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun labelText(
            text: JsExpression,
            selector: String? = null,
            normalizer: JsExpression? = null
        ) =
            ByLabelText(text)
                .apply { selector?.let(::selector) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun labelText(text: String) = ByLabelText(text)

        @JvmStatic
        fun labelText(text: Pattern) = ByLabelText(text)

        @JvmStatic
        fun labelText(text: JsExpression) = ByLabelText(text)

        /**
         *  https://testing-library.com/docs/queries/byplaceholdertext
         */
        @JvmStatic
        fun placeholderText(text: String, exact: Boolean? = null, normalizer: JsExpression? = null) =
            ByPlaceholderText(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun placeholderText(text: Pattern, normalizer: JsExpression? = null) =
            ByPlaceholderText(text).apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun placeholderText(text: JsExpression, normalizer: JsExpression? = null) =
            ByPlaceholderText(text).apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun placeholderText(text: String) = ByPlaceholderText(text)

        @JvmStatic
        fun placeholderText(text: Pattern) = ByPlaceholderText(text)

        @JvmStatic
        fun placeholderText(text: JsExpression) = ByPlaceholderText(text)

        /**
         * https://testing-library.com/docs/queries/bytestid
         */
        @JvmStatic
        fun testId(text: String, exact: Boolean? = null, normalizer: JsExpression? = null) =
            ByTestId(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun testId(text: Pattern, normalizer: JsExpression? = null) =
            ByTestId(text).apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun testId(text: JsExpression, normalizer: JsExpression? = null) =
            ByTestId(text).apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun testId(text: String) = ByTestId(text)

        @JvmStatic
        fun testId(text: Pattern) = ByTestId(text)

        @JvmStatic
        fun testId(text: JsExpression) = ByTestId(text)

        /**
         * https://testing-library.com/docs/queries/bytext
         */
        @JvmStatic
        fun text(
            text: String,
            selector: String? = null,
            exact: Boolean? = null,
            ignore: String? = null,
            normalizer: JsExpression? = null
        ) =
            ByText(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }
                .apply { selector?.let(::selector) }
                .apply { ignore?.let(::ignore) }

        @JvmStatic
        fun text(
            text: Pattern,
            selector: String? = null,
            ignore: String? = null,
            normalizer: JsExpression? = null
        ) =
            ByText(text)
                .apply { normalizer?.let(::normalizer) }
                .apply { selector?.let(::selector) }
                .apply { ignore?.let(::ignore) }

        @JvmStatic
        fun text(
            text: JsExpression,
            selector: String? = null,
            ignore: String? = null,
            normalizer: JsExpression? = null
        ) =
            ByText(text)
                .apply { normalizer?.let(::normalizer) }
                .apply { selector?.let(::selector) }
                .apply { ignore?.let(::ignore) }

        @JvmStatic
        fun text(text: String) = ByText(text)

        @JvmStatic
        fun text(text: Pattern) = ByText(text)

        @JvmStatic
        fun text(text: JsExpression) = ByText(text)

        /**
         * https://testing-library.com/docs/queries/bytitle
         */
        @JvmStatic
        fun title(title: String, exact: Boolean? = null, normalizer: JsExpression? = null) =
            ByTitle(title)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun title(title: Pattern, normalizer: JsExpression? = null) =
            ByTitle(title).apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun title(title: JsExpression, normalizer: JsExpression? = null) =
            ByTitle(title).apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun title(title: String) = ByTitle(title)

        @JvmStatic
        fun title(title: Pattern) = ByTitle(title)

        @JvmStatic
        fun title(title: JsExpression) = ByTitle(title)

        /**
         * https://testing-library.com/docs/queries/byrole
         */
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
        ) = role(role).apply {
            require(listOfNotNull(name, nameAsFunction, nameAsRegex).size <= 1) { "Please provide name just once." }
            require(listOfNotNull(description, descriptionAsFunction, descriptionAsRegex).size <= 1) {
                "Please provide description just once."
            }
            require(listOfNotNull(currentAsBoolean, current).size <= 1) { "Please provide current just once." }
            name?.let(::name)
            name?.let(::name)
            nameAsFunction?.let(::name)
            nameAsRegex?.let(::name)
            description?.let(::description)
            descriptionAsFunction?.let(::description)
            descriptionAsRegex?.let(::description)
            hidden?.let(::hidden)
            normalizer?.let(::normalizer)
            selected?.let(::selected)
            busy?.let(::busy)
            checked?.let(::checked)
            pressed?.let(::pressed)
            suggest?.let(::suggest)
            expanded?.let(::expanded)
            value?.let(::value)
            current?.let(::current)
            currentAsBoolean?.let(::current)
            level?.let(::level)
            queryFallbacks?.let(::queryFallbacks)
        }

        @JvmStatic
        fun role(role: Role) = ByRole(role)
    }
}

abstract class TLBy(private val textMatch: TextMatch) : SeleniumBy() {

    private val by = javaClass.simpleName
    private val options = mutableMapOf<String, Any>()
    protected fun set(key: String, value: Any) = apply { options[key] = value }

    @Suppress("unchecked_cast")
    override fun findElements(context: SearchContext): List<WebElement> {
        val jsExecutor = (getWebDriver(context) as JavascriptExecutor)
        jsExecutor.ensureScript("testing-library.js", "window.__TL__?.queryAllByTestId")
        return jsExecutor.executeScript(buildString {
            append("return window.__TL__.queryAll$by(")
            (context as? WebDriver)?.let {
                append("document.body, ")
            }
            (context as? WebElement)?.let {
                append("arguments[0], ")
            }
            append(textMatch.escaped)
            options.takeIf { it.isNotEmpty() }
                ?.escaped
                ?.let { append(", $it") }
            append(")")
        }, context as? WebElement) as List<WebElement>
    }

    override fun toString(): String {
        val prefix = if (options.entries.isEmpty()) "" else ", "
        return "$by($textMatch$prefix${options.entries.joinToString { "${it.key}: ${it.value}" }})"
    }
}

class ByAltText(text: TextMatch) : TLBy(text) {
    constructor(value: String) : this(value.asJsString())
    constructor(value: Pattern) : this(value.asJsExpression())

    fun exact(exact: Boolean) = apply { set("exact", exact) }
    fun normalizer(normalizer: JsExpression) = apply { set("normalizer", normalizer) }
}

class ByDisplayValue(value: TextMatch) : TLBy(value) {
    constructor(value: String) : this(value.asJsString())
    constructor(value: Pattern) : this(value.asJsExpression())

    fun exact(exact: Boolean) = apply { set("exact", exact) }
    fun normalizer(normalizer: JsExpression) = apply { set("normalizer", normalizer) }
}

class ByLabelText(value: TextMatch) : TLBy(value) {
    constructor(value: String) : this(value.asJsString())
    constructor(value: Pattern) : this(value.asJsExpression())

    fun exact(exact: Boolean) = apply { set("exact", exact) }
    fun selector(selector: String) = apply { set("selector", selector) }
    fun normalizer(normalizer: JsExpression) = apply { set("normalizer", normalizer) }
}

class ByPlaceholderText(text: TextMatch) : TLBy(text) {
    constructor(text: String) : this(text.asJsString())
    constructor(text: Pattern) : this(text.asJsExpression())

    fun exact(exact: Boolean) = apply { set("exact", exact) }
    fun normalizer(normalizer: JsExpression) = apply { set("normalizer", normalizer) }
}

class ByTestId(text: TextMatch) : TLBy(text) {
    constructor(text: String) : this(text.asJsString())
    constructor(text: Pattern) : this(text.asJsExpression())

    fun exact(exact: Boolean) = apply { set("exact", exact) }
    fun normalizer(normalizer: JsExpression) = apply { set("normalizer", normalizer) }
}

class ByText(text: TextMatch) : TLBy(text) {
    constructor(text: String) : this(text.asJsString())
    constructor(text: Pattern) : this(text.asJsExpression())

    fun exact(exact: Boolean) = apply { set("exact", exact) }
    fun ignore(ignore: String) = apply { set("ignore", ignore) }
    fun ignore(ignore: Boolean) = apply { set("ignore", ignore) }
    fun selector(selector: String) = apply { set("selector", selector) }
    fun normalizer(normalizer: JsExpression) = apply { set("normalizer", normalizer) }
}

class ByTitle(text: TextMatch) : TLBy(text) {
    constructor(text: String) : this(text.asJsString())
    constructor(text: Pattern) : this(text.asJsExpression())

    fun exact(exact: Boolean) = apply { set("exact", exact) }
    fun normalizer(normalizer: JsExpression) = apply { set("normalizer", normalizer) }
}

class ByRole private constructor(role: TextMatch) : TLBy(role) {
    constructor(role: Role) : this(role.name.lowercase().asJsString())

    fun name(name: String) = apply { set("name", name) }
    fun name(name: Pattern) = apply { set("name", name.asJsExpression()) }
    fun name(name: JsExpression) = apply { set("name", name) }
    fun description(description: String) = apply { set("description", description) }
    fun description(description: Pattern) = apply { set("description", description.asJsExpression()) }
    fun description(description: JsExpression) = apply { set("description", description) }
    fun hidden(hidden: Boolean) = apply { set("hidden", hidden) }
    fun selected(selected: Boolean) = apply { set("selected", selected) }
    fun busy(busy: Boolean) = apply { set("busy", busy) }
    fun checked(checked: Boolean) = apply { set("checked", checked) }
    fun pressed(pressed: Boolean) = apply { set("pressed", pressed) }
    fun suggest(suggest: Boolean) = apply { set("suggest", suggest) }
    fun current(current: Current) = apply { set("current", current.name.lowercase().asJsString()) }
    fun current(current: Boolean) = apply { set("current", current) }
    fun expanded(expanded: Boolean) = apply { set("expanded", expanded) }
    fun level(level: Int) = apply { set("level", level) }
    fun value(value: Value) = apply { set("value", value.toMap()) }
    fun queryFallbacks(queryFallbacks: Boolean) = apply { set("queryFallbacks", queryFallbacks) }
    fun normalizer(normalizer: JsExpression) = apply { set("normalizer", normalizer) }
}

sealed class TextMatch(open val value: String) {
    internal class JsString(override val value: String) : TextMatch(value)
    class JsExpression(override val value: String) : TextMatch(value)

    override fun toString() = value
}

fun String.asJsExpression() = JsExpression(this)
private fun String.asJsString() = JsString(this)

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
