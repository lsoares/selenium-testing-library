package seleniumtestinglib.userinteractions

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.interactions.*
import seleniumtestinglib.locators.ByRole
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

        assertEquals("pasted value", driver.findElement(ByRole("textbox")).value)
    }

    @Test
    fun cut() {
        driver
            .render("""<input id="a" />""")
            .type(driver.findElement(By.id("a")), "text")
            .pointer(
                mapOf("target" to driver.findElement(ByRole("textbox")), "offset" to 0, "keys" to "[MouseLeft>]"),
                mapOf("offset" to 5)
            )
        assertFalse(driver.findElement(By.id("a")).value?.isEmpty() ?: true)

        driver.user.cut()

        assertTrue(driver.findElement(By.id("a")).value?.isEmpty() ?: false)
    }

    @Disabled
    @Test
    fun `copy paste`() {
        driver.render(
            """
            <h1>heading: title</h1>
            <input />
        """
        )
        driver.user("writeToClipboard" to true).pointer(
            mapOf("target" to driver.findElement(By.tagName("h1")), "offset" to 7, "keys" to "[MouseLeft>]"),
            mapOf("offset" to 14)
        )
        driver.user.copy()
        driver.user.click(driver.findElement(ByRole("textbox")))

        driver.user.paste("sdsd")

        println(driver.findElement(ByRole("textbox")).value)
    }
}
