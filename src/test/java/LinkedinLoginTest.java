import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {

    @Test
    public void succesfulLoginTest() throws InterruptedException {

        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");

      //  Assert.assertEquals("a","b", "Probably 'a' is not equal 'b' ");

        Assert.assertEquals(webDriver.getTitle(),
                        "LinkedIn: Войти или зарегистрироваться",
                        "Login page Title is wrong");

        Assert.assertEquals(webDriver.getCurrentUrl(),
                "https://www.linkedin.com/",
                "URL is wrong");

        Assert.assertTrue(webDriver.findElement(By.xpath("//section[@class='form-body']")).isDisplayed());




        //WebElement inputLogin = webDriver.findElement(By.xpath("//form[@class='login-form']/input[@type='text']"));
       // inputLogin.sendKeys("a.motovilov@everad.com");
           /*    sleep(2000);
        WebElement inputPassword = webDriver.findElement(By.xpath("//form[@class='login-form']/input[@type='password']"));
        inputPassword.sendKeys("pswrd111");

        sleep(2000);
        webDriver.findElement(By.xpath("//form[@class='login-form']/input[@type='submit']")).click();*/

        WebElement emailField = webDriver.findElement(By.id("login-email"));
        WebElement passwordField = webDriver.findElement(By.id("login-password"));
        WebElement signInButton = webDriver.findElement(By.id("login-submit"));

        Assert.assertTrue(signInButton.isDisplayed(),
                "Sign In button is not displayed");

        emailField.sendKeys("a.motovilov@everad.com");
        passwordField.sendKeys("pswrd111");
        signInButton.click();


        Assert.assertEquals(webDriver.getTitle(),
                "LinkedIn",
                "Main page Title is wrong");

        Assert.assertEquals(webDriver.getCurrentUrl(),
                "https://www.linkedin.com/feed/",
                "Main page URL is wrong");

        sleep(5000);
        Assert.assertEquals(webDriver.findElement(By.xpath("//ul[@role='navigation']")).isDisplayed(), true);
    }


    @Test
    public void loginNegativeTest() throws InterruptedException {

        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");

        // без почты и пароля
        webDriver.findElement(By.id("login-submit")).click();

        Assert.assertEquals(webDriver.getCurrentUrl(),
                "https://www.linkedin.com/",
                "Logging was made with mistake!");

        if (webDriver.getCurrentUrl() == "https://www.linkedin.com/feed/")
            webDriver.navigate().back();

        //с паролем без почты
        WebElement passwordField = webDriver.findElement(By.id("login-password"));
        passwordField.sendKeys("pswrd111");

        webDriver.findElement(By.id("login-submit")).click();

        Assert.assertEquals(webDriver.getCurrentUrl(),
                "https://www.linkedin.com/",
                "Logging was made with mistake!");


        if (webDriver.getCurrentUrl() == "https://www.linkedin.com/feed/")
            webDriver.navigate().back();

        webDriver.navigate().refresh();
        sleep(5000);

        // с почтой без пароля
        WebElement emailField = webDriver.findElement(By.id("login-email"));
        emailField.sendKeys("a.motovilov@everad.com");

        webDriver.findElement(By.id("login-submit")).click();

        Assert.assertEquals(webDriver.getCurrentUrl(),
                "https://www.linkedin.com/",
                "Logging was made with mistake!");

        if (webDriver.getCurrentUrl() == "https://www.linkedin.com/feed/")
            webDriver.navigate().back();

    }
}
