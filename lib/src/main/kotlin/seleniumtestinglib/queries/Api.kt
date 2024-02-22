package seleniumtestinglib.queries

import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.ensureScript

/**
 * https://testing-library.com/docs/dom-testing-library/intro
 */
internal inline fun <reified T> RemoteWebDriver.executeTLQuery(
    queryType: QueryType = QueryType.Query,
    all: Boolean = true,
    by: ByType,
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

enum class ByType {
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

private fun RemoteWebDriver.executeTLScript(script: String): Any? {
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
