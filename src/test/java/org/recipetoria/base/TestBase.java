package org.recipetoria.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    private static WebDriver driver;
    public static Properties envConfig;
    private WebDriverWait wait1;
    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;

    public static final String ENV = System.getProperty("env", "Production");
    public static final String BROWSER = System.getProperty("browser", "Chrome");

    //Automation suite setup method to configure and instantiate a particular browser
    @BeforeSuite
    public void suiteSetup() throws IOException {

        if (BROWSER.equals("Firefox")) {
            driver = new FirefoxDriver();
        } else if (BROWSER.equals("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            driver = new ChromeDriver(options);
        } else if (BROWSER.equals("IE")) {
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Browser type unsupported");
        }

        driver.manage().window().maximize();

        InputStream configFile = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\test\\java\\org\\recipetoria\\config\\" + ENV + ".properties");
        envConfig = new Properties();
        envConfig.load(configFile);

    }
    @BeforeMethod()
    public void loadBaseUrl(Method method) {
        driver.get(envConfig.getProperty("baseUrl"));
        System.out.println("address = " + envConfig.getProperty("baseUrl"));
    }

    @AfterMethod
    public void screenshotAndDeleteCookies(ITestResult testResult) throws IOException {
        //Deleting cookies
        driver.manage().deleteAllCookies();
        //driver.quit();
    }

    @AfterSuite
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
