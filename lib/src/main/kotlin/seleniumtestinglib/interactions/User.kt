package seleniumtestinglib.interactions

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.ensureScript

class User(val javascriptExecutor: JavascriptExecutor)

// TODO: test receiving options
/**
 * Obtains a custom instance of user by calling `userEvent.setup()` with the supplied arguments.
 * https://testing-library.com/docs/user-event/options
 */
fun RemoteWebDriver.user(vararg options: Pair<String, Any?>): User {
    require(options.all { it.first in validUserOptions })
    ensureScript("user-event.js", "window.userEvent?.setup")
    executeScript("user = userEvent.setup(arguments[0])", options.toMap())
    return User(this as JavascriptExecutor)
}

/**
 * If there's one Testing Library `user` already, it returns it.
 * Else, it creates a default one (obtained with `userEvent.setup()`).
 */
val RemoteWebDriver.user: User
    get() = if (executeScript("return typeof user != 'undefined'") as Boolean)
        User(this as JavascriptExecutor)
    else user()

private val validUserOptions = setOf(
    "advanceTimers",
    "applyAccept",
    "autoModify",
    "delay",
    "document",
    "keyboardMap",
    "pointerEventsCheck",
    "pointerMap",
    "skipAutoClose",
    "skipClick",
    "skipHover",
    "writeToClipboard",
)
