package com.luissoares

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.json.Json

object TestingLibraryScript {
    private val tlScript by lazy {
        {}.javaClass.getResource("/testing-library.js")?.readText()
            ?: error("script not found")
    }

    @Suppress("UNCHECKED_CAST")
    fun JavascriptExecutor.queryAllBy(
        by: String,
        mainArgument: String,
        options: Map<String, Any?> = emptyMap(),
    ): List<WebElement> {
        addScriptIfMissing()
        return  executeScript(
            """return screen.queryAllBy${by}("$mainArgument", ${Json().toJson(options)})"""
        ) as List<WebElement>
    }

    private fun JavascriptExecutor.addScriptIfMissing() {
        if (!hasTLScript)
            executeScript(tlScript)
    }

    private val JavascriptExecutor.hasTLScript
        get() = executeScript("return typeof screen?.getAllByAltText == 'function'") as Boolean
}
