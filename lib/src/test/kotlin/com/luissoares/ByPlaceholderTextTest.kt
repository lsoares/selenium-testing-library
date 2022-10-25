package com.luissoares

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ByPlaceholderTextTest {
    init {
        WebDriverManager.chromedriver().setup()
    }

    private val driver = ChromeDriver(ChromeOptions().addArguments("--headless"))

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
}
