package seleniumtestinglib.interactions

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.ensureScript

class User(val javascriptExecutor: JavascriptExecutor)

// TODO: test receiving options
// TODO: limit valid options
fun RemoteWebDriver.user(vararg options: Pair<String, Any?>): User {
    ensureScript("user-event.js", "window.userEvent?.setup")
    executeScript("user = userEvent.setup(arguments[0])", options.toMap())
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
