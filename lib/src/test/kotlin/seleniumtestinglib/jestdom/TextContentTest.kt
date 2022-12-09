package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.text.RegexOption.IGNORE_CASE

@ExtendWith(DriverLifeCycle::class)
class TextContentTest(private val driver: RemoteWebDriver) {

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
