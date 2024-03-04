package seleniumtestinglib

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.RemoteWebDriver
import java.lang.System.getenv

val driver: RemoteWebDriver by lazy {
    when (getenv("BROWSER")) {
        "firefox" -> FirefoxDriver(FirefoxOptions().addArguments("--headless"))
        else -> ChromeDriver(ChromeOptions().addArguments("--headless"))
    }
}

fun RemoteWebDriver.render(body: String): User {
    get(body.asDataUrl())
    return user
}

fun String.asDataUrl() =
    """data:text/html;charset=utf-8,<html><body>$this</body></html>"""
