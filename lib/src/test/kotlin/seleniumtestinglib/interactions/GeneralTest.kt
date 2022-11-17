package seleniumtestinglib.interactions

import com.google.common.io.Files
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.render
import java.awt.Desktop
import java.io.File
import kotlin.test.Test

@ExtendWith(DriverLifeCycle::class)
class GeneralTest(private val driver: RemoteWebDriver) {

    @Test
    @Disabled
    fun chaining() {
        driver
            .render(
                """ <div>
                        <input type="checkbox" />
                        <input type="text" />
                        <select>
                            <option value='a'>A<option>
                            <option value='b'>C<option>
                        </select>
                      </div> """
            )
            .tab()
            .click(driver.findElement(ByRole("checkbox")))
            .tab()
            .keyboard("name")
            .tab()
            .keyboard("{Enter}")
            .pointer(
                mapOf(
                    "keys" to "[MouseLeft]",
                    "target" to driver.findElement(By.tagName("select")),
                )
            )
        val file = File.createTempFile("test", ".png")
        Files.write(driver.getScreenshotAs(OutputType.BYTES), file)
        Desktop.getDesktop().open(file)
    }
}
