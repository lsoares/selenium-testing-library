package com.luissoares

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.json.Json

@Suppress("UNCHECKED_CAST")
fun JavascriptExecutor.queryAll(
    by: String,
    arg0: String,
    options: Map<String, Any?> = emptyMap(),
): List<WebElement> {
    ensureTLScript()
    val optionsAsJson = Json().toJson(options)
    val arg0AsJavaScript = if (arg0.isJavaScriptRegex()) arg0 else """'$arg0'"""
    val args = """By$by($arg0AsJavaScript, $optionsAsJson)"""
    return executeScript("return screen.queryAll$args") as List<WebElement>
}

private fun String.isJavaScriptRegex() = Regex("/.+/[dgimsuy]?").find(this) != null

private fun JavascriptExecutor.ensureTLScript() {
    if (!hasTLScript)
        executeScript(tlScript)
}

private val tlScript by lazy {
    {}.javaClass.getResource("/testing-library.js")?.readText()
        ?: error("script not found")
}

private val JavascriptExecutor.hasTLScript
    get() = executeScript("return typeof screen?.getAllByAltText == 'function'") as Boolean
