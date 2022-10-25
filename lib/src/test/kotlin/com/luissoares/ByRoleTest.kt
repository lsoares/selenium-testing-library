package com.luissoares

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class ByRoleTest(private val driver: RemoteWebDriver) {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<div role="textbox"
                    contenteditable="true"
                    aria-placeholder="5-digit zipcode"
                    aria-labelledby="txtboxLabel">
               </div>""",
            """<input type="text" placeholder="5-digit zipcode" id="txtbox" />""",
            """<textarea id="txtboxMultiline" required></textarea>"""
        ]
    )
    fun textbox(content: String) {
        driver.getFromHtml(content)

        val result = driver.findElement(ByRole("textbox"))

        assertEquals("textbox", result.ariaRole)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<h1>h1</h1>""",
            """<h1>h2</h1>""",
            """<h1>h3</h1>""",
            """<h1>h4</h1>""",
            """<h1>h5</h1>""",
            """<h1>h6</h1>""",
            """<div role="heading" aria-level="1">This is a main page heading</div>""",
        ]
    )
    fun heading(content: String) {
        driver.getFromHtml(content)

        val result = driver.findElement(ByRole("heading"))

        assertEquals("heading", result.ariaRole)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<div id="saveChanges" tabindex="0" role="button" aria-pressed="false">Save</div>""",
            """<button type="button" id="saveChanges">Save</button>""",
        ]
    )
    fun button(content: String) {
        driver.getFromHtml(content)

        val result = driver.findElement(ByRole("button"))

        assertEquals("button", result.ariaRole)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<div role="article">
                  <h2>Heading of the segment</h2>
                  <p>Paragraph for the segment.</p>
                  Controls to interact with the article, share it, etc.
               </div>""",
            """<article>
                  <h2>Heading of the segment</h2>
                  <p>Paragraph for the segment.</p>
                  Controls to interact with the article, share it, etc.
                </article>
            """,
        ]
    )
    fun article(content: String) {
        driver.getFromHtml(content)

        val result = driver.findElement(ByRole("article"))

        assertEquals("article", result.ariaRole)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<div role="banner">
                  <h2>Heading of the segment</h2>
                  Controls to interact with the article, share it, etc.
               </div>""",
            """<header>
                  <a href="#main" id="skipToMain" class="skiptocontent">Skip To main content</a>
                  <img src="images/w3c.png" alt="W3C Logo" />
               </header>""",
        ]
    )
    fun banner(content: String) {
        driver.getFromHtml(content)

        val result = driver.findElement(ByRole("banner"))

        assertEquals("banner", result.ariaRole)
    }
}
