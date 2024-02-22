package seleniumtestinglib.jestdom

import seleniumtestinglib.driver
import seleniumtestinglib.queries.LocatorType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.Test

class AttributeTest {

    @Test
    fun `has attribute`() {
        driver.render("""<button data-testid="ok-button" type="submit" disabled>ok</button>""")

        val button = driver.getBy(TestId, "ok-button")

        expect(button).toHaveAttribute("disabled")
        expect(button).toHaveAttribute("type", "submit")
        expect(button).toHaveAttribute("type") {
            it.first() == 's'
        }
        expect(button).not.toHaveAttribute("type") {
            it.last() == 's'
        }
        expect(button).not.toHaveAttribute("type", "button")
        expect(button).not.toHaveAttribute("at1")
    }
}
