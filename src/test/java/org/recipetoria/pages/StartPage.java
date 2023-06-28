package org.recipetoria.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StartPage extends BasePage{

    public StartPage(WebDriver driver) {
        super(driver);
    }

    public StartPage clickButtonGetStarted() {
        getDriver().findElement(By.xpath("//a[text()='Get started']")).click();
        return this;
    }

    public StartPage clickButtonLogIn() {
        getDriver().findElement(By.xpath("//a[text()='Get started']")).click();
        return this;
    }


    public StartPage avatarBtn() {
        getDriver().findElement(By.cssSelector(".avatar__image_default")).click();
        return this;
    }

    public StartPage logoutBtn() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='log-out__btn']")));
        getDriver().findElement(By.xpath("//button[@class='log-out__btn']")).click();
        return this;
    }

    public StartPage okBtnModalWindow() {
        getDriver().findElement(By.xpath("//button[@class='delete-account__ok']"))
                .click();
        return this;
    }

    public StartPage getUsernameTxt() {
        getDriver().findElement(By.xpath("//h3[2]"))
                .getText();
        return this;
    }

    public StartPage getSnackbarAccWasDeletedTxt() {
        getDriver().findElement(By.xpath("//aside/span[@class='snackbar__text']")).getText();
        return this;
    }
}