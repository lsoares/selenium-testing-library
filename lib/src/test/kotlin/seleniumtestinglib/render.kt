package seleniumtestinglib

import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.interactions.User
import seleniumtestinglib.interactions.user

fun RemoteWebDriver.render(body: String): User {
    get(
        """data:text/html;charset=utf-8,
            <html>
                <head></head>
                <body>$body</body>
            </html>
        """
    )
    return user
}
