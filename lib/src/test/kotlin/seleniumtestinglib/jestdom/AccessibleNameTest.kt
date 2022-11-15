package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.coreapi.ByType.TestId
import seleniumtestinglib.coreapi.getBy
import seleniumtestinglib.render
import kotlin.test.Test

@ExtendWith(DriverLifeCycle::class)
class AccessibleNameTest(private val driver: RemoteWebDriver) {

    @Test
    fun `accessible name`() {
        driver.render(
            """
                  <img data-testid="img-alt" src="" alt="Test alt" />
        """
        )

        expect(driver.getBy(TestId, "img-alt")).toHaveAccessibleName()
    }
}
