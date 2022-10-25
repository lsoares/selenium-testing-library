package com.luissoares

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.Select
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
    fun `not exact`() {
        driver.getFromHtml("<input placeholder='username' />")
        driver.findElement(ByPlaceholderText("username")).sendKeys("selenium")

        val result = driver.findElement(ByDisplayValue("SELEN", exact = false))

        assertEquals("input", result.tagName)
    }

    @Test
    fun regex() {
        driver.getFromHtml("<input placeholder='username' />")
        driver.findElement(ByPlaceholderText("username")).sendKeys("selenium")

        val result = driver.findElement(ByDisplayValue("/selen/i", matchTextBy = TextMatchType.REGEX))

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
        driver.getFromHtml(
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
        driver.getFromHtml(
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
