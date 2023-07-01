package org.recipetoria.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    private static WebDriver driver;
    WebDriverManager wdm = WebDriverManager.chromedriver().browserInDocker();
    private WebDriverWait wait1;
    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;

    //Automation suite setup method to configure and instantiate a particular browser
    @BeforeSuite(alwaysRun = true)
    @Parameters({"browserType", "baseURL"})
    public void suiteSetup(String browserType, String baseURL) throws IOException {
        if (browserType.equalsIgnoreCase("Firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless=new");
            driver = new FirefoxDriver(options);
        } else if (browserType.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
            //WebDriverManager.chromedriver().setup();
            //ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless=new");
            //driver = new ChromeDriver(options);
            driver = wdm.create();
        } else {
            throw new RuntimeException("Browser type unsupported");
        }

        driver.manage().window().maximize();
        driver.get(baseURL);
        System.out.println("baseUrl  - " + baseURL);
    }
//    @Parameters("baseURL")
//    public void loadBaseUrl(String baseURL) {
//        TestBase.baseURL = baseURL;
//        driver.get(baseURL);
//        System.out.println("baseUrl  - " + baseURL);
//    }

    @AfterMethod(alwaysRun = true)
    public void screenshotAndDeleteCookies(ITestResult testResult) throws IOException {
        driver.manage().deleteAllCookies();
    }

    @AfterSuite(alwaysRun = true)
    public void suiteTearDown() {

        driver.quit();
    }

    public WebDriverWait getWait1() {
        if (wait1 == null) {
            wait1 = new WebDriverWait(driver, Duration.ofSeconds(1));
        }
        return wait1;
    }

    public WebDriverWait getWait2() {
        if (wait2 == null) {
            wait2 = new WebDriverWait(driver, Duration.ofSeconds(2));
        }
        return wait2;
    }

    public WebDriverWait getWait5() {
        if (wait5 == null) {
            wait5 = new WebDriverWait(driver, Duration.ofSeconds(5));
        }
        return wait5;
    }

    public WebDriverWait getWait10() {
        if (wait10 == null) {
            wait10 = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return wait10;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
