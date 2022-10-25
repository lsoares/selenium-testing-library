package com.luissoares

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.Test
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import kotlin.test.assertEquals

class ByTitleTest {
    init {
        WebDriverManager.chromedriver().setup()
    }

    private val driver = ChromeDriver(ChromeOptions().addArguments("--headless"))

    @Test
    fun `by title`() {
        driver.getFromHtml("<div title='foobar'>Hello World!</div>")

        val result = driver.findElement(ByTitle("foobar"))

        assertEquals("Hello World!", result.text)
    }

    @Test
    fun `by title on svg`() {
        driver.getFromHtml(
            """<svg>
              <title>foobar</title>
              <g><path /></g>
            </svg>"""
        )

        val result = driver.findElement(ByTitle("foobar"))

        assertEquals("foobar", result.text)
    }

    @Test
    fun `not exact`() {
        driver.getFromHtml("<div title='foobar'>Hello World!</div>")

        val result = driver.findElement(ByTitle("FOO", exact = false))

        assertEquals("Hello World!", result.text)
    }
}
