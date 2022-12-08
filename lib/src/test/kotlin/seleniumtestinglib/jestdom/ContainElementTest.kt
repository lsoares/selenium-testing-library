package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.queries.ByType.TestId
import seleniumtestinglib.queries.queryBy
import seleniumtestinglib.render
import kotlin.test.Test

@ExtendWith(DriverLifeCycle::class)
class ContainElementTest(private val driver: RemoteWebDriver) {

    @Test
    fun `contain element`() {
        driver.render("""<span data-testid="ancestor"><span data-testid="descendant"></span></span>""")

        val ancestor = driver.queryBy(TestId, "ancestor")
        val descendant = driver.queryBy(TestId, "descendant")
        val nonExistentElement = driver.queryBy(TestId, "does-not-exist")

        expect(ancestor).toContainElement(descendant)
        expect(descendant).not.toContainElement(ancestor)
        expect(ancestor).not.toContainElement(nonExistentElement)
    }
}
