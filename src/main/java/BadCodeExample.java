import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.awt.peer.SystemTrayPeer;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.By.xpath;

public class BadCodeExample {

    public static void main(String args[]) throws InterruptedException {

        System.out.println("Hello world!!!");
        WebDriver webDriver = new FirefoxDriver();

        webDriver.get("https://www.google.com");

        sleep(1000);
        WebElement searchField = webDriver.findElement(By.id("lst-ib"));
        sleep(1000);
        searchField.sendKeys("Selenium");
        sleep(1000);

        searchField.sendKeys(Keys.ENTER);

        //   WebElement searchButton = webDriver.findElement(By.xpath("//input[@type='button' and contains(@value, 'Google')]"));
        //   sleep (1000);
        //   searchButton.click();

        //div[@class='srg' and contains (@class, 'g')] - выделяет весь блок целиком

        sleep(5000);
        List<WebElement> blockCounter = webDriver.findElements(xpath("//div[@class='srg']//div[@class='g']"));
        System.out.println(blockCounter.size());

/*        for (WebElement blockCount : blockCounter)
        {
            String blockCountText = blockCount.getText();
            if (blockCountText.contains("Selenium")) {

                System.out.println("SearchTerm found!!!");
            }

            System.out.println(blockCountText);
        }*/

     /*   for (int i = 1; i <= 10; i++)
        {
            WebElement searchResult =  webDriver.findElement(xpath("//div[@class='srg']/div[" + i + "]"));
            String searchResultText = searchResult.getText();
            if (searchResultText.contains("Selenium")) {

                System.out.println("SearchTerm found!!!");
            }
            System.out.println(searchResultText);
        }*/

      /*  List<WebElement> seleniumCounter = webDriver.findElements(xpath("//div[@class='srg']//div[@class='g']//h3//a"));
        for (int i = 0; i < blockCounter.size(); i++)
        {
            System.out.println(seleniumCounter.get(i));
        }*/


        sleep (5000);
        webDriver.close();
    }
}
//div[@class='rc' and not contains(@class='g')]