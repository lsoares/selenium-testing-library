package com.luissoares.userevent

import com.luissoares.*
import com.luissoares.TextMatchType.REGEX
import com.luissoares.locators.ByLabelText
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

        driver.userEvent.upload(
            input,
            UserEvent.File(listOf("hello"), "hello.png", mapOf("type" to "image/png")),
        )

        val upload = input.files(driver).single()
        assertEquals("hello.png", upload["name"])
        assertEquals("image/png", upload["type"])
        assertEquals("hello".length.toLong(), upload["size"])
    }
}