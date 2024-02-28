package seleniumtestinglib.interactions

import com.google.common.io.Files
import org.junit.jupiter.api.Disabled
import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import seleniumtestinglib.*
import seleniumtestinglib.PointerOption.Keys
import seleniumtestinglib.PointerOption.Target
import seleniumtestinglib.Role.CheckBox
import seleniumtestinglib.TL.By.role
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
            .click(driver.findElement(role(CheckBox)))
            .tab()
            .keyboard("name")
            .tab()
            .keyboard("{Enter}")
            .pointer(
                mapOf(
                    Keys to "[MouseLeft]",
                    Target to driver.findElement(By.tagName("select")),
                )
            )
        val file = File.createTempFile("test", ".png")
        Files.write(driver.getScreenshotAs(OutputType.BYTES), file)
        Desktop.getDesktop().open(file)
    }
}
