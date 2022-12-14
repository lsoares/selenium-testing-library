package seleniumtestinglib

import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import seleniumtestinglib.locators.ByRole
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@ExtendWith(DriverLifeCycle::class)
class TargetLocatorTest(private val driver: RemoteWebDriver) {

    @Test
    fun `test change to iframe by a selector inside the iframe`() {
        driver.render(
            """
           <iframe id="0" src="${"".asDataUrl()}"></iframe> 
           <iframe id="1" src="${"<section></section>".asDataUrl()}"></iframe> 
           <iframe id="2" src="${"<article></article>".asDataUrl()}"></iframe> 
        """
        )
        assertNull(driver.findElements(ByRole("article")).firstOrNull())

        driver.switchTo().frame(By.cssSelector("article"))

        assertNotNull(driver.findElements(ByRole("article")).firstOrNull())
        driver.switchTo().defaultContent()
        assertNull(driver.findElements(ByRole("article")).firstOrNull())
    }
}
