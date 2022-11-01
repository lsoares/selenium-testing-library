package com.luissoares

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

class UserEvent(private val javascriptExecutor: JavascriptExecutor) {

    fun click(element: WebElement) {
        javascriptExecutor.executeScript(
            "await userEvent.click(arguments[0])", element
        )
    }

    fun dblClick(element: WebElement) {
        javascriptExecutor.executeScript(
            "await userEvent.dblClick(arguments[0])", element
        )
    }

    fun type(element: WebElement, text: String) {
        javascriptExecutor.executeScript(
            "await userEvent.type(arguments[0], arguments[1])",
            element,
            text,
        )
    }

    fun selectOptions(element: WebElement, values: List<SelectValue>) {
        javascriptExecutor.executeScript(
            "await userEvent.selectOptions(arguments[0], arguments[1])",
            element,
            values.map(SelectValue::value),
        )
    }

    fun deselectOptions(element: WebElement, values: List<String>) {
        javascriptExecutor.executeScript(
            "await userEvent.deselectOptions(arguments[0], arguments[1])",
            element,
            values,
        )
    }

    fun clear(element: WebElement) {
        javascriptExecutor.executeScript(
            "await userEvent.clear(arguments[0])", element,
        )
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
