package org.recipetoria.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StartPage extends BasePage{

    public StartPage(WebDriver driver) {
        super(driver);
    }

    public StartPage clickButtonGetStarted() {
        getDriver().findElement(By.xpath("//a[text()='Get started']")).click();
        return this;
    }

    public StartPage avatarBtn() {
        getDriver().findElement(By.cssSelector(".default-avatar")).click();
        return this;
    }

    public StartPage logoutBtn() {
        getDriver().findElement(By.cssSelector(".profile-menu__btns > button:nth-of-type(3)")).click();
        return this;
    }

}

