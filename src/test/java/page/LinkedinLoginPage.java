package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinLoginPage extends LinkedinBasePage {

    @FindBy(id = "login-email")
    private WebElement emailField;

    @FindBy(id = "login-password")
    private WebElement passwordField;

    @FindBy(id = "login-submit")
    private WebElement signInButton;

    @FindBy(xpath = "//input [@class='registration submit-button']")
    private WebElement buttonWillJoin;

    @FindBy(xpath = "//span [@class='alert-content']")
    private WebElement allertContentMassage;

    @FindBy(id = "reg-firstname")
    private WebElement loginRegistration;

    @FindBy(id = "reg-firstname")
    private WebElement lastNameRegistration;

    @FindBy(id = "reg-email")
    private WebElement emailRegistration;

    @FindBy(id = "reg-password")
    private WebElement passwordRegistration;

    @FindBy (className = "link-forgot-password")
    private WebElement forgotPassword;


    public LinkedinLoginPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Override
    public boolean isPageLoaded() throws InterruptedException {
        waitUntilElementIsClickable(signInButton, 10);
        return signInButton.isDisplayed();
    }

    public LinkedinErrorPage loginSubmitPage(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
//        return PageFactory.initElements(webDriver, page.LinkedinErrorPage.class);
        return new LinkedinErrorPage(webDriver);
    }

    public <T> T login(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
        if (getCurrentUrl().contains("/feed")) {
            return (T) new LinkedinHomePage(webDriver);
        }
        if (getCurrentUrl().contains("/login-submit")) {
            return (T) new LinkedinErrorPage(webDriver);
        } else {
            return (T) this;
        }
    }


//    public boolean isSighInButtonDisplayed () {
//        return signInButton.isDisplayed();
//    }


    public void joinButton (){
        buttonWillJoin.click();
    }

    public String getAllertContentMassage (){
        return allertContentMassage.getText();
    }

    public void loginRegistration (String name){
        loginRegistration.sendKeys(name);
        buttonWillJoin.click();
    }

    public void formRegistration (String name, String lastName, String email, String password){
        loginRegistration.sendKeys(name);
        lastNameRegistration.sendKeys(lastName);
        emailRegistration.sendKeys(email);
        passwordRegistration.sendKeys(password);
        buttonWillJoin.click();
    }

    public LinkedinRequestPasswordResetPage clickOnForgotPasswordLink(){

        forgotPassword.click();
        return new LinkedinRequestPasswordResetPage(webDriver);
    }
}