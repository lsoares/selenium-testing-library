package seleniumtestinglib.queries

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

fun RemoteWebDriver.findBy(
    by: ByType,
    textMatch: JsType,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery<WebElement>(
    queryType = QueryType.Find,
    all = false,
    by = by,
    textMatch = textMatch,
    options = options,
)

fun RemoteWebDriver.findAllBy(
    by: ByType,
    textMatch: JsType,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery<List<WebElement>>(
    queryType = QueryType.Find,
    all = true,
    by = by,
    textMatch = textMatch,
    options = options,
)
