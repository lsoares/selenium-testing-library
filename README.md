# Selenium Testing Library

A set of Selenium locators inspired by the [Testing Library](http://testing-library.com/).
A few examples:
```kotlin
driver.findElements(ByText("first name", exact = false, selector="span"))
driver.findElements(ByPlaceholderText("first name"))
driver.findElements(ByPlaceholderText("first name", exact = false))
driver.findElements(ByTitle("first name"))
driver.findElements(ByDisplayValue("first name"))
driver.findElements(ByTestId("test-id"))
```
There are unit tests that [illustrate](https://medium.com/codex/towards-self-documenting-code-371364bdccbb) 
all the usages (this was done with TDD/test-first).

## Why?
I'm a fan of the [Testing Library](http://testing-library.com/).
It has ports for many frameworks but Selenium was missing.
When I use Selenium, I don't want to depend on ids, classes, and similar.
Happily, Selenium is easily expandable by adding new Locators (just inherit from `By`).

## How?
My approach was to create a Kotlin [adapter](https://en.wikipedia.org/wiki/Adapter_pattern):
a set of custom Selenium that is just wrappers around the Testing Library JavaScript functions.

How do I have the Testing Library in Selenium? By dynamically injecting it through JavaScript.
The injected JavaScript was copied from the [Testing Playground Chrome extension](https://chrome.google.com/webstore/detail/testing-playground/hejbmebodbijjdhflfknehhcgaklhano).
(There are some CDKs that serve the Testing Library, 
but I needed the self-contained single-file browser-ready version.)

My first approach was to use Selenium locators (e.g. `xpath`, `cssSelector`) to port the Testing Library behavior.
It turned out this was a limited approach because:
- Selenium locators are limited due to [WebdriverIO](https://webdriver.io/) limitations
- I'd have to port everything, which is a lot of work
- I'd likely introduce bugs
- I wouldn't get library updates easily
(check git history to view the old approach)
With the new approach, I learned a lot about Selenium, the Testing Library, and publishing a library.
