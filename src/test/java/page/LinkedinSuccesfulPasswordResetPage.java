package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LinkedinSuccesfulPasswordResetPage extends LinkedinBasePage{

   @FindBy(id = "reset-password-submit-button")
   private WebElement goHomeButton;

    public LinkedinSuccesfulPasswordResetPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isPageLoaded() {
        return true;
    }

    public LinkedinHomePage clickOnGoToHomeButton() {

        goHomeButton.click();
        return new LinkedinHomePage(webDriver);
    }
}
