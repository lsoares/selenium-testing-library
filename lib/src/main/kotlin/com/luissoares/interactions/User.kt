package com.luissoares.interactions

import com.luissoares.ensureScript
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.remote.RemoteWebDriver

class User(val javascriptExecutor: JavascriptExecutor)

fun RemoteWebDriver.user(options: Map<String, Any> = emptyMap()): User {
    ensureScript("user-event.js", "window.userEvent?.setup")
    executeScript("user = userEvent.setup(arguments[0])", options)
    return User(this as JavascriptExecutor)
}

val RemoteWebDriver.user: User
    get() {
        val thereIsUser = executeScript("return typeof user != 'undefined'") as Boolean
        return when {
            thereIsUser -> User(this as JavascriptExecutor)
            else        -> user()
        }
    }
