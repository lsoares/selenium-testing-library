package seleniumtestinglib.locators

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import seleniumtestinglib.driver
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.text.RegexOption.IGNORE_CASE

class ByDisplayValueTest {

    @Test
    fun input() {
        driver.render("<input placeholder='username' />")
        driver.findElement(ByPlaceholderText("username")).sendKeys("selenium")

        val result = driver.findElement(ByDisplayValue("selenium"))

        assertEquals("input", result.tagName)
    }

    @Test
    fun `not exact`() {
        driver.render("<input placeholder='username' />")
        driver.findElement(ByPlaceholderText("username")).sendKeys("selenium")

        val result = driver.findElement(ByDisplayValue("SELEN", exact = false))

        assertEquals("input", result.tagName)
    }

    @Test
    fun `not exact 2`() {
        driver.render("<input placeholder='username' />")
        driver.findElement(ByPlaceholderText("username")).sendKeys("selenium")

        val result = driver.findElement(ByDisplayValue("SELEN").inexact())

        assertEquals("input", result.tagName)
    }

    @Test
    fun regex() {
        driver.render("<input placeholder='username' />")
        driver.findElement(ByPlaceholderText("username")).sendKeys("selenium")

        val result = driver.findElement(ByDisplayValue(Regex("selen", IGNORE_CASE)))

        assertEquals("input", result.tagName)
    }

    @Test
    fun textarea() {
        driver.render("<textarea id='x'></textarea>")
        driver.findElement(By.tagName("textarea")).sendKeys("selenium")

        val result = driver.findElement(ByDisplayValue("selenium"))

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
        driver.findElement(By.tagName("select")).let(::Select).selectByVisibleText("Alaska")

        val result = driver.findElement(ByDisplayValue("Alaska"))

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
        driver.findElement(By.tagName("select")).let(::Select).selectByVisibleText("Alabama")
        driver.findElement(By.tagName("select")).let(::Select).selectByVisibleText("Arizona")

        val result = driver.findElement(ByDisplayValue("Arizona"))

        assertEquals("select", result.tagName)
    }
}
