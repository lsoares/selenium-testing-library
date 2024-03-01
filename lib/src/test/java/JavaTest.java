import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seleniumtestinglib.DriverKt.getDriver;
import static seleniumtestinglib.DriverKt.render;
import static seleniumtestinglib.TL.testId;


public class JavaTest {

    @Test
    public void function() {
        render(getDriver(), "<div data-testid='custom' />");

        var result = getDriver().findElement(testId("custom"));

        assertEquals("div", result.getTagName());
    }
}
