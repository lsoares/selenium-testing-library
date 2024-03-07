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

    @Test
    fun `upload multiple`() {
        driver.render(
            """
            <div>
              <label for="file-uploader">Upload file:</label>
              <input id="file-uploader" type="file" multiple />
            </div>
        """
        )
        val input = driver.findElement(labelText("upload file").exact(false))

        driver.user.upload(
            input,
            File(listOf("hello1"), "hello.png", mapOf("type" to "image/png")),
            File(listOf("hello2"), "hello.jpg", mapOf("type" to "image/jpeg")),
        )

        val upload = input.files
        assertEquals(2, input.files.size)
        assertEquals("hello.png", upload.first()["name"])
        assertEquals("image/png", upload.first()["type"])
        assertEquals("hello1".length.toLong(), upload.first()["size"])
        assertEquals("hello.jpg", upload.last()["name"])
        assertEquals("image/jpeg", upload.last()["type"])
        assertEquals("hello2".length.toLong(), upload.last()["size"])
    }
}
