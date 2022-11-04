package com.luissoares.userinteractions

import com.luissoares.DriverLifeCycle
import com.luissoares.interactions.clear
import com.luissoares.interactions.user
import com.luissoares.locators.ByRole
import com.luissoares.render
import com.luissoares.value
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class PasteTest(private val driver: RemoteWebDriver) {

    @Test
    fun paste() {
        driver.render("<textarea>Hello, World!</textarea>")
        val input = driver.findElement(ByRole("textbox"))
        assertEquals("Hello, World!", input.value)

        driver.user.clear(input)

        assertEquals("", input.value)
    }
}
