package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.openqa.selenium.json.Json

abstract class ByTestingLibrary(
    private val by: String,
    private val textMatch: String,
    private val textMatchType: TextType = TextType.STRING,
    private val options: Map<String, Any?> = emptyMap(),
) : By() {
    override fun findElements(context: SearchContext) =
        getJavascriptExecutor(context).run {
            ensureTLScript()
            @Suppress("UNCHECKED_CAST")
            executeScript(
                "return screen.queryAll${asString}"
            ) as List<WebElement>
        }

    override fun toString() = asString

    private val asString: String
        get() {
            val mainArg = when (textMatchType) {
                TextType.STRING -> "'$textMatch'"
                else            -> textMatch
            }
            val optionsAsJson = Json().toJson(options.filterValues { it != null })
            return """By$by(${mainArg}, $optionsAsJson)"""
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
}

enum class TextType {
    STRING, REGEX
}
