package seleniumtestinglib.jestdom

import seleniumtestinglib.driver
import seleniumtestinglib.queries.LocatorType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.text.RegexOption.IGNORE_CASE

class TextContentTest {

    @Test
    fun `text content`() {
        driver.render("""<span data-testid="text-content">Text Content</span>""")

        val element = driver.getBy(TestId, "text-content")

        expect(element).toHaveTextContent("Content")
        expect(element).toHaveTextContent(Regex("^Text Content$"))
        expect(element).toHaveTextContent(Regex("content$", IGNORE_CASE))
        expect(element).not.toHaveTextContent("content")
    }
}
