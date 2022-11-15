package seleniumtestinglib.coreapi

import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.coreapi.JsType.Companion.asJsString
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

class JsType private constructor(val value: String) {
    companion object {
        fun String.asJsFunction() = JsType(this)
        fun String.asJsRegex() = JsType(this)
        fun String.asJsString() = JsType("'${replace("'", "\\'")}'")
    }
}

internal enum class QueryType {
    Find, Query, Get
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
        is JsType -> value
        is String -> asJsString().value
        else      -> this
    }
