import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.BaseTestAzat;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;


public class RegistrationTest extends BaseTestAzat {

    @DataProvider(name = "validData")
    public Object [][] validData() {
        return new Object [][] {
                {"azatbay", "azatbay@gmail.com", "123123", "123123"}
        };
    }

    @Test(dataProvider = "valiData")
    public void registrationNewUser() {
        getDriver().get("http://209.38.198.69:3000/");

        WebElement buttonGetStarted = getDriver().findElement(By.xpath("//a[text()='Get started']"));
        buttonGetStarted.click();

        WebElement nickName = getDriver().findElement(By.xpath("//input[@id = 'nickname']"));
        WebElement email = getDriver().findElement(By.xpath("//input[@id='email']"));
        WebElement password = getDriver().findElement(By.xpath("//input[@id='password']"));
        WebElement repeatPassword = getDriver().findElement(By.xpath("//input[@id = 'repeatPassword']"));

        nickName.sendKeys();
        email.sendKeys();
        password.sendKeys();
        repeatPassword.sendKeys();

        WebElement checkboxAgree = getDriver().findElement(By.xpath("//input[@type='checkbox']"));
        checkboxAgree.click();

        WebElement buttonSubmit = getDriver().findElement(By.xpath("//input[@type='submit']"));
        buttonSubmit.click();
    }
}
