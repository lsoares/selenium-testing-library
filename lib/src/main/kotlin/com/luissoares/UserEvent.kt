package com.luissoares

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

class UserEvent(private val javascriptExecutor: JavascriptExecutor) {

    fun click(element: WebElement) {
        executeUserEventCall(element, "click")
    }

    fun dblClick(element: WebElement) {
        executeUserEventCall(element, "dblClick")
    }

    fun type(element: WebElement, text: String) {
        executeUserEventCall(element, "type", text)
    }

    private fun executeUserEventCall(element: WebElement, userEventFunction: String, arg1: String? = null) {
        javascriptExecutor.ensureScript("user-event.js", "window.userEvent?.click")
        javascriptExecutor.executeScript(
            "await userEvent.$userEventFunction(arguments[0], arguments[1])",
            element,
            arg1
        )
    }
}

val RemoteWebDriver.userEvent: UserEvent
    get() = UserEvent(this as JavascriptExecutor)
