package seleniumtestinglib.queries

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import seleniumtestinglib.queries.TextMatch.Companion.asJsString

fun JavascriptExecutor.queryBy(
    by: LocatorType,
    textMatch: TextMatch,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery<WebElement?>(
    queryType = QueryType.Query,
    all = false,
    by = by,
    textMatch = textMatch,
    options = options,
)

fun JavascriptExecutor.queryBy(
    by: LocatorType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = queryBy(by, text.asJsString(), options)

fun JavascriptExecutor.queryAllBy(
    by: LocatorType,
    textMatch: TextMatch,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery<List<WebElement>>(
    queryType = QueryType.Query,
    all = true,
    by = by,
    textMatch = textMatch,
    options = options,
)

fun JavascriptExecutor.queryAllBy(
    by: LocatorType,
    text: String,
    options: Map<String, Any?> = emptyMap(),
) = queryAllBy(by, text.asJsString(), options)
