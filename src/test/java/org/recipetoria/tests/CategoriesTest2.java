package org.recipetoria.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.recipetoria.base.TestBase;
import org.recipetoria.pages.CategoriesPage;
import org.recipetoria.pages.LogInPage;
import org.recipetoria.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CategoriesTest2 extends TestBase {

    @Test
    public void loginUser() throws InterruptedException {

        new LogInPage(getDriver())
                .loginUser("UserTest@gmail.com", "123123");

        new CategoriesPage(getDriver())
                .openCategoryPage();

        Thread.sleep(1500);
    }

    @Test(dependsOnMethods = {"loginUser"})
    public void creatNewCategory() {
        String name = "TestCategory";

        new CategoriesPage(getDriver())
                .openCategoryPage()
                .clickBtnCreateNewCategory()
                .inputCategoryName(name)
                .clickBtnOkModalBox();

        getWait2().until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//h4[text()='TestCategory']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//h4[text()='TestCategory']")).getText(), name);

    }

    @Test(dependsOnMethods = {"creatNewCategory"})
    public void messageMaxValueOfNameCategory() {

        new CategoriesPage(getDriver())
                .openCategoryPage()
                .clickBtnCreateNewCategory()
                .inputCategoryName("Categorycategorycategorycategor")
                .clickBtnOkModalBox();

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[@class='caption caption_error']")).getText(), "Please enter a maximum of 30 characters");

        new CategoriesPage(getDriver())
                .clickBtnCloseModalBox();
    }

}
