package com.luissoares.interactions

/**
 * https://testing-library.com/docs/user-event/keyboard
 */
fun User.keyboard(input: String) {
    javascriptExecutor.executeScript("await user.keyboard(arguments[0])", input)
}

/**
 * https://testing-library.com/docs/user-event/convenience#tab
 */
fun User.tab(shift: Boolean = false) {
    javascriptExecutor.executeScript("await user.tab(arguments[0])", mapOf("shift" to shift))
}
