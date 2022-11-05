package com.luissoares.interactions

/**
 * https://testing-library.com/docs/user-event/clipboard
 */
fun User.copy() {
    javascriptExecutor.executeScript("await user.copy()")
}

fun User.cut() {
    javascriptExecutor.executeScript("await user.cut()")
}

// TODO receive DataTransfer
fun User.paste(clipboardData: String? = null) {
    javascriptExecutor.executeScript("await user.paste(arguments[0])", clipboardData)
}
