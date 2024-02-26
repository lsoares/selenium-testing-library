package seleniumtestinglib

import org.openqa.selenium.By
import seleniumtestinglib.locators.TL.By.role
import seleniumtestinglib.locators.RoleType.Article
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class TargetLocatorTest {

    @Test
    fun `test change to iframe by a selector inside the iframe`() {
        driver.render(
            """
           <iframe id="0" src="${"".asDataUrl()}"></iframe> 
           <iframe id="1" src="${"<section></section>".asDataUrl()}"></iframe> 
           <iframe id="2" src="${"<article></article>".asDataUrl()}"></iframe> 
        """
        )
        assertNull(driver.findElements(role(Article)).firstOrNull())

        driver.switchTo().frame(By.cssSelector("article"))

        assertNotNull(driver.findElements(role(Article)).firstOrNull())
        driver.switchTo().defaultContent()
        assertNull(driver.findElements(role(Article)).firstOrNull())
    }
}
