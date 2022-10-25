package com.luissoares

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver

class DriverLifeCycle : AfterAllCallback, ParameterResolver {
    init {
        WebDriverManager.chromedriver().setup()
    }

    private val driver = ChromeDriver(ChromeOptions().addArguments("--headless"))

    override fun afterAll(context: ExtensionContext) {
        driver.quit()
    }

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext) =
        parameterContext.parameter.type == RemoteWebDriver::class.java

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext) =
        driver
}
