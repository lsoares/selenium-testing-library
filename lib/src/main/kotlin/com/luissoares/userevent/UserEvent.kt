package com.luissoares.userevent

import com.luissoares.ensureScript
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.w3c.dom.events.MouseEvent

class UserEvent(private val javascriptExecutor: JavascriptExecutor) {

    private fun awaitUserEventScript(functionCall: String, vararg args: Any?) =
        javascriptExecutor.executeScript("await userEvent.$functionCall", *args)

    fun click(element: WebElement, eventInit: MouseEvent? = null) {
        awaitUserEventScript("click(arguments[0], arguments[1])", element, eventInit)
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

    fun tab() {
        awaitUserEventScript("tab()")
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

val RemoteWebDriver.userEvent: UserEvent
    get() {
        ensureScript("user-event.js", "window.userEvent?.click")
        return UserEvent(this as JavascriptExecutor)
    }
