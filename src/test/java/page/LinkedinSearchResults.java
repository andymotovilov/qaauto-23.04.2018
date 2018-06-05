package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class LinkedinSearchResults extends LinkedinBasePage{

    @FindBy (xpath = "//li[contains(@class, 'search-result__occluded-item')]")
    private List<WebElement> searchResults;

    @FindBy (xpath = "//h3[contains(@class, 'search-results__total')]")
    private WebElement isResults;

    public LinkedinSearchResults(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        waitUntilElementIsVisible(isResults, 5);
    }



    public List<String> getSearchResualts() throws InterruptedException {
        List<String> searchResultsList = new ArrayList();
        for (WebElement searchResult:searchResults) {
            ((JavascriptExecutor)webDriver).executeScript(
                    "arguments[0].scrollIntoView();", searchResult);

            String searchResultText = searchResult.getText();
            searchResultsList.add(searchResultText);
        }
        System.out.println(searchResultsList);
        System.out.println(searchResultsList.size());
        return searchResultsList;
    }


    public boolean isPageLoaded() {
        waitUntilElementIsClickable(isResults, 5);
        return isResults.isDisplayed();
    }
}
