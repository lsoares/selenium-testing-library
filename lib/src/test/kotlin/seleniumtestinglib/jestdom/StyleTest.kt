package seleniumtestinglib.jestdom

import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.driver
import seleniumtestinglib.expect
import seleniumtestinglib.render
import kotlin.test.Test

class StyleTest {

    @Test
    fun `with classes`() {
        driver.render(
            """<button data-testid="delete-button"
                             style="display: none; background-color: red">
                          Delete item
                    </button>"""
        )

        val button = driver.findElement(testId("delete-button"))

        expect(button).toHaveStyle("display: none")
        expect(button).toHaveStyle(mapOf("display" to "none"))
        expect(button).toHaveStyle(
            """
              background-color: rgba(255, 0, 0, 1);
              display: none;
            """
        )
        expect(button).toHaveStyle(
            mapOf(
                "backgroundColor" to "rgba(255, 0, 0, 1)", // why?
                "display" to "none",
            )
        )
        expect(button).not.toHaveStyle(
            mapOf(
                "background-color" to "blue",
                "display" to "none",
            )
        )
        expect(button).not.toHaveStyle(
            mapOf(
                "backgroundColor" to "rgba(0, 0, 255, 1)",
                "display" to "none",
            )
        )
    }
}
