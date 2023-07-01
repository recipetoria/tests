package org.recipetoria.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage inputNickname(String nickname) {
        getDriver().findElement(By.xpath("//input[@id='nickname']")).sendKeys(nickname);
        return this;
    }

    public RegistrationPage inputEmail(String email) {
        getDriver().findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        return this;
    }

    public RegistrationPage inputPassword(String password) {
        getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        return this;
    }

    public RegistrationPage inputRepeatPassword(String password) {
        getDriver().findElement(By.xpath("//input[@id='repeatPassword']")).sendKeys(password);
        return this;
    }

    public RegistrationPage clickCheckboxAgree() {
        getDriver().findElement(By.xpath("//input[@id='checkbox']")).click();
        return this;
    }

    public RegistrationPage clickButtonGetStarted() {
        getDriver().findElement(By.xpath("//input[@value='Get started']")).click();
        return this;
    }

    public RegistrationPage registrationNewUser(String nickName, String email, String password) {
        new RegistrationPage(getDriver())
                .inputNickname(nickName)
                .inputEmail(email)
                .inputPassword(password)
                .inputRepeatPassword(password)
                .clickCheckboxAgree()
                .clickButtonGetStarted();
        return this;
    }
}