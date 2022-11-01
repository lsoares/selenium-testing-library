package com.luissoares.userevent

import com.luissoares.DriverLifeCycle
import com.luissoares.locators.ByRole
import com.luissoares.render
import com.luissoares.userEvent
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class TypeTest(private val driver: RemoteWebDriver) {

    @Test
    fun click() {
        driver.render("<textarea></textarea>")
        val textArea = driver.findElement(ByRole("textbox"))

        driver.userEvent.type(textArea, "Hello,{enter}World!")

        assertEquals("Hello,\nWorld!", textArea.getAttribute("value"))
    }
}
