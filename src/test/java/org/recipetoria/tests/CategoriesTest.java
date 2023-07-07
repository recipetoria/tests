package org.recipetoria.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.recipetoria.base.TestBase;
import org.recipetoria.pages.CategoriesPage;
import org.recipetoria.pages.LogInPage;
import org.recipetoria.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

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
    public void renameCategory() {

        new LogInPage(getDriver())
                .loginUser("UserTest@gmail.com", "123123");

        new CategoriesPage(getDriver())
                .openCategoryPage()
                .editCategory();
//                .renameCategory("NewTag");

        getWait2().until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//h4[text()='NewTag']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//h4[text()='NewTag']")).getText(), "NewTag");

    }

//    public void changePhotoOfCategory() {
//
//    }
//
//    public void deleteCategory() {
//
//    }

    // Удалить пользователя после тестов
}
