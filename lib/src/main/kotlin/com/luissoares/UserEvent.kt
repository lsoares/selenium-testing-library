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

    fun type(element: WebElement, text: String, options: Map<String, Any> = emptyMap()) {
        executeUserEventCall(element, "type", text, options)
    }

    fun  selectOptions(element: WebElement, values: List<SelectValue>) {
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

    private fun executeUserEventCall(
        element: WebElement,
        userEventFunction: String,
        arg0: String? = null,
        options: Map<String, Any> = emptyMap(),
    ) {
        javascriptExecutor.executeScript(
            "await userEvent.$userEventFunction(arguments[0], arguments[1], arguments[2])",
            element,
            arg0,
            options.entries
                .joinToString(", ", prefix = "{ ", postfix = " }") {
                    "${it.key}: ${it.value}"
                },
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
