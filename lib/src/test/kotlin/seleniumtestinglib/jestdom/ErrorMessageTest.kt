package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.errorMessage
import seleniumtestinglib.locators.ByLabelText
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.text.RegexOption.IGNORE_CASE

@ExtendWith(DriverLifeCycle::class)
class ErrorMessageTest(private val driver: RemoteWebDriver) {

    @Test
    fun `error message`() {
        driver.render(
            """
                <label for="startTime"> Please enter a start time for the meeting: </label>
                <input id="startTime"
                       type="text"
                       aria-errormessage="msgId"
                       aria-invalid="true"
                       value="11:30 PM" />
                <span id="msgId" aria-live="assertive" style="visibility:visible">
                  Invalid time: the time must be 
                  between 9:00 AM and 5:00 PM
                </span>
            """
        )
        val timeInput = driver.findElement(ByLabelText("please enter a start time", exact = false))

        assertEquals("Invalid time: the time must be between 9:00 AM and 5:00 PM", timeInput.errorMessage)
        expect(timeInput).toHaveErrorMessage(
            "Invalid time: the time must be between 9:00 AM and 5:00 PM",
        )
        expect(timeInput).toHaveErrorMessage(Regex("invalid time", IGNORE_CASE))
        expect(timeInput).not.toHaveErrorMessage("Pikachu!")
        expect(timeInput).toHaveErrorMessage {
            it.orEmpty().contains("time must be between")
        }
        expect(timeInput).not.toHaveErrorMessage {
            it.orEmpty().contains("time must not be between")
        }
    }
}
