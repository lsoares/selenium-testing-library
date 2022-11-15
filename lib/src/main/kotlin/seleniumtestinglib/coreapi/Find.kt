package seleniumtestinglib.coreapi

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

fun RemoteWebDriver.findBy(
    by: ByType,
    textMatch: JsType,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery(
    queryType = QueryType.Find,
    all = false,
    by = by,
    textMatch = textMatch,
    options = options,
) as WebElement

@Suppress("UNCHECKED_CAST")
fun RemoteWebDriver.findAllBy(
    by: ByType,
    textMatch: JsType,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery(
    queryType = QueryType.Find,
    all = true,
    by = by,
    textMatch = textMatch,
    options = options,
) as List<WebElement>
