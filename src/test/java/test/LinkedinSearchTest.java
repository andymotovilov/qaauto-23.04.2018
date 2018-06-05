package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinSearchResults;

import java.util.List;

public class LinkedinSearchTest extends LinkedinBaseTest {

    @Test
    public void basicSearchTest() throws InterruptedException {
        String searchTerm = "HR";


        LinkedinHomePage linkedinHomePage = linkedinLoginPage.login("fridrossa@gmail.com","pswrd111");
        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Login page is not loaded!");

        LinkedinSearchResults linkedinSearchResults = linkedinHomePage.search(searchTerm);
        Assert.assertTrue(linkedinSearchResults.isPageLoaded(), "Search page is not loaded!");

        List<String> searchResultsList = linkedinSearchResults.getSearchResualts();

        Assert.assertEquals(searchResultsList.size(), 10, "Count of search result items is wrong!");

        for (String searchResult:searchResultsList){
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()),
                    "Searchterm " + searchResult + " was not found in: \n" + searchResult);

        }

    }
}
