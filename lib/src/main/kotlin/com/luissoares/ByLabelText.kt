package com.luissoares

import org.openqa.selenium.By
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement

data class ByLabelText(
    private val text: String,
) : By() {
    override fun findElements(context: SearchContext): List<WebElement> =
        getWebDriver(context).findElements(cssSelector("label")).flatMap { labelElement ->
            getWebDriver(context).waitUntil {
                listOfNotNull(
                        labelElement.getAttribute("for")?.let { forAttribute ->
                            it.findElements(id(forAttribute))
                        },
                        labelElement.getAttribute("id")?.let { idAttribute ->
                            it.findElements(cssSelector("[aria-labelledby='${idAttribute}']"))
                        },
                    ).flatten()
            } + getWebDriver(context).findElements(
                xpath("//label//*[text()='$text']/ancestor::label[1]//input")
            )
        }
}
