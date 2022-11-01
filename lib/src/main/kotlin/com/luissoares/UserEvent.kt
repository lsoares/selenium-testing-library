package com.luissoares

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

class UserEvent(private val javascriptExecutor: JavascriptExecutor) {
    private fun awaitExecuteScript(script: String, vararg args: Any) =
        javascriptExecutor.executeScript("await $script", *args)

    fun click(element: WebElement) {
        awaitExecuteScript("userEvent.click(arguments[0])", element)
    }

    fun dblClick(element: WebElement) {
        awaitExecuteScript("userEvent.dblClick(arguments[0])", element)
    }

    fun type(element: WebElement, text: String) {
        awaitExecuteScript("userEvent.type(arguments[0], arguments[1])", element, text)
    }

    fun selectOptions(element: WebElement, values: List<SelectValue>) {
        awaitExecuteScript(
            "userEvent.selectOptions(arguments[0], arguments[1])",
            element,
            values.map(SelectValue::value),
        )
    }

    fun deselectOptions(element: WebElement, values: List<String>) {
        awaitExecuteScript(
            "userEvent.deselectOptions(arguments[0], arguments[1])",
            element,
            values,
        )
    }

    fun clear(element: WebElement) {
        awaitExecuteScript("userEvent.clear(arguments[0])", element)
    }

    fun tab() {
        awaitExecuteScript("userEvent.tab()")
    }
}

sealed class SelectValue(open val value: Any) {
    class ByValue(override val value: String) : SelectValue(value)
    class ByWebElement(override val value: WebElement) : SelectValue(value)
}

val RemoteWebDriver.userEvent: UserEvent
    get() {
        ensureScript("user-event.js", "window.userEvent?.click")
        return UserEvent(this as JavascriptExecutor)
    }
