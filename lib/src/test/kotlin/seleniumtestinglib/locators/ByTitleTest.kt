package seleniumtestinglib.locators

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.JsType.Companion.asJsExpression
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.text.RegexOption.IGNORE_CASE

@ExtendWith(DriverLifeCycle::class)
class ByTitleTest(private val driver: RemoteWebDriver) {

    @Test
    fun `by title`() {
        driver.render("<div title='foobar'>Hello World!</div>")

        val result = driver.findElement(ByTitle("foobar"))

        assertEquals("Hello World!", result.text)
    }

    @Test
    fun `by title on svg`() {
        driver.render(
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
        driver.render("<div title='foobar'>Hello World!</div>")

        val result = driver.findElement(ByTitle("FOO", exact = false))

        assertEquals("Hello World!", result.text)
    }
    @Test
    fun `not exact 2`() {
        driver.render("<div title='foobar'>Hello World!</div>")

        val result = driver.findElement(ByTitle("FOO").inexact())

        assertEquals("Hello World!", result.text)
    }

    @Test
    fun regex() {
        driver.render("<div title='foobar'>Hello World!</div>")

        val result = driver.findElement(ByTitle("/FOO/i".asJsExpression()))

        assertEquals("Hello World!", result.text)
    }

    @Test
    fun `regex - alternative`() {
        driver.render("<div title='foobar'>Hello World!</div>")

        val result = driver.findElement(ByTitle(Regex("FOO", IGNORE_CASE)))

        assertEquals("Hello World!", result.text)
    }
}
