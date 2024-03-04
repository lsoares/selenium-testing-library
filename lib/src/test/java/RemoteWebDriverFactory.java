import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.stream.Stream;

public class RemoteWebDriverFactory {
    public static Stream<RemoteWebDriver> drivers() {
        return Stream.of(
                new ChromeDriver(new ChromeOptions().addArguments("--headless")),
                new FirefoxDriver(new FirefoxOptions().addArguments("--headless"))
        );
    }
}
