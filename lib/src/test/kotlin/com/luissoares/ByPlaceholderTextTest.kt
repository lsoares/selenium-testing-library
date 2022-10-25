package com.luissoares

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.text.RegexOption.IGNORE_CASE

@ExtendWith(DriverLifeCycle::class)
class ByPlaceholderTextTest(private val driver: RemoteWebDriver) {

    @ParameterizedTest
    @ValueSource(strings = ["Username", "USERNAME", "user"])
    fun `not exact`(text: String) {
        driver.getFromHtml("<input placeholder='Username' />")

        val result = driver.findElement(ByPlaceholderText(text, exact = false))

        assertEquals("input", result.tagName)
    }

    @Test
    fun `exact found`() {
        driver.getFromHtml("<input placeholder='Username' />")

        val result = driver.findElement(ByPlaceholderText("Username", exact = true))

        assertEquals("input", result.tagName)
    }

    @Test
    fun `exact not found`() {
        driver.getFromHtml("<input placeholder='Username' />")

        val result = runCatching {
            driver.findElement(ByPlaceholderText("username", exact = true))
        }

        assertTrue(result.exceptionOrNull() is NoSuchElementException)
    }

    @Test
    @Disabled("fix me")
    fun `regex search`() {
        driver.getFromHtml("<input placeholder='Username' />")

        val result = driver.findElement(
            ByPlaceholderText(regexText = Regex("user", IGNORE_CASE))
        )

        assertEquals("input", result.tagName)
    }
}
