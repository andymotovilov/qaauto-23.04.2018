import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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


        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);

        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(),
                        "LinkedIn: Войти или зарегистрироваться",
                        "Login page Title is wrong");

        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(),
                "Sign in button is not displayed!");

        linkedinLoginPage.login("a.motovilov@everad.com", "pswrd111");

        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);

        Assert.assertTrue(linkedinHomePage.getCurrentTitle().contains("LinkedIn"),
                "Home page url is wrong.");

        Assert.assertEquals(linkedinHomePage.getCurrentUrl(),
                "https://www.linkedin.com/feed/",
                "Main page URL is wrong");
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
       LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(), "Sign in button is not displayed!");
       linkedinLoginPage.login("a.motovilov@everad.com","1");

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
