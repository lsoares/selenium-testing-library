package seleniumtestinglib.jestdom

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import seleniumtestinglib.Role
import seleniumtestinglib.TL.By.role
import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.driver
import seleniumtestinglib.isDisabled
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DisabledTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<button data-testid="test1" type="submit" disabled>submit</button>""",
            """<fieldset disabled><input type="text" data-testid="test1" /></fieldset>""",
        ]
    )
    fun disabled(html: String) {
        driver.render(html)
        val element = driver.findElement(testId("test1"))

        assertTrue(element.isDisabled)
    }

    @Test
    fun `links cant be disabled`() {
        driver.render("""<a href="/" disabled>link</a>""")
        val element = driver.findElement(role(Role.Link))

        assertFalse(element.isDisabled)
    }
}
