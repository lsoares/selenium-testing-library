package seleniumtestinglib.queries

import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.ensureScript

/**
 * https://testing-library.com/docs/dom-testing-library/intro
 */
internal fun RemoteWebDriver.executeTLQuery(
    queryType: QueryType = QueryType.Query,
    all: Boolean = true,
    by: ByType,
    textMatch: JsType,
    options: Map<String, Any?> = emptyMap(),
): Any? {
    val escapedOptions = options
        .filterValues { it != null }
        .takeIf(Map<String, Any?>::isNotEmpty)
        ?.entries
        ?.joinToString(", ", prefix = "{ ", postfix = " }") {
            "${it.key}: ${it.value?.escaped}"
        }
    return executeTLScript(
        "return"
            + " await".takeIf { queryType == QueryType.Find }.orEmpty()
            + " screen."
            + queryType.name.lowercase()
            + "All".takeIf { all }.orEmpty()
            + "By"
            + by.name
            + """(${textMatch.escaped}${escapedOptions?.let { ", $it" } ?: ""})"""
    )
}

enum class ByType {
    AltText, DisplayValue, LabelText, PlaceholderText, Role, TestId, Text, Title
}

internal enum class QueryType {
    Find, Query, Get
}

sealed class JsType constructor(open val value: String) {
    class JsString(override val value: String) : JsType(value)
    class JsFunction(override val value: String) : JsType(value)
    class JsRegex(override val value: String) : JsType(value)

    companion object {
        fun String.asJsFunction() = JsFunction(this)
        fun String.asJsRegex() = JsRegex(this)
        fun String.asJsString() = JsString(this)
    }
}

private fun RemoteWebDriver.executeTLScript(script: String): Any? {
    ensureScript("testing-library.js", "screen?.getAllByAltText")
    return runCatching {
        executeScript(script)
    }.onFailure {
        System.err.println("JavaScript error running Testing Library script:\n$script")
    }.getOrThrow()
}

private val Any?.escaped: Any?
    get() = when (this) {
        is JsType.JsString -> value.escaped
        is JsType          -> value
        is String          -> "'${replace("'", "\\'")}'"
        else               -> this
    }
