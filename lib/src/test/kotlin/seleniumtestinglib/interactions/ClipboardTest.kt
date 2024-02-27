package seleniumtestinglib.interactions

import org.junit.jupiter.api.Disabled
import org.openqa.selenium.By.id
import org.openqa.selenium.By.tagName
import seleniumtestinglib.*
import seleniumtestinglib.PointerOption.*
import seleniumtestinglib.PointerOption.Target
import seleniumtestinglib.RoleType.TextBox
import seleniumtestinglib.TL.By.role
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ClipboardTest {

    @Test
    fun paste() {
        driver
            .render("<input />")
            .tab()

        driver.user.paste("pasted value")

        assertEquals("pasted value", driver.findElement(role(TextBox)).value)
    }

    @Test
    fun cut() {
        driver
            .render("""<input id="a" />""")
            .type(driver.findElement(id("a")), "text")
            .pointer(
                mapOf(Target to driver.findElement(role(TextBox)), Offset to 0, Keys to "[MouseLeft>]"),
                mapOf(Offset to 5)
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
        driver.user(UserOption.WriteToClipboard to true).pointer(
            mapOf(Target to driver.findElement(tagName("h1")), Offset to 7, Keys to "[MouseLeft>]"),
            mapOf(Offset to 14)
        )
        driver.user.copy()
        driver.user.click(driver.findElement(role(TextBox)))

        driver.user.paste("sdsd")

//        println(driver.findElement(role(TextBox)).value)
    }
}
