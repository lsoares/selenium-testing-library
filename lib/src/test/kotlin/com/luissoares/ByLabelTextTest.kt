package com.luissoares

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.Test
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

    @Test
    fun `aria label`() {
        driver.getFromHtml("<input aria-label='Username' />")

        val result = driver.findElement(ByLabelText("Username"))

        assertEquals("input", result.tagName)
    }

    @Test
    fun `not exact with selector`() {
        driver.getFromHtml(
            """
            <input aria-label='Username' />
            <span><div class="x"><input aria-label='Username' /></div></span>
            """
        )

        val result = driver.findElement(ByLabelText("user", exact = false, selector = "span .x input"))

        assertEquals("input", result.tagName)
    }
}
