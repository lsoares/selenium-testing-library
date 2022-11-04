package com.luissoares.userinteractions

import com.luissoares.DriverLifeCycle
import com.luissoares.interactions.keyboard
import com.luissoares.interactions.tab
import com.luissoares.interactions.user
import com.luissoares.locators.ByRole
import com.luissoares.render
import com.luissoares.value
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class KeyboardTest(private val driver: RemoteWebDriver) {

    @ParameterizedTest
    @MethodSource("test cases")
    fun another(keyboardInput: String, writtenText: String) {
        driver.render("<input />")
        driver.user.tab()

        driver.user.keyboard(keyboardInput)

        assertEquals(writtenText, driver.findElement(ByRole("textbox")).value)
    }

    private fun `test cases`() = setOf(
        Arguments.of("foo", "foo"),
        Arguments.of("{{a[[", "{a["),
        Arguments.of("{Shift}{f}{o}{o}", "foo"),
        Arguments.of("{\\}}", "}"),
        Arguments.of("[ShiftLeft][KeyF][KeyO][KeyO]", "foo"),
        Arguments.of("{a>5/}", "aaaaa"),
    )
}
