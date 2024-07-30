package com.page.base;

import com.config.ConfigurationManager;
import com.pages.Admin.UsersAndSecurityPages.AddNewClientPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class MenuPage extends WestMinsterPageHook {

    //Locator For UserName Field
    protected String emailField = "//input[@name='username']";

    //Locator For Password Field
    protected String passwordField = "//input[@name='password']";

    //Locator For Login With Password button
    protected String loginWithPassword = "//span[text()='Login with Password']";

    //Locator For Dashboard Hover
    protected String dashboardHover = "#navbar-nav__dashboard";
    protected String profileHover = "//label[@class='header__profile__dropdown__trigger__label']";
    //Locator For Home Link
    protected String homeLink = "//a[@aria-label='Go to Home']/img";

    //Locator For Loader Icon
    protected String loaderIcon = "(//div[@class='dx-loadindicator-icon'])[1]";
    //protected String loaderIcon = "(//div[text()='Loading...'])[1]";

    protected String impersonatingAsUserText = "//div[@id='impersonating-user-banner']";

    public String frameDivTextLocator(String placeholder) {
        return "//div[contains(text(),'" + placeholder + "')]";
    }

    //Wait For Loader To Disappear
    public void waitForLoaderToDisappear() {
        if (waitForDisappearance(loaderIcon, "Loader Icon")) {
            reportStep("Loader is disappeared", "info");
        } else {
            reportFail("Loader is not disappeared");
        }
    }

    public void waitForElementToDisappear(String locator) {
        if (waitForDisappearanceWithHighTime(locator, "Loader Icon")) {
            reportStep("Loader is disappeared", "info");
        } else {
            reportFail("Loader is not disappeared");
        }
    }

    //Verify whether the UserName Is Visible
    public MenuPage userNameIsVisible() {
        isVisible(emailField, "Username");
        return this;
    }

    //Verify whether the Password Is Visible
    public MenuPage passwordIsVisible() {
        isVisible(passwordField, "Password");
        return this;
    }

    //Verify whether the Login With Password Button Is Enabled
    public MenuPage signInIsEnabled() {
        isEnabled(loginWithPassword, "Login With Password");
        return this;
    }

    //Verify whether the Login With Password Button Is Disabled
    public MenuPage signInIsDisabled() {
        isDisabled(loginWithPassword, "Login With Password");
        return this;
    }

    //Type The Username
    public MenuPage typeUserName(String username) {
        //type(emailField, username, "Username");
        typeWithType(emailField, username, "Username");
        return this;
    }

    //Type The Password
    public MenuPage typePassword(String password) {
        //type(passwordField, password, "Password");
        typeWithType(passwordField, password, "Username");
        return this;
    }

    //Click The Login With Password Button
    public MenuPage clickLoginWithPassword() {
        click(loginWithPassword, "Login With Password");
        return this;
    }

    public MenuPage clickAccountSettingsBtn() {
        click("//span[text()='Account Settings']", "click on Account Settings");
        return this;
    }

    public MenuPage clickPreferencesBtn() {
        click("//span[text()='Preferences']", "click on Preferences");
        return this;
    }

    public MenuPage clickEditPreferencesBtn() {
        click("//span[text()='Edit Preferences']", "click on Edit Preferences");
        return this;
    }

    public MenuPage clickSaveBtn() {
        click("//span[text()='Save']", "click on Save Button", "Save Button");
        return this;
    }

    //Validate Login Element in Login Page
    public MenuPage validateLoginElements() {
        waitForLoaderToDisappear();
        waitForElementToLoad(emailField);
        if (getPage().title().contains("Login | Westminster")) {
            reportStep("User reaches to the right Sign In page", "info");
            userNameIsVisible().passwordIsVisible().signInIsDisabled();
        } else {
            reportFailAndThrowException("Sign-In Page is not loaded.");
        }
        return this;
    }

    //Mouse Hover On Dashboard Menu
    public DashboardPage mouseHoverOnDashboard() {
        if (mouseOver(dashboardHover, "Dashboard MouseHover")) {
            reportStep("Mouse Hover on Dashboard is success.", "info");
        } else {
            reportFailAndThrowException("Mouser Hover on Dashboard is failed");
        }
        return new DashboardPage();
    }

    public DashboardPage mouseHoverOnProfile() {
        if (mouseOver(profileHover, "Profile MouseHover")) {
            reportStep("Mouse Hover on Profile is success.", "info");
        } else {
            reportFailAndThrowException("Mouser Hover on Profile is failed");
        }
        return new DashboardPage();
    }

    public MenuPage clickAndselectDateFormating() {
        pause("low");
        String innerText = getInputText("//input[contains(@id,'DATE_FORMAT')]");
        if (innerText.equals("MM/DD/YYYY")) {
            click("(//span[text()='Cancel'])[2]", "Cancel", "Button");
        } else {
            clickAndChoose("//input[contains(@id,'DATE_FORMAT')]", "//div[text()='MM/DD/YYYY']", "MM/DD/YYYY", "Date Format");
            clickSaveBtn();
        }
        return this;
    }

    public DashboardPage mouseHover() {
        if (mouseOver(dashboardHover, "Dashboard MouseHover")) {
            reportStep("Mouse Hover on Dashboard is success.", "info");
        } else {
            reportFailAndThrowException("Mouser Hover on Dashboard is failed");
        }
        return new DashboardPage();
    }

    //Login to the Application
    public DashboardPage doLogin(String userName, String password) {
        validateLoginElements().typeUserName(userName).typePassword(password);
        if (isEnabled(loginWithPassword, "Login With Password")) {
            clickLoginWithPassword();
        } else {
            reportFailAndThrowException("login With Password is not enabled");
        }
        pause("medium");
        waitForLoaderToDisappear();
        return new DashboardPage();
    }

    //Navigate to the application URL
    public DashboardPage navigateURL() {
        waitForAppearance("//span[contains(text(),'OK')]", "OK");
        click("//span[contains(text(),'OK')]", "OK", "Button");
        navigate(ConfigurationManager.configuration().baseStagingUrl());
        return new DashboardPage();
    }

    public DashboardPage validateLoginErrorMsg(String errorMsg) {
        String errorMsgText = getInnerText(frameDivTextLocator(errorMsg));
        Assert.assertEquals(errorMsgText, errorMsg);
        String error = getInnerText("//p[contains(text(),'Sorry')]");
        reportPass("Oops! Login failed with error message : " + errorMsgText + "  " + error);
        return new DashboardPage();
    }

    public DashboardPage dateFormattingInPreferences() {
        mouseHoverOnProfile()
                .clickAccountSettingsBtn()
                .waitForLoaderToDisappear();
        clickPreferencesBtn()
                .clickEditPreferencesBtn()
                .clickAndselectDateFormating();
        new DashboardPage().clickOnHomePage();

        return new DashboardPage();
    }


}
