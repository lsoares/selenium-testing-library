package seleniumtestinglib

import io.github.bonigarcia.wdm.WebDriverManager.chromedriver
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver

class DriverLifeCycle : AfterAllCallback, ParameterResolver {

    private val driver = chromedriver()
        .capabilities(ChromeOptions().addArguments("--headless"))
        .create()

    override fun afterAll(context: ExtensionContext) {
        driver.quit()
    }

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext) =
        parameterContext.parameter.type == RemoteWebDriver::class.java

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): WebDriver =
        driver
}
