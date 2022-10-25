# Selenium Testing Library

A set of Selenium locators using the [Testing Library](http://testing-library.com/); for Kotlin/Java.

A few examples:

```kotlin
driver.findElements(ByAltText("first name"))
driver.findElements(ByDisplayValue("John Doe"))
driver.findElements(ByLabelText("first name"))
driver.findElements(ByPlaceholderText("first name", exact = false))
driver.findElements(ByRole("heading"))
driver.findElements(ByTestId("test-id"))
driver.findElements(ByText("present", exact = false, selector = "span"))
driver.findElements(ByTitle("title 1"))
```

There are unit tests that [illustrate](https://medium.com/codex/towards-self-documenting-code-371364bdccbb)
all the usages.

## Why?

I'm a fan of the [Testing Library](http://testing-library.com/).
It has ports for many frameworks but Selenium was missing.
When I use Selenium, I don't want to depend on ids, classes, and similar.
Happily, Selenium is easily expandable by adding new Locators (just inherit from `By`).

## How?

My approach was to create an [adapter](https://en.wikipedia.org/wiki/Adapter_pattern):
a set of custom Selenium locators that wrap the Testing Library JavaScript functions.

How do I have the Testing Library in Selenium? By dynamically injecting it through JavaScript.
The injected JavaScript was copied from
the [Testing Playground Chrome extension](https://chrome.google.com/webstore/detail/testing-playground/hejbmebodbijjdhflfknehhcgaklhano)
.
(There are some CDKs that serve the Testing Library,
but I needed the self-contained single-file browser-ready version.)

### Port or Adapt?

My first approach was to use Selenium locators (e.g. `xpath`, `cssSelector`) to port the Testing Library behavior.
It turned out this was a limited approach because:

- Selenium locators can't compete with Selenium locators due to [WebdriverIO](https://webdriver.io/) limitations
- I'd have to port all functionality, which is a lot of work
- I'd likely introduce bugs
- I wouldn't get library updates easily

(check git history to view the old approach)

With the new approach (an adapter/wrapper), I learned a lot about Selenium, the Testing Library, and publishing a Kotlin
library.

## Limitations

- Regex
- Normalizer function
- Search function
- Most arguments of ByRole

## Other

There's a [Python version](https://github.com/anze3db/selenium-testing-library).
