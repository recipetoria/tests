package org.recipetoria.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.recipetoria.base.TestBase;
import org.recipetoria.pages.CategoriesPage;
import org.recipetoria.pages.RegistrationPage;
import org.recipetoria.pages.UserProfilePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


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
                .clickBtnOkModalBox();

        getWait2().until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//h4[text()='TestCategory']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//h4[text()='TestCategory']")).getText(), name);

    }

    @Test(dependsOnMethods = {"creatNewCategory"})
    public void messageMinValueOfNameCategory() {

        new CategoriesPage(getDriver())
                .openCategoryPage()
                .clickBtnCreateNewCategory()
                .inputCategoryName("Ca")
                .clickBtnOkModalBox();

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[@class='caption caption_error']")).getText(), "Please enter a minimum of 3 characters");

        new CategoriesPage(getDriver())
                .clickBtnCancelModalBox();
    }

    @Test(dependsOnMethods = {"creatNewCategory"})
    public void messageMaxValueOfNameCategory() {

        new CategoriesPage(getDriver())
                .openCategoryPage()
                .clickBtnCreateNewCategory()
                .inputCategoryName("Categorycategorycategorycategor")
                .clickBtnOkModalBox();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='caption caption_error']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[@class='caption caption_error']")).getText(), "Please enter a maximum of 30 characters");

        new CategoriesPage(getDriver())
                .clickBtnCloseModalBox();
    }

    @Test(dependsOnMethods = {"creatNewCategory"})
    public void messageCategoryIsAlreadyExist() {

        new CategoriesPage(getDriver())
                .openCategoryPage()
                .clickBtnCreateNewCategory()
                .inputCategoryName("TestCategory")
                .clickBtnOkModalBox();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='caption caption_error']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[@class='caption caption_error']")).getText(), "This category is already exist");

        new CategoriesPage(getDriver())
                .clickBtnCloseModalBox();
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
    public void changePictureOfCategory() throws InterruptedException {

        Thread.sleep(1500);

        new Actions(getDriver())
                .moveToElement(getDriver().findElement(By.xpath("//img[@alt='category']")))
                .moveToElement(getDriver().findElement(By.xpath("(//*[name()='svg'])[4]")))
                .click(getDriver().findElement(By.xpath("//button[normalize-space()='Change photo']")))
                .build()
                .perform();

        new CategoriesPage(getDriver())
                .inputPictureCategory ("C:\\Users\\User\\Documents\\PET\\tests\\src\\test\\resources\\categoryPict.jpg");

        Thread.sleep(10000);

        new CategoriesPage(getDriver())
                .openCategoryPage();

        Thread.sleep(2000);

        Assert.assertNotEquals(getDriver().findElement(By.xpath("//img[@alt='category']")).getAttribute("src"),
                "/static/media/no_photo_categ.8522d87fe489e6581fc7.png");

    }

    @Test(dependsOnMethods = {"changePictureOfCategory"})
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

    @DataProvider(name = "registrationData")
    public Object[][] getData() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/test/resources/dataCategories.txt"));
        List<Object[]> data = new ArrayList<>();

        for (String line : lines) {
            data.add(new Object[]{line});
        }

        return data.toArray(new Object[0][]);

    }

    @Test(dataProvider = "registrationData")
    public void registrationNewCategory(String name) {

        new CategoriesPage(getDriver())
                .openCategoryPage()
                .clickBtnCreateNewCategory()
                .inputCategoryName(name)
                .clickBtnOkModalBox();


        if (name.contains("'")) {
            String escapedName = name.replace("'", "\",\"'\",\"");
            getWait2().until(ExpectedConditions.
                    visibilityOfElementLocated(By.xpath("//h4[text()=concat('" + escapedName + "')]")));
            Assert.assertEquals(getDriver().findElement(By.xpath("//h4[text()=concat('" + escapedName + "')]")).getText(), name);
        } else {
            getWait2().until(ExpectedConditions.
                    visibilityOfElementLocated(By.xpath("//h4[text()='" + name + "']")));
            Assert.assertEquals(getDriver().findElement(By.xpath("//h4[text()='" + name + "']")).getText(), name);
        }


        getWait2().until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//h4[text()='"+ name + "']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//h4[text()='"+ name + "']")).getText(), name);
    }

    @Test(dependsOnMethods = {"deleteCategory"})
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

        Thread.sleep(2000);
        Assert.assertTrue(userProfilePage.checkLoginBtn(), "Account was deleted");
    }
}
