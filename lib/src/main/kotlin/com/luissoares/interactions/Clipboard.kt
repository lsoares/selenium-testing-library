package com.luissoares.interactions

/**
 * https://testing-library.com/docs/user-event/clipboard
 */
@Suppress("UNCHECKED_CAST")
fun User.copy(): Map<String, Any?> =
    javascriptExecutor.executeScript("return await user.copy()") as Map<String, Any?>

// TODO return DataTransfer
fun User.cut() {
    javascriptExecutor.executeScript("await user.cut()")
}

// TODO receive DataTransfer
fun User.paste(clipboardData: String? = null) {
    javascriptExecutor.executeScript("await user.paste(arguments[0])", clipboardData)
}
