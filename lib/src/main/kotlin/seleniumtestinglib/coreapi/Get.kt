package seleniumtestinglib.coreapi

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.coreapi.TextMatch.Companion.asJsString

fun RemoteWebDriver.getBy(
    by: ByType,
    textMatch: TextMatch,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery(
    queryType = QueryType.Get,
    all = false,
    by = by,
    textMatch = textMatch,
    options = options,
) as WebElement

fun RemoteWebDriver.getBy(
    by: ByType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = getBy(by, text.asJsString(), options)

@Suppress("UNCHECKED_CAST")
fun RemoteWebDriver.getAllBy(
    by: ByType,
    textMatch: TextMatch,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery(
    queryType = QueryType.Get,
    all = true,
    by = by,
    textMatch = textMatch,
    options = options,
) as List<WebElement>

fun RemoteWebDriver.getAllBy(
    by: ByType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = getAllBy(by, text.asJsString(), options)
