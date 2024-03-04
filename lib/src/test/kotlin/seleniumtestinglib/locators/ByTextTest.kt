package seleniumtestinglib.locators

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.NoSuchElementException
import seleniumtestinglib.TL
import seleniumtestinglib.asJsExpression
import seleniumtestinglib.driver
import seleniumtestinglib.render
import java.util.regex.Pattern
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ByTextTest {

    @Test
    fun `by text`() {
        driver.render("<article><span>I accept</span><div></div></article>")

        val result = driver.findElement(TL.text("I accept"))

        assertEquals("span", result.tagName)
    }

    @Test
    fun `ensure quotes are escaped`() {
        driver.render(""""<article><span>with quotes"'`</span><div></div></article>""")

        val result = driver.findElement(TL.By.text("""with quotes"'`"""))

        assertEquals("span", result.tagName)
    }

    @Test
    fun `with a selector`() {
        driver.render("<article><span>Username</span><div>Username</div></article>")

        val result = driver.findElement(TL.text("Username", selector = "div"))

        assertEquals("div", result.tagName)
    }

    @Test
    fun `not found`() {
        driver.render("<span></span>")

        val result = runCatching {
            driver.findElement(TL.text("abc"))
        }

        assertTrue(result.exceptionOrNull() is NoSuchElementException)
    }

    @Test
    fun `not found with selector`() {
        driver.render("<span>Username</span><div>Username</div>")

        val result = runCatching {
            driver.findElement(TL.text("Username", selector = "x"))
        }

        assertTrue(result.exceptionOrNull() is NoSuchElementException)
    }

    @Test
    fun `not exact with selector`() {
        driver.render(
            """
            <div>
                <span>I accept</span>
                <p>I accept</p>
            </div>
            <p>I accept</p>
        """
        )

        val result = driver.findElement(TL.text("accept", exact = false, selector = "div p"))

        assertEquals("p", result.tagName)
    }

    @Test
    fun regex() {
        driver.render("<p>I accept</p>")

        val result = driver.findElement(TL.text(Pattern.compile("ACCEPT", Pattern.CASE_INSENSITIVE)))

        assertEquals("p", result.tagName)
    }

    @Test
    fun function() {
        driver.render("<p>Hello World</p>")

        val result = driver.findElement(
            TL.text("(content, element) => content.startsWith('Hello') && element.tagName == 'P'".asJsExpression())
        )

        assertEquals("p", result.tagName)
    }

    @Test
    fun `case insensitive`() {
        driver.render("<p>I accept</p>")

        val result = driver.findElement(TL.text("ACCEPT", exact = false))

        assertEquals("p", result.tagName)
    }

    @ParameterizedTest
    @MethodSource("ignore values")
    fun ignore(ignore: String, expectedFound: Int) {
        driver.render(
            """
            <p>I accept</p>
            <script>I accept</script>
            <style>I accept</style>
            """
        )

        val result = driver.findElements(TL.text("I accept", ignore = ignore))

        assertEquals(expectedFound, result.size)
    }

    private fun `ignore values`() = setOf(
        of("", 3),
        of("style", 2),
        of("style,script", 1),
        of("style,script,p", 0),
    )

    @Test
    fun `ignore with boolean`() {
        driver.render(
            """
            <p>I accept</p>
            <script>I accept</script>
            <style>I accept</style>
            """
        )

        val result = driver.findElements(TL.text("I accept").ignore(false))

        assertEquals(3, result.size)
    }

    @Test
    fun normalizer() {
        driver.render("<p>I accept</p>")

        val result = driver.findElement(
            TL.text("I accept123", normalizer = "str => str + '123'".asJsExpression())
        )

        assertEquals("p", result.tagName)
    }
}
