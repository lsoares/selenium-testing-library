package seleniumtestinglib

import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.interactions.User
import seleniumtestinglib.interactions.user

fun RemoteWebDriver.render(body: String): User {
    get(body.asDataUrl())
    return user
}

internal fun String.asDataUrl() =
    """data:text/html;charset=utf-8,<html><body>${this}</body></html>"""
