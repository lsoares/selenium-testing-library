package seleniumtestinglib.coreapi

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

fun RemoteWebDriver.getBy(
    by: ByType,
    textMatch: TextMatch,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery(
    queryType = QueryType.Get,
    plural = false,
    by = by,
    textMatch = textMatch,
    options = options,
) as WebElement

fun RemoteWebDriver.getBy(
    by: ByType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = getBy(by, TextMatch.String(text), options)

fun RemoteWebDriver.queryBy(
    by: ByType,
    textMatch: TextMatch,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery(
    queryType = QueryType.Query,
    plural = false,
    by = by,
    textMatch = textMatch,
    options = options,
) as WebElement?

fun RemoteWebDriver.queryBy(
    by: ByType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = queryBy(by, TextMatch.String(text), options)

fun RemoteWebDriver.findBy(
    by: ByType,
    textMatch: TextMatch,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery(
    queryType = QueryType.Find,
    plural = false,
    by = by,
    textMatch = textMatch,
    options = options,
) as WebElement
