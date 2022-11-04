# Selenium Testing Library

[Testing Library](http://testing-library.com/) selectors available as Selenium locators for Kotlin/Java.

## Usage

These are just a few examples. There are unit tests
that [illustrate](https://medium.com/codex/towards-self-documenting-code-371364bdccbb)
all the usages.

### Core API

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

ℹ️ All the Testing Library core functionality is available.

### User Interactions

The Testing Library's [user-event](https://testing-library.com/docs/user-event/intro) is also mapped:

```kotlin
driver.user.click(active)
driver.user.dblClick(panel)
driver.user.type(input, "foobar")
driver.user.selectOptions(driver.findElement(ByRole("listbox")), "C")
```

ℹ️ user-event's JavaScript only loads if it's used.

## Setup

````kotlin
testImplementation("com.luissoares:selenium-testing-library:VERSION")
````

Check the latest `VERSION`
at [Maven Central](https://search.maven.org/artifact/com.luissoares/selenium-testing-library).

## Why?

When I use Selenium, I don't want to depend on ids, classes, and similar.
I'm a fan of the [Testing Library](http://testing-library.com/) because it encourages "testing as a user":

> The more your tests resemble the way your software is used,
> the more confidence they can give you.

## How?

The Testing Library has ports for many JavaScript frameworks. I realized it was possible to have it in Selenium for
Java/Kotlin as well.
I created an [adapter](https://en.wikipedia.org/wiki/Adapter_pattern):
a set of custom Selenium locators that wrap the corresponding Testing Library JavaScript functions.

How can I use Testing Library in Selenium? By dynamically injecting it through JavaScript; then, I can make Testing
Library calls and map the results back to Java/Kotlin objects.
