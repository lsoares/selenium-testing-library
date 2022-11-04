package com.luissoares.interactions

import org.openqa.selenium.WebElement

/**
 * https://testing-library.com/docs/user-event/utility#clear
 */
fun User.clear(element: WebElement) {
    javascriptExecutor.executeScript("await user.clear(arguments[0])", element)
}

/**
 * https://testing-library.com/docs/user-event/utility#-selectoptions-deselectoptions
 */
fun User.selectOptions(element: WebElement, values: List<SelectOption>) {
    javascriptExecutor.executeScript(
        "await user.selectOptions(arguments[0], arguments[1])", element,
        values.map(SelectOption::value)
    )
}

/**
 * https://testing-library.com/docs/user-event/utility#-selectoptions-deselectoptions
 */
fun User.deselectOptions(element: WebElement, values: List<SelectOption>) {
    javascriptExecutor.executeScript(
        "await user.deselectOptions(arguments[0], arguments[1])", element,
        values.map(SelectOption::value)
    )
}

sealed class SelectOption(open val value: Any) {
    class ByValue(override val value: String) : SelectOption(value)
    class ByWebElement(override val value: WebElement) : SelectOption(value)
}

/**
 * https://testing-library.com/docs/user-event/utility#type
 */
fun User.type(
    element: WebElement, text: String,
    skipClick: Boolean? = null,
    skipAutoClose: Boolean? = null,
    initialSelectionStart: Int? = null,
    initialSelectionEnd: Int? = null,
) {
    javascriptExecutor.executeScript(
        "await user.type(arguments[0], arguments[1], arguments[2])",
        element,
        text,
        mapOf(
            "skipClick" to skipClick,
            "skipAutoClose" to skipAutoClose,
            "initialSelectionStart" to initialSelectionStart,
            "initialSelectionEnd" to initialSelectionEnd,
        ).filterValues { it != null }
    )
}

/**
 * https://testing-library.com/docs/user-event/utility#upload
 */
fun User.upload(input: WebElement, file: File) {
    javascriptExecutor.executeScript(
        "await user.upload(arguments[0], new File(arguments[1], arguments[2], arguments[3]))",
        input,
        file.bits,
        file.name,
        file.options
    )
}

data class File(val bits: List<String>, val name: String, val options: Map<String, String> = emptyMap())
