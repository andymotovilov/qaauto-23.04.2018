import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LinkedinLoginTest {

    WebDriver webDriver;

    @BeforeMethod
    public void before(){
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
    }

    @DataProvider
    public Object[][] rightValuesLoginPage() {
        return new Object[][]{
                { "a.motovilov@everad.com", "pswrd111" },
                { "A.MOTOVILOV@EVERAD.COM", "pswrd111" }
        };
    }

    @DataProvider
    public Object[][] wrongValuesLoginPage() {
        return new Object[][]{
                { "1", "1" },
                { "a", "a" }
        };
    }

    @DataProvider
    public Object[][] wrongValuesRegistrationForm() {
        return new Object[][]{
                { "andrey", "motovilov", "a.m@mail.com", "1" },
                { "a", "b", "c", "d"  }
        };
    }


    @Test(dataProvider = "rightValuesLoginPage")
    public void succesfulLoginTest(String email, String password) {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);

        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(),
                        "LinkedIn: Войти или зарегистрироваться",
                        "Login page Title is wrong");

        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(),
                "Sign in button is not displayed!");

        linkedinLoginPage.login(email, password);

        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);

        Assert.assertTrue(linkedinHomePage.getCurrentTitle().contains("LinkedIn"),
                "Home page url is wrong.");

        Assert.assertEquals(linkedinHomePage.getCurrentUrl(),
                "https://www.linkedin.com/feed/",
                "Main page URL is wrong");
    }

    @Test(dataProvider = "wrongValuesLoginPage")
    public void loginNegativeWrongValuesTest(String email, String password) {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);

        linkedinLoginPage.login(email, password);

        LinkedinAuthorizationFailPage linkedinAuthorizationFailPage = new LinkedinAuthorizationFailPage(webDriver);

        Assert.assertEquals(linkedinAuthorizationFailPage.getErrorText(),
                "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Wrong error message text displayed.");

    }

    @Test(dataProvider = "wrongValuesRegistrationForm")
    public void registrationFormWrongValuesTest(String name, String surname, String email, String password){

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        linkedinLoginPage.registration(name, surname, email, password);

        Assert.assertEquals(linkedinLoginPage.failRegistrMessage(),
                "Укажите действительный адрес электронной почты",
                "Wrong error message text displayed.");

    }


    @Test
    public void signInButtonTest() {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);

        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(),
                "Sign in button is not displayed!");

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

    @Test
    public void forgotPasswordTest(){

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        linkedinLoginPage.forgotPasswordClick();

        LinkedinForgotPasswordPage linkedinForgotPasswordPage = new LinkedinForgotPasswordPage(webDriver);
        Assert.assertEquals(linkedinForgotPasswordPage.getCurrentTitle(),
                "Изменить пароль | LinkedIn", "It is not forgot password page!");

    }



    @AfterMethod
    public void after(){

        webDriver.close();
    }

}
