package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.classes
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.getBy
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(DriverLifeCycle::class)
class ClassTest(private val driver: RemoteWebDriver) {

    @Test
    fun `with classes`() {
        driver.render(
            """<button data-testid="delete-button" class="btn extra btn-danger">
                       Delete item
                     </button>"""
        )

        val deleteButton = driver.getBy(TestId, "delete-button")
        assertEquals(setOf("btn", "btn-danger", "extra"), deleteButton.classes)
        expect(deleteButton).toHaveClass()
        expect(deleteButton).toHaveClass("extra")
        expect(deleteButton).toHaveClass("btn-danger btn")
        expect(deleteButton).toHaveClass("btn-danger", "btn")
        expect(deleteButton).not.toHaveClass("btn-link")
        expect(deleteButton).toHaveClass("btn-danger extra btn", exact = true)
        expect(deleteButton).not.toHaveClass("btn-danger extra", exact = true)
    }

    @Test
    fun `no classes`() {
        driver.render("""<button data-testid="no-classes">No Classes</button>""")

        val noClasses = driver.getBy(TestId, "no-classes")
        assertEquals(emptySet(), noClasses.classes)
        expect(noClasses).not.toHaveClass()
    }
}
