package org.recipetoria.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserProfilePage {
    private WebDriver driver;
    @FindBy(css = ".orange-btn")
    WebElement StartedBtn;
    @FindBy(id = "nickname")
    WebElement nicknameInput;
    @FindBy(id = "password")
    WebElement passwordInput;
    @FindBy(id = "repeatPassword")
    WebElement repeatPassword;
    @FindBy(id = "email")
    WebElement emailInput;
    @FindBy(css = ".avatar__image_default")
    WebElement profileIconBtn;
    @FindBy(css = ".submit-btn")
    WebElement getStartedBtn;
    @FindBy(id = "checkbox")
    WebElement checkAgreeCheckbox;
    @FindBy(linkText = "Shopping list")
    WebElement checkShoppingListBtn;
    @FindBy(css = ".data-btn-cross__btn")
    WebElement enterToProfileBtn;
    @FindBy(css = ".profile-data__h3")
    WebElement profileTxt;
    @FindBy(css = ".profile-menu__btn_active")
    WebElement menuGeneralBtn;
    @FindBy(css = ".general__header")
    WebElement generalTxt;
    @FindBy(css = ".general__btn")
    WebElement generalReplacePicBtn;
    @FindBy(css = ".add-profile-photo__header")
    WebElement addProfilePicTxt;
    @FindBy(css = ".add-profile-photo__cancel")
    WebElement addProfilePicCancelBtn;
    @FindBy(css = ".add-profile-photo__upload")
    WebElement addProfilePicUploadPicBtn;
    @FindBy(css = ".drop-photo__img")
    WebElement addProfilePicDragAndDropBtn;
    @FindBy(css = ".general__caption")
    WebElement imageAllowedTxt;
    @FindBy(css = "input#nickname")
    WebElement newNicknameInput;
    @FindBy(css = ".caption")
    WebElement nameLabelTxt;
    @FindBy(css = ".profile-general__submit-btn")
    WebElement saveChangesBtn;
    @FindBy(css = ":nth-child(1) > .caption")
    WebElement nameErrorMsg;
    @FindBy(css =":nth-child(2) > .caption")
    WebElement emailErrorMsg;
    @FindBy(css = ".false.profile-menu__btn")
    WebElement changePasswordMenuBtn;
    @FindBy(css = "#oldPassword")
    WebElement oldPasswordInput;
    @FindBy(css = "#password")
    WebElement newPasswordInput;
    @FindBy(css = "#repeatPassword")
    WebElement newPasswordRepeatInput;
    @FindBy(css = ".profile-change-password__submit-btn")
    WebElement saveNewPasswordBtn;
    @FindBy(css = ".profile-menu__btns > button:nth-of-type(3)")
    WebElement logoutBtn;
    @FindBy(css = ".delete-account__text")
    WebElement logoutMsg;
    @FindBy(css = ".delete-account__cancel")
    WebElement logoutCancelBtn;
    @FindBy(css = ".delete-account__ok")
    WebElement logoutOKBtn;
    @FindBy(css = ".btn")
    WebElement loginBtn;
    @FindBy(css = ".main")
    WebElement welcomeBackTxt;
    @FindBy(css = "#checkbox")
    WebElement rememberMeChbx;
    @FindBy(css = ".submit-btn")
    WebElement sighInBtn;
    @FindBy(css = ".profile-menu > div > .profile-menu__btn")
    WebElement deleteAccBtn;
    @FindBy(css = ".delete-account__text")
    WebElement deleteAccText;
    @FindBy(css = ".delete-account__cancel")
    WebElement deleteAccCancelBtn;
    @FindBy(css = ".delete-account__ok")
    WebElement deleteAccOkBtn;

    public UserProfilePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickStartedBtn(){
        StartedBtn.click();
    }
    public void typeNickname(String nickname){
        nicknameInput.sendKeys(nickname);
    }
    public void typePassword(String password){
        passwordInput.sendKeys(password);
    }
    public void typeRepeatPassword(String password){
        repeatPassword.sendKeys(password);
    }
    public void typeEmail(String email){
        emailInput.sendKeys(email);
    }
    public void checkAgreeChc(){
        checkAgreeCheckbox.click();
    }
    public void clickGetStartedBtn(){
        getStartedBtn.click();
    }
    public boolean checkShoppingListBtn(){
        return checkShoppingListBtn.isDisplayed();
    }
    public void clickProfileIconBtn(){
        profileIconBtn.click();
    }
    public void clickEnterToProfileBtn(){
        enterToProfileBtn.click();
    }
    public String checkProfileTxt(){
        return profileTxt.getText();
    }
    public void clickMenuGeneralBtn(){
        menuGeneralBtn.click();
    }
    public void clickGeneralReplacePicBtn(){
        generalReplacePicBtn.click();
    }

    public String checkAddProfilePicTxt(){
        return addProfilePicTxt.getText();
    }
    public void clickAddProfilePicDragAndDropBtn(){
        addProfilePicDragAndDropBtn.click();
    }
    public void clickAddProfilePicUploadPicBtn(){
        addProfilePicUploadPicBtn.click();
    }
    public void clickAddProfilePicCancelBtn(){
        addProfilePicCancelBtn.click();
    }
    public void typeNewNickname(String nickname){
        newNicknameInput.sendKeys(nickname);
    }
    public void clickSaveChangesBtn(){
        saveChangesBtn.click();
    }
}
