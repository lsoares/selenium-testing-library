package seleniumtestinglib.interactions

/**
 * https://testing-library.com/docs/user-event/clipboard
 */
fun User.copy(): User {
    driver.executeScript("await user.copy()")
    return this
}

fun User.cut(): User {
    driver.executeScript("await user.cut()")
    return this
}

// TODO receive DataTransfer
fun User.paste(clipboardData: String? = null): User {
    driver.executeScript("await user.paste(arguments[0])", clipboardData)
    return this
}
