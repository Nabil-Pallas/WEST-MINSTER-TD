package com.pages.Report.myReportPages;

import com.pages.Report.ReportsPage;
import org.testng.Assert;

public class CreateNewReportPage extends ReportsPage {

    protected String newReportsText = "//h1[text()='New Report']";

    private String addBudgetOrAnalysisPage = "//button[@aria-label='Add a budget or analysis']";

    private String ReportTitle = "//input[@aria-label='Report Title']";

    private String AddPage = "//button[@aria-label='Add a new page']";

    private String AddBudget = "//button[@aria-label='Add a budget or analysis']";

    private String analysisValidate = "//div[@data-report-visualization='ANALYSIS']";

    private String budgetingValidate = "//div[@data-report-visualization='BUDGET']";

    public CreateNewReportPage validateNewReportsPage() {
        pause("medium");
        if (isVisible(newReportsText, "New Reports Text")) {
            reportPass("User successfully reached the correct New Reports page");
        } else {
            reportFailAndThrowException("New Report Page is not loaded.");
        }
        return this;
    }


    public CreateNewReportPage typeReportTitle(String reportName) {
        pause("low");
        type(ReportTitle, reportName, "Report Title Field");
        return this;
    }

    public CreateNewReportPage typeReportRangeType(String reportName) {
        pause("low");
        return this;
    }

    public CreateNewReportPage StartDate(String reportName) {
        pause("low");
        return this;
    }


    public CreateNewReportPage EndDate(String reportName) {
        pause("low");
        return this;
    }

    public CreateNewReportPage clickTheme(String theme) {
        clickAndChoose(frameSpanTextLocator("Theme"), "//p[text()='" + theme + "']", theme, "Theme");
        pause("low");
        return this;
    }

    public CreateNewReportPage clickPalette(String palette) {
        clickAndChoose(frameSpanTextLocator("Palette"), frameDivTextLocator(palette), palette, "Palette");
        pause("low");
        return this;
    }

    public CreateNewReportPage selectSlideTemplate(String template) {
        pause("low");
        clickAndChoose(frameSpanTextLocator("Slide Template"), frameDivTextLocator(template), template, "template");
        return this;
    }


    public CreateNewReportPage fillReportDetail(String reportTitle, String theme, String palette) {
        typeReportTitle(reportTitle)
                .clickTheme(theme)
                .clickPalette(palette);
        reportPass("User successfully filled the report title.");
        return this;
    }


    public CreateNewReportPage typeAddContent(String placeholder, int index, String content) {
        pause("low");
        type(addReportLocator(placeholder, index), content, content);
        return this;
    }

    public CreateNewReportPage validate(String locator, String text) {

        boolean verifyExactText = verifyExactText(locator, text);
        Assert.assertTrue(verifyExactText);

        return this;
    }


    //First Page
    public CreateNewReportPage fillIntroPage(String title, String introContent) {
        pause("medium");
        typeAddContent("Click here to add a title", 1, title)
                .typeAddContent("Click here to add text", 1, introContent);
        return this;
    }

    public CreateNewReportPage validateIntroPage(String title, String text) {
        validate(addReportLocator("Click here to add a title", 1), title)
                .validate(addReportLocator("Click here to add text", 1), text);
        return this;
    }

    //Second Page
    public CreateNewReportPage fillAnalyticsPage(String title, String introContent) {
        typeAddContent("Click here to add a title", 2, title)
                .typeAddContent("Click here to add text", 2, introContent)
                .clickOnAddBudgetOrAnalysis("Add a budget or analysis", 1)
                .validateAddVisualizationReportPage()
                .addAnalytics();
        return this;
    }

    public CreateNewReportPage validateAnalyticsPage(String title, String text) {
        validate(addReportLocator("Click here to add a title", 2), title)
                .validate(addReportLocator("Click here to add text", 2), text);
        if (isVisible(analysisValidate)) {
            Assert.assertTrue(isVisible(analysisValidate, "Analytics Table"));
        } else {
            clickOnAddBudgetOrAnalysis("Add a budget or analysis", 1)
                    .validateAddVisualizationReportPage()
                    .addAnalytics();
        }

        return this;
    }

    //Third Page
    public CreateNewReportPage fillBudgetPage(String title, String introContent) {
        typeAddContent("Click here to add a title", 3, title)
                .typeAddContent("Click here to add text", 3, introContent)
                .clickOnAddBudgetOrAnalysis("Add a budget or analysis", 2)
                .validateAddVisualizationReportPage()
                .addBudgeting();
        return this;
    }

    public CreateNewReportPage validateBudgetingPage(String title, String text) {
        validate(addReportLocator("Click here to add a title", 3), title)
                .validate(addReportLocator("Click here to add text", 3), text);
        if (isVisible(budgetingValidate)) {
            Assert.assertTrue(isVisible(budgetingValidate, "Budgeting Table"));
        } else {
            clickOnAddBudgetOrAnalysis("Add a budget or analysis", 2)
                    .validateAddVisualizationReportPage()
                    .addBudgeting();
        }
        return this;
    }

    public CreateNewReportPage clickOnAddBudgetOrAnalysis(String placeholder, int index) {
        click(frameAddBudgetOrAnalysisLocator(placeholder, index), "Add a budget or analysis", "Table");
        return this;
    }

    public CreateNewReportPage addAnalytics() {
        click(frameAddBudgeOrAnalysisLocator("Analytics", 1), "Analytics", "visualization ");
        pause("medium");
        if (isVisible(frameSpanTextLocator("I understand"))) {
            click(frameSpanTextLocator("I understand"), "I understand");
        }
        return this;
    }

    public CreateNewReportPage addBudgeting() {
        click(frameAddBudgeOrAnalysisLocator("Budgeting", 1), "Budgeting", "visualization ");
        pause("medium");
        return this;
    }

    public CreateNewReportPage validateAddVisualizationReportPage() {
        waitForElementToLoad(frameDivTextLocator("Add a visualization to your report"));
        if (isVisible(frameDivTextLocator("Add a visualization to your report"), "Add a visualization to your report")) {
            reportPass("User reaches to the right Add a visualization to your report page");
        } else {
            reportFailAndThrowException("Add a visualization to your report is not loaded.");
        }
        return this;
    }

    public CreateNewReportPage clickOnViewReport() {
        pause("low");
        click(frameSpanTextLocator("View Report"), "View Report");
        pause("medium");
        waitForLoaderToDisappear();
        pause("medium");

        return this;
    }

    public ReportsPage clickOnExitBtn() {
        pause("medium");
        pause("low");
        click(frameSpanTextLocator("< Exit"), "Exit");
        waitForLoaderToDisappear();
        pause("medium");
        return new ReportsPage();
    }


    public CreateNewReportPage clickOnAddNewPageAndValidate(String placeholder, int index) {
        click(AddPage, "Add New Page");
        if (isVisible(frameSpanTextLocator("Cancel", 5))) {
            click(frameSpanTextLocator("Cancel", 5), "Cancel");
        }
        pause("medium");
        waitForElementToLoad(frameAddBudgetOrAnalysisLocator(placeholder, index));
        if (isVisible(frameAddBudgetOrAnalysisLocator(placeholder, index), "1-column Page")) {
            reportPass("User reaches to the 1-column page");
        } else {
            reportFailAndThrowException("Column Page is not loaded.");
        }
        return this;
    }

}

