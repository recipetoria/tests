package org.recipetoria.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.recipetoria.base.TestBase;
import org.recipetoria.base.utils;
import org.recipetoria.pages.CategoriesPage;
import org.recipetoria.pages.LogInPage;
import org.recipetoria.pages.RegistrationPage;
import org.recipetoria.pages.UserProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CategoriesTest extends TestBase {

    @Test
    public void creatNewCategory() {
        String name = "TestCategory";

        new RegistrationPage(getDriver())
                .registrationNewUser2("UserTest", "UserTest@gmail.com", "123123");

        new CategoriesPage(getDriver())
                .openCategoryPage()
                .clickBtnCreateNewCategory()
                .inputCategoryName(name)
                .clickBtnOk();

        getWait2().until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//h4[text()='TestCategory']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//h4[text()='TestCategory']")).getText(), name);

    }

    @Test(dependsOnMethods = {"creatNewCategory"})
    public void renameCategory() throws InterruptedException {

        Thread.sleep(1500);

        new Actions(getDriver())
                .moveToElement(getDriver().findElement(By.xpath("//img[@alt='category']")))
                .moveToElement(getDriver().findElement(By.xpath("(//*[name()='svg'])[4]")))
                .click(getDriver().findElement(By.xpath("//button[normalize-space()='Rename category']")))
                .build()
                .perform();

        getWait2().until(ExpectedConditions.
                elementToBeClickable(By.xpath("//div//input[@id='categoryRename']")));

        new CategoriesPage(getDriver())
                .renameCategory("RenameTag");

        getWait2().until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//h4[text()='RenameTag']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//h4[text()='RenameTag']")).getText(), "RenameTag");

    }
    @Test(dependsOnMethods = {"renameCategory"})
    public void deleteCategory() throws InterruptedException {

        Thread.sleep(1500);

        new Actions(getDriver())
                .moveToElement(getDriver().findElement(By.xpath("//img[@alt='category']")))
                .moveToElement(getDriver().findElement(By.xpath("(//*[name()='svg'])[4]")))
                .click(getDriver().findElement(By.xpath("//button[normalize-space()='Delete category']")))
                .build()
                .perform();

        new CategoriesPage(getDriver())
                .clickBtnDeleteCategory();

        getWait2().until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//span[normalize-space()='The category was deleted']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[normalize-space()='The category was deleted']")).getText(), "The category was deleted");
    }


//    public void changePhotoOfCategory() {
//
//    }
//


    @Test
    public void deleteAccountTest() throws InterruptedException {
        UserProfilePage userProfilePage;

        userProfilePage = new UserProfilePage(getDriver());

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
