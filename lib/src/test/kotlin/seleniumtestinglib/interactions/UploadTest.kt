package seleniumtestinglib.interactions

import seleniumtestinglib.driver
import seleniumtestinglib.files
import seleniumtestinglib.locators.ByLabelText
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.text.RegexOption.IGNORE_CASE

class UploadTest {

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
        val input = driver.findElement(ByLabelText(Regex("upload file", IGNORE_CASE)))

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
