package com.pages.Analytics;

import com.config.ConfigurationManager;
import com.microsoft.playwright.Locator;
import com.page.base.DashboardPage;
import com.page.base.MenuPage;
import com.pages.Admin.AdminPage;
import com.pages.Admin.RulesManagerPages.RulesManagerPage;
import org.testng.Assert;

public class AnalyticsPage extends MenuPage {

    protected String analyticsText = "//h1[text()='Analytics']";
    private String clientSelectDrpDown = "(//div[@class='dx-dropdowneditor-icon'])[1]";

    public String frameSpanTextLocator(String Placeholder) {
        return "//span[contains(text(),'" + Placeholder + "')]";
    }

    public String frameSpanTextLocator(String Placeholder, int index) {
        return "(//span[contains(text(),'" + Placeholder + "')])[" + index + "]";
    }

    public String frameDivTextLocator(String Placeholder) {
        return "//div[contains(text(),'" + Placeholder + "')]";
    }

    public String frameSeriesValueField(String Placeholder) {
        return "//div[contains(@class, 'wdx-custom-dropdown__text') and contains(text(), '" + Placeholder + "')]";
    }

    public String frameDivTextLocator(String Placeholder, int index) {
        return "(//div[contains(text(),'" + Placeholder + "')])[" + index + "]";
    }

    public String frameFilterGroupLocator(String Placeholder, int index) {
        return "((//div[text()='" + Placeholder + "'])//ancestor::table/parent::div/parent::div/following-sibling::div//input[@class='dx-texteditor-input'])[" + index + "]";
    }

    public String frameVisualizationTypeLocator(String Placeholder) {
        return "(//div[@class='dataviz__title']//p[text()='" + Placeholder + "'])";
    }

    public String frameAnalysisFieldLocator(String Placeholder, int index) {
        return "(//span[text()='" + Placeholder + "']/ancestor::label/following-sibling::div//input)[" + index + "]";
    }

    public String frameAnalysisFieldLocator(String Placeholder) {
        return "(//span[text()='" + Placeholder + "']/ancestor::label/following-sibling::div//input)";
    }

    public String framePlotDataSelectDropDownLocator(String placeholder) {
        return "//div[@class='wdx-custom-dropdown__text']/div[text()='" + placeholder + "']";
    }

    public AnalyticsPage setClient() {
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


        return new AnalyticsPage();
    }

    public AnalyticsPage validateAnalyticsPage() {
        waitForElementToLoad(analyticsText);
        if (isVisible(analyticsText, "Analytics Text")) {
            reportPass("User successfully reached the correct Analytics page");
        } else {
            reportFailAndThrowException("Analytics Page is not loaded.");
        }
        return this;
    }

    public NewAnalysisPage clickOnCreateNewDataAnalysis() {
        click(frameSpanTextLocator("Create New Data Analysis"), "Create New Data Analysis");
        pause("low");
        return new NewAnalysisPage();
    }

    public AnalyticsPage validateAnalysis(String analysisName) {
        pause("medium");
        waitForLoaderToDisappear();
        pause("medium");
        long analysisListCount = getLocatorsCount("((//h3[@class='dataviz__title'])//span)");
        for (int i = 1; i < analysisListCount; i++) {
            pause("medium");
            String analysisList = "((//h3[@class='dataviz__title'])//span)[" + i + "]";
            pause("medium");
            boolean verifyExactText = verifyExactText(analysisList, analysisName);
            Assert.assertTrue(verifyExactText);
            break;
            // Exit once the match is found and validated
        }
        return this;
    }

    public AnalyticsPage deleteAnalytics(String analysisName){
        waitForLoaderToDisappear();
        pause("low");
        click("//h3[@title='"+analysisName+"']//following::div[@aria-label='Actions'][1]","Actions Button :"+analysisName);
        pause("low");
        click(frameSpanTextLocator("Delete Analysis"),"Delete Analysis");

        pause("low");
        //Delete popup
        click("//span[text()='Delete']","Delete Button");
        pause("low");
        return this;
    }


}
