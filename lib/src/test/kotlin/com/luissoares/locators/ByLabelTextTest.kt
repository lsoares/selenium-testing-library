package com.luissoares.locators

import com.luissoares.DriverLifeCycle
import com.luissoares.TextMatchType
import com.luissoares.render
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class ByLabelTextTest(private val driver: RemoteWebDriver) {

    @Test
    fun `by for`() {
        driver.render(
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
        driver.render(
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
        driver.render(
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
        driver.render("<input aria-label='Username' />")

        val result = driver.findElement(ByLabelText("Username"))

        assertEquals("input", result.tagName)
    }

    @Test
    fun `not exact with selector`() {
        driver.render(
            """
            <input aria-label='Username' />
            <span><div class="x"><input aria-label='Username' /></div></span>
            """
        )

        val result = driver.findElement(ByLabelText("user", exact = false, selector = "span .x input"))

        assertEquals("input", result.tagName)
    }

    @Test
    fun regex() {
        driver.render("<input aria-label='Username' />")

        val result = driver.findElement(ByLabelText("/user/i", matchTextBy = TextMatchType.REGEX))

        assertEquals("input", result.tagName)
    }
}
