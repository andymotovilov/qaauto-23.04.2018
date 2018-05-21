import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedinLoginTest {

    WebDriver webDriver;

    @BeforeMethod
    public void before(){
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
    }

    @Test
    public void succesfulLoginTest() {

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
    public void signInButtonTest() {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);

        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(),
                "Sign in button is not displayed!");

    }

    @Test
    public void loginNegativeWrongValuesTest() {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);

        linkedinLoginPage.login("1","1");

        LinkedinAuthorizationFailPage linkedinAuthorizationFailPage = new LinkedinAuthorizationFailPage(webDriver);

        Assert.assertEquals(linkedinAuthorizationFailPage.getErrorText(),
                "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Wrong error message text displayed.");

    }

    @Test
    public void loginNegativeEmailTest() {

       LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);

       linkedinLoginPage.login("1","pswrd111");

       LinkedinAuthorizationFailPage linkedinAuthorizationFailPage = new LinkedinAuthorizationFailPage(webDriver);

       Assert.assertEquals(linkedinAuthorizationFailPage.getErrorText(),
               "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Wrong error message text displayed.");

    }

    @Test
    public void loginNegativePasswordTest() {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);

        linkedinLoginPage.login("a.motovilov@everad.com","1");

        LinkedinAuthorizationFailPage linkedinAuthorizationFailPage = new LinkedinAuthorizationFailPage(webDriver);

        Assert.assertEquals(linkedinAuthorizationFailPage.getErrorText(),
                "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Wrong error message text displayed.");

    }

    @AfterMethod
    public void after(){

        webDriver.close();
    }

}
