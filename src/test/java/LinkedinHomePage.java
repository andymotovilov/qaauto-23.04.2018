import org.openqa.selenium.WebDriver;


public class LinkedinHomePage {

    WebDriver webDriver;

    public LinkedinHomePage(WebDriver webDriver){

        this.webDriver = webDriver;
    }

    public String getCurrentTitle() {
        return webDriver.getTitle();
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }
}
