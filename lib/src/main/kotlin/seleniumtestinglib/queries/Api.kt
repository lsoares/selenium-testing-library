package seleniumtestinglib.queries

import org.openqa.selenium.JavascriptExecutor
import seleniumtestinglib.ensureScript
import java.util.regex.Pattern
import java.util.regex.Pattern.*
import kotlin.text.RegexOption.*

/**
 * https://testing-library.com/docs/dom-testing-library/intro
 */
internal inline fun <reified T> JavascriptExecutor.executeTLQuery(
    queryType: QueryType = QueryType.Query,
    all: Boolean = true,
    by: LocatorType,
    textMatch: TextMatch,
    options: Map<String, Any?> = emptyMap(),
): T {
    val escapedOptions = options
        .filterValues { it != null }
        .takeIf(Map<String, Any?>::isNotEmpty)
        ?.escaped

    return listOfNotNull(
        "return",
        " await".takeIf { queryType == QueryType.Find },
        " screen.",
        queryType.name.lowercase(),
        "All".takeIf { all },
        "By",
        by.name,
        """(${textMatch.escaped}${escapedOptions?.let { ", $it" } ?: ""})"""
    ).joinToString("")
        .let(::executeTLScript) as T
}

enum class LocatorType {
    AltText, DisplayValue, LabelText, PlaceholderText, Role, TestId, Text, Title
}

internal enum class QueryType {
    Find, Query, Get
}

sealed class TextMatch(internal open val value: String) {
    class JsFunction(override val value: String) : TextMatch(value)
    internal class JsString(override val value: String) : TextMatch(value)
    class JsExpression(override val value: String) : TextMatch(value)

    override fun toString() = value

    companion object {
        fun String.asJsFunction() = JsFunction(this)
        internal fun String.asJsString() = JsString(this)
    }
}

internal fun Pattern.asJsExpression(): TextMatch.JsExpression {
    val flags: Int = flags()
    val jsFlags = buildString {
        if (flags and CASE_INSENSITIVE != 0) append('i')
        if (flags and MULTILINE != 0) append('m')
        if (flags and DOTALL != 0) append('s')
        if (flags and COMMENTS != 0) append('x')
        if (flags and UNICODE_CASE != 0) append('u')
    }
    return TextMatch.JsExpression(("/" + pattern()).toString() + "/" + jsFlags.toString())
}

private fun JavascriptExecutor.executeTLScript(script: String): Any? {
    ensureScript("testing-library.js", "screen?.queryAllByTestId")
    return executeScript(script)
}

private val String.quoted get() = "'${replace("'", "\\'")}'"

private val Any?.escaped: Any?
    get() = when (this) {
        is TextMatch.JsString -> value.quoted
        is TextMatch.JsFunction -> value
        is TextMatch.JsExpression -> value
        is String -> quoted
        is Map<*, *> -> entries.joinToString(", ", prefix = "{ ", postfix = " }") {
            "${it.key}: ${it.value?.escaped}"
        }

        else -> this
    }
