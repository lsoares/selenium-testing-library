package seleniumtestinglib.coreapi

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

@Suppress("UNCHECKED_CAST")
fun RemoteWebDriver.getAllBy(
    by: ByType,
    textMatch: TextMatch,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery(
    queryType = QueryType.Get,
    plural = true,
    by = by,
    textMatch = textMatch,
    options = options,
) as List<WebElement>

fun RemoteWebDriver.getAllBy(
    by: ByType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = getAllBy(by, TextMatch.String(text), options)

@Suppress("UNCHECKED_CAST")
fun RemoteWebDriver.queryAllBy(
    by: ByType,
    textMatch: TextMatch,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery(
    queryType = QueryType.Query,
    plural = true,
    by = by,
    textMatch = textMatch,
    options = options,
) as List<WebElement>

fun RemoteWebDriver.queryAllBy(
    by: ByType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = queryAllBy(by, TextMatch.String(text), options)

@Suppress("UNCHECKED_CAST")
fun RemoteWebDriver.findAllBy(
    by: ByType,
    textMatch: TextMatch,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery(
    queryType = QueryType.Find,
    plural = true,
    by = by,
    textMatch = textMatch,
    options = options,
) as List<WebElement>
