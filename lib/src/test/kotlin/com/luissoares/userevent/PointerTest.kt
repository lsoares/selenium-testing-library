package com.luissoares.userevent

import com.luissoares.DriverLifeCycle
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
