package com.luissoares

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class ByRoleTest(private val driver: RemoteWebDriver) {

    @ParameterizedTest
    @MethodSource("examples")
    fun `by role`(role: String, content: String) {
        driver.getFromHtml(content)

        val result = driver.findElement(ByRole(role))

        assertEquals(role, result.ariaRole)
    }

    private fun examples() = setOf(
        of(
            "textbox", """<div role="textbox"
                    contenteditable="true"
                    aria-placeholder="5-digit zipcode"
                    aria-labelledby="txtboxLabel">
               </div>"""
        ),
        of("textbox", """<input type="text" placeholder="5-digit zipcode" id="txtbox" />"""),
        of("textbox", """<textarea id="txtboxMultiline" required></textarea>"""),
        of("heading", """<h1>h1</h1>"""),
        of("heading", """<h1>h2</h1>"""),
        of("heading", """<h1>h3</h1>"""),
        of("heading", """<h1>h4</h1>"""),
        of("heading", """<h1>h5</h1>"""),
        of("heading", """<h1>h6</h1>"""),
        of("heading", """<div role="heading" aria-level="1">This is a main page heading</div>"""),
        of("button", """"<div id="saveChanges" tabindex="0" role="button" aria-pressed="false">Save</div>"""),
        of("button", """<button type="button" id="saveChanges">Save</button>"""),
        of(
            "article", """<div role="article">
                  <h2>Heading of the segment</h2>
                  <p>Paragraph for the segment.</p>
                  Controls to interact with the article, share it, etc.
               </div>"""
        ),
        of(
            "article", """<article>
                  <h2>Heading of the segment</h2>
                  <p>Paragraph for the segment.</p>
                  Controls to interact with the article, share it, etc.
                </article>
            """
        ),
        of(
            "banner", """<div role="banner">
                  <h2>Heading of the segment</h2>
                  Controls to interact with the article, share it, etc.
               </div>"""
        ),
        of(
            "banner", """<header>
                  <a href="#main" id="skipToMain" class="skiptocontent">Skip To main content</a>
                  <img src="images/w3c.png" alt="W3C Logo" />
               </header>"""
        ),
        of(
            "checkbox", """<<span role="checkbox"
                      aria-checked="false"
                      tabindex="0"
                      aria-labelledby="chk1-label"></span>
                    <label id="chk1-label">Remember my preferences</label>"""
        ),
        of(
            "checkbox", """<input type="checkbox" id="chk1-label" />
               <label for="chk1-label">Remember my preferences</label>"""
        ),
        of("link", """<a href="https://mozilla.org">Link 123</a>"""),
        of(
            "link", """<span data-href="https://mozilla.org" tabindex="0" role="link">
              Fake accessible link created using a span
            </span>"""
        ),
        of("toolbar", """""<div role="toolbar"></div>"""),
        of(
            "figure", """<figure>
                          <img src="image.png" alt="put image description here" />
                          <figcaption>Figure 1: The caption</figcaption>
                        </figure>"""
        ),
        of(
            "figure", """<div role="figure" aria-labelledby="figure-1">
                          …
                          <p id="figure-1">Text that describes the figure.</p>
                        </div>"""
        ),
        of("form", """<div role="form"></div>"""),
        of("form", """<form aria-label="xyz">test</form>"""),
    )

    @Test
    fun `aria-selected`() {
        driver.getFromHtml("""
            <body>
              <div role="tablist">
                <button role="tab" aria-selected="false">React</button>
                <button role="tab" aria-selected="true">Native</button>
                <button role="tab" aria-selected="false">Cypress</button>
              </div>
            </body>
        """)

        val result = driver.findElements(ByRole("tab", selected=true))

        assertEquals("Native", result.single().text)
    }
}
