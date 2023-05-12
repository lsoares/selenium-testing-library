package seleniumtestinglib.queries

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.queries.JsType.Companion.asJsString

fun RemoteWebDriver.getBy(
    by: ByType,
    textMatch: JsType,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery<WebElement>(
    queryType = QueryType.Get,
    all = false,
    by = by,
    textMatch = textMatch,
    options = options,
)

fun RemoteWebDriver.getBy(
    by: ByType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = getBy(by, text.asJsString(), options)

fun RemoteWebDriver.getAllBy(
    by: ByType,
    textMatch: JsType,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery<List<WebElement>>(
    queryType = QueryType.Get,
    all = true,
    by = by,
    textMatch = textMatch,
    options = options,
)

fun RemoteWebDriver.getAllBy(
    by: ByType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = getAllBy(by, text.asJsString(), options)
