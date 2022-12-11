package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.queryBy
import seleniumtestinglib.render
import kotlin.test.Test

@ExtendWith(DriverLifeCycle::class)
class ContainHtmlTest(private val driver: RemoteWebDriver) {

    @Test
    fun `contain html`() {
        driver.render("""<span data-testid="parent"><span data-testid="child"></span></span>""")

        val element = driver.queryBy(TestId, "parent")
        expect(element).toContainHtml("""<span data-testid="child"></span>""")
        expect(element).toContainHtml("<span data-testid='child' />")
        expect(element).not.toContainHtml("<br />")
    }
}
