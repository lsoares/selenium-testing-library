package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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

        val required = driver.findElements(By.tagName("input")).first()
        assertTrue(required.isRequired)
        expect(required).toBeRequired()
        val notRequired = driver.findElements(By.tagName("input")).last()
        assertFalse(notRequired.isRequired)
        expect(notRequired).not.toBeRequired()
    }
}
