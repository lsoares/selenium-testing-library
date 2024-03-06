package seleniumtestinglib.locators

import org.openqa.selenium.By.tagName
import org.openqa.selenium.support.ui.Select
import seleniumtestinglib.JsFunction
import seleniumtestinglib.TL.By.displayValue
import seleniumtestinglib.TL.By.placeholderText
import seleniumtestinglib.driver
import seleniumtestinglib.render
import java.util.regex.Pattern
import java.util.regex.Pattern.CASE_INSENSITIVE
import kotlin.test.Test
import kotlin.test.assertEquals

class ByDisplayValueTest {

    @Test
    fun input() {
        driver.render("<input placeholder='username' />")
        driver.findElement(placeholderText("username")).sendKeys("selenium")

        val result = driver.findElement(displayValue("selenium"))

        assertEquals("input", result.tagName)
    }

    @Test
    fun `not exact`() {
        driver.render("<input placeholder='username' />")
        driver.findElement(placeholderText("username")).sendKeys("selenium")

        val result = driver.findElement(displayValue("SELEN", exact = false))

        assertEquals("input", result.tagName)
    }

    @Test
    fun regex() {
        driver.render("<input placeholder='username' />")
        driver.findElement(placeholderText("username")).sendKeys("selenium")

        val result = driver.findElement(displayValue(Pattern.compile("selenium", CASE_INSENSITIVE)))

        assertEquals("input", result.tagName)
    }

    @Test
    fun function() {
        driver.render("<input placeholder='username' />")
        driver.findElement(placeholderText("username")).sendKeys("selenium")

        val result = driver.findElement(displayValue(JsFunction("c => c.startsWith('selen')")))

        assertEquals("input", result.tagName)
    }

    @Test
    fun textarea() {
        driver.render("<textarea id='x'></textarea>")
        driver.findElement(tagName("textarea")).sendKeys("selenium")

        val result = driver.findElement(displayValue("selenium"))

        assertEquals("textarea", result.tagName)
    }

    @Test
    fun select() {
        driver.render(
            """
            <select>
              <option value="">State</option>
              <option value="AL">Alabama</option>
              <option value="AK">Alaska</option>
              <option value="AZ">Arizona</option>
            </select> 
        """
        )
        driver.findElement(tagName("select")).let(::Select).selectByVisibleText("Alaska")

        val result = driver.findElement(displayValue("Alaska"))

        assertEquals("select", result.tagName)
    }

    @Test
    fun `select multiple`() {
        driver.render(
            """
            <select multiple>
              <option value="">State</option>
              <option value="AL">Alabama</option>
              <option value="AK">Alaska</option>
              <option value="AZ">Arizona</option>
            </select> 
        """
        )
        driver.findElement(tagName("select")).let(::Select).selectByVisibleText("Alabama")
        driver.findElement(tagName("select")).let(::Select).selectByVisibleText("Arizona")

        val result = driver.findElement(displayValue("Arizona"))

        assertEquals("select", result.tagName)
    }
}
