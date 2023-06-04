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
}
