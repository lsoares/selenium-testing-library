# Selenium Testing Library

[![.github/workflows/test.yml](https://github.com/lsoares/selenium-testing-library/actions/workflows/test.yml/badge.svg)](https://github.com/lsoares/selenium-testing-library/actions/workflows/test.yml)
[![Download](https://img.shields.io/maven-central/v/com.luissoares/selenium-testing-library?logo=apache%20maven)](https://search.maven.org/artifact/com.luissoares/selenium-testing-library)

The goal is to provide the [Testing Library](http://testing-library.com) selectors as Selenium locators.
The reason is that when I use Selenium, I want to be independent of ids, classes, and similar.
I'm a fan of the Testing Library because it encourages "testing as a
user". [Read more](https://medium.com/codex/the-testing-library-meets-selenium-5f74cc712114).

> The more your tests resemble the way your software is used,
> the more confidence they can give you.

To get started, use
the [library's latest version](https://search.maven.org/artifact/com.luissoares/selenium-testing-library):

```kotlin
implementation("com.luissoares:selenium-testing-library:4.1.3")
```

Now you can use the library:
- [Core API](https://testing-library.com/docs) contains the selectors which are mapped into Selenium locators:
    ```kotlin
    driver.findElement(altText("first name")) // or findElements
    driver.findElement(displayValue(JsFunction("c => c.startsWith('selen')")))
    driver.findElement(labelText("active"))
    driver.findElement(placeholderText("first name", exact = false))
    driver.findElement(role(Heading, nameAsFunction = JsFunction("c => c.startsWith('something')")))
    driver.findElement(role(Button, nameAsRegex = Pattern.compile("confirm")))
    driver.findElement(testId("test-id"))
    driver.findElement(text("present", exact = false, selector = "span"))
    driver.findElement(title("title 1"))
    driver.findElement(title(Pattern.compile("FOO")))
    ```

- [user-event](https://testing-library.com/docs/user-event/intro) triggers events from user interactions:
   ```kotlin
   driver.user.click(active)
   driver.user.dblClick(panel)
   driver.user.type(input, "foobar")
   driver.user.selectOptions(letterSelector, driver.findElement(ByRole(ListBox, name = "C")))
   ```

- [fireEvent](https://testing-library.com/docs/dom-testing-library/api-events) is a lower-level way to trigger events:
  ```kotlin
  input.fireEvent(Change, mapOf(Target to mapOf("value" to "2020-05-24")))
  ```

- [jest-dom](https://testing-library.com/docs/ecosystem-jest-dom) matchers are available indirectly:
  ```kotlin
  val formValues = registrationForm.formValues
  val userAgrees = checkboxMarketing.isChecked
  val name = element.accessibleName
  val displayedValue = element.displayValue
  ```

Check [the tests](/lib/src/test/kotlin/seleniumtestinglib)
that [illustrate](https://medium.com/codex/towards-self-documenting-code-371364bdccbb) all the usages
([there are examples in Java as well](/lib/src/test/java)).
