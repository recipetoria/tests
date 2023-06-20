package org.recipetoria.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.recipetoria.base.TestBase;
import org.recipetoria.pages.RegistrationPage;
import org.recipetoria.pages.StartPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegistrationTest extends TestBase {
    @DataProvider(name = "registrationData")
    public Object[][] registrationData() throws FileNotFoundException {
        List<String[]> linesList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("src/test/resources/dataRegistration.txt"))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" \\| ");
                linesList.add(line);
            }
        }

        Object[][] data = new Object[linesList.size()][3];

        for (int i = 0; i < linesList.size(); i++) {
            String[] line = linesList.get(i);
            data[i][0] = line[0];
            data[i][1] = line[1];
            data[i][2] = line[2];
        }

        return data;
    }

    @Test(dataProvider = "registrationData")
    public void registrationNewUser(String nickName, String email, String password) throws InterruptedException {

        StartPage startPage = new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        RegistrationPage registrationPage = new RegistrationPage(getDriver())
                .inputNickname(nickName)
                .inputEmail(email)
                .inputPassword(password)
                .inputRepeatPassword(password)
                .clickCheckboxAgree()
                .clickButtonGetStarted();

        getWait1().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[normalize-space()='Start page']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//h3[normalize-space()='Start page']")).getText(), "Start page");

        StartPage startPage1 = new StartPage(getDriver())
                .avatarBtn()
                .logoutBtn();
    }
}