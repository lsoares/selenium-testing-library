package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.Test

@ExtendWith(DriverLifeCycle::class)
class HaveAttributeTest(private val driver: RemoteWebDriver) {

    @Test
    fun `has attribute`() {
        driver.render("""<button data-testid="ok-button" type="submit" disabled>ok</button>""")

        val button = driver.getBy(TestId, "ok-button")

        expect(button).toHaveAttribute("disabled")
        expect(button).toHaveAttribute("type", "submit")
        expect(button).not.toHaveAttribute("type", "button")
        expect(button).not.toHaveAttribute("at1")
    }
}
