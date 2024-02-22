package seleniumtestinglib.interactions

import com.google.common.io.Files
import org.junit.jupiter.api.Disabled
import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import seleniumtestinglib.driver
import seleniumtestinglib.locators.ByRole
import seleniumtestinglib.locators.Role.CheckBox
import seleniumtestinglib.render
import java.awt.Desktop
import java.io.File
import kotlin.test.Test

class GeneralTest {

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
            .click(driver.findElement(ByRole(CheckBox)))
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
