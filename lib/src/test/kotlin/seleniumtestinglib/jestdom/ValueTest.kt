package seleniumtestinglib.jestdom

import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.driver
import seleniumtestinglib.expect
import seleniumtestinglib.render
import seleniumtestinglib.value
import kotlin.test.Test
import kotlin.test.assertEquals

class ValueTest {

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

        val textInput = driver.findElement(testId("input-text"))
        val numberInput = driver.findElement(testId("input-number"))
        val emptyInput = driver.findElement(testId("input-empty"))
        val selectInput = driver.findElement(testId("select-number"))

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
