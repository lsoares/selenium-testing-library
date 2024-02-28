package seleniumtestinglib

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

class User(val driver: RemoteWebDriver)

// TODO: test receiving options
/**
 * Obtains a custom instance of user by calling `userEvent.setup()` with the supplied arguments.
 *
 * https://testing-library.com/docs/user-event/options
 */
fun RemoteWebDriver.user(vararg options: Pair<UserOption, Any?>): User {
    ensureScript("user-event.js", "window.userEvent?.setup")
    executeScript(
        "user = userEvent.setup(arguments[0])",
        options.toMap().mapKeys { it.key.name.replaceFirstChar(Char::lowercase) }
    )
    return User(this)
}

/**
 * If there's one Testing Library `user` already, it returns it.
 * Else, it creates a default one (obtained with `userEvent.setup()`).
 *
 * https://testing-library.com/docs/user-event/intro
 */
val RemoteWebDriver.user: User
    get() = if (executeScript("return typeof user != 'undefined'") as Boolean)
        User(this)
    else user()

@Suppress("unused")
enum class UserOption {
    AdvanceTimers,
    ApplyAccept,
    AutoModify,
    Delay,
    Document,
    KeyboardMap,
    PointerEventsCheck,
    PointerMap,
    SkipAutoClose,
    SkipClick,
    SkipHover,
    WriteToClipboard,
}

/**
 * https://testing-library.com/docs/user-event/clipboard
 */
fun User.copy(): User {
    driver.executeScript("await user.copy()")
    return this
}

fun User.cut(): User {
    driver.executeScript("await user.cut()")
    return this
}

// TODO receive DataTransfer
fun User.paste(clipboardData: String? = null): User {
    driver.executeScript("await user.paste(arguments[0])", clipboardData)
    return this
}

/**
 * https://testing-library.com/docs/user-event/keyboard
 */
fun User.keyboard(input: String): User {
    driver.executeScript("await user.keyboard(arguments[0])", input)
    return this
}

/**
 * https://testing-library.com/docs/user-event/convenience#tab
 */
fun User.tab(shift: Boolean = false): User {
    driver.executeScript("await user.tab(arguments[0])", mapOf("shift" to shift))
    return this
}

/**
 * https://testing-library.com/docs/user-event/pointer
 */
fun User.pointer(vararg inputs: String): User {
    driver.executeScript("await user.pointer(arguments[0])", inputs)
    return this
}

fun User.pointer(vararg inputs: Map<PointerOption, Any>): User {
    driver.executeScript(
        "await user.pointer(arguments[0])",
        inputs.toList().map { it.mapKeys { it.key.name.replaceFirstChar(Char::lowercase) } })
    return this
}

enum class PointerOption {
    Target, Keys, Offset, PointerName, Node
}

/**
 * https://testing-library.com/docs/user-event/convenience#click
 */
fun User.click(element: WebElement): User {
    driver.executeScript("await user.click(arguments[0])", element)
    return this
}

/**
 * https://testing-library.com/docs/user-event/convenience#dblclick
 */
fun User.dblClick(element: WebElement): User {
    driver.executeScript("await user.dblClick(arguments[0])", element)
    return this
}

/**
 * https://testing-library.com/docs/user-event/convenience#tripleclick
 */
fun User.tripleClick(element: WebElement): User {
    driver.executeScript("await user.tripleClick(arguments[0])", element)
    return this
}

/**
 * https://testing-library.com/docs/user-event/convenience#hover
 */
fun User.hover(element: WebElement): User {
    driver.executeScript("await user.hover(arguments[0])", element)
    return this
}

/**
 * https://testing-library.com/docs/user-event/convenience#unhover
 */
fun User.unhover(element: WebElement): User {
    driver.executeScript("await user.unhover(arguments[0])", element)
    return this
}

/**
 * https://testing-library.com/docs/user-event/utility#clear
 */
fun User.clear(element: WebElement): User {
    driver.executeScript("await user.clear(arguments[0])", element)
    return this
}

fun User.selectOptions(element: WebElement, vararg values: String): User {
    driver.executeScript(
        "await user.selectOptions(arguments[0], arguments[1])", element, values
    )
    return this
}

fun User.selectOptions(element: WebElement, vararg values: WebElement): User {
    driver.executeScript(
        "await user.selectOptions(arguments[0], arguments[1])", element, values
    )
    return this
}

/**
 * https://testing-library.com/docs/user-event/utility#-selectoptions-deselectoptions
 */
fun User.deselectOptions(element: WebElement, vararg values: String): User {
    driver.executeScript(
        "await user.deselectOptions(arguments[0], arguments[1])", element, values
    )
    return this
}

fun User.deselectOptions(element: WebElement, vararg values: WebElement): User {
    driver.executeScript(
        "await user.deselectOptions(arguments[0], arguments[1])", element, values
    )
    return this
}

/**
 * https://testing-library.com/docs/user-event/utility#type
 */
fun User.type(
    element: WebElement,
    text: String,
    skipClick: Boolean? = null,
    skipAutoClose: Boolean? = null,
    initialSelectionStart: Int? = null,
    initialSelectionEnd: Int? = null,
): User {
    driver.executeScript(
        "await user.type(arguments[0], arguments[1], arguments[2])",
        element,
        text,
        mapOf(
            "skipClick" to skipClick,
            "skipAutoClose" to skipAutoClose,
            "initialSelectionStart" to initialSelectionStart,
            "initialSelectionEnd" to initialSelectionEnd,
        ).filterValues { it != null }
    )
    return this
}

/**
 * https://testing-library.com/docs/user-event/utility#upload
 */
fun User.upload(input: WebElement, file: File): User {
    driver.executeScript(
        "await user.upload(arguments[0], new File(arguments[1], arguments[2], arguments[3]))",
        input,
        file.bits,
        file.name,
        file.options
    )
    return this
}

data class File(val bits: List<String>, val name: String, val options: Map<String, String> = emptyMap())