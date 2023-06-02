import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;


public class RegistrationTest extends BaseTest {
    @Test
    public void registrationNewUser() {
        getDriver().get("http://209.38.198.69:3000/");

        WebElement buttonGetStarted = getDriver().findElement(By.xpath("//a[text() = 'Get started']"));
        buttonGetStarted.click();

        WebElement buttonLogIn = getDriver().findElement(By.xpath("//a[text() = 'Log in']"));
        buttonLogIn.click();

        WebElement userEmail = getDriver().findElement(By.id("email"));
        WebElement userPassword = getDriver().findElement(By.id("password"));

        userEmail.sendKeys();
        userPassword.sendKeys();
 }
}
