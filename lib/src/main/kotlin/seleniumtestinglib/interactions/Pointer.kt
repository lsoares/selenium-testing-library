package seleniumtestinglib.interactions

import org.openqa.selenium.WebElement

/**
 * https://testing-library.com/docs/user-event/pointer
 */
fun User.pointer(vararg inputs: String): User {
    javascriptExecutor.executeScript("await user.pointer(arguments[0])", inputs)
    return this
}

private val pointerValidOptions = setOf("target", "keys", "offset", "pointerName", "node")
fun User.pointer(vararg inputs: Map<String, Any>): User {
    inputs.forEach { input ->
        input.forEach {
            require(it.key in pointerValidOptions) { "${it.key} is not valid (valid keys are $pointerValidOptions)" }
        }
    }
    javascriptExecutor.executeScript("await user.pointer(arguments[0])", inputs)
    return this
}

/**
 * https://testing-library.com/docs/user-event/convenience#click
 */
fun User.click(element: WebElement): User {
    javascriptExecutor.executeScript("await user.click(arguments[0])", element)
    return this
}

/**
 * https://testing-library.com/docs/user-event/convenience#dblclick
 */
fun User.dblClick(element: WebElement): User {
    javascriptExecutor.executeScript("await user.dblClick(arguments[0])", element)
    return this
}

/**
 * https://testing-library.com/docs/user-event/convenience#tripleclick
 */
fun User.tripleClick(element: WebElement): User {
    javascriptExecutor.executeScript("await user.tripleClick(arguments[0])", element)
    return this
}

/**
 * https://testing-library.com/docs/user-event/convenience#hover
 */
fun User.hover(element: WebElement): User {
    javascriptExecutor.executeScript("await user.hover(arguments[0])", element)
    return this
}

/**
 * https://testing-library.com/docs/user-event/convenience#unhover
 */
fun User.unhover(element: WebElement): User {
    javascriptExecutor.executeScript("await user.unhover(arguments[0])", element)
    return this
}
