package org.recipetoria.tests;

import org.recipetoria.base.TestBase;
import org.recipetoria.pages.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SighUpTest extends TestBase {
    SignUpPage signUpPage;
    @Test
    public void registrationNewUser() {
        signUpPage = new SignUpPage(getDriver());

        signUpPage.setGetStartedBtn();
        String welcome = signUpPage.setGetWelcomeText();
        Assert.assertEquals(welcome, "Welcome to Reciptoria!");

        Assert.assertEquals("Welcome to Reciptoria!", "Welcome to User!", "True doesn't equel to false");

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
