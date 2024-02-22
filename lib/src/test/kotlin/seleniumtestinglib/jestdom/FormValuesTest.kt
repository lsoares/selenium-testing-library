package seleniumtestinglib.jestdom

import org.openqa.selenium.By
import seleniumtestinglib.driver
import seleniumtestinglib.formValues
import seleniumtestinglib.locators.ByTestId
import seleniumtestinglib.render
import kotlin.test.Test
import kotlin.test.assertEquals

class FormValuesTest {

    @Test
    fun `expect form values`() {
        driver.render(
            """
               <form data-testid="login-form">
                  <input type="text" name="username" value="jane.doe" />
                  <input type="password" name="password" value="12345678" />
                  <input type="checkbox" name="rememberMe" checked />
                  <button type="submit">Sign in</button>
             </form>
        """
        )

        val form = driver.findElement(ByTestId(("login-form")))
        expect(form).toHaveFormValues("username" to "jane.doe", "rememberMe" to true)
    }

    @Test
    fun `form values`() {
        driver.render(
            """
             <form id="x">
                <input name="name" value="the one" />
                <input type="text" name="username" value="jane.doe" />
                <input type="password" name="password" value="12345678" />
                <input type="checkbox" name="rememberMe" checked />                
                <input type="number" name="age" value="45" />
                <select name="single">
                    <option value="x">X</option>
                    <option value="y" selected>Y</option>
                </select>
                <select name="multiple" multiple>
                    <option value="x" selected>X</option>
                    <option value="y">Y</option>
                    <option value="z" selected>Z</option>
                </select>
                <input type="radio" name="pick-one" value="radio1" />
                <input type="radio" name="pick-one" value="radio2" />
                <input type="radio" name="pick-one" value="radio3" checked />
            
                <input type="checkbox" name="group-checkboxes" value="check1" checked />
                <input type="checkbox" name="group-checkboxes" value="check2" checked />
                <input type="checkbox" name="group-checkboxes" value="check3" />
                <input type="checkbox" name="group-checkboxes" value="check4" checked />
            
                <textarea name="textarea1">value of textarea</textarea>
            
                <button type="submit">Sign in</button>
             </form>
        """
        )

        assertEquals(
            mapOf(
                "name" to "the one",
                "username" to "jane.doe",
                "password" to "12345678",
                "rememberMe" to true,
                "age" to 45,
                "single" to "y",
                "multiple" to listOf("x", "z"),
                "pick-one" to "radio3",
                "group-checkboxes" to listOf("check1", "check2", "check4"),
                "textarea1" to "value of textarea",
            ),
            driver.findElement(By.id(("x"))).formValues
        )
    }
}
