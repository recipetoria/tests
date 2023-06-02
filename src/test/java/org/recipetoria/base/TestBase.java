package org.recipetoria.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    protected static WebDriver driver;
    public static Properties envConfig;
    WebDriverWait wait;

    public static final String ENV = System.getProperty("env", "Production");
    public static final String BROWSER = System.getProperty("browser", "Chrome");

    //Automation suite setup method to configure and instantiate a particular browser
    @BeforeSuite
    public void suiteSetup() throws IOException {

        if (BROWSER.equals("Firefox")) {
            driver = new FirefoxDriver();
        } else if (BROWSER.equals("Chrome")) {
            driver = new ChromeDriver();
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

    }

    @AfterMethod
    public void screenshotAndDeleteCookies(ITestResult testResult) throws IOException {
            //Deleting cookies
        driver.manage().deleteAllCookies();
    }

    @AfterSuite
    public void suiteTearDown() {
        driver.quit();
    }

}



