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

    private fun executeUserEventCall(element: WebElement, userEventFunction: String) {
        javascriptExecutor.ensureScript("user-event.js", "window.userEvent?.click")
        javascriptExecutor.executeScript("await userEvent.$userEventFunction(arguments[0])", element)
    }
}

val RemoteWebDriver.userEvent: UserEvent
    get() = UserEvent(this as JavascriptExecutor)
