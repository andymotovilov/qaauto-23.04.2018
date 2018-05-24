import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLoginPage extends LinkedinBasePage{

    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signInButton;
    private WebElement forgotPassword;
    private WebElement registrationFormName;
    private WebElement registrationFormSurname;
    private WebElement registrationFormEmail;
    private WebElement registrationFormPassword;
    private WebElement registrationSubmitButton;
    private WebElement failRegistrationMessage;

    public LinkedinLoginPage(WebDriver webDriver){
        super(webDriver);
        initElements();
    }

    public void initElements(){
        emailField = webDriver.findElement(By.id("login-email"));
        passwordField = webDriver.findElement(By.id("login-password"));
        signInButton = webDriver.findElement(By.id("login-submit"));
        forgotPassword = webDriver.findElement(By.className("link-forgot-password"));
        registrationFormName = webDriver.findElement(By.id("reg-firstname"));
        registrationFormSurname = webDriver.findElement(By.id("rreg-lastname"));
        registrationFormEmail = webDriver.findElement(By.id("reg-email"));
        registrationFormPassword = webDriver.findElement(By.id("reg-password"));
        registrationSubmitButton = webDriver.findElement(By.id("registration-submit"));
        failRegistrationMessage = webDriver.findElement(By.className("alert-content"));
    }

    public void login(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
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
