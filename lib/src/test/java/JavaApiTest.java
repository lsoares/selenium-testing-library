import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seleniumtestinglib.CoreKt.asJsExpression;
import static seleniumtestinglib.DriverKt.render;
import static seleniumtestinglib.Role.Heading;
import static seleniumtestinglib.TL.*;


public class JavaApiTest {

    @RemoteWebDriverTest
    public void byTestId(RemoteWebDriver driver) {
        render(driver, "<div data-testid='custom' />");

        var result = driver.findElement(testId("custom"));
        assertEquals("div", result.getTagName());
    }

    @RemoteWebDriverTest
    public void testByRole(RemoteWebDriver driver) {
        render(driver, "<h1>something as a user something</h1>");

        var result = driver.findElements(
                role(Heading).name(asJsExpression("/something/")).level(1)
        );

        assertEquals("something as a user something", result.get(0).getAccessibleName());
    }

    @RemoteWebDriverTest
    public void testByAltText(RemoteWebDriver driver) {
        render(driver, "<img alt='Incredibles 2 Poster' src='/incredibles-2.png' />");

        var result = driver.findElements(altText("incredibles").exact(false));

        assertEquals("img", result.get(0).getTagName());
    }
}
