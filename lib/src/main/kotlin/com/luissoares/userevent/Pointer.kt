package com.luissoares.userevent

import org.openqa.selenium.WebElement

/**
 * https://testing-library.com/docs/user-event/pointer
 */
fun User.pointer(input: String) {
    javascriptExecutor.executeScript("await user.pointer(arguments[0])", input)
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
fun User.tripleClick() {
    TODO()
}

/**
 * https://testing-library.com/docs/user-event/convenience#hover
 */
fun User.hover() {
    TODO()
}

/**
 * https://testing-library.com/docs/user-event/convenience#unhover
 */
fun User.unhover() {
    TODO()
}
