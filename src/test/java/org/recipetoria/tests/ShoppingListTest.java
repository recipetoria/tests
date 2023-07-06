package org.recipetoria.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.recipetoria.base.TestBase;
import org.recipetoria.pages.LogInPage;
import org.recipetoria.pages.StartPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingListTest extends TestBase {

    @Test
    public void openShoppingList() throws InterruptedException {

//        new StartPage(getDriver())
//                .clickButtonLogin();
//
//        new LoginPage(getDriver())
//                .enterEmail("azatbay@gmail.com")
//                .enterPassword("123123")
//                .clickCheckBoxRememberMe()
//                .clickButtonSignin();
//
//        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Shopping list']")));
//
//        new StartPage(getDriver())
//                .clickShoppingList();

        Thread.sleep(2000);
    }

    @DataProvider(name = "ingredientData")
    public Object[][] registrationData() throws FileNotFoundException {
        List<String[]> linesList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("src/test/dataIngredient.txt"))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" \\| ");
                linesList.add(line);
            }
        }

        Object[][] data = new Object[linesList.size()][2];

        for (int i = 0; i < linesList.size(); i++) {
            String[] line = linesList.get(i);
            data[i][0] = line[0];
            data[i][1] = line[1];
        }
        return data;
    }

    @Test(dataProvider = "ingredientData")
    public void addNewIngredient(String name, String amount) throws InterruptedException {

        Thread.sleep(1000);

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div//span[2]")));

        WebElement addNewItem = getDriver().findElement(By.xpath("//span[normalize-space()='Add new item']"));
        Assert.assertEquals(addNewItem.getText(), "Add new item");
        WebElement emptyField = getDriver().findElement(By.xpath("//button[@class='empty-string-on-hover_true__button']"));WebElement emptyAmount = getDriver().findElement(By.xpath("//button[@class='empty-string-on-hover_true__button']"));

        new Actions(getDriver())
                .moveToElement(getDriver().findElement(By.xpath("//span[normalize-space()='Add new item']")))
                .click(getDriver().findElement(By.xpath("//button[@class='empty-string-on-hover_true__button']")))
                .sendKeys(name)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.DELETE)
                .sendKeys(amount)
                .perform();
    }
}
