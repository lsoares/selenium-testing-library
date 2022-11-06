package seleniumtestinglib.interactions

import org.openqa.selenium.WebElement

/**
 * https://testing-library.com/docs/user-event/utility#clear
 */
fun User.clear(element: WebElement): User {
    javascriptExecutor.executeScript("await user.clear(arguments[0])", element)
    return this
}

fun User.selectOptions(element: WebElement, vararg values: String): User {
    javascriptExecutor.executeScript(
        "await user.selectOptions(arguments[0], arguments[1])", element, values
    )
    return this
}

fun User.selectOptions(element: WebElement, vararg values: WebElement): User {
    javascriptExecutor.executeScript(
        "await user.selectOptions(arguments[0], arguments[1])", element, values
    )
    return this
}

/**
 * https://testing-library.com/docs/user-event/utility#-selectoptions-deselectoptions
 */
fun User.deselectOptions(element: WebElement, vararg values: String): User {
    javascriptExecutor.executeScript(
        "await user.deselectOptions(arguments[0], arguments[1])", element, values
    )
    return this
}

fun User.deselectOptions(element: WebElement, vararg values: WebElement): User {
    javascriptExecutor.executeScript(
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
    javascriptExecutor.executeScript(
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
    javascriptExecutor.executeScript(
        "await user.upload(arguments[0], new File(arguments[1], arguments[2], arguments[3]))",
        input,
        file.bits,
        file.name,
        file.options
    )
    return this
}

data class File(val bits: List<String>, val name: String, val options: Map<String, String> = emptyMap())
