package com.luissoares.userevent

import org.openqa.selenium.WebElement

class MouseEvent(
    val type: String? = null,
    val target: WebElement? = null,
    val currentTarget: WebElement? = null,
    val eventPhase: Short? = null,
    val bubbles: Boolean? = null,
    val cancelable: Boolean? = null,
    val timeStamp: Long? = null,
    val detail: Int? = null,
    val screenX: Int? = null,
    val screenY: Int? = null,
    val clientX: Int? = null,
    val clientY: Int? = null,
    val ctrlKey: Boolean? = null,
    val shiftKey: Boolean? = null,
    val altKey: Boolean? = null,
    val metaKey: Boolean? = null,
    val button: Short? = null,
)
