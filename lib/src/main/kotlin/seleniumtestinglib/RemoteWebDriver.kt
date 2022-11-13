package seleniumtestinglib

import org.openqa.selenium.remote.RemoteWebDriver

internal fun RemoteWebDriver.ensureScript(fileName: String, isPresentFunction: String) {
    if (!isLoaded(isPresentFunction))
        executeScript(loadScript(fileName))
}

private fun RemoteWebDriver.isLoaded(isPresentFunction: String) =
    executeScript("return typeof $isPresentFunction == 'function'") as Boolean

private val resources = mutableMapOf<String, String>()
private fun loadScript(fileName: String) =
    resources.computeIfAbsent(fileName) {
        {}.javaClass.getResource("/$fileName")?.readText() ?: error("$fileName not found")
    }

val RemoteWebDriver.selection: String
    get() = executeScript("return window.getSelection().toString()") as String
