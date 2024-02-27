package seleniumtestinglib.jestdom

import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.driver
import seleniumtestinglib.expect
import seleniumtestinglib.render
import kotlin.test.Test

class ContainHtmlTest {

    @Test
    fun `contain html`() {
        driver.render("""<span data-testid="parent"><span data-testid="child"></span></span>""")

        val element = driver.findElement(testId( "parent"))
        expect(element).toContainHtml("""<span data-testid="child"></span>""")
        expect(element).toContainHtml("<span data-testid='child' />")
        expect(element).not.toContainHtml("<br />")
    }
}
