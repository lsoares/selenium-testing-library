package com.luissoares.userinteractions

import com.luissoares.*
import com.luissoares.interactions.type
import com.luissoares.interactions.user
import com.luissoares.locators.ByRole
import org.junit.jupiter.api.extension.ExtendWith
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
}
