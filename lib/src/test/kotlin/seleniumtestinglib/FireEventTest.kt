package seleniumtestinglib

import seleniumtestinglib.Event.Change
import seleniumtestinglib.RoleType.TextBox
import seleniumtestinglib.TL.By.role
import kotlin.test.Test
import kotlin.test.assertEquals

class FireEventTest {

    @Test
    fun `fire event`() {
        driver.render("<input />")
        val input = driver.findElement(role(TextBox))

        input.fireEvent(Change, mapOf("target" to mapOf("value" to "2020-05-24")))

        assertEquals("2020-05-24", input.value)
    }
}
