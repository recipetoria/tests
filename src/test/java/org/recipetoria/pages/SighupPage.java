package org.recipetoria.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SighupPage {
    private WebDriver driver;

    @FindBy(xpath="//a[text() = 'Get started']")
    private WebElement getStartedBtn;

    @FindBy(css = ".sign-page__header")
    private WebElement getWelcomeText;

    public SighupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setGetStartedBtn() {
        getStartedBtn.click();
    }
    public String setGetWelcomeText(){
        return getWelcomeText.getText();

    }
}
