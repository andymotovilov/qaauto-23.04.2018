import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {

    WebDriver webDriver;

    //@BeforeClass - Один раз на класс (на все тесты в этом классе)
    @BeforeMethod
    public void before(){
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
    }

    @Test
    public void succesfulLoginTest() throws InterruptedException {

      //  Assert.assertEquals("a","b", "Probably 'a' is not equal 'b' ");

        Assert.assertEquals(webDriver.getTitle(),
                        "LinkedIn: Войти или зарегистрироваться",
                        "Login page Title is wrong");

        Assert.assertEquals(webDriver.getCurrentUrl(),
                "https://www.linkedin.com/",
                "URL is wrong");

        Assert.assertTrue(webDriver.findElement(By.xpath("//section[@class='form-body']")).isDisplayed());

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage();
        linkedinLoginPage.login("a.motovilov@everad.com", "pswrd111");

        Assert.assertTrue(signInButton.isDisplayed(),
                "Sign In button is not displayed");

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

        // без почты и пароля
        webDriver.findElement(By.id("login-submit")).click();

        Assert.assertEquals(webDriver.getCurrentUrl(),
                "https://www.linkedin.com/",
                "Logging was made with mistake!");


        //с паролем без почты
        WebElement passwordField = webDriver.findElement(By.id("login-password"));
        passwordField.sendKeys("pswrd111");

        webDriver.findElement(By.id("login-submit")).click();

        Assert.assertEquals(webDriver.getCurrentUrl(),
                "https://www.linkedin.com/",
                "Logging was made with mistake!");


        webDriver.navigate().refresh();
        sleep(5000);

        // с почтой без пароля
        WebElement emailField = webDriver.findElement(By.id("login-email"));
        emailField.sendKeys("a.motovilov@everad.com");

        webDriver.findElement(By.id("login-submit")).click();

        Assert.assertEquals(webDriver.getCurrentUrl(),
                "https://www.linkedin.com/",
                "Logging was made with mistake!");


    }


    @Test
    public void loginNegativeTestWithTeacher() throws InterruptedException {
        WebElement emailField = webDriver.findElement(By.id("login-email"));
        WebElement passwordField = webDriver.findElement(By.id("login-password"));
        WebElement signInButton = webDriver.findElement(By.id("login-submit"));

        emailField.sendKeys("a.motovilov@everad.com");
        passwordField.sendKeys("1");
        signInButton.click();

        sleep(5000);

        String currentPageUrl = webDriver.getCurrentUrl();
        String currentPageTitle = webDriver.getTitle();

        Assert.assertEquals(currentPageUrl, "https://www.linkedin.com/uas/login-submit",
                "Login-Submit page url is wrong");

        Assert.assertEquals(currentPageTitle, "Войти в LinkedIn", "Login-Submit page Title is wrong");

        WebElement errorMessage = webDriver.findElement(By.xpath("//div[@role='alert']"));

        Assert.assertEquals(errorMessage.getText(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Wrong error message text displayed.");

    }

    @AfterMethod
    public void after(){

        webDriver.close();
    }

}
