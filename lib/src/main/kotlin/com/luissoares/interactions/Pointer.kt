package com.luissoares.interactions

import org.openqa.selenium.WebElement

/**
 * https://testing-library.com/docs/user-event/pointer
 */
fun User.pointer(vararg element: String) {
    javascriptExecutor.executeScript("await user.pointer(arguments[0])", element)
}

fun User.pointer(vararg element: Map<String, Any>) {
    javascriptExecutor.executeScript("await user.pointer(arguments[0])", element)
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
