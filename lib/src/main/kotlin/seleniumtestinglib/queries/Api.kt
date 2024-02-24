package seleniumtestinglib.queries

import org.openqa.selenium.JavascriptExecutor
import seleniumtestinglib.ensureScript
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
    internal class JsRegex(override val value: String) : TextMatch(value)

    override fun toString() = value

    companion object {
        fun String.asJsFunction() = JsFunction(this)
        internal fun String.asJsString() = JsString(this)
    }
}

internal fun Regex.asJsRegex(): TextMatch.JsRegex {
    val jsFlags = buildString {
        if (IGNORE_CASE in options) append('i')
        if (MULTILINE in options) append('m')
        if (DOT_MATCHES_ALL in options) append('s')
        if (COMMENTS in options) append('x')
        if (CANON_EQ in options) append('u')
    }
    return TextMatch.JsRegex("/${this.pattern}/$jsFlags")
}

private fun JavascriptExecutor.executeTLScript(script: String): Any? {
    ensureScript("testing-library.js", "screen?.queryAllByTestId")
    return executeScript(script)
}

private val Any?.escaped: Any?
    get() = when (this) {
        is TextMatch.JsString -> value.escaped
        is TextMatch.JsFunction -> value
        is TextMatch.JsRegex -> value
        is String -> "'${replace("'", "\\'")}'"
        is Map<*, *> -> entries.joinToString(", ", prefix = "{ ", postfix = " }") {
            "${it.key}: ${it.value?.escaped}"
        }

        else -> this
    }
