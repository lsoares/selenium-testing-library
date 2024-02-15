package seleniumtestinglib.interactions

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.files
import seleniumtestinglib.locators.ByLabelText
import seleniumtestinglib.queries.JsType.Companion.asJsExpression
import seleniumtestinglib.render
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
        val input = driver.findElement(ByLabelText("/upload file/i".asJsExpression()))

        driver.user.upload(
            input,
            File(listOf("hello"), "hello.png", mapOf("type" to "image/png")),
        )

        val upload = input.files.single()
        assertEquals("hello.png", upload["name"])
        assertEquals("image/png", upload["type"])
        assertEquals("hello".length.toLong(), upload["size"])
    }

    // TODO: multiple files
}
