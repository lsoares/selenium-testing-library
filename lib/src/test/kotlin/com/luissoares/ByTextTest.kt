package com.luissoares

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class ByTextTest(private val driver: RemoteWebDriver) {

    @Test
    fun `by text`() {
        driver.getFromHtml("<article><span>I accept</span><div></div></article>")

        val result = driver.findElement(ByText("I accept"))

        assertEquals("span", result.tagName)
    }

    @Test
    fun `with a selector`() {
        driver.getFromHtml("<article><span>Username</span><div>Username</div></article>")

        val result = driver.findElement(ByText("Username", selector = "div"))

        assertEquals("div", result.tagName)
    }

    @Test
    fun `not found`() {
        driver.getFromHtml("<span></span>")

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

    @Test
    fun `case insensitive`() {
        driver.getFromHtml("<p>I accept</p>")

        val result = driver.findElement(ByText("ACCEPT", exact = false))

        assertEquals("p", result.tagName)
    }
}
