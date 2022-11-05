package seleniumtestinglib.interactions

/**
 * https://testing-library.com/docs/user-event/clipboard
 */
fun User.copy(): User {
    javascriptExecutor.executeScript("await user.copy()")
    return this
}

fun User.cut(): User {
    javascriptExecutor.executeScript("await user.cut()")
    return this
}

// TODO receive DataTransfer
fun User.paste(clipboardData: String? = null): User {
    javascriptExecutor.executeScript("await user.paste(arguments[0])", clipboardData)
    return this
}
