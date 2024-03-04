import org.junitpioneer.jupiter.cartesian.CartesianTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junitpioneer.jupiter.cartesian.CartesianTest.Enum;
import static org.junitpioneer.jupiter.cartesian.CartesianTest.Values;
import static seleniumtestinglib.CoreKt.asJsExpression;
import static seleniumtestinglib.DriverKt.render;
import static seleniumtestinglib.Role.Heading;
import static seleniumtestinglib.TL.*;


public class JavaApiTest {

    @BrowserTest
    public void byTestId(Browser browser) {
        render(browser.getDriver(), "<div data-testid='custom' />");

        var result = browser.getDriver().findElement(testId("custom"));
        assertEquals("div", result.getTagName());
    }

    @BrowserTest
    public void testByRole(Browser browser) {
        render(browser.getDriver(), "<h1>something as a user something</h1>");

        var result = browser.getDriver().findElements(
                role(Heading).name(asJsExpression("/something/")).level(1)
        );

        assertEquals("something as a user something", result.get(0).getAccessibleName());
    }

    @CartesianTest
    public void testByAltText(@Enum Browser browser,
                              @Values(strings = {"img", "input", "area"}) String tagName) {
        render(browser.getDriver(), String.format("<%s alt='Incredibles 2 Poster' src='/incredibles-2.png' />", tagName));

        var result = browser.getDriver().findElement(altText("Incredibles 2 Poster").exact(false));

        assertEquals(tagName, result.getTagName());
    }
}
