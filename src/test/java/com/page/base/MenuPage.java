package com.page.base;

import com.config.ConfigurationManager;
import com.pages.Admin.FileManagerPages.FileManagerPage;
import org.testng.Assert;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.pages.Admin.FileManagerPages.FileManagerPage.timeInFileManager;


public class MenuPage extends WestMinsterPageHook {

    //to generate random number
    public static int randomNumber;

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

    // Decimal Precision value
    public static String decimalPrecision;

    // Number format value
    public static String preferenceNumberFormat = "";

    // Date format value
    public static String preferenceDateFormat;

    // Date format value
    public static String preferenceTimeFormat;

    // example current time in Local Timezone
    public static String currentTimeBeforeFileUpload;
    public static String currentTimeAfterFileUpload;

    //default FirstName and LastName of the user
//    public static String firstName="";
//    public static String lastName="";

    // To store Local Timezone options in this list
    public static List<String> texts = new ArrayList<>();

    public String frameDivTextLocator(String placeholder) {
        return "//div[contains(text(),'" + placeholder + "')]";
    }

    public String frameSpanTextLocator(String placeholder) {
        return "//span[text()='" + placeholder + "']";
    }

    public String frameh2TextLocator(String placeholder) {
        return "//h2[text()='" + placeholder + "']";
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

    public MenuPage clickProfileBtn() {
        click("//span[text()='Profile']", "click on Profile");
        return this;
    }

    public MenuPage clickEditProfileBtn() {
        click("//span[text()='Edit Profile']", "click on Edit Profile");
        return this;
    }

    public MenuPage clickProfileSaveBtn() {
        click("//div[@id='profile-view']//span[text()='Save']", "click on Save Button", "Save Button");
        boolean message = isVisible("//span[text()='Success']");
        Assert.assertTrue(message);
        pause("low");
        return this;
    }

    public MenuPage clickEditPreferencesBtn() {
        click("//span[text()='Edit Preferences']", "click on Edit Preferences");
        return this;
    }

    public MenuPage clickPreferenceSaveBtn() {
        click("//div[@id='preferences-view']//span[text()='Save']", "click on Save Button", "Save Button");
        pause("low");
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

    public DashboardPage editProfileInfo(String firstName, String lastName){
        mouseHoverOnProfile()
                .clickAccountSettingsBtn()
                .waitForLoaderToDisappear();
        clickProfileBtn()
                .clickEditProfileBtn()
                .editFirstAndLastName(firstName,lastName)
                .clickProfileSaveBtn();
        return new DashboardPage();
    }

    public DashboardPage editFirstAndLastName(String fName, String lName){
        waitForLoaderToDisappear();
        firstName.set(getInputText("//input[contains(@id,'FIRST_NAME')]"));
        lastName.set(getInputText("//input[contains(@id,'LAST_NAME')]"));
        typeAndEnter("//input[contains(@id,'FIRST_NAME')]",fName,"First Name");
        typeAndEnter("//input[contains(@id,'LAST_NAME')]",lName,"Last Name");
        reportPass("Successfully entered First Name: "+fName+" || Last Name: "+lName);
        pause("low");
        return new DashboardPage();
    }

    public MenuPage clickAndselectDefaultDateFormat() {
        pause("low");
//        preferenceDateFormat = "MM/DD/YYYY";
        String innerText = getInputText("//input[contains(@id,'DATE_FORMAT')]");
//        clickAndChoose("//input[contains(@id,'DATE_FORMAT')]", "//div[text()='MM/DD/YYYY']", "MM/DD/YYYY", "Date Format");
//        preferenceDateFormat = "MM/DD/YYYY";
        if (innerText.equals("MM/DD/YYYY")) {
            preferenceDateFormat = "MM/DD/YYYY";
            reportPass("Successfully validated preferred Date Format: "+preferenceDateFormat);
//            click("(//span[text()='Cancel'])[2]", "Cancel", "Button");

        } else {
            clickAndChoose("//input[contains(@id,'DATE_FORMAT')]", "//div[text()='MM/DD/YYYY']", "MM/DD/YYYY", "Date Format");
            preferenceDateFormat = "MM/DD/YYYY";
            reportPass("Successfully selected Data Format: "+preferenceDateFormat);
//            clickPreferenceSaveBtn();
        }
        return this;
    }

    public MenuPage clickAndselectDefaultDecimalPrecision() {
        String innerText = getInputText("//input[contains(@id,'DECIMAL_PRECISION')]");
        if (innerText.equals("1 decimal")) {
            decimalPrecision = "1 decimal";
            reportPass("Successfully validated preferred Decimal Precision: "+decimalPrecision);
//            click("(//span[text()='Cancel'])[2]", "Cancel", "Button");
        } else {
            clickAndChoose("//input[contains(@id,'DECIMAL_PRECISION')]", "//div[text()='1 decimal']", "1 decimal", "Decimal Precision");
            decimalPrecision = "1 decimal";
            reportPass("Successfully selected Decimal Precision: "+decimalPrecision);
            //            clickPreferenceSaveBtn();
        }

        return this;
    }

    public MenuPage clickAndselectDefaultNumberFormat() {
        String innerText = getInputText("//input[contains(@id,'NUMBER_FORMAT')]");
        if (innerText.equals("#,##0.00")) {
            preferenceNumberFormat = "#,##0.00";
            reportPass("Successfully validated preferred Number Format: "+preferenceNumberFormat);
//            click("(//span[text()='Cancel'])[2]", "Cancel", "Button");
        } else {
            clickAndChoose("//input[contains(@id,'NUMBER_FORMAT')]", "//div[text()='#,##0.00']", "#,##0.00", "Number Format");
            preferenceNumberFormat = "#,##0.00";
            reportPass("Successfully selected Number Format: "+preferenceNumberFormat);
//            clickPreferenceSaveBtn();
        }
        return this;
    }


    public MenuPage clickAndselectDateFormat() {
        pause("low");
        String innerText = getInputText("//input[contains(@id,'DATE_FORMAT')]");
        if (innerText.equals("YYYY-MM-DD")) {
            clickAndChoose("//input[contains(@id,'DATE_FORMAT')]", "//div[text()='MM/DD/YYYY']", "MM/DD/YYYY", "Date Format");
            pause("low");
            preferenceDateFormat = "MM/DD/YYYY";
            clickPreferenceSaveBtn();
            reportPass("Date format have been setted to: " + preferenceDateFormat);
        } else if (innerText.equals("MM/DD/YYYY")) {
            clickAndChoose("//input[contains(@id,'DATE_FORMAT')]", "//div[text()='DD/MM/YYYY']", "DD/MM/YYYY", "Date Format");
            pause("low");
            preferenceDateFormat = "DD/MM/YYYY";
            clickPreferenceSaveBtn();
            reportPass("Date format have been set to: " + preferenceDateFormat);
        } else {
            clickAndChoose("//input[contains(@id,'DATE_FORMAT')]", "//div[text()='YYYY-MM-DD']", "YYYY-MM-DD", "Date Format");
            pause("low");
            preferenceDateFormat = "YYYY-MM-DD";
            clickPreferenceSaveBtn();
            reportPass("Date format have been set to: " + preferenceDateFormat);
        }
        return this;
    }

    public MenuPage clickAndselectDecimalPrecision() {
        pause("low");
        String innerText = getInputText("//input[contains(@id,'NUMBER_FORMAT')]");
        if (innerText.equals("# ##0,00")) {
            preferenceNumberFormat = "# ##0,00";
        } else {
            preferenceNumberFormat = "#,##0.00";
        }
        String innerText2 = getInputText("//input[contains(@id,'DECIMAL_PRECISION')]");
        switch (innerText2) {
            case "2 decimals":
                clickAndChoose("//input[contains(@id,'DECIMAL_PRECISION')]", "//div[text()='3 decimals']", "3 decimals", "Decimal Precision");
                decimalPrecision = "3 decimals";
                pause("low");
                clickPreferenceSaveBtn();
                reportPass("User successfully edited decimal preference" + decimalPrecision);
                break;
            case "3 decimals":
                clickAndChoose("//input[contains(@id,'DECIMAL_PRECISION')]", "//div[text()='4 decimals']", "4 decimals", "Decimal Precision");
                decimalPrecision = "4 decimals";
                pause("low");
                clickPreferenceSaveBtn();
                reportPass("User successfully edited decimal preference" + decimalPrecision);
                break;
            case "4 decimals":
                clickAndChoose("//input[contains(@id,'DECIMAL_PRECISION')]", "//div[text()='5 decimals']", "5 decimals", "Decimal Precision");
                decimalPrecision = "5 decimals";
                pause("low");
                clickPreferenceSaveBtn();
                reportPass("User successfully edited decimal preference" + decimalPrecision);
                break;
            case "5 decimals":
                clickAndChoose("//input[contains(@id,'DECIMAL_PRECISION')]", "//div[text()='1 decimal']", "1 decimal", "Decimal Precision");
                decimalPrecision = "1 decimal";
                pause("low");
                clickPreferenceSaveBtn();
                reportPass("User successfully edited decimal preference" + decimalPrecision);
                break;
            default:
                decimalPrecision = innerText2;
                click("(//span[text()='Cancel'])[2]", "Cancel", "Button");
                break;
        }
        return this;
    }

    public MenuPage clickAndSelectNumberFormat() {
        pause("low");
        String innerText = getInputText("//input[contains(@id,'NUMBER_FORMAT')]");
        if (innerText.equals("#,##0.00")) {
//            click("(//span[text()='Cancel'])[2]", "Cancel", "Button");
            clickAndChoose("//input[contains(@id,'NUMBER_FORMAT')]", "//div[text()='# ##0,00']", "# ##0,00", "Number Format");
            preferenceNumberFormat = "# ##0,00";
            pause("low");
            clickPreferenceSaveBtn();
            reportPass("User successfully edited number format " + preferenceNumberFormat);
        } else {
            pause("low");
            click("(//span[text()='Cancel'])[2]", "Cancel", "Button");
//            clickAndChoose("//input[contains(@id,'NUMBER_FORMAT')]", "//div[text()='#,##0.00']", "#,##0.00", "Number Format");
//            preferenceNumberFormat= "#,##0.00";
//            pause("low");
//            clickPreferenceSaveBtn();
//            reportPass("User successfully edited number format "+ preferenceNumberFormat);
        }
        return this;
    }

    public MenuPage clickAndselectTimeFormat() {
        pause("low");
        String innerText = getInputText("//input[contains(@id,'TIME_FORMAT')]");
        if (innerText.equals("24-hour")) {
            clickAndChoose("//input[contains(@id,'TIME_FORMAT')]", "//div[text()='12-hour']", "12-hour", "Time Format");
            pause("low");
            preferenceTimeFormat = "12-hour";
            clickPreferenceSaveBtn();
            pause("low");
            reportPass("User successfully edited time format to: " + preferenceTimeFormat);
        } else {
            clickAndChoose("//input[contains(@id,'TIME_FORMAT')]", "//div[text()='24-hour']", "24-hour", "Time Format");
            pause("low");
            preferenceTimeFormat = "24-hour";
            clickPreferenceSaveBtn();
            pause("low");
            reportPass("User successfully edited time format to: " + preferenceTimeFormat);
        }
        return this;
    }

    public MenuPage clickAndselectLocalTimezone() {

        String innerText = getInputText("//input[contains(@id,'TIME_FORMAT')]");
        if (innerText.equals("24-hour")) {
            preferenceTimeFormat = "24-hour";
        } else {
            preferenceTimeFormat = "12-hour";
        }
        click("//input[contains(@id,'LOCAL_TIMEZONE')]", "Drop Down");
        int count = getLocatorsCount("//div[@class='dx-scrollview-content']//div[@class='wdx-custom-dropdown']//div[@class='wdx-custom-dropdown__text']//div[1]");
        for (int i = 1; i <= count; i++) {
            String innertext = getInnerText("(//div[@class='dx-scrollview-content']//div[@class='wdx-custom-dropdown']//div[@class='wdx-custom-dropdown__text']//div[1])[" + i + "]");
            texts.add(innertext);
        }
        System.out.println("list size ranges: " + texts.size());
        Random random = new Random();
        int min = 1;
        int max = texts.size();
        randomNumber = random.nextInt(max - min + 1) + min;
        System.out.println("random number is: " + randomNumber);
        clickUsingJs("//div[@class='wdx-custom-dropdown__text']/div[text()='" + texts.get(randomNumber) + "']", "option","Drop Down");
        pause("low");
        clickPreferenceSaveBtn();
        return this;
    }


    public DashboardPage clickAndselectLocalTimezone(String timeZone) {
        mouseHoverOnProfile()
                .clickAccountSettingsBtn()
                .waitForLoaderToDisappear();
        clickPreferencesBtn()
                .clickEditPreferencesBtn();
        waitForElementToLoad("//input[contains(@id,'LOCAL_TIMEZONE')]");
        selectOptionFromDropdown("//input[contains(@id,'LOCAL_TIMEZONE')]", "//div[@class='dx-scrollview-content']//div[@class='wdx-custom-dropdown']//div[@class='wdx-custom-dropdown__text']//div[1]", timeZone, "Time Zone");
        clickPreferenceSaveBtn();
        return new DashboardPage();
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
            reportPass("User Successfully Logged Into the Application");
        } else {
            reportFailAndThrowException("login With Password is not enabled");
        }
        pause("medium");
        waitForLoaderToDisappear();
        return new DashboardPage();
    }

    //Navigate to the application URL
    public DashboardPage navigateURL() {
        pause("low");
        if (isVisible("//span[contains(text(),'OK')]")) {
            click("//span[contains(text(),'OK')]", "OK", "Button");
        }
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

    public DashboardPage defaultFormattingInPreferences() {
        mouseHoverOnProfile()
                .clickAccountSettingsBtn()
                .waitForLoaderToDisappear();
        clickPreferencesBtn()
                .clickEditPreferencesBtn()
                .clickAndselectDefaultDateFormat()
                .clickAndselectDefaultNumberFormat()
                .clickAndselectDefaultDecimalPrecision()
                .clickPreferenceSaveBtn();
        new DashboardPage().clickOnHomePage();

        return new DashboardPage();
    }

    public DashboardPage setDecicalPrecisionInPreferences() {
        mouseHoverOnProfile()
                .clickAccountSettingsBtn()
                .waitForLoaderToDisappear();
        clickPreferencesBtn()
                .clickEditPreferencesBtn()
                .clickAndselectDecimalPrecision();
        return new DashboardPage();
    }

    public DashboardPage validateDecimalPrecision() {
        pause("low");
        String str = getAttribute("//input[@type='hidden' and @name='DECIMAL_PRECISION']", "value");
        if (decimalPrecision.equals("1 decimal")) {
            boolean result = verifyInnerText(str, "1");
            Assert.assertTrue(result);
            reportPass("Successfully validated decimal precision. Option selected is: " + str);
        } else if (decimalPrecision.equals("2 decimals")) {
            boolean result = verifyInnerText(str, "2");
            Assert.assertTrue(result);
            reportPass("Successfully validated decimal precision. Option selected is: " + str);
        } else if (decimalPrecision.equals("3 decimals")) {
            boolean result = verifyInnerText(str, "3");
            Assert.assertTrue(result);
            reportPass("Successfully validated decimal precision. Option selected is: " + str);
        } else if (decimalPrecision.equals("4 decimals")) {
            boolean result = verifyInnerText(str, "4");
            Assert.assertTrue(result);
            reportPass("Successfully validated decimal precision. Option selected is: " + str);
        } else {
            boolean result = verifyInnerText(str, "5");
            Assert.assertTrue(result);
            reportPass("Successfully validated decimal precision. Option selected is: " + str);
        }

        return new DashboardPage();
    }

    public DashboardPage setNumberFormatInPreferences() {

        if (isVisible("//span[text()='Edit Preferences']")) {
            clickEditPreferencesBtn()
                    .clickAndSelectNumberFormat();
        } else {
            mouseHoverOnProfile()
                    .clickAccountSettingsBtn()
                    .waitForLoaderToDisappear();
            clickPreferencesBtn()
                    .clickEditPreferencesBtn()
                    .clickAndSelectNumberFormat();
        }

        return new DashboardPage();
    }

    public DashboardPage validateNumberFormat() {
        pause("low");
        String str = getAttribute("//input[@type='hidden' and @name='NUMBER_FORMAT']", "value");
        if (preferenceNumberFormat.equals("# ##0,00")) {
            boolean result = verifyInnerText(str, "fr-FR");
            Assert.assertTrue(result);
            reportPass("Successfully validated number format. Option selected is: " + str);
        } else {
            boolean result = verifyInnerText(str, "en-US");
            Assert.assertTrue(result);
            reportPass("Successfully validated number format. Option selected is: " + str);
        }
        return new DashboardPage();
    }

    public DashboardPage setDateFormatInPreferences() {
        mouseHoverOnProfile()
                .clickAccountSettingsBtn()
                .waitForLoaderToDisappear();
        clickPreferencesBtn()
                .clickEditPreferencesBtn()
                .clickAndselectDateFormat();

        return new DashboardPage();
    }

    public DashboardPage validateDateFormat() {
        pause("low");
        String str = getAttribute("//input[@type='hidden' and @name='DATE_FORMAT']", "value");
        if (preferenceDateFormat.equals("MM/DD/YYYY")) {
            boolean result = verifyInnerText(str, "MM/dd/yyyy");
            Assert.assertTrue(result);
            reportPass("Successfully validated date format. Option selected is: " + str);
        } else if (preferenceDateFormat.equals("DD/MM/YYYY")) {
            boolean result = verifyInnerText(str, "dd/MM/yyyy");
            Assert.assertTrue(result);
            reportPass("Successfully validated date format. Option selected is: " + str);
        } else {
            boolean result = verifyInnerText(str, "yyyy-MM-dd");
            Assert.assertTrue(result);
            reportPass("Successfully validated date format. Option selected is: " + str);
        }
        return new DashboardPage();
    }

    public DashboardPage setTimeFormatInPreferences() {
        mouseHoverOnProfile()
                .clickAccountSettingsBtn()
                .waitForLoaderToDisappear();
        clickPreferencesBtn()
                .clickEditPreferencesBtn()
                .clickAndselectTimeFormat();
        return new DashboardPage();
    }

    public DashboardPage setLocalTimezoneInPreferences() {
        mouseHoverOnProfile()
                .clickAccountSettingsBtn()
                .waitForLoaderToDisappear();
        clickPreferencesBtn()
                .clickEditPreferencesBtn()
                .clickAndselectLocalTimezone();

        return new DashboardPage();
    }

    public DashboardPage getTimeInLocalTimezoneBeforeFileUpload() {
        pause("low");

        if (isVisible("//span[text()='Edit Preferences']")) {
            clickEditPreferencesBtn();
            click("//input[contains(@id,'LOCAL_TIMEZONE')]", "Drop Down");
            String str = getInnerText("//div[@class='wdx-custom-dropdown__text']/div[text()='" + texts.get(randomNumber) + "']//following-sibling::div");
            currentTimeBeforeFileUpload = str.replaceAll("e.g. current time: ", "");
        } else {

            mouseHoverOnProfile()
                    .clickAccountSettingsBtn()
                    .waitForLoaderToDisappear();
            clickPreferencesBtn()
                    .clickEditPreferencesBtn();
            click("//input[contains(@id,'LOCAL_TIMEZONE')]", "Drop Down");
            String str = getInnerText("//div[@class='wdx-custom-dropdown__text']/div[text()='" + texts.get(randomNumber) + "']//following-sibling::div");
            currentTimeBeforeFileUpload = str.replaceAll("e.g. current time: ", "");
        }
        return new DashboardPage();
    }

    public DashboardPage getTimeInLocalTimezoneAfterFileUpload() {
        mouseHoverOnProfile()
                .clickAccountSettingsBtn()
                .waitForLoaderToDisappear();
        clickPreferencesBtn()
                .clickEditPreferencesBtn();
        click("//input[contains(@id,'LOCAL_TIMEZONE')]", "Drop Down");
        String str = getInnerText("//div[@class='wdx-custom-dropdown__text']/div[text()='" + texts.get(randomNumber) + "']//following-sibling::div");
        currentTimeAfterFileUpload = str.replaceAll("e.g. current time: ", "");

        return new DashboardPage();
    }

    public DashboardPage validateLocalTimezonevalue() {

        System.out.println("before time: " + currentTimeBeforeFileUpload);
        System.out.println("upload time: " + timeInFileManager);
        System.out.println("After time: " + currentTimeAfterFileUpload);
        System.out.println("preference time format: " + preferenceTimeFormat);

        // Check if the timestamp is within the range
        if (timeInFileManager.compareTo(currentTimeBeforeFileUpload) >= 0 && timeInFileManager.compareTo(currentTimeAfterFileUpload) <= 0) {
            System.out.println("The timestamp is within the specified range.");
            boolean result = true;
            Assert.assertTrue(result);
            reportPass("Successfully validated Local timeZone. Fileupload time: " + timeInFileManager +
                    "lies between range beforeUpload: " + currentTimeBeforeFileUpload + " and afterUpload: " + currentTimeAfterFileUpload);
        } else {
            System.out.println("The timestamp is not within the specified range.");
            boolean result = false;
            Assert.assertTrue(result);
            reportFail("Failed to validate Local timezone");
        }
        return new DashboardPage();
    }


}
