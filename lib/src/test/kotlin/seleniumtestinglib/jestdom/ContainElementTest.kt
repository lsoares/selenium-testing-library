package seleniumtestinglib.jestdom

import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.driver
import seleniumtestinglib.expect
import seleniumtestinglib.render
import kotlin.test.Test

class ContainElementTest {

    @Test
    fun `contain element`() {
        driver.render("""<span data-testid="ancestor"><span data-testid="descendant"></span></span>""")

        val ancestor = driver.findElement(testId( "ancestor"))
        val descendant = driver.findElement(testId( "descendant"))

        expect(ancestor).toContainElement(descendant)
        expect(descendant).not.toContainElement(ancestor)
    }
}
