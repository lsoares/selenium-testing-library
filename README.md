# Selenium Testing Library

The [Testing Library](http://testing-library.com/) for Selenium Kotlin/Java.

A few examples:

```kotlin
driver.findElements(ByAltText("first name"))
driver.findElements(ByDisplayValue("/john/i"))
driver.findElements(ByLabelText("first name"))
driver.findElements(ByPlaceholderText("first name", exact = false))
driver.findElements(ByRole("heading"))
driver.findElements(ByTestId("test-id"))
driver.findElements(ByText("present", exact = false, selector = "span"))
driver.findElements(ByTitle("title 1"))
```

There are unit tests that [illustrate](https://medium.com/codex/towards-self-documenting-code-371364bdccbb)
all the usages. Currently, all the Testing Library functionality is available.

## Setup

````kotlin
testImplementation("com.luissoares:selenium-testing-library:0.9")
````

Check the latest version at [Maven Repository](https://mvnrepository.com/).

## Why?

I'm a fan of the [Testing Library](http://testing-library.com/).
It has ports for many frameworks but Selenium was missing.
When I use Selenium, I don't want to depend on ids, classes, and similar.
Happily, Selenium is easily expandable by adding new Locators (just inherit from `By`).

## How?

My approach was to create an [adapter](https://en.wikipedia.org/wiki/Adapter_pattern):
a set of custom Selenium locators that wrap the Testing Library JavaScript functions.

How do I have the Testing Library in Selenium? By dynamically injecting it through JavaScript.
The injected JavaScript was copied from the
[Chrome extension](https://chrome.google.com/webstore/detail/testing-playground/hejbmebodbijjdhflfknehhcgaklhano).
There are some CDKs that serve the Testing Library, but I needed the self-contained single-file browser-ready version.

---
There's a [Python version](https://github.com/anze3db/selenium-testing-library).
