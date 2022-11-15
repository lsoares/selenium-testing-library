# Selenium Testing Library

**_[Testing Library](http://testing-library.com/) selectors available as Selenium locators for Kotlin/Java._**

Why? When I use Selenium, I don't want to depend on ids, classes, and similar.
I'm a fan of the Testing Library because it encourages "testing as a user":

> The more your tests resemble the way your software is used,
> the more confidence they can give you.


ℹ️ _To set it up, pick the [library's latest version at **Maven
Central** <img src="https://search.maven.org/favicon.ico" width="16" height="16" />](https://search.maven.org/artifact/com.luissoares/selenium-testing-library)
.
Then, copy the declaration for your build tool._

---

These are just a few examples. There are unit tests
that [illustrate](https://medium.com/codex/towards-self-documenting-code-371364bdccbb)
all the usages.

## Core API

The [core API](https://testing-library.com/docs) contains the selectors which are mapped into Selenium locators:

```kotlin
driver.findElements(ByAltText("first name"))
driver.findElements(ByDisplayValue("/john/i".asJsRegex()))
val active = driver.findElements(ByLabelText("active"))
val input = driver.findElements(ByPlaceholderText("first name", exact = false))
val firstName = input.text
driver.findElements(ByRole("heading", name = "/as a user/i".asJsRegex()))
val panel = driver.findElements(ByTestId("test-id"))
panel.click()
driver.findElements(ByText("present", exact = false, selector = "span"))
driver.findElements(ByTitle("title 1"))

// an alternative API that does not use Selenium locators:
val result1 = driver.queryBy(AltText, "alt 1", mapOf("exact" to false))
val result2 = driver.getBy(DisplayValue, "incredibles")
val result3 = driver.queryAllBy(LabelText, "label x")
val result4 = driver.getAllBy(Role, "listbox")

```

ℹ️ _All the Testing Library core functionality is available._

## User Interactions

The Testing Library's [user-event](https://testing-library.com/docs/user-event/intro) is also mapped:

```kotlin
driver.user.click(active)
driver.user.dblClick(panel)
driver.user.type(input, "foobar")
driver.user.selectOptions(driver.findElement(ByRole("listbox")), "C")
```

ℹ️ _user-event's JavaScript only loads if it's used._

## jest-dom matchers

[jest-dom](https://testing-library.com/docs/ecosystem-jest-dom) matchers are mapped:

```kotlin
val input = driver.findElements(ByTestId("test1"))
assertTrue(input.isInTheDocument)
assertFalse(input.isEmptyDomElement)

// alternative API, closer to the original version:
expect(input).not.toBeInvalid()
expect(input).toBeInTheDocument()
```

---

ℹ️ _Want to know about the creation process? Read more about it
at [The Testing Library meets Selenium](https://medium.com/codex/the-testing-library-meets-selenium-5f74cc712114)._
