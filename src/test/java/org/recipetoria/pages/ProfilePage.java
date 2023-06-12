package org.recipetoria.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage{
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage clickProfile() {
        getDriver().findElement(By.xpath("//a[@href='/profile']")).click();
        return this;
    }


    public ProfilePage clickButtonGeneral() {
        getDriver().findElement(By.xpath("//button[normalize-space()='General']")).click();
        return this;
    }

    public ProfilePage clickReplacePicture() {
        getDriver().findElement(By.xpath("//button[text()='Replace picture']")).click();
        return this;
    }

    public ProfilePage inputNickname(String nickname) {
        getDriver().findElement(By.xpath("//input[@id='nickname']")).sendKeys(nickname);
        return this;
    }

    public ProfilePage inputEmail(String email) {
        getDriver().findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        return this;
    }

    public ProfilePage clickButtonSaveChanges() {
        getDriver().findElement(By.xpath("//input[@value='Save changes']")).click();
        return this;
    }

    public ProfilePage clickButtonChangePassword() {
        getDriver().findElement(By.xpath("//button[normalize-space()='Change password']")).click();
        return this;
    }

    public ProfilePage inputOldPassword(String oldPassword) {
        getDriver().findElement(By.xpath("//input[@id='oldPassword']")).sendKeys(oldPassword);
        return this;
    }

    public ProfilePage inputnewPassword(String newPassword) {
        getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys(newPassword);
        return this;
    }

    public ProfilePage inputRepeatPassword(String repeatPassword) {
        getDriver().findElement(By.xpath("//input[@id='repeatPassword']")).sendKeys(repeatPassword);
        return this;
    }


    public ProfilePage clickButtonLogOut() {
        getDriver().findElement(By.xpath("//button[normalize-space()='Log Out']")).click();
        return this;
    }

    public ProfilePage clickButtonDeleteAccount() {
        getDriver().findElement(By.xpath("//button[normalize-space()='Delete account']")).click();
        return this;
    }
}


