package seleniumtestinglib.jestdom

import seleniumtestinglib.TL.By.labelText
import seleniumtestinglib.driver
import seleniumtestinglib.errorMessage
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals

class ErrorMessageTest {

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
        val timeInput = driver.findElement(labelText("please enter a start time", exact = false))

        assertEquals("Invalid time: the time must be between 9:00 AM and 5:00 PM", timeInput.errorMessage)
    }
}
