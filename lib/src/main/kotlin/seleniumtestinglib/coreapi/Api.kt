package seleniumtestinglib.coreapi

import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.ensureScript

internal fun RemoteWebDriver.executeTLQuery(
    queryType: QueryType = QueryType.Query,
    plural: Boolean = true,
    by: ByType,
    textMatch: String,
    matchTextBy: MatchType = MatchType.STRING,
    matchDescriptionBy: MatchType = MatchType.STRING,
    options: Map<String, Any?> = emptyMap(),
): Any? {
    val mainArg = when (matchTextBy) {
        MatchType.STRING -> textMatch.escapeString()
        else             -> textMatch
    }
    val escapedOptions = options
        .filterValues { it != null }
        .takeIf(Map<String, Any?>::isNotEmpty)
        ?.entries
        ?.joinToString(", ", prefix = "{ ", postfix = " }") {
            "${it.key}: ${it.getEscapedValue(matchDescriptionBy)}"
        }
    return executeTLScript(
        "return "
            + "await".takeIf { queryType == QueryType.Find }.orEmpty()
            + " screen."
            + queryType.name.lowercase()
            + "All".takeIf { plural }.orEmpty()
            + "By"
            + by.name
            + """(${mainArg}${escapedOptions?.let { ", $it" } ?: ""})"""
    )
}

enum class QueryType {
    Find, Query, Get
}

enum class ByType {
    AltText, DisplayValue, LabelText, PlaceholderText, Role, TestId, Text, Title
}

enum class MatchType {
    STRING, REGEX, FUNCTION
}

/**
 * https://testing-library.com/docs/queries/about/#priority
 */
private fun RemoteWebDriver.executeTLScript(script: String, vararg args: Any?): Any? {
    ensureScript("testing-library.js", "screen?.getAllByAltText")
    return executeScript(script, args)
}

private fun Map.Entry<String, Any?>.getEscapedValue(matchDescriptionBy: MatchType) =
    value.takeIf {
        (it !is String)
            .or(key == "normalizer")
            .or(
                (key == "description")
                    .and(matchDescriptionBy != MatchType.STRING)
            )
    }
        ?: value.toString().escapeString()

private fun String.escapeString() = "'${replace("'", "\\'")}'"
