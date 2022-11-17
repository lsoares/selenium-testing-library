package seleniumtestinglib.queries

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.queries.JsType.Companion.asJsString

fun RemoteWebDriver.queryBy(
    by: ByType,
    textMatch: JsType,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery(
    queryType = QueryType.Query,
    all = false,
    by = by,
    textMatch = textMatch,
    options = options,
) as WebElement?

fun RemoteWebDriver.queryBy(
    by: ByType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = queryBy(by, text.asJsString(), options)

@Suppress("UNCHECKED_CAST")
fun RemoteWebDriver.queryAllBy(
    by: ByType,
    textMatch: JsType,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery(
    queryType = QueryType.Query,
    all = true,
    by = by,
    textMatch = textMatch,
    options = options,
) as List<WebElement>

fun RemoteWebDriver.queryAllBy(
    by: ByType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = queryAllBy(by, text.asJsString(), options)
