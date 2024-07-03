package com.pages.Report;

import com.config.ConfigurationManager;
import com.microsoft.playwright.Locator;
import com.page.base.DashboardPage;
import com.page.base.MenuPage;
import com.pages.Admin.AdminPage;
import org.testng.Assert;

public class ReportsPage extends MenuPage {

    protected String reportsText = "//h1[text()='Reports']";
    private String clientSelectDrpDown = "(//div[@class='dx-dropdowneditor-icon'])[1]";
    protected String listOfReportName = "(//h2[contains(text(),'My Reports')]/following::a[@class='dataviz__card'])[1]";

    public String frameSpanTextLocator(String Placeholder) {
        return "//span[contains(text(),'" + Placeholder + "')]";
    }

    public String frameh1TextLocator(String Placeholder) {
        return "//h1[contains(text(),'" + Placeholder + "')]";
    }

    public String frameSpanTextLocator(String Placeholder, int index) {
        return "(//span[contains(text(),'" + Placeholder + "')])[" + index + "]";
    }

    public String frameDivTextLocator(String Placeholder) {
        return "//div[contains(text(),'" + Placeholder + "')]";
    }

    public String frameDivTextLocator(String Placeholder, int index) {
        return "(//div[contains(text(),'" + Placeholder + "')])[" + index + "]";
    }

    public String frameAddBudgetOrAnalysisLocator(String placeholder, int index) {
        return "(//button[@aria-label='" + placeholder + "'])[" + index + "]";
    }


    public String frameDropDownlocator(int index) {
        return "(//div[@class='dx-dropdowneditor-icon'])[" + index + "]";
    }


    public String addReportLocator(String Placeholder, int index) {
        return "(//div[@data-placeholder='" + Placeholder + "']//p)[" + index + "]";
    }

    public String frameAnalysisFieldLocator(String Placeholder) {
        return "(//span[text()='" + Placeholder + "']/ancestor::label/following-sibling::div//input)";
    }

    public String frameAddBudgeOrAnalysisLocator(String placeholder, int index) {
        return "(//div//p[text()='" + placeholder + "']//following::div[2]//li//button)[" + index + "]";
    }

    public String frameScheduleAutomaticDeliveryLocator(String placeholder) {
        return "//div[@class='dx-switch-container']//div[contains(text(),'" + placeholder.toUpperCase() + "')]";
    }

    //select client
    public ReportsPage setClient() {
        switch (xmlUserLevel) {
            case "Tier2Admin":
                break;
            case "Tier2User":
                break;
            case "Tier3EndUser":
                break;
            case "Tier1Admin":
                try {
                    clickAndChoose(clientSelectDrpDown, "//div[contains(text(),'" + ConfigurationManager.configuration().appClientNameTier1Admin() + "')]", "Client Name", ConfigurationManager.configuration().appClientNameTier1Admin());
                    pause("low");
                    reportPass("User successfully selected the Client.");
                } catch (Exception e) {
                    reportWarning("Client is not selected as it is set as default");
                }
            case "Tier1User":
                try {
                    clickAndChoose(clientSelectDrpDown, "//div[contains(text(),'" + ConfigurationManager.configuration().appClientNameTier1User() + "')]", "Client Name", ConfigurationManager.configuration().appClientNameTier1User());
                    pause("low");
                    reportPass("User successfully selected the Client.");
                } catch (Exception e) {
                    reportWarning("Client is not selected as it is set as default");
                }
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
        return new ReportsPage();
    }

    //Validate Report Page
    public ReportsPage validateReportsPage() {
        waitForElementToLoad(reportsText);
        if (isVisible(reportsText, "Reports Text")) {
            reportPass("User successfully reached the correct Reports page");
        } else {
            reportFailAndThrowException("Report Page is not loaded.");
        }
        return this;
    }

    public CreateNewReportPage clickOnCreateNewReport() {
        click(frameSpanTextLocator("Create New Report"), "Create New Report");
        pause("low");
        waitForLoaderToDisappear();
        return new CreateNewReportPage();
    }

    public DashboardPage validateMyReportTitle(String reportTitle) {
        waitForLoaderToDisappear();
        pause("medium");
//        reLoadPage();
//        waitForElementToLoad(frameSpanTextLocator(reportTitle));
        boolean verifyExactText = verifyExactText(frameSpanTextLocator(reportTitle), reportTitle);
        Assert.assertTrue(verifyExactText);
        return new DashboardPage();
    }

    //Click On Report to view and Delivery
    public ViewReportPage clickOnReportToView() {
        pause("medium");
        click(listOfReportName, "My Report", "My report");
        return new ViewReportPage();
    }

    public ViewReportPage clickOnReport() {
        click(frameSpanTextLocator("⋯", 1), "My Report", "My report");
//        pause("low");
        return new ViewReportPage();
    }

    public EditReportPage clickOnEditReport() {
        click(frameSpanTextLocator("Edit Report"), "Edit Report", "Edit report");
        return new EditReportPage();
    }

    public EditReportPage clickOnEditIcon() {
        click("//i[@class='dx-icon dx-icon-edit']", "Edit Icon", "Edit Icon");
        pause("low");
        return new EditReportPage();
    }


}


