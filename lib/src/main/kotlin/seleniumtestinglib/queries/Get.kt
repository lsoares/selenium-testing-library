package seleniumtestinglib.queries

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import seleniumtestinglib.queries.TextMatch.Companion.asJsString

fun JavascriptExecutor.getBy(
    by: LocatorType,
    textMatch: TextMatch,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery<WebElement>(
    queryType = QueryType.Get,
    all = false,
    by = by,
    textMatch = textMatch,
    options = options,
)

fun JavascriptExecutor.getBy(
    by: LocatorType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = getBy(by, text.asJsString(), options)

fun JavascriptExecutor.getAllBy(
    by: LocatorType,
    textMatch: TextMatch,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery<List<WebElement>>(
    queryType = QueryType.Get,
    all = true,
    by = by,
    textMatch = textMatch,
    options = options,
)

fun JavascriptExecutor.getAllBy(
    by: LocatorType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = getAllBy(by, text.asJsString(), options)
