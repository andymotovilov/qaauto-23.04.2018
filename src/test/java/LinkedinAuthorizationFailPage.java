import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinAuthorizationFailPage extends LinkedinBasePage{

    private WebElement errorMessage;

    public LinkedinAuthorizationFailPage(WebDriver webDriver){

        super(webDriver);
        initElements();
    }

    public void initElements(){
        errorMessage = webDriver.findElement(By.xpath("//div[@role='alert']"));
    }

    public String getErrorText(){
        return errorMessage.getText();
    }

}
