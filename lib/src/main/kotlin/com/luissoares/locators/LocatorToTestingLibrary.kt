package com.luissoares.locators

import com.luissoares.ensureScript
import com.luissoares.escapeString
import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

abstract class ByTestingLibrary(
    private val by: String,
    private val textMatch: String,
    private val matchTextBy: TextMatchType = TextMatchType.STRING,
    private val matchDescriptionBy: TextMatchType = TextMatchType.STRING,
    private val options: Map<String, Any?> = emptyMap(),
) : By() {

    override fun findElements(context: SearchContext): List<WebElement> {
        getJavascriptExecutor(context).ensureScript("testing-library.js", "screen?.getAllByAltText")
        @Suppress("UNCHECKED_CAST")
        return getJavascriptExecutor(context).executeScript("return $testingLibraryCall") as List<WebElement>
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

    override fun toString() = testingLibraryCall
}
