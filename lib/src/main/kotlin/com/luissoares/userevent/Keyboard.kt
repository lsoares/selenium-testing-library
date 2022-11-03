package com.luissoares.userevent

/**
 * https://testing-library.com/docs/user-event/keyboard
 */
fun User.keyboard() {
    TODO()
}

/**
 * https://testing-library.com/docs/user-event/convenience#tab
 */
fun User.tab(shift: Boolean = false) {
    javascriptExecutor.executeScript("await user.tab(arguments[0])", mapOf("shift" to shift))
}
