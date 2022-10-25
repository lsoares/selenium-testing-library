package com.luissoares

import org.openqa.selenium.WebDriver

fun WebDriver.getFromHtml(body: String) {
    get(
        """data:text/html;charset=utf-8,
            <html>
                <head></head>
                <body>$body</body>
            </html>
        """
    )
}
