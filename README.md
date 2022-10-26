# Selenium Testing Library

[Testing Library](http://testing-library.com/) selectors available as Selenium locators for Kotlin/Java.

A few examples:

```kotlin
driver.findElements(ByAltText("first name"))
driver.findElements(ByDisplayValue("/john/i", matchTextBy = REGEX))
driver.findElements(ByLabelText("first name"))
driver.findElements(ByPlaceholderText("first name", exact = false))
driver.findElements(ByRole("heading", name = "/as a user/i", matchTextBy = REGEX))
driver.findElements(ByTestId("test-id"))
driver.findElements(ByText("present", exact = false, selector = "span"))
driver.findElements(ByTitle("title 1"))
```

There are unit tests that [illustrate](https://medium.com/codex/towards-self-documenting-code-371364bdccbb)
all the usages. All the Testing Library functionality is available.

## Setup

````kotlin
testImplementation("com.luissoares:selenium-testing-library:1.2")
````

Check the latest version
at [Maven Central](https://search.maven.org/artifact/com.luissoares/selenium-testing-library).

## Why?

When I use Selenium, I don't want to depend on ids, classes, and similar.
I'm a fan of the [Testing Library](http://testing-library.com/) because it encourages "testing as a user":

> The more your tests resemble the way your software is used,
> the more confidence they can give you.

## How?

The Testing Library has ports for many JavaScript frameworks. I realized it was possible to have it Java/Kotlin as
well as Selenium can easily be expandable with new Locators.
My approach was to create an [adapter](https://en.wikipedia.org/wiki/Adapter_pattern):
a set of custom Selenium locators that wrap the Testing Library JavaScript functions.

How can I use Testing Library in Selenium? By dynamically injecting it through JavaScript; then, I can make Testing
Library calls and map the results back to Java/Kotlin objects.
