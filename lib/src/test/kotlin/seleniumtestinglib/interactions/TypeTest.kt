package seleniumtestinglib.interactions

import org.openqa.selenium.By
import seleniumtestinglib.*
import seleniumtestinglib.TL.By.role
import seleniumtestinglib.Role.TextBox
import kotlin.test.Test
import kotlin.test.assertEquals

class TypeTest {

    @Test
    fun type() {
        driver.render("<textarea></textarea>")
        val textArea = driver.findElement(role(TextBox))

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
            element = driver.findElement(role(TextBox)),
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
