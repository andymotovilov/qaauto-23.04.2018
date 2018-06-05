package test;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class LinkedinResetPasswordTest extends LinkedinBaseTest{

    @Test
    public void succesfulPasswordResetTest(){
        String userEmail = "fridrossa@gmail.com";
        String newUserPassword = "P@ssword12345";

        LinkedinRequestPasswordResetPage linkedinRequestPasswordResetPage = linkedinLoginPage.clickOnForgotPasswordLink();
        Assert.assertTrue(linkedinRequestPasswordResetPage.isPageLoaded(), "RequestPasswordResetPage is not loaded!");

        LinkedinRequestPasswordResetSubmitPage linkedinRequestPasswordResetSubmitPage =
                linkedinRequestPasswordResetPage.submitUserEmail(userEmail);
        Assert.assertTrue(linkedinRequestPasswordResetSubmitPage.isPageLoaded(), "RequestPasswordResetSubmitPage is not loaded!");

        LinkedinSetNewPasswordPage linkedinSetNewPasswordPage = linkedinRequestPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(linkedinSetNewPasswordPage.isPageLoaded(), "SetNewPasswordPage is not loaded!");

        LinkedinSuccesfulPasswordResetPage linkedinSuccesfulPasswordResetPage = linkedinSetNewPasswordPage.submitNewPassword(newUserPassword);
        Assert.assertTrue(linkedinSuccesfulPasswordResetPage.isPageLoaded(), "SuccesfulPasswordResetPage is not loaded!");

        LinkedinHomePage linkedinHomePage = linkedinSuccesfulPasswordResetPage.clickOnGoToHomeButton();
       Assert.assertTrue(linkedinHomePage.isPageLoaded(), "HomePage is not loaded!");




    }

}
