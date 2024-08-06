package com.pages.Report.myReportsPages;

import com.pages.Report.ReportsPage;
import org.testng.Assert;

public class EditReportPage extends ReportsPage {

    String editReportPage = "//h1[text()='Edit Report']";
    private String analysisValidate = "//div[@data-report-visualization='ANALYSIS']";

    public EditReportPage typeAddContent(String placeholder, int index, String content) {
        pause("low");
        type(addReportLocator(placeholder, index), content, content);
        return this;
    }

    public EditReportPage validateEditReportsPage() {
        waitForElementToLoad(editReportText);
        if (isVisible(editReportText, "Reports Text")) {
            reportPass("User reaches to the right Reports page");
        } else {
            reportFailAndThrowException("Report Page is not loaded.");
        }
        return this;
    }


    public EditReportPage typeReportTitle(String reportTitle) {

        type("//input[@aria-label='Report Title']", reportTitle, "Report Title");
        pause("low");
        return new EditReportPage();
    }

    public EditReportPage clickOnThemeIcon() {
        click(frameSpanTextLocator("Theme"), "Theme icon", "Theme icon");
        return new EditReportPage();
    }


    public EditReportPage changeTheme(String theme) {
        clickOnThemeIcon();
        click("//p[text()='" + theme + "']", "Change Theme", "Change Theme");
        pause("low");
        return new EditReportPage();
    }

    public EditReportPage clickOnPaletteIcon() {
        click(frameSpanTextLocator("Palette"), "Palette icon", "Palette icon");
        return new EditReportPage();
    }

    public EditReportPage changePalette(String palette) {
        clickOnPaletteIcon();
        click(frameDivTextLocator(palette), "Change Palette", "Change Palette");
        pause("low");
        return new EditReportPage();
    }

    public EditReportPage addTitle(String title) {
        type("//div[@data-placeholder='Click here to add a title']/p", title, "Add a Title");
        pause("low");
        return new EditReportPage();
    }

    public EditReportPage addDescription(String description) {
        type("//div[@data-placeholder='Click here to add text']", description, "Add a description");
        pause("low");
        return new EditReportPage();
    }

    public EditReportPage validateIntroPage(String title, String text) {
        validate(addReportLocator("Click here to add a title", 1), title)
                .validate(addReportLocator("Click here to add text", 1), text);
        return new EditReportPage();
    }

    public EditReportPage validate(String locator, String text) {
        boolean verifyExactText = verifyExactText(locator, text);
        Assert.assertTrue(verifyExactText);
        return this;
    }

    public EditReportPage removeVisualization() {
        try {
            click("//i[@class='dx-icon dx-icon-remove']", "Remove Visualization", "Remove Visualization");
            pause("low");
            addBudgetOrAnalysis();
        } catch (Exception e) {
            addBudgetOrAnalysis();
        }

        return new EditReportPage();
    }

    public EditReportPage fillAnalyticsPage(String title, String introContent) {
        clickOnAnalyticsPage()
                .removeVisualization()
                .typeAddContent("Click here to add a title", 2, title)
                .typeAddContent("Click here to add text", 2, introContent);
        return new EditReportPage();
    }

    public EditReportPage clickOnAnalyticsPage() {
        click("(//div[@class='report-page__thumbnail-preview'])[2]", "Analytics", "Analytics");
        return new EditReportPage();
    }

    public EditReportPage clickOnAddBudgetOrAnalysis() {
        click("//button[@aria-label='Add a budget or analysis']", "Add a budget or analysis", "Add a budget or analysis");
        pause("low");
        return new EditReportPage();
    }

    public EditReportPage addBudgetOrAnalysis() {
        clickOnAddBudgetOrAnalysis()
                .addAnalytics();
        return this;
    }

    public EditReportPage addAnalytics() {
        click(frameAddBudgeOrAnalysisLocator("Analytics", 2), "Analytics", "visualization ");
        pause("medium");
        if (isVisible(frameSpanTextLocator("I understand"))) {
            click(frameSpanTextLocator("I understand"), "I understand");
        }
        return this;
    }

    public EditReportPage addBudget() {
        click(frameAddBudgeOrAnalysisLocator("Budgeting", 2), "Budgeting", "visualization ");
        pause("medium");
        if (isVisible(frameSpanTextLocator("I understand"))) {
            click(frameSpanTextLocator("I understand"), "I understand");
        }
        return this;
    }

    public EditReportPage clickOnAddBudgetOrAnalysis(String placeholder, int index) {
        click(frameAddBudgetOrAnalysisLocator(placeholder, index), "Add a budget or analysis", "Table");
        return this;
    }

    public EditReportPage validateAddVisualizationReportPage() {
        waitForElementToLoad(frameDivTextLocator("Add a visualization to your report"));
        if (isVisible(frameDivTextLocator("Add a visualization to your report"), "Add a visualization to your report")) {
            reportPass("User reaches to the right Add a visualization to your report page");
        } else {
            reportFailAndThrowException("Add a visualization to your report is not loaded.");
        }
        return this;
    }

    public EditReportPage validateAnalyticsPage(String title, String text) {
        validate(addReportLocator("Click here to add a title", 2), title)
                .validate(addReportLocator("Click here to add text", 2), text);
        if (isVisible(analysisValidate)) {
            Assert.assertTrue(isVisible(analysisValidate, "Analytics Table"));
        } else {
            clickOnAddBudgetOrAnalysis("Add a budget or analysis", 1)
                    .validateAddVisualizationReportPage()
                    .addAnalytics();
        }

        return new EditReportPage();
    }

    public EditReportPage clickOnViewReport() {
        pause("low");
        click(frameSpanTextLocator("View Report"), "View Report");
        pause("medium");
        waitForLoaderToDisappear();
        pause("medium");

        return this;
    }

    public EditReportPage clickOnExitBtn() {
        pause("medium");
        pause("low");
        click(frameSpanTextLocator("< Exit"), "Exit");
        waitForLoaderToDisappear();
        pause("medium");
        return new EditReportPage();
    }
    public EditReportPage clickOnSaveIcon() {
        click("//i[@class='dx-icon dx-icon-save']", "Save Icon", "Edit Icon");
        pause("low");
        return new EditReportPage();
    }

    public EditReportPage editReportTitle(String title) {
        clickOnEditIcon()
                .typeReportTitle(title)
                .clickOnSaveIcon();
        reportPass("User successfully edited the report title.");
        return new EditReportPage();
    }
}
