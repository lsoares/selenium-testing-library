package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.*
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import seleniumtestinglib.value
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class ValueTest(private val driver: RemoteWebDriver) {

    @Test
    fun valid() {
        driver.render(
            """
            <input type="text" value="text" data-testid="input-text" />
            <input type="number" value="5" data-testid="input-number" />
            <input type="text" data-testid="input-empty" />
            <select multiple data-testid="select-number">
              <option value="first">First Value</option>
              <option value="second" selected>Second Value</option>
              <option value="third" selected>Third Value</option>
            </select> 
        """
        )

        val textInput = driver.getBy(TestId, "input-text")
        val numberInput = driver.getBy(TestId, "input-number")
        val emptyInput = driver.getBy(TestId, "input-empty")
        val selectInput = driver.getBy(TestId, "select-number")

        assertEquals("text", textInput.value)
        expect(textInput).toHaveValue("text")
        assertEquals(5, numberInput.value)
        expect(numberInput).toHaveValue(5)
        assertEquals("", emptyInput.value)
        expect(emptyInput).not.toHaveValue()
        assertEquals(listOf("second", "third"), selectInput.value)
        expect(selectInput).toHaveValue(listOf("second", "third"))
    }
}
