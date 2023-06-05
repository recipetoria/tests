package org.recipetoria.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.recipetoria.base.TestBase;
import org.recipetoria.pages.RegistrationPage;
import org.recipetoria.pages.StartPage;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {
    @Test
    public void registrationNewUser() {

        StartPage startPage = new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        RegistrationPage registrationPage = new RegistrationPage(getDriver())
                .inputNickname("azatbay")
                .inputEmail("azatbay@gmail.com")
                .inputPassword("123123123")
                .inputRepeatPassword("123123123")
                .clickCheckboxAgree()
                .clickButtonGetStarted();

//      Прописать проверку регистрации. Относительно чего проверять ?
    }
}
