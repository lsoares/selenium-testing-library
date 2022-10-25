package com.luissoares

import org.openqa.selenium.WebDriver

fun WebDriver.getFromHtml(html: String) {
    get("data:text/html;charset=utf-8,$html")
}
