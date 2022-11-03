package com.luissoares.userinteractions

import com.luissoares.*
import com.luissoares.interactions.File
import com.luissoares.interactions.upload
import com.luissoares.interactions.user
import com.luissoares.locators.ByLabelText
import com.luissoares.locators.TextMatchType.REGEX
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class UploadTest(private val driver: RemoteWebDriver) {

    @Test
    fun upload() {
        driver.render(
            """
            <div>
              <label for="file-uploader">Upload file:</label>
              <input id="file-uploader" type="file" />
            </div> 
        """
        )
        val input = driver.findElement(ByLabelText("/upload file/i", matchTextBy = REGEX))

        driver.user.upload(
            input,
            File(listOf("hello"), "hello.png", mapOf("type" to "image/png")),
        )

        val upload = input.files(driver).single()
        assertEquals("hello.png", upload["name"])
        assertEquals("image/png", upload["type"])
        assertEquals("hello".length.toLong(), upload["size"])
    }
}
