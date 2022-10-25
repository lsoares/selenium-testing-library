package com.luissoares

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.json.Json
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration.ofMillis
import java.time.Duration.ofSeconds

object TestingLibraryScript {
    private val tlScript = {}.javaClass.getResource("/testing-library.js")?.readText()
        ?: error("script not found")

    @Suppress("UNCHECKED_CAST")
    fun JavascriptExecutor.queryAllBy(
        by: String,
        mainArgument: String,
        options: Map<String, Any?> = emptyMap(),
    ): List<WebElement> {
        addScriptIfMissing()
        return executeScript(
            """return screen.queryAllBy${by}("$mainArgument", ${Json().toJson(options)})"""
        ) as List<WebElement>
    }

    private fun JavascriptExecutor.addScriptIfMissing() {
        if (!hasTLScript)
            executeScript(tlScript)
    }

    private val JavascriptExecutor.hasTLScript get() = executeScript("return typeof screen?.getAllByAltText == 'function'") as Boolean

    fun WebDriver.findAllBy(by: String, mainArgument: String, options: Map<String, Any?> = emptyMap()): List<WebElement> {
        val implicitWaitTimeout = manage().timeouts().implicitWaitTimeout
        val timeout = implicitWaitTimeout.seconds.takeIf { it > 0 } ?: 5
        return WebDriverWait(this, ofSeconds(timeout))
                .pollingEvery(ofMillis(100))
                .until {
                    (this as JavascriptExecutor).queryAllBy(by, mainArgument, options)
                }
    }
}
