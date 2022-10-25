package com.luissoares

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.remote.RemoteWebDriver
import kotlin.test.assertNotNull

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

        assertNotNull(result)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """<h1>h1</h1""",
            """<h1>h2</h1""",
            """<h1>h3</h1""",
            """<h1>h4</h1""",
            """<h1>h5</h1""",
            """<h1>h6</h1""",
            """<div role="heading" aria-level="1">This is a main page heading</div>""",
        ]
    )
    fun heading(content: String) {
        driver.getFromHtml(content)

        val result = driver.findElement(ByRole("heading"))

        assertNotNull(result)
    }
}
