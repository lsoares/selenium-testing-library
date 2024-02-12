package seleniumtestinglib.interactions

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By.id
import org.openqa.selenium.By.tagName
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role.TEXTBOX
import seleniumtestinglib.render
import seleniumtestinglib.value
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class ClipboardTest(private val driver: RemoteWebDriver) {

    @Test
    fun paste() {
        driver
            .render("<input />")
            .tab()

        driver.user.paste("pasted value")

        assertEquals("pasted value", driver.findElement(ByRole(TEXTBOX)).value)
    }

    @Test
    fun cut() {
        driver
            .render("""<input id="a" />""")
            .type(driver.findElement(id("a")), "text")
            .pointer(
                mapOf("target" to driver.findElement(ByRole(TEXTBOX)), "offset" to 0, "keys" to "[MouseLeft>]"),
                mapOf("offset" to 5)
            )
        assertFalse(driver.findElement(id("a")).value?.toString()?.isEmpty() ?: true)

        driver.user.cut()

        assertTrue(driver.findElement(id("a")).value?.toString()?.isEmpty() ?: false)
    }

    @Disabled
    @Test
    fun `copy paste`() {
        driver.render(
            """<h1>heading: title</h1>
                     <input />"""
        )
        driver.user("writeToClipboard" to true).pointer(
            mapOf("target" to driver.findElement(tagName("h1")), "offset" to 7, "keys" to "[MouseLeft>]"),
            mapOf("offset" to 14)
        )
        driver.user.copy()
        driver.user.click(driver.findElement(ByRole(TEXTBOX)))

        driver.user.paste("sdsd")

        println(driver.findElement(ByRole(TEXTBOX)).value)
    }
}
