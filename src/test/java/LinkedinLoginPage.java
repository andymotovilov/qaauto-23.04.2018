import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class LinkedinLoginPage {

    private WebDriver webDriver;

    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signInButton;
    private WebElement forgotPassword;

    public LinkedinLoginPage(WebDriver webdriver){
        this.webDriver = webdriver;
        initElements();
    }

    public void initElements(){
        emailField = webDriver.findElement(By.id("login-email"));
        passwordField = webDriver.findElement(By.id("login-password"));
        signInButton = webDriver.findElement(By.id("login-submit"));
        forgotPassword = webDriver.findElement(By.className("link-forgot-password"));
    }

    public void login(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
    }

    public boolean isSignInButtonDisplayed() {

        return signInButton.isDisplayed();
    }

    public String getCurrentUrl(){
        return webDriver.getCurrentUrl();
    }

    public String getCurrentTitle(){
        return webDriver.getTitle();
    }

    public void forgotPasswordClick(){
        forgotPassword.click();
    }
}
