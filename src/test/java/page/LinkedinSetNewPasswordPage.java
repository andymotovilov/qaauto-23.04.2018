package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LinkedinSetNewPasswordPage extends LinkedinBasePage{

    @FindBy(id = "newPassword")
    private WebElement newPassword;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(id = "reset-password-submit-button")
    private WebElement resetPassword;

    public LinkedinSetNewPasswordPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isPageLoaded() {
        return true;
    }

    public LinkedinSuccesfulPasswordResetPage submitNewPassword(String newUserPassword) {

        newPassword.sendKeys(newUserPassword);
        confirmPassword.sendKeys(newUserPassword);
        resetPassword.click();

        return new LinkedinSuccesfulPasswordResetPage(webDriver);
    }
}
