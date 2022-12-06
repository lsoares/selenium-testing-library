package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.render

@ExtendWith(DriverLifeCycle::class)
class RequiredTest(private val driver: RemoteWebDriver) {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<input required />""",
            """<input type="password" required>""",
            """<input type="file" required>""",
            """<input type="date" required>""",
            """<input aria-required="true" />""",
            """<input required aria-required="false" />""",
            """<select required></select>""",
            """<select multiple required></select>""",
            """<textarea required></textarea>""",
            """<div role="tree" aria-required="true"></div>""",
        ]
    )
    fun required(html: String) {
        driver.render(html)

        expect(driver.findElement(By.cssSelector("input,select,textarea,div"))).toBeRequired()
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<input aria-required="false" />""",
            """<input />""",
            """<input type="image" required />""",
            """<div role="tree" required></div>""",
        ]
    )
    fun `not required`(html: String) {
        driver.render(html)

        expect(driver.findElement(By.cssSelector("input,div"))).not.toBeRequired()
    }
}
