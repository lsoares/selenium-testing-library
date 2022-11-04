package com.luissoares.userinteractions

import com.luissoares.DriverLifeCycle
import com.luissoares.interactions.dblClick
import com.luissoares.interactions.pointer
import com.luissoares.interactions.user
import com.luissoares.isChecked
import com.luissoares.locators.ByRole
import com.luissoares.render
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.Test
import kotlin.test.assertFalse

@ExtendWith(DriverLifeCycle::class)
class DoubleClickTest(private val driver: RemoteWebDriver) {

    @Test
    fun `double click`() {
        driver.render("<input type='checkbox' />")
        val checkbox = driver.findElement(ByRole("checkbox"))

        driver.user.dblClick(checkbox)

        assertFalse(checkbox.isChecked)
    }

    @Test
    fun `equivalent to double click`() {
        driver.render("<input type='checkbox' />")
        val checkbox = driver.findElement(ByRole("checkbox"))

        driver.user.pointer(
            mapOf("target" to checkbox),
            mapOf("keys" to "[MouseLeft][MouseLeft]", "target" to checkbox),
        )

        assertFalse(checkbox.isChecked)
    }
}
