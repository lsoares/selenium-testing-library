package seleniumtestinglib.queries

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.queries.JsType.Companion.asJsString

fun RemoteWebDriver.queryBy(
    by: ByType,
    textMatch: JsType,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery<WebElement?>(
    queryType = QueryType.Query,
    all = false,
    by = by,
    textMatch = textMatch,
    options = options,
)

fun RemoteWebDriver.queryBy(
    by: ByType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = queryBy(by, text.asJsString(), options)

fun RemoteWebDriver.queryAllBy(
    by: ByType,
    textMatch: JsType,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery<List<WebElement>>(
    queryType = QueryType.Query,
    all = true,
    by = by,
    textMatch = textMatch,
    options = options,
)

fun RemoteWebDriver.queryAllBy(
    by: ByType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = queryAllBy(by, text.asJsString(), options)
