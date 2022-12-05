package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.render
import kotlin.test.Test

@ExtendWith(DriverLifeCycle::class)
class RequiredTest(private val driver: RemoteWebDriver) {

    @Test
    fun required() {
        driver.render(
            """
            <input required />
            <input />
        """
        )

        expect(driver.findElements(By.tagName("input")).first()).toBeRequired()
        expect(driver.findElements(By.tagName("input")).last()).not.toBeRequired()
    }
}
