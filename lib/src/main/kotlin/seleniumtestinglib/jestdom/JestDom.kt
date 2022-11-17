package seleniumtestinglib.jestdom

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement
import seleniumtestinglib.ensureScript
import seleniumtestinglib.queries.JsType

val WebElement.isDisabled get() = executeJestDomQuery("toBeDisabled")
val WebElement.isEmptyDomElement get() = executeJestDomQuery("toBeEmptyDOMElement")
val WebElement.isInTheDocument get() = executeJestDomQuery("toBeInTheDocument")
val WebElement.isInvalid get() = executeJestDomQuery("toBeInvalid")
val WebElement.isRequired get() = executeJestDomQuery("toBeRequired")
val WebElement.isValid get() = executeJestDomQuery("toBeValid")
val WebElement.isVisible get() = executeJestDomQuery("toBeVisible")
fun WebElement.containsElement(element: WebElement? = null) = executeJestDomQuery("toContainElement", element)
fun WebElement.containsHtml(htmlText: String) = executeJestDomQuery("toContainHTML", htmlText)
fun WebElement.hasAccessibleDescription(expectedAccessibleDescription: JsType?) =
    executeJestDomQuery("toHaveAccessibleDescription")

fun WebElement.hasAccessibleName(expectedAccessibleName: JsType?) = executeJestDomQuery("toHaveAccessibleName")

private fun WebElement.executeJestDomQuery(domFunction: String, vararg args: Any?): Boolean {
    val driver = (this as RemoteWebElement).wrappedDriver as RemoteWebDriver
    driver.ensureScript("jest-dom.js", "window.matchers?.toBeInTheDocument")
    val argumentPlaceholders = args.withIndex().joinToString(prefix = ", ") { "arguments[${it.index + 1}]" }
    return driver.executeScript(
        "return matchers.$domFunction(arguments[0]$argumentPlaceholders).pass",
        this, *args
    ) as Boolean
}
