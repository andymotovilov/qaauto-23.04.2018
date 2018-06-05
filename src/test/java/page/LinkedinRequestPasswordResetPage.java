package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

public class LinkedinRequestPasswordResetPage extends LinkedinBasePage{

    @FindBy (id = "username")
    private WebElement emailField;

    @FindBy (className = "form__submit")
    private WebElement emailSubmitButton;





    public LinkedinRequestPasswordResetPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public LinkedinRequestPasswordResetSubmitPage submitUserEmail(String userEmail){

        gMailService.connect();

        emailField.sendKeys(userEmail);
        emailSubmitButton.click();



      //  String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 30);
      //  System.out.println("Content: " + message);
        return new LinkedinRequestPasswordResetSubmitPage(webDriver);
    }

   public boolean isPageLoaded() {

        return emailField.isDisplayed();
    }

}
