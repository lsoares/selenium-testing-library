package seleniumtestinglib.interactions

import seleniumtestinglib.*
import seleniumtestinglib.TL.By.labelText
import java.util.regex.Pattern
import java.util.regex.Pattern.CASE_INSENSITIVE
import kotlin.test.Test
import kotlin.test.assertEquals

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
        val input = driver.findElement(labelText(Pattern.compile("upload file", CASE_INSENSITIVE)))

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
