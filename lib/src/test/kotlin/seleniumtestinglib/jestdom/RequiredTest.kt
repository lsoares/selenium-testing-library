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
            <input aria-required />
            <input aria-required="true" />
            <input />
        """
        )

        expect(driver.findElements(By.tagName("input"))[0]).toBeRequired()
        expect(driver.findElements(By.tagName("input"))[1]).toBeRequired()
        expect(driver.findElements(By.tagName("input"))[2]).toBeRequired()
    }
}
