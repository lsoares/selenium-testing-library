package com.luissoares

import org.openqa.selenium.JavascriptExecutor

internal fun JavascriptExecutor.ensureScript(fileName: String, isPresentFunction: String) {
    if (!isLoaded(isPresentFunction))
        executeScript(loadScript(fileName))
}

internal fun String.escapeString() = "'${replace("'", "\\'")}'"

private fun JavascriptExecutor.isLoaded(isPresentFunction: String) =
    executeScript("return typeof $isPresentFunction == 'function'") as Boolean

private val resources = mutableMapOf<String, String>()
private fun loadScript(fileName: String) =
    resources.computeIfAbsent(fileName) {
        {}.javaClass.getResource("/$fileName")?.readText() ?: error("$fileName not found")
    }
