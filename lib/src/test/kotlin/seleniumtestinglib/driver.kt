package seleniumtestinglib

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver

val driver by lazy {
    ChromeDriver(ChromeOptions().addArguments("--headless"))
}

fun RemoteWebDriver.render(body: String): User {
    get(body.asDataUrl())
    return user
}

fun String.asDataUrl() =
    """data:text/html;charset=utf-8,<html><body>$this</body></html>"""
