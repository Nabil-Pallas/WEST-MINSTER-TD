package com.page.base;

import com.config.ConfigurationManager;
import com.pages.Admin.AdminPage;
import com.pages.Analytics.AnalyticsPage;
import com.pages.Analytics.NewAnalysisPage;
import com.pages.Report.ReportsPage;
import com.pages.Bugeting.AllBudgetsPage;
import org.testng.Assert;

public class DashboardPage extends MenuPage {

    //Locator For Mouse Hover On The Menu Section
    private String headerProfileMouseHover = "//label[@class='header__profile__dropdown__trigger__label']";

    //Locator For Admin Option
    private String adminLink = "//span[text()='Admin']";

    //Locator For Analytics Option
    private String analyticsLink = "//span[text()='Analytics']";

    //Locator ForBudgeting Option
    private String budgetingLink = "//span[text()='Budgeting']";

    //Locator For Save Button
    private String saveBtn = "//div[@aria-label='Save']";

    //Locator For Client Selection DropDown
    private String clientSelectDrpDown = "(//div[@class='dx-dropdowneditor-icon'])[1]";

    //Locator For Logout Button
    private String logout = "//span[text()='Logout']";

    //Logout Method
    private String reportsLink = "//span[text()='Reports']";


    public MenuPage clickOnLogout() {
        if (mouseOver(headerProfileMouseHover, "Header Profile MouseHover")) {
            click(logout, "Logout");
        } else {
            reportFailAndThrowException("Mouser Hover on Header Profile is failed");
        }
        return new MenuPage();
    }

    //Validate Dashboard Element in Dashboard Page
    public DashboardPage validateDashBoardElements() {
        waitForLoaderToDisappear();
        waitForElementToLoad(dashboardHover);
        if (getPage().title().contains("Dashboard | Westminster")) {
            reportStep("User successfully reached the correct Dashboard page", "info");
        } else {
            reportFailAndThrowException("Dashboard page is not loaded.");
        }
        return this;

    }

    public DashboardPage validateImpersonatingAsUserText() {
        waitForLoaderToDisappear();
        waitForElementToLoad(impersonatingAsUserText);
        if (isVisible(impersonatingAsUserText, "impersonating a user Text.")) {
            reportStep("User successfully Impersonated the User", "pass");
        } else {
            reportFailAndThrowException("User Unable to Impersonate.");
        }
        return this;

    }

    public DashboardPage validateImpersonateInAccountSettings(String email) {
        mouseHoverOnProfile()
                .clickAccountSettingsBtn()
                .waitForLoaderToDisappear();
        waitForLoaderToDisappear();
        System.out.println(getInputText("//input[contains(@id,'_email')]") + "---");
        boolean AssertStatus = verifyInputText("//input[contains(@id,'_email')]", email);
        Assert.assertTrue(AssertStatus);
        return this;

    }

    // Click the Admin Option
    public AdminPage clickOnAdmin() {
        switch (xmlUserLevel) {
            case "Tier3EndUser":
                break;
            default:
                mouseHoverOnDashboard();
                click(adminLink, "Admin Link");
                pause("medium");
                break;
        }
        return new AdminPage();
    }

    // Click the Analytics Option
    public AnalyticsPage clickOnAnalytics() {
        mouseHoverOnDashboard();
        click(analyticsLink, "Analytics Link");
        return new AnalyticsPage();
    }

    //Click the Budgeting Option
    public AllBudgetsPage clickOnBudgeting() {
        mouseHoverOnDashboard();
        click(budgetingLink, "Budgeting Link");
        return new AllBudgetsPage();
    }

    //Return to the Home Page
    public DashboardPage clickOnHomePage() {
        click(homeLink, "Home Link");
        waitForLoaderToDisappear();
        return this;

    }

    public ReportsPage clickOnReports() {
        mouseHoverOnDashboard();
        click(reportsLink, "Reports Link");
        return new ReportsPage();
    }

    public DashboardPage checkAnalysisIsInDashboardOrNot(String analysisName) {

        pause("low");
        if (isVisible(frameh2TextLocator(analysisName), "Analysis")) {
            reportPass("The analysis " + analysisName + " is present in the dashboard.");
        } else {
            reportPass("The analysis " + analysisName + " is not present in the dashboard.");
        }

        return this;
    }

    public DashboardPage checkCompareDDIsPresentOrNot(String analysisName) {
        try {
            pause("medium");
            if (isVisible("//h2[text()='" + analysisName + "']/following::span[contains(text(),'Compare')]")) {
                click("//h2[text()='" + analysisName + "']/following::span[contains(text(),'Compare')]/following::div[@aria-label='Select']", "Compare", "Drop Down");

                if (isVisible("//h2[text()='" + analysisName + "']/following::span[contains(text(),'Compare')]/following::div[@aria-label='Select']", "Comparison Dropdown")) {
                    reportPass("Successfully validated that the Comparison Drop Down is present in View Analysis Page.");
                } else {
                    reportFail("The Comparison Drop Down is not present after clicking.");
                }
            } else {
                reportFail("The 'Compare' element is not visible on the page.");
            }
        } catch (Exception e) {
            reportFail("Error checking Comparison Drop Down: " + e.getMessage());
        }
        return this;
    }

    public DashboardPage clickOnAnalysisActionBtn(String analysisName) {
        pause("low");
        click("//h2[text()='" + analysisName + "']/following::div[1]//div[@aria-label='Actions']", "Analysis Action Button");
        return this;
    }

    // Click on the "Remove from Dashboard" link
    public DashboardPage clickOnRemoveFromDashboard() {
        pause("low");
        click(frameSpanTextLocator("Remove from Dashboard"), "Remove from Dashboard Link");
        return this;
    }

    //select client
    public DashboardPage setClient() {
        switch (xmlUserLevel) {
            case "Tier2Admin":
                break;
            case "Tier2User":
                break;
            case "Tier3EndUser":
                break;
            case "Tier1User":
                try {
                    clickAndChoose(clientSelectDrpDown, "//div[contains(text(),'" + ConfigurationManager.configuration().appClientNameTier1User() + "')]", "Client Name", ConfigurationManager.configuration().appClientNameTier1User());
                    pause("low");
                    reportPass("User successfully selected the Client.");
                } catch (Exception e) {
                    reportWarning("Client is not selected as it is set as default");
                }
                break;
            default:
                try {
                    clickAndChoose(clientSelectDrpDown, "//div[contains(text(),'" + ConfigurationManager.configuration().appClientNameTier1Admin() + "')]", "Client Name", ConfigurationManager.configuration().appClientNameTier1Admin());
                    pause("low");
                    reportPass("User successfully selected the Client.");
                } catch (Exception e) {
                    reportWarning("Client is not selected as it is set as default");
                }
                break;
        }
        return this;
    }
    public DashboardPage validateEditedUserName(String firstName, String lastName){
        String innertext = getInnerText("//p[@class='header__profile__info__name']");
        String verification = firstName+" "+lastName;
        if(innertext.equals(verification)){
            reportPass("Successfully validated the edited name");
        }else {
            reportFail("Failed to validate");
        }


        return this;
    }


}
