package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.coreapi.ByType.TestId
import seleniumtestinglib.coreapi.getBy
import seleniumtestinglib.coreapi.queryBy
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class InTheDocumentTest(private val driver: RemoteWebDriver) {

    @Test
    fun `in the document`() {
        driver.render(
            """<span data-testid="html-element"><span>Html Element</span></span>"""
        )

        val inTheDoc = driver.getBy(TestId, "html-element")
        assertTrue(inTheDoc.isInTheDocument)
        expect(inTheDoc).toBeInTheDocument()
        val notThere = driver.queryBy(TestId, "not-there")
        expect(notThere).not.toBeInTheDocument()
    }
}
