package com.luissoares

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class ByDisplayValueTest(private val driver: RemoteWebDriver) {

    @Test
    fun input() {
        driver.getFromHtml("<input placeholder='username' />")
        driver.findElement(ByPlaceholderText("username")).sendKeys("selenium")

        val result = driver.findElement(ByDisplayValue("selenium"))

        assertEquals("input", result.tagName)
    }

    @Test
    fun textarea() {
        driver.getFromHtml("<textarea id='x'></textarea>")
        driver.findElement(By.tagName("textarea")).sendKeys("selenium")

        val result = driver.findElement(ByDisplayValue("selenium"))

        assertEquals("textarea", result.tagName)
    }

    @Test
    fun select() {
        driver.getFromHtml("""
            <select>
              <option value="">State</option>
              <option value="AL">Alabama</option>
              <option selected value="AK">Alaska</option>
              <option value="AZ">Arizona</option>
            </select> 
        """)

        val result = driver.findElement(ByDisplayValue("Alaska"))

        assertEquals("select", result.tagName)
    }

     @Test
    fun `select multiple`() {
        driver.getFromHtml("""
            <select multiple>
              <option value="">State</option>
              <option selected value="AL">Alabama</option>
              <option value="AK">Alaska</option>
              <option selected value="AZ">Arizona</option>
            </select> 
        """)

        val result = driver.findElement(ByDisplayValue("Arizona"))

        assertEquals("select", result.tagName)
    }
}
