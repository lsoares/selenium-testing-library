package com.luissoares.userinteractions

import com.luissoares.DriverLifeCycle
import com.luissoares.interactions.pointer
import com.luissoares.interactions.user
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.Test

@ExtendWith(DriverLifeCycle::class)
class PointerTest(private val driver: RemoteWebDriver) {

    @Test
    fun movePointer() {
        driver.user.pointer("[MouseLeft>")
    }
}
