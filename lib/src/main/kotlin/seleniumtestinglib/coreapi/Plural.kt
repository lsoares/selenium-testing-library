package seleniumtestinglib.coreapi

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

@Suppress("UNCHECKED_CAST")
fun RemoteWebDriver.getAllBy(
    by: ByType,
    textMatch: String,
    options: Map<String, Any?> = emptyMap(),
    matchTextBy: MatchType = MatchType.STRING,
    matchDescriptionBy: MatchType = MatchType.STRING,
) = executeTLQuery(
    queryType = QueryType.Get,
    plural = true,
    by = by,
    textMatch = textMatch,
    matchTextBy = matchTextBy,
    matchDescriptionBy = matchDescriptionBy,
    options = options,
) as List<WebElement>

@Suppress("UNCHECKED_CAST")
fun RemoteWebDriver.queryAllBy(
    by: ByType,
    textMatch: String,
    options: Map<String, Any?> = emptyMap(),
    matchTextBy: MatchType = MatchType.STRING,
    matchDescriptionBy: MatchType = MatchType.STRING,
) = executeTLQuery(
    queryType = QueryType.Query,
    plural = true,
    by = by,
    textMatch = textMatch,
    matchTextBy = matchTextBy,
    matchDescriptionBy = matchDescriptionBy,
    options = options,
) as List<WebElement>

@Suppress("UNCHECKED_CAST")
fun RemoteWebDriver.findAllBy(
    by: ByType,
    textMatch: String,
    options: Map<String, Any?> = emptyMap(),
    matchTextBy: MatchType = MatchType.STRING,
    matchDescriptionBy: MatchType = MatchType.STRING,
) = executeTLQuery(
    queryType = QueryType.Find,
    plural = true,
    by = by,
    textMatch = textMatch,
    matchTextBy = matchTextBy,
    matchDescriptionBy = matchDescriptionBy,
    options = options,
) as List<WebElement>
