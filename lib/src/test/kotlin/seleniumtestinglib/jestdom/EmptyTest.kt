package seleniumtestinglib.jestdom

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.DriverLifeCycle
import seleniumtestinglib.coreapi.ByType.TestId
import seleniumtestinglib.coreapi.getBy
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(DriverLifeCycle::class)
class EmptyTest(private val driver: RemoteWebDriver) {

    @Test
    fun empty() {
        driver.render("<span data-testid=\"not-empty\"><span data-testid=\"empty\"></span></span>")

        val empty = driver.getBy(TestId, "empty")
        assertTrue(empty.isEmptyDomElement)
        expect(empty).toBeEmptyDomElement()
        val notEmpty = driver.getBy(TestId, "not-empty")
        assertFalse(notEmpty.isEmptyDomElement)
        expect(notEmpty).not.toBeEmptyDomElement()
    }
}
