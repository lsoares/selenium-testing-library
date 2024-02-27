package seleniumtestinglib.jestdom

import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.driver
import seleniumtestinglib.expect
import seleniumtestinglib.render
import kotlin.test.Test

class InTheDocumentTest {

    @Test
    fun `in the document`() {
        driver.render(
            """<span data-testid="html-element"><span>Html Element</span></span>"""
        )

        expect(driver.findElement(testId("html-element"))).toBeInTheDocument()
    }
}
