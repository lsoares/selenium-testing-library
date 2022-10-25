package com.luissoares

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class ByLabelTextTest(private val driver: RemoteWebDriver) {

    @Test
    fun `by for`() {
        driver.getFromHtml(
            """
               <label for="username-input">Username</label>
               <input id="username-input" /> 
            """
        )

        val result = driver.findElement(ByLabelText("Username"))

        assertEquals("input", result.tagName)
    }

    @Test
    fun `by aria-labeled-by`() {
        driver.getFromHtml(
            """
               <label id="username-label">Username</label>
               <input aria-labelledby="username-label" />
            """
        )

        val result = driver.findElement(ByLabelText("Username"))

        assertEquals("input", result.tagName)
    }

    @Test
    @Disabled("TODO")
    fun `by wrapping label`() {
        driver.getFromHtml(
            """
                <label>
                  <span>Username</span>
                  <input />
                </label>
            """
        )

        val result = driver.findElement(ByLabelText("Username"))

        assertEquals("input", result.tagName)
    }
}
