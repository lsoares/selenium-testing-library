package seleniumtestinglib.jestdom

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.driver
import seleniumtestinglib.expect
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.text.RegexOption.IGNORE_CASE

class AccessibleNameTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<img data-testid="x" src="" alt="accessible name" />""",
            """<svg data-testid="x"><title>accessible name</title></svg>""",
            """<input data-testid="x" title="accessible name" />""",
            """<button data-testid="x"><img src="" alt="accessible name" /></button>""",
        ]
    )
    fun `accessible name`(html: String) {
        driver.render(html)

        assertEquals("accessible name", driver.findElement(testId( "x")).accessibleName)
        expect(driver.findElement(testId( "x"))).toHaveAccessibleName()
        expect(driver.findElement(testId( "x"))).toHaveAccessibleName("accessible name")
        expect(driver.findElement(testId( "x"))).not.toHaveAccessibleName("abc")
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<img data-testid="x" src="" alt="" />""",
            """<p><img data-testid="x" src="" alt="" /> Test content</p>""",
            """<div><svg data-testid="x"></svg></div>""",
        ]
    )
    fun `not accessible name`(html: String) {
        driver.render(html)

        assertTrue(driver.findElement(testId( "x")).accessibleName.isNullOrBlank())
        expect(driver.findElement(testId( "x"))).not.toHaveAccessibleName()
        expect(driver.findElement(testId( "x"))).not.toHaveAccessibleName("abc")
    }

    @Test
    fun regex() {
        driver.render(
            """<span data-testid="x" aria-description="Accessible description"></span>"""

        )

        expect(driver.findElement(testId( "x"))).toHaveAccessibleName(Regex("accessible", IGNORE_CASE))
        expect(driver.findElement(testId( "x"))).not.toHaveAccessibleName(Regex("nope", IGNORE_CASE))
    }

    @Test
    fun function() {
        driver.render("""<img data-testid="x" src="" alt="accessible name" />""")

        expect(driver.findElement(testId( "x"))).toHaveAccessibleName {
            it.endsWith("name")
        }
        expect(driver.findElement(testId( "x"))).not.toHaveAccessibleName {
            it.endsWith("NAME")
        }
    }
}
