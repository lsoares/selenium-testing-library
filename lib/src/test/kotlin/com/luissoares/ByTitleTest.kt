package com.luissoares

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class ByTitleTest(private val driver: RemoteWebDriver) {

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
