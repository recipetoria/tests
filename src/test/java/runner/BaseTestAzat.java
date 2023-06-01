package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public abstract class BaseTestAzat {
    private WebDriver driver;
    private WebDriverWait wait1;
    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;

    @BeforeMethod
    protected void beforeMethod() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        driver = new ChromeDriver(chromeOptions);
    }

    @AfterMethod
    protected void afterMethod() {
        driver.quit();
    }

    protected WebDriver getDriver() {
        return driver;
    }


    public WebDriverWait getWait5() {
        if (wait5 == null) {
            wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }
        return wait5;
    }

    public WebDriverWait getWait1() {
        if (wait1 == null) {
            wait1 = new WebDriverWait(getDriver(), Duration.ofSeconds(1));
        }
        return wait1;
    }

    public WebDriverWait getWait2() {
        if (wait2 == null) {
            wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        }
        return wait2;
    }

    public WebDriverWait getWait10() {
        if (wait10 == null) {
            wait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait10;
    }
}
