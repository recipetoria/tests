package org.recipetoria.tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.recipetoria.base.TestBase;
import org.recipetoria.base.utils;
import org.recipetoria.pages.UserProfilePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UserProfileTest extends TestBase {
    UserProfilePage userProfilePage;
    Faker faker = new Faker();
    utils utilsInstance = new utils();
    String nickname = faker.name().username();
    String newNickmame = faker.name().username();
    String email = faker.internet().emailAddress();
    String password;
    String newPassword;
    String localStorageData = utils.readLocalStorageFile("src/test/resources/localStorageData.json");

    @Test(groups = {"regression"})
    public void sighUpTest() throws InterruptedException {
        userProfilePage = new UserProfilePage(getDriver());
        userProfilePage.clickStartedBtn();
        nickname = faker.name().username();
        userProfilePage.typeNickname(nickname);
        userProfilePage.typeEmail(email);

        password = utilsInstance.generatePassword(6, 10, true, true);
        userProfilePage.typePassword(password);
        userProfilePage.typeRepeatPassword(password);
        userProfilePage.checkAgreeChc();
        userProfilePage.clickGetStartedBtn();
        Thread.sleep(2000);

        Assert.assertTrue(userProfilePage.checkShoppingListBtn(), "the user haven't been created");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        String localStorageData = (String) jsExecutor.executeScript("return JSON.stringify(window.localStorage);");

        utils.saveLocalStorageDataAsJson(localStorageData, "src/test/resources/localStorageData.json");
    }

    @Test(dependsOnMethods = {"sighUpTest"}, groups = {"regression"})
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

        userProfilePage.clickSaveChangesBtn();

        //add assertion that name was changed

    }

    @Test(dependsOnMethods = {"changeNameTest"}, groups = {"regression"})
    public void changePasswordTest() throws InterruptedException {
        JavascriptExecutor jsExecutor =(JavascriptExecutor) getDriver();
        jsExecutor.executeScript("window.localStorage.clear();");
        jsExecutor.executeScript("window.localStorage.setItem('authRegister', arguments[0]);", localStorageData);

        userProfilePage = new UserProfilePage(getDriver());
        getDriver().navigate().refresh();

        userProfilePage.clickProfileIconBtn();
        userProfilePage.clickEnterToProfileBtn();

        userProfilePage.clickChangePasswordMenuBtn();
        userProfilePage.typeOldPasswordInput(password);

        newPassword = utilsInstance.generatePassword(6, 10, false, true);
        userProfilePage.typeNewPasswordInput(newPassword);
        userProfilePage.typeNewPasswordRepeatInput(newPassword);
        userProfilePage.clickSaveNewPasswordBtn();
        System.out.println("New password = " + newPassword);
        userProfilePage.clickLogoutBtn();
        userProfilePage.clicklogoutOKBtn();
        userProfilePage.clickloginBtn();
        userProfilePage.typeEmail(email);
        userProfilePage.typeNewPasswordInput(newPassword);
        userProfilePage.clickSighinBtn();

        Thread.sleep(2000);
        Assert.assertTrue(userProfilePage.checkAddRecipeBtn(), "password was changed");
    }
    @Test(dependsOnMethods = {"changePasswordTest"}, groups = {"regression"})
    public void logoutLogInTest() throws InterruptedException {
        JavascriptExecutor jsExecutor =(JavascriptExecutor) getDriver();
        jsExecutor.executeScript("window.localStorage.clear();");
        jsExecutor.executeScript("window.localStorage.setItem('authRegister', arguments[0]);", localStorageData);

        userProfilePage = new UserProfilePage(getDriver());
        getDriver().navigate().refresh();

        userProfilePage.clickProfileIconBtn();
        userProfilePage.clickEnterToProfileBtn();

        userProfilePage.clickLogoutBtn();

        Assert.assertEquals(userProfilePage.checkLogoutMsg(), "Are you sure you want to log out?");

        userProfilePage.clicklogoutCancelBtn();
        userProfilePage.clickLogoutBtn();
        userProfilePage.clicklogoutOKBtn();

        Assert.assertTrue(userProfilePage.checkLoginBtn(), "Logout was successful");
    }
    @Test(dependsOnMethods = {"logoutLogInTest"}, groups = {"regression"})
    public void deleteAccountTest() throws InterruptedException {
        JavascriptExecutor jsExecutor =(JavascriptExecutor) getDriver();
        jsExecutor.executeScript("window.localStorage.clear();");
        jsExecutor.executeScript("window.localStorage.setItem('authRegister', arguments[0]);", localStorageData);

        userProfilePage = new UserProfilePage(getDriver());
        getDriver().navigate().refresh();

        userProfilePage.clickProfileIconBtn();
        userProfilePage.clickEnterToProfileBtn();

        userProfilePage.clickdeleteAccBtn();

        Assert.assertEquals(userProfilePage.checkdeleteAccText(), "Are you sure you want to delete your account?");

        userProfilePage.clickdeleteAccCancelBtn();
        userProfilePage.clickdeleteAccBtn();
        userProfilePage.clickdeleteAccOkBtn();
        //add assertion user acc was deleted msg
        Thread.sleep(2000);
        Assert.assertTrue(userProfilePage.checkLoginBtn(), "Account was deleted");
    }
}
