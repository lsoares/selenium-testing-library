package com.luissoares

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.json.Json

@Suppress("UNCHECKED_CAST")
fun JavascriptExecutor.queryAll(
    by: String,
    arg0: String,
    options: Map<String, Any?> = emptyMap(),
) = ensureTLScript()
    .executeScript(
        """return screen.queryAllBy${by}("$arg0", ${Json().toJson(options)})"""
    ) as List<WebElement>

private fun JavascriptExecutor.ensureTLScript(): JavascriptExecutor {
    if (!hasTLScript)
        executeScript(tlScript)
    return this
}

private val tlScript by lazy {
    {}.javaClass.getResource("/testing-library.js")?.readText()
        ?: error("script not found")
}

private val JavascriptExecutor.hasTLScript
    get() = executeScript("return typeof screen?.getAllByAltText == 'function'") as Boolean
