package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.openqa.selenium.json.Json

abstract class ByTestingLibrary(
    private val by: String,
    private val textMatch: String,
    private val textMatchIsString: Boolean = true,
    private val options: Map<String, Any?> = emptyMap(),
) : By() {
    override fun findElements(context: SearchContext) =
        getJavascriptExecutor(context).run {
            ensureTLScript()
            @Suppress("UNCHECKED_CAST")
            executeScript(
                "return $testingLibraryCall"
            ) as List<WebElement>
        }

    override fun toString() = testingLibraryCall

    private val testingLibraryCall: String
        get() {
            val mainArg = when (textMatchIsString) {
                true  -> "'${textMatch.replace("'", "\\'")}'"
                false -> textMatch
            }
            val optionsAsJson = options
                .filterValues { it != null }
                .let(Json()::toJson)
                .let {
                    it.replace(Regex(""""normalizer": "(.*)"""")) { match ->
                        """"normalizer": ${match.groups[1]?.value} """
                    }
                }

            return """screen.queryAllBy$by(${mainArg}, $optionsAsJson)"""
                .replace(", {\n})", ", {})")
        }
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
