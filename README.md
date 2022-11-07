# Selenium Testing Library

**_[Testing Library](http://testing-library.com/) selectors available as Selenium locators for Kotlin/Java._**

Why? When I use Selenium, I don't want to depend on ids, classes, and similar.
I'm a fan of the Testing Library because it encourages "testing as a user":

> The more your tests resemble the way your software is used,
> the more confidence they can give you.


ℹ️ _To set it up, pick the [library's latest version at **Maven
Central**](https://search.maven.org/artifact/com.luissoares/selenium-testing-library). Then, copy the declaration for
your build tool._

---

These are just a few examples. There are unit tests
that [illustrate](https://medium.com/codex/towards-self-documenting-code-371364bdccbb)
all the usages.

## Core API

The [core API](https://testing-library.com/docs) contains the selectors which are mapped into Selenium locators:

```kotlin
driver.findElements(ByAltText("first name"))
driver.findElements(ByDisplayValue("/john/i", matchTextBy = REGEX))
val active = driver.findElements(ByLabelText("active"))
val input = driver.findElements(ByPlaceholderText("first name", exact = false))
val firstName = input.text
driver.findElements(ByRole("heading", name = "/as a user/i", matchTextBy = REGEX))
val panel = driver.findElements(ByTestId("test-id"))
panel.click()
driver.findElements(ByText("present", exact = false, selector = "span"))
driver.findElements(ByTitle("title 1"))
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

---

ℹ️ _Want to know about the creation process? Read more about it
at [The Testing Library meets Selenium](https://medium.com/codex/the-testing-library-meets-selenium-5f74cc712114)._
