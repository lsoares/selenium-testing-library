package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

abstract class ByTestingLibrary(
    private val by: String,
    private val textMatch: String,
    private val matchTextBy: TextMatchType = TextMatchType.STRING,
    private val matchDescriptionBy: TextMatchType = TextMatchType.STRING,
    private val options: Map<String, Any?> = emptyMap(),
) : By() {
    override fun findElements(context: SearchContext) =
        getJavascriptExecutor(context).run {
            ensureTLScript()
            @Suppress("UNCHECKED_CAST")
            executeScript("return $testingLibraryCall") as List<WebElement>
        }

    private val testingLibraryCall: String
        get() {
            val mainArg = when (matchTextBy) {
                TextMatchType.STRING -> textMatch.escapeString()
                else                 -> textMatch
            }
            val escapedOptions = options
                .filterValues { it != null }
                .takeIf(Map<String, Any?>::isNotEmpty)
                ?.entries
                ?.joinToString(", ", prefix = "{ ", postfix = " }") {
                    "${it.key}: ${it.escapedValue}"
                }

            return """screen.queryAllBy$by(${mainArg}${escapedOptions?.let { ", $it" } ?: ""})"""
        }

    private val Map.Entry<String, Any?>.escapedValue
        get() =
            value.takeIf {
                it !is String
                    || key == "normalizer"
                    || key == "description" && matchDescriptionBy != TextMatchType.STRING
            }
                ?: value.toString().escapeString()

    private fun String.escapeString() = "'${replace("'", "\\'")}'"

    override fun toString() = testingLibraryCall
}

private fun JavascriptExecutor.ensureTLScript() {
    if (!hasTLScript)
        executeScript(tlScript)
}

private val JavascriptExecutor.hasTLScript
    get() = executeScript("return typeof screen?.getAllByAltText == 'function'") as Boolean

private val tlScript by lazy {
    {}.javaClass.getResource("/testing-library.js")?.readText()
        ?: error("script not found")
}

enum class TextMatchType {
    STRING, REGEX, FUNCTION
}
