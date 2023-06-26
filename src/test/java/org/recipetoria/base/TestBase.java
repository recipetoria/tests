package org.recipetoria.base;

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
    public static Properties envConfig;
    private static String baseURL;
    private WebDriverWait wait1;
    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;

    public static final String BROWSER = System.getProperty("browser", "Chrome");

    //Automation suite setup method to configure and instantiate a particular browser
    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() throws IOException {
        if (BROWSER.equals("Firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless=new");
            driver = new FirefoxDriver(options);
        } else if (BROWSER.equals("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            driver = new ChromeDriver(options);
        } else {
            throw new RuntimeException("Browser type unsupported");
        }

        driver.manage().window().maximize();
    }
    @BeforeMethod(alwaysRun = true)
    @Parameters("baseURL")
    public void loadBaseUrl(String baseURL) {
        TestBase.baseURL = baseURL;
        driver.get(baseURL);
        System.out.println("baseUrl  - " + baseURL);
    }

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
