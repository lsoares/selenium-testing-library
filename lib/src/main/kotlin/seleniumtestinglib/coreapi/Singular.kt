package seleniumtestinglib.coreapi

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

fun RemoteWebDriver.getBy(
    by: ByType,
    textMatch: String,
    options: Map<String, Any?> = emptyMap(),
    matchTextBy: MatchType = MatchType.STRING,
    matchDescriptionBy: MatchType = MatchType.STRING,
) = executeTLQuery(
    queryType = QueryType.Get,
    plural = false,
    by = by,
    textMatch = textMatch,
    matchTextBy = matchTextBy,
    matchDescriptionBy = matchDescriptionBy,
    options = options,
) as WebElement

fun RemoteWebDriver.queryBy(
    by: ByType,
    textMatch: String,
    options: Map<String, Any?> = emptyMap(),
    matchTextBy: MatchType = MatchType.STRING,
    matchDescriptionBy: MatchType = MatchType.STRING,
) = executeTLQuery(
    queryType = QueryType.Query,
    plural = false,
    by = by,
    textMatch = textMatch,
    matchTextBy = matchTextBy,
    matchDescriptionBy = matchDescriptionBy,
    options = options,
) as WebElement?

fun RemoteWebDriver.findBy(
    by: ByType,
    textMatch: String,
    options: Map<String, Any?> = emptyMap(),
    matchTextBy: MatchType = MatchType.STRING,
    matchDescriptionBy: MatchType = MatchType.STRING,
) = executeTLQuery(
    queryType = QueryType.Find,
    plural = false,
    by = by,
    textMatch = textMatch,
    matchTextBy = matchTextBy,
    matchDescriptionBy = matchDescriptionBy,
    options = options,
) as WebElement
