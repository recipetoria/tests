package org.recipetoria.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.recipetoria.base.TestBase;
import org.recipetoria.pages.CategoriesPage;
import org.recipetoria.pages.LogInPage;
import org.recipetoria.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CategoriesTest extends TestBase {

//    @Test
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

    @Test
    public void renameCategory() throws InterruptedException {

        new LogInPage(getDriver())
                .loginUser("UserTest@gmail.com", "123123");

        new CategoriesPage(getDriver())
                .openCategoryPage();

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
    @Test(dependsOnMethods = renameCategory)
    public void deleteCategory() throws InterruptedException {

        new LogInPage(getDriver())
                .loginUser("UserTest@gmail.com", "123123");

        new CategoriesPage(getDriver())
                .openCategoryPage();

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


    // Удалить пользователя после тестов
}
