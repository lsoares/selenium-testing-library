package seleniumtestinglib.jestdom

import seleniumtestinglib.TL.By.testId
import seleniumtestinglib.classes
import seleniumtestinglib.driver
import seleniumtestinglib.hasClass
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ClassTest {

    @Test
    fun `with classes`() {
        driver.render(
            """<button data-testid="delete-button" class="btn extra btn-danger">
                       Delete item
                     </button>"""
        )

        val deleteButton = driver.findElement(testId("delete-button"))
        assertEquals(setOf("btn", "btn-danger", "extra"), deleteButton.classes)
        assertTrue(deleteButton.hasClass())
        assertTrue(deleteButton.hasClass("extra"))
        assertTrue(deleteButton.hasClass("btn-danger btn"))
        assertTrue(deleteButton.hasClass("btn-danger", "btn"))
        assertFalse(deleteButton.hasClass("btn-danger", "btn-link"))
        assertTrue(deleteButton.hasClass("btn-danger extra btn", exact = true))
        assertFalse(deleteButton.hasClass("btn-danger", "extra",  exact = true))
    }

    @Test
    fun `no classes`() {
        driver.render("""<button data-testid="no-classes">No Classes</button>""")

        val noClasses = driver.findElement(testId("no-classes"))
        assertEquals(emptySet(), noClasses.classes)
        assertFalse(noClasses.hasClass())
    }
}
