package seleniumtestinglib

import org.openqa.selenium.remote.RemoteWebDriver

val RemoteWebDriver.selection: String
    get() = executeScript("return window.getSelection().toString()") as String
