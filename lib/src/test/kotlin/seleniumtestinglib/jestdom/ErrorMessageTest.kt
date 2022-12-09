package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.locators.ByLabelText
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.text.RegexOption.IGNORE_CASE

@ExtendWith(DriverLifeCycle::class)
class ErrorMessageTest(private val driver: RemoteWebDriver) {

    @Test
    fun `in the document`() {
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

        expect(timeInput).toHaveErrorMessage(
            "Invalid time: the time must be between 9:00 AM and 5:00 PM",
        )
        expect(timeInput).toHaveErrorMessage(Regex("invalid time", IGNORE_CASE))
        expect(timeInput).not.toHaveErrorMessage("Pikachu!")
    }
}
