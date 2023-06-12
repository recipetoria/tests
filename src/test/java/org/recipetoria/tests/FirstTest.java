package org.recipetoria.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.recipetoria.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest extends TestBase {

    @Test
    public void loginTest() {

        WebElement login = getDriver().findElement(By.xpath("//a[normalize-space()='Log in']"));
        login.click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));

        WebElement inputEmail = getDriver().findElement(By.xpath("//label[@for='Email']"));

        Assert.assertEquals(inputEmail.getText(), "Email");
    }

//    @Test
//    public void firstTest() {
//
//        WebElement testCase = getDriver().findElement(By.xpath("//a[normalize-space()='Test Cases']"));
//        testCase.click();
//
//        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[normalize-space()='Test Cases']")));
//
//        WebElement textTestCases = getDriver().findElement(By.xpath("//a[normalize-space()='Test Cases']"));
//
//        Assert.assertEquals(textTestCases.getText(),"Test Cases");
//    }
}
