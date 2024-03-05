import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seleniumtestinglib.CoreKt.asJsExpression;
import static seleniumtestinglib.DriverKt.getDriver;
import static seleniumtestinglib.DriverKt.render;
import static seleniumtestinglib.Role.Heading;
import static seleniumtestinglib.TL.*;


public class JavaApiTest {


    @Test
    public void byAltText() {
        render(getDriver(), "<img alt='Incredibles 2 Poster' src='/incredibles-2.png' />");

        var result = getDriver().findElements(altText("incredibles").exact(false));

        assertEquals("img", result.get(0).getTagName());
    }


    @Test
    public void byDisplayValue() {
        render(getDriver(), "<input placeholder='username' />");
        getDriver().findElement(placeholderText("username")).sendKeys("selenium");

        var result = getDriver().findElement(displayValue("SELEN").exact(false));

        assertEquals("input", result.getTagName());
    }

    @Test
    public void byLabelText() {
        render(getDriver(),
                " <input aria-label='Username' />" +
                        "<span><div class='x'><input aria-label='Username' /></div></span>>"
        );

        var result = getDriver().findElement(labelText("user").exact(false).selector("span .x input"));

        assertEquals("input", result.getTagName());
    }

    @Test
    public void byPlaceholderText() {
        render(getDriver(), "<input placeholder='Username' />");

        var result = getDriver().findElement(placeholderText(asJsExpression("c => c.startsWith('User')")));

        assertEquals("input", result.getTagName());
    }

    @Test
    public void byTestId() {
        render(getDriver(), "<div data-testid='custom123' />");

        var result = getDriver().findElement(testId("custom").exact(false));

        assertEquals("div", result.getTagName());
    }

    @Test
    public void byText() {
        render(getDriver(), "<span>I accept</span>" +
                "<div>I accept</div>" +
                "<script>I accept</script>");

        var result = getDriver().findElements(text("I accept").exact(true).ignore(false).selector("span"));

        assertEquals("span", result.get(0).getTagName());
    }


    @Test
    public void byTitle() {
        render(getDriver(), "<div title='foobar'>Hello World!</div>");

        var result = getDriver().findElement(title("FOO").exact(false));

        assertEquals("Hello World!", result.getText());
    }

    @Test
    public void byRole() {
        render(getDriver(), "<h1>something as a user something</h1>");

        var result = getDriver().findElements(
                role(Heading).name(Pattern.compile("Something", CASE_INSENSITIVE)).level(1)
        );

        assertEquals("something as a user something", result.get(0).getAccessibleName());
    }
}
