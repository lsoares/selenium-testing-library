# Selenium Testing Library

[![.github/workflows/test.yml](https://github.com/lsoares/selenium-testing-library/actions/workflows/test.yml/badge.svg)](https://github.com/lsoares/selenium-testing-library/actions/workflows/test.yml)
[![Download](https://img.shields.io/maven-central/v/com.luissoares/selenium-testing-library?logo=apache%20maven)](https://search.maven.org/artifact/com.luissoares/selenium-testing-library)

It brings the [Testing Library](http://testing-library.com) selectors as Selenium locators.
Why? When I use Selenium, I want to be independent of ids, classes, and similar.
I'm a fan of the Testing Library because it encourages "testing as a user":

> The more your tests resemble the way your software is used,
> the more confidence they can give you.

Use the [library's latest version](https://search.maven.org/artifact/com.luissoares/selenium-testing-library):

```kotlin
implementation("com.luissoares:selenium-testing-library:4.1.1")
```

---
These are just a few examples. Check [the tests](/lib/src/test/kotlin/seleniumtestinglib)
that [illustrate](https://medium.com/codex/towards-self-documenting-code-371364bdccbb) all the usages
([there are examples in Java](/lib/src/test/java)).

## Core API

The [core API](https://testing-library.com/docs) contains the selectors which are mapped into Selenium locators:

```kotlin
driver.findElements(altText("first name"))
driver.findElement(displayValue(JsFunction("c => c.startsWith('selen')")))
driver.findElements(labelText("active"))
driver.findElements(placeholderText("first name", exact = false))
driver.findElements(role(Heading, nameAsFunction = JsFunction("c => c.startsWith('something')")))
driver.findElements(role(Button, nameAsRegex = Pattern.compile("confirm")))
driver.findElements(testId("test-id"))
driver.findElements(text("present", exact = false, selector = "span"))
driver.findElement(title("title 1"))
driver.findElement(title(Pattern.compile("FOO")))
```

## User Interactions

The Testing Library's [user-event](https://testing-library.com/docs/user-event/intro) is also mapped:

```kotlin
driver.user.click(active)
driver.user.dblClick(panel)
driver.user.type(input, "foobar")
driver.user.selectOptions(letterSelector, driver.findElement(ByRole(ListBox, name = "C")))
```

[fireEvent](https://testing-library.com/docs/dom-testing-library/api-events) is also available:

```kotlin
input.fireEvent(Change, mapOf(Target to mapOf("value" to "2020-05-24")))
```

## jest-dom

[jest-dom](https://testing-library.com/docs/ecosystem-jest-dom) matchers are available with a similar API, although it
makes more sense to use the corresponding utilities and assert with JUnit (or an assertion library):

```kotlin
// API similar to the original version:
expect(button.toHaveAccessibleDescription("Register"))
expect(checkboxMarketing).toBeChecked()
assertEquals(setOf("btn", "btn-danger", "extra"), deleteButton.classes)
expect(element).not.toBePartiallyChecked()

// utilities that can be used on their own:
val formValues = registrationForm.formValues
val userAgrees = checkboxMarketing.isChecked
val name = element.accessibleName
val displayedValue = element.displayValue
```

---
ℹ️ _[Read more](https://medium.com/codex/the-testing-library-meets-selenium-5f74cc712114)._
