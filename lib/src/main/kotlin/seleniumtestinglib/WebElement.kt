package seleniumtestinglib

import org.openqa.selenium.By
import org.openqa.selenium.By.id
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.support.ui.Select

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

fun WebElement.hasFormValues(vararg values: Pair<String, Any?>): Boolean =
    values.toMap().all { formValues[it.key] == it.value }


private fun WebElement.hasStyle(css: Map<String, String>): Boolean {
    val expectedCss = css.mapKeys { it.key.normalizeCssProp() }
    val existingCss = expectedCss.keys
        .associateWith(::getCssValue)
    return expectedCss == existingCss
}


fun WebElement.hasStyle(css: String): Boolean =
    hasStyle(css.split(";")
        .filter(String::isNotBlank)
        .associate {
            it.split(":").map(String::trim)
                .let { it.first() to it.last() }
        })

fun WebElement.hasStyle(vararg css: Pair<String, String>) = hasStyle(css.toMap())

private fun String.normalizeCssProp() = "(?<=[a-zA-Z])[A-Z]".toRegex().replace(this) { "-${it.value}" }.lowercase()

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
    get() = ((tagName == "input") and (getAttribute("type") in setOf("file", "password", "date"))
            or (ariaRole?.lowercase() in setOf(
        "textbox",
        "checkbox",
        "radio",
        "email",
        "spinbutton",
        "combobox",
        "listbox",
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
val WebElement.accessibleDescription: String?
    get() =
        getAttribute("aria-describedby")
            ?.let { wrappedDriver.findElement(id(it)) }?.text
            ?: getAttribute("aria-description")
            ?: getAttribute("title")
                .ifEmpty { null }

val WebElement.classes: Set<String>
    get() = getAttribute("class").takeIf(String::isNotBlank)?.split(Regex("\\s+"))?.toSet() ?: emptySet()

fun WebElement.hasClass(vararg classNames: String, exact: Boolean = false): Boolean {
    val expectedClasses = classNames.map { it.split(Regex("\\s+")) }.flatten().toSet()
    if (expectedClasses.isEmpty()) {
        return classes.isNotEmpty()
    }
    return when (exact) {
        false -> classes.containsAll(expectedClasses)
        true -> expectedClasses == classes
    }
}

val WebElement.errorMessage: String?
    get() = when {
        isValid -> null
        else -> wrappedDriver.findElement(id(getAttribute("aria-errormessage"))).text
    }

val WebElement.isEmptyDomElement: Boolean
    get() = getAttribute("innerHTML").replace("<!--.*?-->".toRegex(), "").isEmpty()

fun WebElement.containsElement(ancestor: WebElement) =
    findElements(By.xpath(".//*")).contains(ancestor)

fun WebElement.containsHtml(htmlText: String): Boolean =
    innerHtml.contains(
        wrappedDriver.executeScript(
            """
                const htmlDoc = new DOMParser().parseFromString(arguments[0], "text/html")
                return htmlDoc.querySelector("body").innerHTML
            """, htmlText
        ) as String
    )

internal val WebElement.wrappedDriver get() = (this as RemoteWebElement).wrappedDriver as RemoteWebDriver

fun WebElement.fireEvent(eventName: Event, eventProperties: Map<String, Map<String, Any?>> = emptyMap()): Any? {
    wrappedDriver.ensureScript("testing-library.js", "window.__TL__?.fireEvent?.change")
    val event = eventName.name.replaceFirstChar(Char::lowercase)
    return wrappedDriver.executeScript(
        "window.__TL__.fireEvent.$event(arguments[0], arguments[1])",
        this,
        eventProperties
    )
}

@Suppress("unused")
enum class Event {
    Copy, Cut, Paste, CompositionEnd, CompositionStart, CompositionUpdate, KeyDown, KeyPress, KeyUp, Focus, Blur,
    FocusIn, FocusOut, Change, Input, Invalid, Submit, Reset, Click, ContextMenu, DblClick, Drag, DragEnd, DragEnter,
    DragExit, DragLeave, DragOver, DragStart, Drop, MouseDown, MouseEnter, MouseLeave, MouseMove, MouseOut, MouseOver,
    MouseUp, Select, TouchCancel, TouchEnd, TouchMove, TouchStart, Resize, Scroll, Wheel, Abort, CanPlay,
    CanPlayThrough, DurationChange, Emptied, Encrypted, Ended, LoadedData, LoadedMetadata, LoadStart, Pause, Play,
    Playing, Progress, RateChange, Seeked, Seeking, Stalled, Suspend, TimeUpdate, VolumeChange, Waiting, Load, Error,
    AnimationStart, AnimationEnd, AnimationIteration, TransitionCancel, TransitionEnd, TransitionRun, TransitionStart,
    PointerOver, PointerEnter, PointerDown, PointerMove, PointerUp, PointerCancel, PointerOut, PointerLeave,
    GotPointerCapture, LostPointerCapture, PopState, Offline, Online, DoubleClick
}
