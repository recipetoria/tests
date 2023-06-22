package org.recipetoria.tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;

import org.recipetoria.base.TestBase;
import org.recipetoria.base.utils;
import org.recipetoria.pages.UserProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserProfileTest extends TestBase {
    UserProfilePage userProfilePage;
    Faker faker = new Faker();
    utils utilsInstance = new utils();
    String nickname = faker.name().username();
    String newNickmame = faker.name().username();

    String email = faker.internet().emailAddress();
    String password = utilsInstance.generatePassword(6, 10, true, true);
    String newPassword = utilsInstance.generatePassword(6, 10, false, true);

    String localStorageData;

    @Test
    public void sighUpTest() throws InterruptedException {
        userProfilePage = new UserProfilePage(getDriver());
        userProfilePage.clickStartedBtn();
        nickname = faker.name().username();
        userProfilePage.typeNickname(nickname);
        userProfilePage.typeEmail(email);
        userProfilePage.typePassword(password);
        userProfilePage.typeRepeatPassword(password);
        userProfilePage.checkAgreeChc();
        userProfilePage.clickGetStartedBtn();
        Thread.sleep(3000);

        Assert.assertTrue(userProfilePage.checkShoppingListBtn(), "the user haven't been created");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        String localStorageData = (String) jsExecutor.executeScript("return JSON.stringify(window.localStorage);");

        utils.saveLocalStorageDataAsJson(localStorageData, "src/test/resources/localStorageData.json");
    }

    @Test
    public void changeNameTest() throws InterruptedException {
        localStorageData = utils.readLocalStorageFile("src/test/resources/localStorageData.json");
        JavascriptExecutor jsExecutor =(JavascriptExecutor) getDriver();
        jsExecutor.executeScript("window.localStorage.clear();");
        jsExecutor.executeScript("window.localStorage.setItem('authRegister', arguments[0]);", localStorageData);

        userProfilePage = new UserProfilePage(getDriver());
        getDriver().navigate().refresh();

        userProfilePage.clickProfileIconBtn();
        userProfilePage.clickEnterToProfileBtn();
        String profileTxt = userProfilePage.checkProfileTxt();

        Assert.assertEquals(profileTxt, "Profile");

        userProfilePage.clickMenuGeneralBtn();
        userProfilePage.clickGeneralReplacePicBtn();
        String addProfilePicTxt = userProfilePage.checkAddProfilePicTxt();

        Assert.assertEquals(addProfilePicTxt, "Add profile photo");

        //add action to replace the profile pic

        //userProfilePage.clickAddProfilePicUploadPicBtn();
        //userProfilePage.clickAddProfilePicDragAndDropBtn();
        userProfilePage.clickAddProfilePicCancelBtn();

        userProfilePage.typeNewNickname(newNickmame);

        //add assertion that name was changed

    }
    @Test
    public void changePasswordTest(){

    }
    @Test
    public void logoutLogInTest(){

    }
    @Test
    public void deleteAccountTest(){
    }
}
