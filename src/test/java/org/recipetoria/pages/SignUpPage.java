package org.recipetoria.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    private WebDriver driver;

    @FindBy(css="//a[text() = 'Get started']")
    private WebElement getStartedBtn;

    @FindBy(css = ".sign-page__header")
    private WebElement getWelcomeText;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SignUpPage setGetStartedBtn() {
        getStartedBtn.click();
        return this;
    }
    public String setGetWelcomeText(){
        return getWelcomeText.getText();

    }
}
