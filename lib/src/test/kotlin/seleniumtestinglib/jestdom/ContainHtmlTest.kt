package seleniumtestinglib.jestdom

import seleniumtestinglib.driver
import seleniumtestinglib.queries.LocatorType.TestId
import seleniumtestinglib.queries.queryBy
import seleniumtestinglib.render
import kotlin.test.Test

class ContainHtmlTest {

    @Test
    fun `contain html`() {
        driver.render("""<span data-testid="parent"><span data-testid="child"></span></span>""")

        val element = driver.queryBy(TestId, "parent")
        expect(element).toContainHtml("""<span data-testid="child"></span>""")
        expect(element).toContainHtml("<span data-testid='child' />")
        expect(element).not.toContainHtml("<br />")
    }
}
