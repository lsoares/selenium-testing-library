package seleniumtestinglib

import org.openqa.selenium.JavascriptExecutor
import java.lang.Thread.sleep
import java.util.concurrent.ConcurrentHashMap

internal fun JavascriptExecutor.ensureScript(fileName: String, isPresentFunction: String, retries: Int = 0) {
    require(retries < 5) { "can't ensure script $fileName after $retries retries" }
    if (!isLoaded(isPresentFunction))
        executeScript(loadScript(fileName))
    if (!isLoaded(isPresentFunction)) {
        println("retrying loading script $fileName...")
        sleep(retries * 10L)
        ensureScript(fileName, isPresentFunction, retries + 1)
    }
}

private fun JavascriptExecutor.isLoaded(isPresentFunction: String) =
    executeScript("return typeof $isPresentFunction == 'function'") as Boolean

private val resources = ConcurrentHashMap<String, String>()
private fun loadScript(fileName: String) =
    resources.computeIfAbsent(fileName) {
        {}.javaClass.getResource("/$fileName")?.readText() ?: error("$fileName not found")
    }

val JavascriptExecutor.selection: String
    get() = executeScript("return window.getSelection().toString()") as String
