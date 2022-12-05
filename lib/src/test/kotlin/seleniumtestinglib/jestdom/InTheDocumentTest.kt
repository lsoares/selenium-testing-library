package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.Test

@ExtendWith(DriverLifeCycle::class)
class InTheDocumentTest(private val driver: RemoteWebDriver) {

    @Test
    fun `in the document`() {
        driver.render(
            """<span data-testid="html-element"><span>Html Element</span></span>"""
        )

        expect(driver.getBy(TestId, "html-element")).toBeInTheDocument()
//        expect(driver.queryBy(TestId, "not-there")).not.toBeInTheDocument()
    }
}
