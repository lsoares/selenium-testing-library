package com.luissoares.interactions

import org.openqa.selenium.WebElement

/**
 * https://testing-library.com/docs/user-event/pointer
 */
fun User.pointer(vararg inputs: String) {
    javascriptExecutor.executeScript("await user.pointer(arguments[0])", inputs)
}

private val pointerValidOptions = setOf("target", "keys", "offset", "pointerName", "node")
fun User.pointer(vararg inputs: Map<String, Any>) {
    inputs.forEach { input ->
        input.forEach {
            require(it.key in pointerValidOptions) { "${it.key} is not valid (valid keys are $pointerValidOptions)" }
        }
    }
    javascriptExecutor.executeScript("await user.pointer(arguments[0])", inputs)
}

/**
 * https://testing-library.com/docs/user-event/convenience#click
 */
fun User.click(element: WebElement) {
    javascriptExecutor.executeScript("await user.click(arguments[0])", element)
}

/**
 * https://testing-library.com/docs/user-event/convenience#dblclick
 */
fun User.dblClick(element: WebElement) {
    javascriptExecutor.executeScript("await user.dblClick(arguments[0])", element)
}

/**
 * https://testing-library.com/docs/user-event/convenience#tripleclick
 */
fun User.tripleClick(element: WebElement) {
    javascriptExecutor.executeScript("await user.tripleClick(arguments[0])", element)
}

/**
 * https://testing-library.com/docs/user-event/convenience#hover
 */
fun User.hover(element: WebElement) {
    javascriptExecutor.executeScript("await user.hover(arguments[0])", element)
}

/**
 * https://testing-library.com/docs/user-event/convenience#unhover
 */
fun User.unhover(element: WebElement) {
    javascriptExecutor.executeScript("await user.unhover(arguments[0])", element)
}
