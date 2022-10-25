# Selenium Testing Library

A set of Selenium locators inspired by the [Testing Library](http://testing-library.com/).

## ByText
```kotlin
driver.findElements(ByText("first name"))
driver.findElements(ByText("first name", exact = false, selector="span"))
```


## ByPlaceholderText
```kotlin
driver.findElements(ByPlaceholderText("first name"))
driver.findElements(ByPlaceholderText("first name", exact = false))
```

## ByTitle
```kotlin
driver.findElements(ByTitle("first name"))
```

## ByDisplayValue
```kotlin
driver.findElements(ByDisplayValue("first name"))
```
