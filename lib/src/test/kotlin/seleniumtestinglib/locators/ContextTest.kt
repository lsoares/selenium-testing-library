package seleniumtestinglib.locators

import org.openqa.selenium.By
import org.openqa.selenium.support.pagefactory.ByChained
import seleniumtestinglib.Role
import seleniumtestinglib.TL.By.role
import seleniumtestinglib.driver
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals


class ContextText {

    @Test
    fun `chaining test`() {
        driver.render(
            """
           <form aria-label='createUser'>
             <input type='checkbox' />
           </form>
        """.repeat(2)
        )
        val checkboxes = driver.findElements(ByChained(By.tagName("form"), By.tagName("input")))

        val checkboxesTL = driver.findElements(ByChained(role(Role.Form), role(Role.CheckBox)))

        assertEquals(checkboxes.size, checkboxesTL.size)
    }
}