package seleniumtestinglib.jestdom

import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.driver
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
        assertEquals(5, numberInput.value)
        assertEquals("", emptyInput.value)
        assertEquals(emptyInput.value, "")
        assertEquals(listOf("second", "third"), selectInput.value)
        assertEquals(selectInput.value, listOf("second", "third"))
    }
}
