import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinAuthorizationFailPage {

    WebDriver webDriver;
    private WebElement errorMessage;

    public LinkedinAuthorizationFailPage(WebDriver webDriver){

        this.webDriver = webDriver;
        initElements();
    }

    public void initElements(){
        errorMessage = webDriver.findElement(By.xpath("//div[@role='alert']"));
    }

    public String getErrorText(){
        return errorMessage.getText();
    }

    public String getCurrentTitle() {
        return webDriver.getTitle();
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }
}
