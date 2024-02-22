package seleniumtestinglib.jestdom

import seleniumtestinglib.driver
import seleniumtestinglib.queries.LocatorType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.queries.queryBy
import seleniumtestinglib.render
import kotlin.test.Test

class InTheDocumentTest {

    @Test
    fun `in the document`() {
        driver.render(
            """<span data-testid="html-element"><span>Html Element</span></span>"""
        )

        expect(driver.getBy(TestId, "html-element")).toBeInTheDocument()
        expect(driver.queryBy(TestId, "not-there")).not.toBeInTheDocument()
    }
}
