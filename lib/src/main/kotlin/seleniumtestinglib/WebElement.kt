package seleniumtestinglib

import org.openqa.selenium.By.id
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.support.ui.Select
import seleniumtestinglib.queries.executeTLQuery

val WebElement.value: Any?
    get() {
        if (tagName == "input" && getAttribute("type") == "number")
            return getAttribute("value")?.toIntOrNull()
        if (tagName == "select" && Select(this).isMultiple) {
            return Select(this).allSelectedOptions.map { it.getAttribute("value") }
        }
        return getAttribute("value")
    }

val WebElement.displayValue: Any?
    get() = if (tagName == "select")
        Select(this).let {
            when {
                it.isMultiple -> it.allSelectedOptions.map(WebElement::getText)
                else -> it.firstSelectedOption.text
            }
        } else getAttribute("value")

val WebElement.formValues: Map<String, Any?>
    get() {
        require(tagName == "form" || tagName == "fieldset") { "this should be a form or a fieldset but it's a $tagName" }
        @Suppress("UNCHECKED_CAST")
        return (wrappedDriver.executeScript("return arguments[0].elements", this) as List<WebElement>)
            .groupBy { it.getAttribute("name") }
            .filterKeys(String::isNotBlank)
            .mapValues { (_, elements) ->
                when (elements.first().tagName) {
                    "input" -> when (elements.first().getAttribute("type")) {
                        "checkbox" -> when {
                            elements.size > 1 -> elements.filter { it.isChecked }.map { it.getAttribute("value") }
                            else -> elements.first().isChecked
                        }

                        "radio" -> elements.firstOrNull { it.isChecked }?.value
                        else -> elements.first().value
                    }

                    else -> elements.first().value
                }
            }
    }

val WebElement.isChecked: Boolean
    get() {
        if (tagName == "input" && getAttribute("type") in setOf("radio", "checkbox"))
            return isSelected
        if (ariaRole in setOf("checkbox", "radio", "switch"))
            return getAttribute("aria-checked").toBoolean()
        throw IllegalArgumentException("invalid aria role: $ariaRole")
    }

val WebElement.isDisabled: Boolean
    get() = isEnabled.not()

val WebElement.isFocused: Boolean
    get() = equals(wrappedDriver.switchTo().activeElement())

val WebElement.isRequired
    get() = ((tagName == "input") and (getAttribute("type") == "file") or (ariaRole?.lowercase() in setOf(
        "textbox",
        "checkbox",
        "radio",
        "email",
        "spinbutton",
        "combobox",
        "listbox",
        "date",
    ))) and (getAttribute("required") in setOf(
        "",
        "true"
    )) or (getAttribute("aria-required") in setOf("", "true"))

val WebElement.isValid
    get() = when (tagName) {
        "form" -> wrappedDriver.executeScript("return arguments[0].checkValidity()", this) as Boolean
        else -> wrappedDriver.executeScript(
            "return arguments[0].getAttribute('aria-invalid')",
            this
        ) in setOf(null, "false")
    }

val WebElement.isInvalid
    get() = isValid.not()

val WebElement.isVisible
    get() = isDisplayed

val WebElement.isPartiallyChecked: Boolean
    get() {
        require(ariaRole == "checkbox")
        return wrappedDriver.executeScript(
            "return arguments[0].indeterminate || arguments[0].ariaChecked == 'mixed'", this
        ) as Boolean
    }

val WebElement.files: List<Map<String, Any>>
    get() {
        require(tagName == "input")
        require(getAttribute("type") == "file")
        @Suppress("UNCHECKED_CAST")
        return wrappedDriver.executeScript(
            "return arguments[0].files",
            this

        ) as? List<Map<String, Any>> ?: emptyList()
    }

val WebElement.innerHtml: String
    get() = getAttribute("innerHTML")

/**
 * https://w3c.github.io/accname
 */
val WebElement.accessibleDescription: String
    get() =
        getAttribute("aria-describedby")
            ?.let { wrappedDriver.findElement(id(it)) }?.text
            ?: getAttribute("aria-description")
            ?: getAttribute("title")

val WebElement.classes: Set<String>
    get() = getAttribute("class").takeIf(String::isNotBlank)?.split(Regex("\\s+"))?.toSet() ?: emptySet()

val WebElement.errorMessage: String?
    get() {
        if (isValid) return null
        return wrappedDriver.findElement(id(getAttribute("aria-errormessage"))).text
    }

internal val WebElement.wrappedDriver get() = (this as RemoteWebElement).wrappedDriver as RemoteWebDriver


fun WebElement.fireEvent(eventName: Event, eventProperties: Map<String, Map<String, Any?>> = emptyMap()): Any? {
    wrappedDriver.ensureScript("testing-library.js", "fireEvent.change")
    return wrappedDriver.executeScript("fireEvent.$eventName(arguments[0], arguments[1])", this, eventProperties)
}

@Suppress("unused")
enum class Event {
    copy, cut, paste, compositionEnd, compositionStart, compositionUpdate, keyDown, keyPress, keyUp, focus, blur,
    focusIn, focusOut, change, input, invalid, submit, reset, click, contextMenu, dblClick, drag, dragEnd, dragEnter,
    dragExit, dragLeave, dragOver, dragStart, drop, mouseDown, mouseEnter, mouseLeave, mouseMove, mouseOut, mouseOver,
    mouseUp, select, touchCancel, touchEnd, touchMove, touchStart, resize, scroll, wheel, abort, canPlay,
    canPlayThrough, durationChange, emptied, encrypted, ended, loadedData, loadedMetadata, loadStart, pause, play,
    playing, progress, rateChange, seeked, seeking, stalled, suspend, timeUpdate, volumeChange, waiting, load, error,
    animationStart, animationEnd, animationIteration, transitionCancel, transitionEnd, transitionRun, transitionStart,
    pointerOver, pointerEnter, pointerDown, pointerMove, pointerUp, pointerCancel, pointerOut, pointerLeave,
    gotPointerCapture, lostPointerCapture, popState, offline, online, doubleClick
}