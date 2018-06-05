package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LinkedinRequestPasswordResetSubmitPage extends LinkedinBasePage{

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    public LinkedinRequestPasswordResetSubmitPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isPageLoaded() {
        return true;
//        return resendLinkButton.isDisplayed();
    }

    public LinkedinSetNewPasswordPage navigateToLinkFromEmail()
    {

        //navigate
        String messageSubject = "Фридрих, данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "fridrossa@gmail.com";
        String messageFrom = "LinkedIn <security-noreply@linkedin.com>";
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 60);

        String resetPasswordLink = StringUtils.substringBetween(
                message,
                "Чтобы изменить пароль в LinkedIn, нажмите <a href=\"",
                "\" style").replace("&amp;", "&");
        System.out.println("Content: " + resetPasswordLink);

        webDriver.get(resetPasswordLink);

        return new LinkedinSetNewPasswordPage(webDriver);

    }
}
