package seleniumtestinglib.queries

import org.openqa.selenium.JavascriptExecutor
import seleniumtestinglib.ensureScript
import seleniumtestinglib.queries.JsType.Companion.asJsExpression
import kotlin.text.RegexOption.*

/**
 * https://testing-library.com/docs/dom-testing-library/intro
 */
internal inline fun <reified T> JavascriptExecutor.executeTLQuery(
    queryType: QueryType = QueryType.Query,
    all: Boolean = true,
    by: LocatorType,
    textMatch: JsType,
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

sealed class JsType(internal open val value: String) {
    class JsString(override val value: String) : JsType(value)
    class JsExpression(override val value: String) : JsType(value)

    override fun toString() = value

    companion object {
        fun String.asJsExpression() = JsExpression(this)
        fun String.asJsString() = JsString(this)
    }
}

internal fun Regex.asJsExpression(): JsType.JsExpression {
    val jsFlags = buildString {
        if (IGNORE_CASE in options) append('i')
        if (MULTILINE in options) append('m')
        if (DOT_MATCHES_ALL in options) append('s')
        if (COMMENTS in options) append('x')
        if (CANON_EQ in options) append('u')
    }
    return "/${this.pattern}/$jsFlags".asJsExpression()
}

private fun JavascriptExecutor.executeTLScript(script: String): Any? {
    ensureScript("testing-library.js", "screen?.queryAllByTestId")
    return executeScript(script)
}

private val Any?.escaped: Any?
    get() = when (this) {
        is JsType.JsString -> value.escaped
        is JsType.JsExpression -> value
        is String -> "'${replace("'", "\\'")}'"
        is Map<*, *> -> entries.joinToString(", ", prefix = "{ ", postfix = " }") {
            "${it.key}: ${it.value?.escaped}"
        }

        else -> this
    }
