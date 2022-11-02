package com.luissoares.userevent

import com.luissoares.DriverLifeCycle
import com.luissoares.isChecked
import com.luissoares.locators.ByRole
import com.luissoares.render
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.assertFalse

@ExtendWith(DriverLifeCycle::class)
class DoubleClickTest(private val driver: RemoteWebDriver) {

    @Test
    fun `double click`() {
        driver.render(
            """ <input type="checkbox" /> """
        )
        val checkbox = driver.findElement(ByRole("checkbox"))

        driver.user.dblClick(checkbox)

        assertFalse(checkbox.isChecked)
    }
}
