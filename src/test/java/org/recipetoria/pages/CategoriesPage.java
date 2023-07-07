package org.recipetoria.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CategoriesPage extends BasePage {
    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    public CategoriesPage openCategoryPage() {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Categories']")));
        getDriver().findElement(By.xpath("//a[text()='Categories']")).click();
        return this;
    }

    public CategoriesPage clickBtnCreateNewCategory() {
        getWait2().until(ExpectedConditions.
                elementToBeClickable(By.xpath("//span[text()='Create new category']")));

        getDriver().findElement(By.xpath("//span[text()='Create new category']")).click();
        return this;
    }

    public CategoriesPage inputCategoryName(String categoryName) {
        getWait2().until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//input[@id='categoryName']")));

        getDriver().findElement(By.xpath("//input[@id='categoryName']")).sendKeys(categoryName);
        return this;
    }

    public CategoriesPage clickBtnOk(){
        getDriver().findElement(By.xpath("//button[text()='Ok']")).click();
        return this;
    }

    public CategoriesPage clickBtnCancel(){
        getDriver().findElement(By.xpath("//button[text()='Cancel']")).click();
        return this;
    }

    public CategoriesPage openCategory(){
        getDriver().findElement(By.xpath("")).click();
        return this;
    }

    public CategoriesPage editCategory() {
        getWait2().until(ExpectedConditions.
                elementToBeClickable(By.xpath("(//*[name()='svg'])[4]")));
        getDriver().findElement(By.xpath("(//*[name()='svg'])[4]")).click();
        return this;
    }

    public CategoriesPage renameCategory(String newName) {
        getDriver().findElement(By.xpath("//div//input[@id='categoryRename']")).sendKeys(newName);
        getDriver().findElement(By.xpath("//button[normalize-space()='Ok']")).click();
        return this;
    }

    public CategoriesPage clickBtnDeleteCategory() {
        getDriver().findElement(By.xpath("//button[normalize-space()='Delete']")).click();
        return this;
    }

    public CategoriesPage clickBtnCancelDeleteCategory() {
        getDriver().findElement(By.xpath("//button[normalize-space()='Cancel']")).click();
        return this;
    }

    public CategoriesPage clickBtnUploadPictureCategory() {
        getDriver().findElement(By.xpath("//div[normalize-space()='Upload picture']")).click();
        return this;
    }

    public CategoriesPage inputPictureCategory(String filePath) {
        getDriver().findElement(By.xpath("(//input[@type='file'])[1]")).sendKeys(filePath);
        return this;
    }
}
