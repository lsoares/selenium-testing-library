package seleniumtestinglib.interactions

import org.openqa.selenium.By
import seleniumtestinglib.driver
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role.TextBox
import seleniumtestinglib.render
import seleniumtestinglib.value
import kotlin.test.Test
import kotlin.test.assertEquals

class TypeTest {

    @Test
    fun type() {
        driver.render("<textarea></textarea>")
        val textArea = driver.findElement(ByRole(TextBox))

        driver.user.type(textArea, "Hello,{enter}World!")

        assertEquals("Hello,\nWorld!", textArea.value)
    }

    @Test
    fun `send options`() {
        driver.render("<textarea></textarea><span id='spy'></span>")
        driver.user()
        driver.executeScript(
            """
           userType = user.type
           user.type = (elem, text, options) => {
              document.getElementById('spy').innerText = elem.tagName + ' ' + text + ' ' + JSON.stringify(options)
              userType(elem, text, options)
           }
        """
        )

        driver.user.type(
            element = driver.findElement(ByRole(TextBox)),
            text = "foo",
            skipAutoClose = true,
            skipClick = true,
            initialSelectionEnd = 1,
            initialSelectionStart = 3,
        )

        assertEquals(
            """TEXTAREA foo {"initialSelectionEnd":1,"initialSelectionStart":3,"skipAutoClose":true,"skipClick":true}""",
            driver.findElement(By.id("spy")).text,
        )
    }
}
