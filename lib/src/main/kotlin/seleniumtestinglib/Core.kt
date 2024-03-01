package seleniumtestinglib

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import seleniumtestinglib.CurrentValue.AsBool
import seleniumtestinglib.CurrentValue.AsType
import seleniumtestinglib.TextMatch.Companion.asJsExpression
import seleniumtestinglib.TextMatch.Companion.asJsString
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
        fun altText(text: String, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = "AltText",
                textMatch = text.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer?.asJsExpression()),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun altText(text: Pattern, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = "AltText",
                textMatch = text.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer?.asJsExpression()),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun altText(text: JsFunction, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = "AltText",
                textMatch = text.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer?.asJsExpression()),
            ) {}

        /**
         * https://testing-library.com/docs/queries/bydisplayvalue
         */
        @JvmOverloads
        @JvmStatic
        fun displayValue(value: String, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = "DisplayValue",
                textMatch = value.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer?.asJsExpression()),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun displayValue(value: Pattern, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = "DisplayValue",
                textMatch = value.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer?.asJsExpression()),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun displayValue(value: JsFunction, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = "DisplayValue",
                textMatch = value.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer?.asJsExpression()),
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
            normalizer: JsFunction? = null
        ) =
            object : TL(
                by = "LabelText",
                textMatch = text.asJsString(),
                options = mapOf(
                    "exact" to exact,
                    "normalizer" to normalizer?.asJsExpression(),
                    "selector" to selector
                ),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun labelText(
            text: Pattern,
            exact: Boolean? = null,
            selector: String? = null,
            normalizer: JsFunction? = null
        ) =
            object : TL(
                by = "LabelText",
                textMatch = text.asJsExpression(),
                options = mapOf(
                    "exact" to exact,
                    "normalizer" to normalizer?.asJsExpression(),
                    "selector" to selector
                ),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun labelText(
            text: JsFunction,
            exact: Boolean? = null,
            selector: String? = null,
            normalizer: JsFunction? = null
        ) =
            object : TL(
                by = "LabelText",
                textMatch = text.asJsExpression(),
                options = mapOf(
                    "exact" to exact,
                    "normalizer" to normalizer?.asJsExpression(),
                    "selector" to selector
                ),
            ) {}

        /**
         *  https://testing-library.com/docs/queries/byplaceholdertext
         */
        @JvmOverloads
        @JvmStatic
        fun placeholderText(text: String, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = "PlaceholderText",
                textMatch = text.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer?.asJsExpression()),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun placeholderText(text: Pattern, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = "PlaceholderText",
                textMatch = text.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer?.asJsExpression()),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun placeholderText(text: JsFunction, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = "PlaceholderText",
                textMatch = text.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer?.asJsExpression()),
            ) {}

        /**
         * https://testing-library.com/docs/queries/bytestid
         */
        @JvmOverloads
        @JvmStatic
        fun testId(text: String, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = "TestId",
                textMatch = text.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer?.asJsExpression()),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun testId(text: Pattern, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = "TestId",
                textMatch = text.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer?.asJsExpression()),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun testId(text: JsFunction, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = "TestId",
                textMatch = text.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer?.asJsExpression()),
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
            normalizer: JsFunction? = null
        ) =
            object : TL(
                by = "Text",
                textMatch = text.asJsString(),
                options = mapOf(
                    "exact" to exact,
                    "normalizer" to normalizer?.asJsExpression(),
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
            normalizer: JsFunction? = null
        ) =
            object : TL(
                by = "Text",
                textMatch = text.asJsExpression(),
                options = mapOf(
                    "exact" to exact,
                    "normalizer" to normalizer?.asJsExpression(),
                    "selector" to selector,
                    "ignore" to ignore
                ),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun text(
            text: JsFunction,
            selector: String? = null,
            exact: Boolean? = null,
            ignore: String? = null,
            normalizer: JsFunction? = null
        ) =
            object : TL(
                by = "Text",
                textMatch = text.asJsExpression(),
                options = mapOf(
                    "exact" to exact,
                    "normalizer" to normalizer?.asJsExpression(),
                    "selector" to selector,
                    "ignore" to ignore
                ),
            ) {}

        /**
         * https://testing-library.com/docs/queries/bytitle
         */
        @JvmOverloads
        @JvmStatic
        fun title(title: String, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = "Title",
                textMatch = title.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer?.asJsExpression()),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun title(title: Pattern, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = "Title",
                textMatch = title.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer?.asJsExpression()),
            ) {}

        @JvmOverloads
        @JvmStatic
        fun title(title: JsFunction, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = "Title",
                textMatch = title.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer?.asJsExpression()),
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
            nameAsFunction: JsFunction? = null,
            description: String? = null,
            descriptionAsRegex: Pattern? = null,
            descriptionAsFunction: JsFunction? = null,
            hidden: Boolean? = null,
            normalizer: JsFunction? = null,
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
        ) = object : TL(
            by = "Role",
            textMatch = role.name.lowercase().asJsString(),
            options = mapOf(
                "name" to (name?.asJsString() ?: nameAsRegex?.asJsExpression()
                ?: nameAsFunction?.asJsExpression()),
                "description" to (description?.asJsString() ?: descriptionAsRegex?.asJsExpression()
                ?: descriptionAsFunction?.asJsExpression()),
                "hidden" to hidden,
                "normalizer" to normalizer?.asJsExpression(),
                "selected" to selected,
                "busy" to busy,
                "checked" to checked,
                "pressed" to pressed,
                "suggest" to suggest,
                "expanded" to expanded,
                "value" to value?.toMap(),
                "current" to (current?.let(::AsType)?.value ?: currentAsBoolean?.let(::AsBool)),
                "level" to level,
                "queryFallbacks" to queryFallbacks,
            )
        ) {}
    }
}

class JsFunction(val value: String)

fun String.asJsFunction() = JsFunction(this)

private fun JsFunction.asJsExpression() = value.asJsExpression()


// TODO: do not expose class
sealed class TextMatch(open val value: String) {
    class JsString(override val value: String) : TextMatch(value)
    class JsExpression(override val value: String) : TextMatch(value)

    override fun toString() = value

    companion object {
        fun String.asJsExpression() = JsExpression(this)
        fun String.asJsString() = JsString(this)
    }
}

private fun Pattern.asJsExpression(): TextMatch.JsExpression {
    val jsFlags = buildString {
        if (flags() and Pattern.CASE_INSENSITIVE != 0) append('i')
        if (flags() and RegexOption.MULTILINE.value != 0) append('m')
        if (flags() and Pattern.DOTALL != 0) append('s')
        if (flags() and RegexOption.COMMENTS.value != 0) append('x')
        if (flags() and Pattern.UNICODE_CASE != 0) append('u')
    }
    return TextMatch.JsExpression("/${pattern()}/$jsFlags")
}

private val String.quoted get() = "'${replace("'", "\\'")}'"
private val Any?.escaped: Any?
    get() = when (this) {
        is TextMatch.JsString -> value.quoted
        is TextMatch.JsExpression -> value
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
    private val textAsFunction: JsFunction? = null,
) {

    init {
        require(listOfNotNull(text, textAsRegex, textAsFunction).size <= 1) { "Please provide text just once." }
    }

    internal fun toMap() =
        mapOf(
            "min" to min,
            "max" to max,
            "now" to now,
            "text" to (text ?: textAsRegex?.asJsExpression() ?: textAsFunction?.asJsExpression())
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