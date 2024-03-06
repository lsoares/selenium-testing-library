package seleniumtestinglib.jestdom

import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.containsElement
import seleniumtestinglib.driver
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ContainElementTest {

    @Test
    fun `contain element`() {
        driver.render("""<span data-testid="ancestor"><span data-testid="descendant"></span></span>""")

        val ancestor = driver.findElement(testId( "ancestor"))
        val descendant = driver.findElement(testId( "descendant"))

        assertTrue(ancestor.containsElement(descendant))
        assertFalse(descendant.containsElement(ancestor))
    }
}
