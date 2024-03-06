package seleniumtestinglib.jestdom

import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.driver
import seleniumtestinglib.hasStyle
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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

        assertTrue(button.hasStyle("display: none"))
        assertTrue(button.hasStyle("display" to "none"))

        val backgroundColor = when (System.getenv("BROWSER")) {
            "firefox" -> "rgb(255, 0, 0)"
            else -> "rgba(255, 0, 0, 1)"
        }
        assertTrue(
            button.hasStyle(
                """
              background-color: $backgroundColor;
              display: none;
            """
            )
        )
        assertTrue(
            button.hasStyle(
                "backgroundColor" to backgroundColor, // why?
                "display" to "none",
            )
        )
        assertFalse(
            button.hasStyle(
                "background-color" to "blue",
                "display" to "none",
            )
        )

        val wrongBackgroundColor = when (System.getenv("BROWSER")) {
            "firefox" -> "rgb(0, 0, 255)"
            else -> "rgba(0, 0, 255, 1)"
        }
        assertFalse(
            button.hasStyle(
                "backgroundColor" to wrongBackgroundColor,
                "display" to "none",
            )
        )
    }
}
