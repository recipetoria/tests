package org.recipetoria.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage {
    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public LogInPage openLoginPage() {
        getDriver().findElement(By.xpath("//a[text()='Log in']")).click();
        return this;
    }

    public LogInPage inputEmail(String email) {
        getDriver().findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        return this;
    }

    public LogInPage inputPassword(String password) {
        getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        return this;
    }

    public LogInPage clickBtnSignIn() {
        getDriver().findElement(By.xpath("//input[@value='Sign in']"))
                .click();
        return this;
    }

    public LogInPage clickCheckBoxRememberMe() {
        getDriver().findElement(By.xpath("//label[normalize-space()='Remember me']"))
                .click();
        return this;
    }

    public LogInPage loginUser(String email, String password) {
        new LogInPage(getDriver())
                .openLoginPage()
                .inputEmail(email)
                .inputPassword(password)
                .clickCheckBoxRememberMe()
                .clickBtnSignIn();
        return this;
    }

    public LogInPage getWelcomeTxt() {
        getDriver().findElement(By.xpath("//h3[1]"))
                .getText();
        return this;
    }

    public LogInPage getUsernameTxt() {
        getDriver().findElement(By.xpath("//h3[2]"))
                .getText();
        return this;
    }
}
