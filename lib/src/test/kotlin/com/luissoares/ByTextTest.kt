package com.luissoares

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.Test
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ByTextTest {
    init {
        WebDriverManager.chromedriver().setup()
    }

    private val driver = ChromeDriver(ChromeOptions().addArguments("--headless"))

    @Test
    fun `by text`() {
        driver.getFromHtml("<span>I accept</span><div></div>")

        val result = driver.findElement(ByText("I accept"))

        assertEquals("span", result.tagName)
    }

    @Test
    fun `with a selector`() {
        driver.getFromHtml("<span>Username</span><div>Username</div>")

        val result = driver.findElement(ByText("Username", selector = "div"))

        assertEquals("div", result.tagName)
    }

    @Test
    fun `not found`() {
        driver.getFromHtml("<span>Username</span><div>Username</div>")

        val result = runCatching {
            driver.findElement(ByText("abc"))
        }

        assertTrue(result.exceptionOrNull() is NoSuchElementException)
    }

    @Test
    fun `not found with selector`() {
        driver.getFromHtml("<span>Username</span><div>Username</div>")

        val result = runCatching {
            driver.findElement(ByText("Username", selector = "x"))
        }

        assertTrue(result.exceptionOrNull() is NoSuchElementException)
    }

    @Test
    fun `not exact`() {
        driver.getFromHtml("<span> Username </span><div>Username</div>")

        val result = driver.findElement(ByText("User", exact = false))

        assertEquals("span", result.tagName)
    }

    @Test
    fun `not exact with selector`() {
        driver.getFromHtml(
            """
            <div>
                <span>I accept</span>
                <p>I accept</p>
            </div>
            <p>I accept</p>
        """
        )

        val result = driver.findElement(ByText("accept", exact = false, selector = "div p"))

        assertEquals("p", result.tagName)
    }
}
