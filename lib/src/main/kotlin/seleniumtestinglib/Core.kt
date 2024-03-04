package seleniumtestinglib

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.SearchContext
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
            AltTextOptions(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun altText(text: Pattern, exact: Boolean? = null, normalizer: JsExpression? = null) =
            AltTextOptions(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun altText(text: JsExpression, exact: Boolean? = null, normalizer: JsExpression? = null) =
            AltTextOptions(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun altText(text: String) = AltTextOptions(text)

        /**
         * https://testing-library.com/docs/queries/bydisplayvalue
         */
        @JvmStatic
        fun displayValue(value: String, exact: Boolean? = null, normalizer: JsExpression? = null) =
            DisplayValueOptions(value)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun displayValue(value: Pattern, exact: Boolean? = null, normalizer: JsExpression? = null) =
            DisplayValueOptions(value)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun displayValue(value: JsExpression, exact: Boolean? = null, normalizer: JsExpression? = null) =
            DisplayValueOptions(value)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }


        @JvmStatic
        fun displayValue(value: String) = DisplayValueOptions(value)

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
            LabelTextOptions(text)
                .apply { exact?.let(::exact) }
                .apply { selector?.let { selector(it) } }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun labelText(
            text: Pattern,
            exact: Boolean? = null,
            selector: String? = null,
            normalizer: JsExpression? = null
        ) =
            LabelTextOptions(text)
                .apply { exact?.let(::exact) }
                .apply { selector?.let { selector(it) } }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun labelText(
            text: JsExpression,
            exact: Boolean? = null,
            selector: String? = null,
            normalizer: JsExpression? = null
        ) =
            LabelTextOptions(text)
                .apply { exact?.let(::exact) }
                .apply { selector?.let(::selector) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun labelText(text: String) = LabelTextOptions(text)

        /**
         *  https://testing-library.com/docs/queries/byplaceholdertext
         */
        @JvmStatic
        fun placeholderText(text: String, exact: Boolean? = null, normalizer: JsExpression? = null) =
            PlaceholderTextOptions(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun placeholderText(text: Pattern, exact: Boolean? = null, normalizer: JsExpression? = null) =
            PlaceholderTextOptions(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun placeholderText(text: JsExpression, exact: Boolean? = null, normalizer: JsExpression? = null) =
            PlaceholderTextOptions(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun placeholderText(text: String) = PlaceholderTextOptions(text)

        /**
         * https://testing-library.com/docs/queries/bytestid
         */
        @JvmStatic
        fun testId(text: String, exact: Boolean? = null, normalizer: JsExpression? = null) =
            TestIdOptions(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun testId(text: Pattern, exact: Boolean? = null, normalizer: JsExpression? = null) =
            TestIdOptions(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun testId(text: JsExpression, exact: Boolean? = null, normalizer: JsExpression? = null) =
            TestIdOptions(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun testId(text: String) = TestIdOptions(text)

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
            TextOptions(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }
                .apply { selector?.let(::selector) }
                .apply { ignore?.let { ignore(it) } }

        @JvmStatic
        fun text(
            text: Pattern,
            selector: String? = null,
            exact: Boolean? = null,
            ignore: String? = null,
            normalizer: JsExpression? = null
        ) =
            TextOptions(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }
                .apply { selector?.let { selector(it) } }
                .apply { ignore?.let(::ignore) }

        @JvmStatic
        fun text(
            text: JsExpression,
            selector: String? = null,
            exact: Boolean? = null,
            ignore: String? = null,
            normalizer: JsExpression? = null
        ) =
            TextOptions(text)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }
                .apply { selector?.let { selector(it) } }
                .apply { ignore?.let { ignore(it) } }

        @JvmStatic
        fun text(text: String) = TextOptions(text)

        /**
         * https://testing-library.com/docs/queries/bytitle
         */
        @JvmStatic
        fun title(title: String, exact: Boolean? = null, normalizer: JsExpression? = null) =
            TitleOptions(title)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun title(title: Pattern, exact: Boolean? = null, normalizer: JsExpression? = null) =
            TitleOptions(title)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }


        @JvmStatic
        fun title(title: JsExpression, exact: Boolean? = null, normalizer: JsExpression? = null) =
            TitleOptions(title)
                .apply { exact?.let(::exact) }
                .apply { normalizer?.let(::normalizer) }

        @JvmStatic
        fun title(title: String) = TitleOptions(title)

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
        ) = role(role).let {
            require(listOfNotNull(name, nameAsFunction, nameAsRegex).size <= 1) { "Please provide name just once." }
            require(listOfNotNull(description, descriptionAsFunction, descriptionAsRegex).size <= 1) {
                "Please provide description just once."
            }
            name?.let(it::name)
            name?.let(it::name)
            nameAsFunction?.let(it::name)
            nameAsRegex?.let(it::name)
            description?.let(it::description)
            descriptionAsFunction?.let(it::description)
            descriptionAsRegex?.let(it::description)
            hidden?.let(it::hidden)
            normalizer?.let(it::normalizer)
            selected?.let(it::selected)
            busy?.let(it::busy)
            checked?.let(it::checked)
            pressed?.let(it::pressed)
            suggest?.let(it::suggest)
            expanded?.let(it::expanded)
            value?.let(it::value)
            current?.let(it::current)
            currentAsBoolean?.let(it::current)
            level?.let(it::level)
            queryFallbacks?.let(it::queryFallbacks)
            it
        }

        @JvmStatic
        fun role(role: Role) = RoleOptions(role)
    }
}

abstract class TLBy(private val by: String, private val textMatch: TextMatch) :
    SeleniumBy(),
    MutableMap<String, Any> by mutableMapOf() {

    @Suppress("unchecked_cast")
    override fun findElements(context: SearchContext): List<WebElement> {
        val jsExecutor = (getWebDriver(context) as JavascriptExecutor)
        jsExecutor.ensureScript("testing-library.js", "window.__TL__?.screen?.queryAllByTestId")
        val script = buildString {
            append("return window.__TL__.screen.queryAllBy$by(")
            append(textMatch.escaped)
            this@TLBy
                .takeIf(TLBy::isNotEmpty)
                ?.escaped
                ?.let<Any, StringBuilder?> { append(", $it") }
            append(")")
        }
        return jsExecutor.executeScript(script) as List<WebElement>
    }

    fun normalizer(normalizer: JsExpression) = apply { set("normalizer", normalizer) }

    override fun toString(): String {
        val prefix = if (entries.isEmpty()) "" else ", "
        return "ByRole($textMatch$prefix${entries.joinToString { "${it.key}: ${it.value}" }})"
    }
}

class AltTextOptions internal constructor(text: TextMatch) : TLBy("AltText", text) {
    constructor(value: String) : this(value.asJsString())
    constructor(value: Pattern) : this(value.asJsExpression())

    fun exact(exact: Boolean) = apply { set("exact", exact) }
}


class DisplayValueOptions internal constructor(value: TextMatch) : TLBy("DisplayValue", value) {
    constructor(value: String) : this(value.asJsString())
    constructor(value: Pattern) : this(value.asJsExpression())

    fun exact(exact: Boolean) = apply { set("exact", exact) }
}

class LabelTextOptions internal constructor(value: TextMatch) : TLBy("LabelText", value) {
    constructor(value: String) : this(value.asJsString())
    constructor(value: Pattern) : this(value.asJsExpression())

    fun exact(exact: Boolean) = apply { set("exact", exact) }
    fun selector(selector: String) = apply { set("selector", selector) }
}

class PlaceholderTextOptions internal constructor(text: TextMatch) : TLBy("PlaceholderText", text) {
    constructor(text: String) : this(text.asJsString())
    constructor(text: Pattern) : this(text.asJsExpression())

    fun exact(exact: Boolean) = apply { set("exact", exact) }
}

class TestIdOptions internal constructor(text: TextMatch) : TLBy("TestId", text) {
    constructor(text: String) : this(text.asJsString())
    constructor(text: Pattern) : this(text.asJsExpression())

    fun exact(exact: Boolean) = apply { set("exact", exact) }
}


class TextOptions internal constructor(text: TextMatch) : TLBy("Text", text) {
    constructor(text: String) : this(text.asJsString())
    constructor(text: Pattern) : this(text.asJsExpression())

    fun exact(exact: Boolean) = apply { set("exact", exact) }
    fun ignore(ignore: String) = apply { set("ignore", ignore) }
    fun selector(selector: String) = apply { set("selector", selector) }
}


class TitleOptions internal constructor(text: TextMatch) : TLBy("Title", text) {
    constructor(text: String) : this(text.asJsString())
    constructor(text: Pattern) : this(text.asJsExpression())

    fun exact(exact: Boolean) = apply { set("exact", exact) }
}

class RoleOptions internal constructor(role: TextMatch) : TLBy("Role", role) {
    constructor(role: Role) : this(role.name.lowercase().asJsString())

    fun name(name: String) = apply { set("name", name) }
    fun name(name: Pattern) = apply { set("name", name.asJsExpression()) }
    fun name(name: TextMatch) = apply { set("name", name) }
    fun description(description: String) = apply { set("description", description) }
    fun description(description: Pattern) = apply { set("description", description.asJsExpression()) }
    fun description(description: TextMatch) = apply { set("description", description) }
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
