package com.luissoares.userinteractions

import com.luissoares.*
import com.luissoares.interactions.type
import com.luissoares.interactions.user
import com.luissoares.locators.ByRole
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class TypeTest(private val driver: RemoteWebDriver) {

    @Test
    fun type() {
        driver.render("<textarea></textarea>")
        val textArea = driver.findElement(ByRole("textbox"))

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
            element = driver.findElement(ByRole("textbox")),
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
