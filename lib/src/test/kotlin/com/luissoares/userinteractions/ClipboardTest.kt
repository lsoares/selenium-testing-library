package com.luissoares.userinteractions

import com.luissoares.DriverLifeCycle
import com.luissoares.interactions.*
import com.luissoares.locators.ByRole
import com.luissoares.render
import com.luissoares.selection
import com.luissoares.value
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class ClipboardTest(private val driver: RemoteWebDriver) {

    @Test
    fun paste() {
        driver.render("<input />")
        driver.user.tab()

        driver.user.paste("pasted value")

        assertEquals("pasted value", driver.findElement(ByRole("textbox")).value)
    }

    @Disabled
    @Test
    fun `copy paste`() {
        driver.render(
            """
            <h1>heading: title</h1>
            <input />
        """
        )
        driver.user("writeToClipboard" to true).pointer(
            mapOf("target" to driver.findElement(By.tagName("h1")), "offset" to 7, "keys" to "[MouseLeft>]"),
            mapOf("offset" to 14)
        )
        println(driver.selection)
        driver.user.copy()
        driver.user.click(driver.findElement(ByRole("textbox")))

        driver.user.paste("sdsd")

        println(driver.findElement(ByRole("textbox")).value)
    }
}
