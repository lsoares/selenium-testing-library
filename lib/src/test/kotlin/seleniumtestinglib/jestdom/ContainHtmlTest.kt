package seleniumtestinglib.jestdom

import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.containsHtml
import seleniumtestinglib.driver
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ContainHtmlTest {

    @Test
    fun `contain html`() {
        driver.render("""<span data-testid="parent"><span data-testid="child"></span></span>""")

        val element = driver.findElement(testId( "parent"))

        assertTrue(element.containsHtml("""<span data-testid="child"></span>"""))
        assertTrue(element.containsHtml("<span data-testid='child' />"))
        assertFalse(element.containsHtml("<br />"))
    }
}
