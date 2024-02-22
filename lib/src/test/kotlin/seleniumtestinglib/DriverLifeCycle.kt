package seleniumtestinglib

import io.github.bonigarcia.wdm.WebDriverManager.chromedriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver

internal val driver = chromedriver()
    .avoidBrowserDetection()
    .capabilities(ChromeOptions().addArguments("--headless"))
    .create() as RemoteWebDriver