package seleniumtestinglib.interactions

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role.TextBox
import seleniumtestinglib.render
import seleniumtestinglib.value
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class KeyboardTest(private val driver: RemoteWebDriver) {

    @ParameterizedTest
    @MethodSource("test cases")
    fun another(keyboardInput: String, writtenText: String) {
        driver.render("<input />")
            .tab()

        driver.user.keyboard(keyboardInput)

        assertEquals(writtenText, driver.findElement(ByRole(TextBox)).value)
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
