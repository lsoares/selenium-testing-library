package seleniumtestinglib.interactions

/**
 * https://testing-library.com/docs/user-event/keyboard
 */
fun User.keyboard(input: String): User {
    driver.executeScript("await user.keyboard(arguments[0])", input)
    return this
}

/**
 * https://testing-library.com/docs/user-event/convenience#tab
 */
fun User.tab(shift: Boolean = false): User {
    driver.executeScript("await user.tab(arguments[0])", mapOf("shift" to shift))
    return this
}
