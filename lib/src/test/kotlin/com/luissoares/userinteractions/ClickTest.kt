package com.luissoares.userinteractions

import com.luissoares.DriverLifeCycle
import com.luissoares.interactions.click
import com.luissoares.interactions.user
import com.luissoares.isChecked
import com.luissoares.locators.ByLabelText
import com.luissoares.render
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.Test
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class ClickTest(private val driver: RemoteWebDriver) {

    @Test
    fun click() {
        driver.render(
            """ <div>
                        <label for="checkbox">Check</label>
                        <input id="checkbox" type="checkbox" />
                      </div> """
        )
        val checkbox = driver.findElement(ByLabelText("Check"))

        driver.user().click(checkbox)

        assertTrue(checkbox.isChecked)
    }
}
