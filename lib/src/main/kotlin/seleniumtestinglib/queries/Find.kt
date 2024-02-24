package seleniumtestinglib.queries

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement

@Suppress("unused")
fun JavascriptExecutor.findBy(
    by: LocatorType,
    textMatch: TextMatch,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery<WebElement>(
    queryType = QueryType.Find,
    all = false,
    by = by,
    textMatch = textMatch,
    options = options,
)

@Suppress("unused")
fun JavascriptExecutor.findAllBy(
    by: LocatorType,
    textMatch: TextMatch,
    options: Map<String, Any?> = emptyMap(),
) = executeTLQuery<List<WebElement>>(
    queryType = QueryType.Find,
    all = true,
    by = by,
    textMatch = textMatch,
    options = options,
)
