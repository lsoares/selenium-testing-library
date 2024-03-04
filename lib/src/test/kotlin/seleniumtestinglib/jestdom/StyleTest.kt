package seleniumtestinglib.jestdom

import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.driver
import seleniumtestinglib.expect
import seleniumtestinglib.render
import kotlin.test.Test

class StyleTest {

    @Test
    fun `to have style`() {
        driver.render(
            """<button data-testid="delete-button"
                             style="display: none; background-color: red">
                          Delete item
                    </button>"""
        )

        val button = driver.findElement(testId("delete-button"))

        expect(button).toHaveStyle("display: none")
        expect(button).toHaveStyle(mapOf("display" to "none"))

        val backgroundColor = when (System.getenv("BROWSER")) {
            "firefox" -> "rgb(255, 0, 0)"
            else -> "rgba(255, 0, 0, 1)"
        }
        expect(button).toHaveStyle(
            """
              background-color: $backgroundColor;
              display: none;
            """
        )
        expect(button).toHaveStyle(
            mapOf(
                "backgroundColor" to backgroundColor, // why?
                "display" to "none",
            )
        )
        expect(button).not.toHaveStyle(
            mapOf(
                "background-color" to "blue",
                "display" to "none",
            )
        )

        val wrongBackgroundColor = when (System.getenv("BROWSER")) {
            "firefox" -> "rgb(0, 0, 255)"
            else -> "rgba(0, 0, 255, 1)"
        }
        expect(button).not.toHaveStyle(
            mapOf(
                "backgroundColor" to wrongBackgroundColor,
                "display" to "none",
            )
        )
    }
}
