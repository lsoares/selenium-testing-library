package com.luissoares.userevent

import com.luissoares.ensureScript
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

class User(private val javascriptExecutor: JavascriptExecutor) {

    private fun awaitUserEventScript(functionCall: String, vararg args: Any?) =
        javascriptExecutor.executeScript("await user.$functionCall", *args)

    fun click(element: WebElement) {
        awaitUserEventScript("click(arguments[0])", element)
    }

    fun dblClick(element: WebElement) {
        awaitUserEventScript("dblClick(arguments[0])", element)
    }

    fun type(element: WebElement, text: String) {
        awaitUserEventScript("type(arguments[0], arguments[1])", element, text)
    }

    fun selectOptions(element: WebElement, values: List<SelectValue>) {
        awaitUserEventScript(
            "selectOptions(arguments[0], arguments[1])",
            element,
            values.map(SelectValue::value),
        )
    }

    fun deselectOptions(element: WebElement, values: List<String>) {
        awaitUserEventScript(
            "deselectOptions(arguments[0], arguments[1])",
            element,
            values,
        )
    }

    fun clear(element: WebElement) {
        awaitUserEventScript("clear(arguments[0])", element)
    }

    fun tab(shift: Boolean = false) {
        awaitUserEventScript("tab(arguments[0])", mapOf("shift" to shift))
    }

    fun upload(input: WebElement, file: File) {
        awaitUserEventScript(
            "upload(arguments[0], new File(arguments[1], arguments[2], arguments[3]))",
            input,
            file.bits,
            file.name,
            file.options
        )
    }

    data class File(val bits: List<String>, val name: String, val options: Map<String, String> = emptyMap())
}

fun RemoteWebDriver.user(options: Map<String, Any?> = emptyMap()): User {
    ensureScript("user-event.js", "window.userEvent?.setup")
    executeScript("user = userEvent.setup(arguments[0])", options)
    return User(this as JavascriptExecutor)
}

val RemoteWebDriver.user: User
    get() = user()
