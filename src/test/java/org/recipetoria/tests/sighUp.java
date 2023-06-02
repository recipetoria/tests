package org.recipetoria.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.recipetoria.base.TestBase;
import org.recipetoria.pages.SighupPage;
import org.testng.Assert;
import org.testng.annotations.Test;



public class sighUp extends TestBase {
    SighupPage sighupPage;
    @Test
    public void registrationNewUser() {
        sighupPage = new SighupPage(driver);

        sighupPage.setGetStartedBtn();
        String welcome = sighupPage.setGetWelcomeText();
        Assert.assertEquals(welcome, "Welcome to Reciptoria!");

//        WebElement buttonLogIn = getDriver().findElement(By.xpath("//a[text() = 'Log in']"));
//        buttonLogIn.click();
//
//        WebElement userEmail = getDriver().findElement(By.id("email"));
//        WebElement userPassword = getDriver().findElement(By.id("password"));
//
//        userEmail.sendKeys();
//        userPassword.sendKeys();
 }
}
