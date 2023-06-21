package org.recipetoria.tests;

import com.github.javafaker.Faker;
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
    String email = faker.internet().emailAddress();
    String password = utilsInstance.generatePassword(6, 10, true, true);
    String newPassword = utilsInstance.generatePassword(6, 10, false, true);

    @Test
    public void sighUp() throws InterruptedException {
        userProfilePage = new UserProfilePage(getDriver());
        System.out.println(getDriver().getCurrentUrl());
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
    }
}
