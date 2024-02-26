package seleniumtestinglib.locators

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import seleniumtestinglib.locators.Current.CurrentAsBool
import seleniumtestinglib.locators.Current.CurrentAsType
import seleniumtestinglib.queries.LocatorType
import seleniumtestinglib.queries.LocatorType.*
import seleniumtestinglib.queries.TextMatch
import seleniumtestinglib.queries.TextMatch.Companion.asJsString
import seleniumtestinglib.queries.TextMatch.JsFunction
import seleniumtestinglib.queries.asJsExpression
import seleniumtestinglib.queries.executeTLQuery
import java.util.regex.Pattern
import org.openqa.selenium.By as SeleniumBy


abstract class TL(
    private val by: LocatorType,
    private val textMatch: TextMatch,
    private val options: Map<String, Any?> = emptyMap()
) : SeleniumBy() {

    override fun findElements(context: SearchContext?): List<WebElement> =
        (getWebDriver(context) as JavascriptExecutor).executeTLQuery(
            by = by,
            textMatch = textMatch,
            options = options,
        )

    override fun toString(): String {
        val entries = options.filterValues { it != null }.entries
        val prefix = if (entries.isEmpty()) "" else ", "
        return "By$by($textMatch$prefix${entries.joinToString { "${it.key}: ${it.value}" }})"
    }

    companion object By {
        /**
         * https://testing-library.com/docs/queries/byalttext
         */
        fun altText(text: String, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = AltText,
                textMatch = text.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        fun altText(text: Pattern, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = AltText,
                textMatch = text.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        fun altText(text: JsFunction, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = AltText,
                textMatch = text,
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        /**
         * https://testing-library.com/docs/queries/bydisplayvalue
         */
        fun displayValue(value: String, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = DisplayValue,
                textMatch = value.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        fun displayValue(value: Pattern, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = DisplayValue,
                textMatch = value.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        fun displayValue(value: JsFunction, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = DisplayValue,
                textMatch = value,
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        /**
         * https://testing-library.com/docs/queries/bylabeltext
         */
        fun labelText(
            text: String,
            exact: Boolean? = null,
            selector: String? = null,
            normalizer: JsFunction? = null
        ) =
            object : TL(
                by = LabelText,
                textMatch = text.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer, "selector" to selector),
            ) {}

        fun labelText(
            text: Pattern,
            exact: Boolean? = null,
            selector: String? = null,
            normalizer: JsFunction? = null
        ) =
            object : TL(
                by = LabelText,
                textMatch = text.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer, "selector" to selector),
            ) {}

        fun labelText(
            text: JsFunction,
            exact: Boolean? = null,
            selector: String? = null,
            normalizer: JsFunction? = null
        ) =
            object : TL(
                by = LabelText,
                textMatch = text,
                options = mapOf("exact" to exact, "normalizer" to normalizer, "selector" to selector),
            ) {}

        /**
         *  https://testing-library.com/docs/queries/byplaceholdertext
         */
        fun placeholderText(text: String, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = PlaceholderText,
                textMatch = text.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        fun placeholderText(text: Pattern, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = PlaceholderText,
                textMatch = text.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        fun placeholderText(text: JsFunction, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = PlaceholderText,
                textMatch = text,
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        /**
         * https://testing-library.com/docs/queries/bytestid
         */
        fun testId(text: String, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = TestId,
                textMatch = text.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        fun testId(text: Pattern, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = TestId,
                textMatch = text.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        fun testId(text: JsFunction, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = TestId,
                textMatch = text,
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}


        /**
         * https://testing-library.com/docs/queries/bytext
         */
        fun text(
            text: String,
            selector: String? = null,
            exact: Boolean? = null,
            ignore: String? = null,
            normalizer: JsFunction? = null
        ) =
            object : TL(
                by = Text,
                textMatch = text.asJsString(),
                options = mapOf(
                    "exact" to exact,
                    "normalizer" to normalizer,
                    "selector" to selector,
                    "ignore" to ignore
                ),
            ) {}

        fun text(
            text: Pattern,
            selector: String? = null,
            exact: Boolean? = null,
            ignore: String? = null,
            normalizer: JsFunction? = null
        ) =
            object : TL(
                by = Text,
                textMatch = text.asJsExpression(),
                options = mapOf(
                    "exact" to exact,
                    "normalizer" to normalizer,
                    "selector" to selector,
                    "ignore" to ignore
                ),
            ) {}

        fun text(
            text: JsFunction,
            selector: String? = null,
            exact: Boolean? = null,
            ignore: String? = null,
            normalizer: JsFunction? = null
        ) =
            object : TL(
                by = Text,
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
        fun title(title: String, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = Title,
                textMatch = title.asJsString(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        fun title(title: Pattern, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = Title,
                textMatch = title.asJsExpression(),
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        fun title(title: JsFunction, exact: Boolean? = null, normalizer: JsFunction? = null) =
            object : TL(
                by = Title,
                textMatch = title,
                options = mapOf("exact" to exact, "normalizer" to normalizer),
            ) {}

        /**
         * https://testing-library.com/docs/queries/byrole
         */
        fun role(
            role: RoleType,
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
            current: CurrentType? = null,
            currentAsBoolean: Boolean? = null,
            expanded: Boolean? = null,
            level: Int? = null,
            value: Value? = null,
            queryFallbacks: Boolean? = null,
        ) = object : TL(
            by = Role,
            textMatch = role.name.lowercase().asJsString(),
            options = mapOf(
                "name" to (name?.asJsString() ?: nameAsRegex?.asJsExpression() ?: nameAsFunction),
                "description" to (description?.asJsString() ?: descriptionAsRegex?.asJsExpression()
                ?: descriptionAsFunction),
                "hidden" to hidden,
                "normalizer" to normalizer,
                "selected" to selected,
                "busy" to busy,
                "checked" to checked,
                "pressed" to pressed,
                "suggest" to suggest,
                "expanded" to expanded,
                "value" to value?.toMap(),
                "current" to (current?.let(::CurrentAsType)?.value ?: currentAsBoolean?.let(::CurrentAsBool)),
                "level" to level,
                "queryFallbacks" to queryFallbacks,
            )
        ) {}
    }
}

private fun Value.toMap() =
    mapOf("min" to min, "max" to max, "now" to now, "text" to text).filterValues { it != null }