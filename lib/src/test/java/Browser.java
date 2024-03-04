import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public enum Browser {
    CHROME(new ChromeDriver(new ChromeOptions().addArguments("--headless"))),
    FIREFOX(new FirefoxDriver(new FirefoxOptions().addArguments("--headless")));

    private final RemoteWebDriver driver;

    Browser(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }
}
