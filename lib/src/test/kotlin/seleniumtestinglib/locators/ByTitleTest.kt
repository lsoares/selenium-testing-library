package seleniumtestinglib.locators

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.coreapi.TextMatch.Regex
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals

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
    fun regex() {
        driver.render("<div title='foobar'>Hello World!</div>")

        val result = driver.findElement(ByTitle(Regex("/FOO/i")))

        assertEquals("Hello World!", result.text)
    }
}
