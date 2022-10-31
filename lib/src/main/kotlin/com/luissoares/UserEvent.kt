package com.luissoares

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

class UserEvent(private val javascriptExecutor: JavascriptExecutor) {

    fun click(checkbox: WebElement) {
        javascriptExecutor.ensureScript("user-event.js", "window.userEvent?.click")
        javascriptExecutor.executeScript("await userEvent.click(arguments[0])", checkbox)
    }
}

val RemoteWebDriver.userEvent: UserEvent
    get() = UserEvent(this as JavascriptExecutor)
