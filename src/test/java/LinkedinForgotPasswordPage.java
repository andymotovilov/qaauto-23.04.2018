import org.openqa.selenium.WebDriver;

public class LinkedinForgotPasswordPage {
    WebDriver webDriver;

    public LinkedinForgotPasswordPage(WebDriver webDriver){

        this.webDriver = webDriver;
    }

    public String getCurrentTitle() {

        return webDriver.getTitle();
    }

}
