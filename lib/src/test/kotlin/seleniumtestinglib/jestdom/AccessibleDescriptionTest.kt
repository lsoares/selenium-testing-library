package seleniumtestinglib.jestdom

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.By
import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.accessibleDescription
import seleniumtestinglib.driver
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AccessibleDescriptionTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<span data-testid="x" aria-description="accessible description"></span>""",
            """<img src="logo.jpg" data-testid="x" alt="Company logo" aria-describedby="t1" />
                <span id="t1" role="presentation">accessible description</span>""",
            """<a data-testid="x" href="/" aria-label="Home page" 
                  title="accessible description">Start</a>""",
        ]
    )
    fun `accessible description`(html: String) {
        driver.render(html)

        assertEquals("accessible description", driver.findElement(testId("x")).accessibleDescription)
        assertEquals(driver.findElement(testId("x")).accessibleDescription, "accessible description")
    }

    @Test
    fun `no accessible description`() {
        driver.render("""<img src="avatar.jpg" alt="User profile pic" />""")

        assertNull(driver.findElement(By.tagName("img")).accessibleDescription)
    }
}
