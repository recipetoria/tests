package org.recipetoria.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.recipetoria.base.TestBase;
import org.recipetoria.pages.LogInPage;
import org.recipetoria.pages.RegistrationPage;
import org.recipetoria.pages.StartPage;
import org.recipetoria.pages.UserProfilePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegistrationTest extends TestBase {
//    @DataProvider(name = "registrationData")
//    public Object[][] registrationData() throws FileNotFoundException {
//        List<String[]> linesList = new ArrayList<>();
//
//        try (Scanner scanner = new Scanner(new File("src/test/resources/dataRegistration.txt"))) {
//            while (scanner.hasNextLine()) {
//                String[] line = scanner.nextLine().split(" \\| ");
//                linesList.add(line);
//            }
//        }
//
//        Object[][] data = new Object[linesList.size()][3];
//
//        for (int i = 0; i < linesList.size(); i++) {
//            String[] line = linesList.get(i);
//            data[i][0] = line[0];
//            data[i][1] = line[1];
//            data[i][2] = line[2];
//        }
//
//        return data;
//    }
//
//    @Test(dataProvider = "registrationData")
//    public void registrationNewUser(String nickName, String email, String password) {
//
//        new StartPage(getDriver())
//                .clickButtonGetStarted();
//
//        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));
//
//        new RegistrationPage(getDriver())
//                .inputNickname(nickName)
//                .inputEmail(email)
//                .inputPassword(password)
//                .inputRepeatPassword(password)
//                .clickCheckboxAgree()
//                .clickButtonGetStarted();
//
//        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".start-page-top-part .left-container__h2")));
//
//        UserProfilePage userProfilePage = new UserProfilePage(getDriver());
//        userProfilePage.clickProfileIconBtn();
//
//        Assert.assertEquals(userProfilePage.checkNicknameInTheModalWindow(), nickName);
//
//        userProfilePage.clickEnterToProfileBtn();
//        userProfilePage.clickdeleteAccBtn();
//        userProfilePage.clickdeleteAccOkBtn();
//
//        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//aside/span[@class='snackbar__text']")));
//
//        Assert.assertEquals(getDriver().findElement(By.xpath("//aside/span[@class='snackbar__text']")).getText(),
//                "Your account was deleted");
//    }

    @Test()
    public void messageNicknameIsEmpty() throws InterruptedException {

        new StartPage(getDriver())
                .clickButtonGetStarted();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        new RegistrationPage(getDriver())
                .registrationNewUser("", "jorik@gmail.com", "123123");

        Thread.sleep(3000);

//        Assert.assertEquals(getDriver().findElement(By.xpath("")).getText(),
//                "Заполните это поле.");
    }

    @Test
    public void messageMaxValueOfNickname() {

        new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        new RegistrationPage(getDriver())
                .registrationNewUser("useruseruseruseruseruseruseruse", "jorik@gmail.com", "123123");

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[normalize-space()='Please enter a maximum of 30 characters']")).getText(),
                "Please enter a maximum of 30 characters");
    }

    @Test
    public void messageMinValueOfNickname() {

        new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        new RegistrationPage(getDriver())
                .registrationNewUser("Us", "jorik@gmail.com", "123123");

//        Assert.assertEquals(getDriver().findElement(By.xpath("")).getText(),
//                "Заполните это поле.");
    }

    @Test
    public void messageEmailIsEmpty() {

        new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        new RegistrationPage(getDriver())
                .registrationNewUser("User", "", "123123");

//        Assert.assertEquals(getDriver().findElement(By.xpath("")).getText(),
//                "Заполните это поле.");
    }

    @Test
    public void messageIncorrectEmail() {

        new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        new RegistrationPage(getDriver())
                .registrationNewUser("User", "jo ra@gmail.com", "123123");

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[normalize-space()='Please enter a correct e-mail']")).getText(),
                "Please enter a correct e-mail");
    }

    @Test
    public void messagePasswordIsEmpty() {

        new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        new RegistrationPage(getDriver())
                .registrationNewUser("User", "jora@gmail.com", "");

//        Assert.assertEquals(getDriver().findElement(By.xpath("")).getText(),
//                "Заполните это поле.");
    }

    @Test
    public void messageMaxValueOfPassword() {

        new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        new RegistrationPage(getDriver())
                .registrationNewUser("User", "jora@gmail.com", "PasswordПарольpasпар1234567890123");

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='error-in-form']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[@class='error-in-form']")).getText(),
                "Password must be between 3 and 30 characters...");
    }

    @Test
    public void messageMinValueOfPassword() {

        new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        new RegistrationPage(getDriver())
                .registrationNewUser("User", "jora@gmail.com", "123");

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[normalize-space()='Please enter a minimum of 6 characters']")).getText(),
                "Please enter a minimum of 6 characters");
    }

    @Test
    public void messageRepeatPasswordIsEmpty() {

        new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        new RegistrationPage(getDriver())
                .inputNickname("User")
                .inputEmail("user@gmail.com")
                .inputPassword("123123")
                .inputRepeatPassword("")
                .clickCheckboxAgree()
                .clickButtonGetStarted();

//        Assert.assertEquals(getDriver().findElement(By.xpath("")).getText(),
//                "Заполните это поле.");
    }

    @Test
    public void messageRepeatPasswordShouldBeEqual() {

        new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        new RegistrationPage(getDriver())
                .inputNickname("User")
                .inputEmail("user@gmail.com")
                .inputPassword("123123123")
                .inputRepeatPassword("123123");

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[normalize-space()='Password should be equal']")).getText(),
                "Password should be equal");
    }

    @Test
    public void messageCheckboxNotClick() {

        new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        new RegistrationPage(getDriver())
                .inputNickname("User")
                .inputEmail("user@gmail.com")
                .inputPassword("123123123")
                .inputRepeatPassword("123123")
                .clickButtonGetStarted();

//        Assert.assertEquals(getDriver().findElement(By.xpath("")).getText(),
//                "");
    }

    @Test
    public void clickableOfBtnSignIn() {

        new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//a[normalize-space()='Sign in']")).getAttribute("href"),
                "http://209.38.198.69:3000/sign_in");
    }

    @Test
    public void messegeEmailAlreadyExist() {

        new StartPage(getDriver())
                .clickButtonGetStarted();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Welcome to Reciptoria!']")));

        new RegistrationPage(getDriver())
                .registrationNewUser("User", "jora@gmail.com", "123");

        // выйти через профиль

        new RegistrationPage(getDriver())
                .registrationNewUser("User", "jora@gmail.com", "123");

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[normalize-space()='Please enter a minimum of 6 characters']")).getText(),
                "Please enter a minimum of 6 characters");

        //авторизоваться и удалить профиль

    }
}