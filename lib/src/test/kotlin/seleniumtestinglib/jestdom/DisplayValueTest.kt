package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.displayValue
import seleniumtestinglib.queries.ByType.LabelText
import seleniumtestinglib.queries.ByType.Role
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import java.util.stream.Stream
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class DisplayValueTest(private val driver: RemoteWebDriver) {

    @ParameterizedTest
    @MethodSource("tests")
    fun `display value`(html: String, expectedDisplayValue: String, expectedDisplayValueByRegex: Regex) {
        driver.render(html)

        val element = driver.getBy(LabelText, "example")
        assertEquals(expectedDisplayValue, element.displayValue)
        expect(element).toHaveDisplayValue(expectedDisplayValue)
        expect(element).toHaveDisplayValue(expectedDisplayValueByRegex)
    }

    private fun tests() = Stream.of(
        arguments(
            """<label for="input-example">example</label>
               <input type="text" id="input-example" value="Luca" />""",
            "Luca",
            Regex("Luc"),
        ),
        arguments(
            """<label for="textarea-example">example</label>
               <textarea id="textarea-example">An example description here.</textarea>""",
            "An example description here.",
            Regex("example"),
        ),
        arguments(
            """<label for="single-select-example">example</label>
                <select id="single-select-example">
                  <option value="">Select a fruit...</option>
                  <option value="banana">Banana</option>
                  <option value="ananas">Ananas</option>
                  <option value="avocado">Avocado</option>
                </select>""",
            "Select a fruit...",
            Regex("Select"),
        ),
    )

    @Test
    fun `select multiple`() {
        driver.render(
            """<select id="multiple-select-example" multiple>
                        <option value="">Select a fruit...</option>
                        <option value="banana" selected>Banana</option>
                        <option value="ananas">Ananas</option>
                        <option value="avocado" selected>Avocado</option>
                     </select>"""
        )

        val element = driver.getBy(Role, "listbox")
        expect(element).toHaveDisplayValue(listOf("Banana", "Avocado"))
    }
}
