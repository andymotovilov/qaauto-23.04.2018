import org.openqa.selenium.WebDriver;

public class LinkedinBasePage {

    protected WebDriver webDriver;

    public  LinkedinBasePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public String getCurrentTitle() {

        return webDriver.getTitle();
    }

    public String getCurrentUrl() {

        return webDriver.getCurrentUrl();
    }
}
