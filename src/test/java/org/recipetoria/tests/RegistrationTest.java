package org.recipetoria.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.recipetoria.base.TestBase;
import org.recipetoria.pages.RegistrationPage;
import org.recipetoria.pages.StartPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {
    @DataProvider(name = "validData")
    public Object[][] providerValidData() {
        return new Object[][]
                {
                        {"azatbay", "azatbay@gmail.com", "123123", "123123"}
                };
    }

    @Test(dataProvider = "validData")
    public void registrationNewUser(String nickName, String email, String password, String repeatPassword) {

        StartPage startPage = new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        RegistrationPage registrationPage = new RegistrationPage(getDriver())
                .inputNickname(nickName)
                .inputEmail(email)
                .inputPassword(password)
                .inputRepeatPassword(repeatPassword)
                .clickCheckboxAgree()
                .clickButtonGetStarted();

//      Прописать проверку регистрации. Относительно чего проверять ?
    }
}
