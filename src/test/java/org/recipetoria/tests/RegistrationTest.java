package org.recipetoria.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.recipetoria.base.TestBase;
import org.recipetoria.pages.RegistrationPage;
import org.recipetoria.pages.StartPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RegistrationTest extends TestBase {
    @DataProvider(name = "registrationData")
    public Object[][] registrationData() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/test/resources/dataRegistration.txt"));
        int lines = 0;
        while (scanner.hasNextLine()) {
            lines++;
            scanner.nextLine();
        }
        scanner.close();
        Object[][] data = new Object[lines][4];
        scanner = new Scanner(new File("src/test/resources/dataRegistration.txt"));
        for (int i = 0; i < lines; i++) {
            String[] line = scanner.nextLine().split(" \\| ");
            data[i][0] = line[0];
            data[i][1] = line[1];
            data[i][2] = line[2];
            data[i][3] = line[3];
        }
        scanner.close();
        return data;
    }

    @Test(dataProvider = "registrationData")
    public void registrationNewUser(String nickName, String email, String password, String repeatPassword) {

        StartPage startPage = new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        RegistrationPage registrationPage = new RegistrationPage(getDriver())
                .inputNickname(nickName)
                .inputEmail(email)
                .inputPassword(password)
                .inputRepeatPassword(repeatPassword)
                .clickCheckboxAgree()
                .clickButtonGetStarted();

        getWait1().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[normalize-space()='Start page']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//h3[normalize-space()='Start page']")).getText(), "Start page");
    }
}