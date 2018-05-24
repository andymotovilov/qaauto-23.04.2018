import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinLoginPage extends LinkedinBasePage{


    @FindBy(id = "login-email")
    private WebElement emailField;

    @FindBy(id = "login-password")
    private WebElement passwordField;

    @FindBy(id = "login-submit")
    private WebElement signInButton;

    @FindBy(id = "link-forgot-password")
    private WebElement forgotPassword;

    @FindBy(id = "reg-firstname")
    private WebElement registrationFormName;

    @FindBy(id = "reg-lastname")
    private WebElement registrationFormSurname;

    @FindBy(id = "reg-email")
    private WebElement registrationFormEmail;

    @FindBy(id = "reg-password")
    private WebElement registrationFormPassword;

    @FindBy(id = "registration-submit")
    private WebElement registrationSubmitButton;

    @FindBy(id = "alert-content")
    private WebElement failRegistrationMessage;

    public LinkedinLoginPage(WebDriver webDriver){
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }


    public LinkedinHomePage login(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
        return PageFactory.initElements(webDriver, LinkedinHomePage.class);
    }

    public void registration(String name, String surname, String email, String password){
        registrationFormName.sendKeys(name);
        registrationFormSurname.sendKeys(surname);
        registrationFormEmail.sendKeys(email);
        registrationFormPassword.sendKeys(password);
        registrationSubmitButton.click();

    }

    public String failRegistrMessage(){

        return failRegistrationMessage.getText();
    }

    public boolean isSignInButtonDisplayed() {

        return signInButton.isDisplayed();
    }

    public void forgotPasswordClick(){
        forgotPassword.click();
    }
}
