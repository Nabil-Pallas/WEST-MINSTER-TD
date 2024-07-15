package com.pages.Analytics;

import com.pages.Admin.RulesManagerPages.AddNewRulePage;
import org.testng.Assert;

public class NewAnalysisPage extends AnalyticsPage {

    private String newAnalysisText = "//h1[text()='New Analysis']";
    private String brokerNameFirstGrid = "(//td[@class='dx-required-field-heading'])[1]";
    private String rowFields = "(//table[@class='dx-datagrid-table dx-datagrid-table-fixed'])[4]/tbody/tr[last()-1]";

    public NewAnalysisPage validateNewAnalysisPage() {
        waitForElementToLoad(newAnalysisText);
        if (isVisible(newAnalysisText, "New Analysis Text")) {
            reportPass("User successfully reached the correct New Analysis page");
        } else {
            reportFailAndThrowException("New Analysis Page is not loaded.");
        }
        return this;
    }

    public NewAnalysisPage clickOnCreateAFilter() {
        pause("low");
        click(frameSpanTextLocator("Create A Filter"), "Create a Filter");
        return this;
    }

    public NewAnalysisPage typeFilterFieldName(String field) {
        pause("low");
        typeAndEnter(frameFilterGroupLocator("Field", 1), field, "Field");
        return this;
    }

    public NewAnalysisPage typeFilterOperator(String operator) {
        pause("medium");
        typeAndEnter(frameFilterGroupLocator("Operator", 3), operator, "Operator");
        pause("medium");
        return this;
    }

    public NewAnalysisPage typeFilterValue() {
        pause("medium");
        String getBrokerName = getInnerText(brokerNameFirstGrid);
        pause("low");
        typeAndEnter(frameFilterGroupLocator("Value", 4), getBrokerName, "Value");
        click(frameSpanTextLocator("Apply"), "Apply");
        pause("medium");
        return this;
    }

    public NewAnalysisPage validateDataFilter() {
        pause("medium");
        int tableRowCount = getLocatorsCount(rowFields);
        Assert.assertEquals(tableRowCount, 1);
        return this;
    }

    public NewAnalysisPage clickOnNextBtn() {
        click(frameSpanTextLocator("Next"), "Next");
        return this;
    }

    public NewAnalysisPage clickOnSaveBtn() {
        click(frameSpanTextLocator("Save"), "Save");
        return this;
    }

    public AnalyticsPage clickOnBackBtn() {
        pause("low");
        click(frameSpanTextLocator("< Back"), "Back");
        pause("low");
        return new AnalyticsPage();
    }


    public NewAnalysisPage addFilterAndValidate(String fieldName, String operator) {
        clickOnCreateAFilter()
                .typeFilterFieldName(fieldName) //Broker Name
                .typeFilterOperator(operator) //Symbol[=,!=,>,<,>=,<=]
                .typeFilterValue()
                .validateDataFilter();
        reportPass("User Successfully Added the Filter & validated");
        return this;
    }

    public NewAnalysisPage selectVisualizationType(String visualizationType) {
        pause("low");
        click(frameVisualizationTypeLocator(visualizationType), visualizationType, "visualization Type :" + visualizationType);
        reportPass("User successfully Selected Visualization Type");
        return this;
    }

    public NewAnalysisPage reviewYourAnalysis(String analysisName, String color) {
        typeAndEnter(frameAnalysisFieldLocator("Analysis Name:"), analysisName, "Analysis Name Field");
        pause("medium");
        click(frameDivTextLocator(color), " Chart Color", "color");
        waitForDisappearance("(//*[contains(text(),'Loading...')])[3]", "Loading...");
        pause("medium");
        click(frameSpanTextLocator("Full Data"), "Full Data Tab", "Section Tab");
        waitForDisappearance("(//*[contains(text(),'Loading...')])[3]", "Loading...");
        pause("medium");
        String verifyExactText = getInputText(frameAnalysisFieldLocator("Analysis Name:"));
        Assert.assertEquals(verifyExactText, analysisName);
        reportPass("User successfully reviewed the Analysis");
        return this;
    }

    public NewAnalysisPage plotDetailsRingChart(String mainField, String singleSeriesValueField, String numOfItems) {
        clickAndChoose(frameAnalysisFieldLocator("Main Field:", 2), frameDivTextLocator(mainField, 2), mainField, "Main Field");
        pause("low");
        clickAndChoose(frameAnalysisFieldLocator("Single Series Value Field:", 2), frameSeriesValueField(singleSeriesValueField), singleSeriesValueField, "Single Series Value Field");
        pause("low");
        typeWithType(frameAnalysisFieldLocator("Define the maximum number of items appearing in this chart:", 2), numOfItems, "number of items appearing in this chart");
        reportPass("User successfully entered the plot details.");
        return this;
    }

    public NewAnalysisPage plotDetailsBarChart(String mainField, String multipleSeriesValueField1, String multipleSeriesValueField2) {
        clickAndChoose(frameAnalysisFieldLocator("Main Field:", 2), frameDivTextLocator(mainField, 2), mainField, "Main Field");
        pause("low");
        clickAndChoose(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), frameSeriesValueField(multipleSeriesValueField1), multipleSeriesValueField1, "Multiple Series Value Field 1");
        pause("low");
//        clickAndChoose(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), frameDivTextLocator(multipleSeriesValueField1,2), multipleSeriesValueField2, "Multiple Series Value Field 2");
        reportPass("User successfully entered the plot details.");
        return this;
    }

    public NewAnalysisPage plotDetailsPivotTable(String multipleSeriesRowField, String multipleSeriesValueField, String multipleSeriesColumnField, String multipleSeriesFilterField) {
        clickAndChoose(frameAnalysisFieldLocator("Multiple Series Row Fields:", 1), frameDivTextLocator(multipleSeriesRowField, 2), multipleSeriesRowField, "Multiple Series Row Fields");
        pause("low");
        clickAndChoose(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), frameSeriesValueField(multipleSeriesValueField), multipleSeriesValueField, "Multiple Series Value Field");
        pause("low");

        clickAndChoose(frameAnalysisFieldLocator("Multiple Series column Fields:", 1), frameDivTextLocator(multipleSeriesColumnField, 3), multipleSeriesColumnField, "Multiple Series column Fields");
        pause("low");
//        clickAndChoose(frameAnalysisFieldLocator("Multiple Series Filter Fields:", 1), frameDivTextLocator(multipleSeriesFilterField,3), multipleSeriesFilterField, "Multiple Series Filter Fields");
        pause("low");
        reportPass("User successfully entered the plot details.");
        return this;
    }

    public NewAnalysisPage plotDetailsScatterPlot(String mainField, String singleSeriesValueField) {
        clickAndChoose(frameAnalysisFieldLocator("Main Field:", 2), frameDivTextLocator(mainField, 2), mainField, "Main Field");
        pause("low");
        clickAndChoose(frameAnalysisFieldLocator("Single Series Value Field:", 2), frameSeriesValueField(singleSeriesValueField), singleSeriesValueField, "Single Series Value Field");
        pause("low");
        reportPass("User successfully entered the plot details.");
        return this;
    }

    public NewAnalysisPage plotDetailsLineChart(String mainField, String multipleSeriesValueField1, String multipleSeriesValueField2) {
        clickAndChoose(frameAnalysisFieldLocator("Main Field:", 2), frameDivTextLocator(mainField, 2), mainField, "Main Field");
        pause("low");
        clickAndChoose(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), frameSeriesValueField(multipleSeriesValueField1), multipleSeriesValueField1, "Multiple Series Value Field 1");
        pause("low");
        reportPass("User successfully entered the plot details.");
        return this;
    }

    public NewAnalysisPage plotDetailsSummaryTable(String mainField, String multipleSeriesValueField1, String multipleSeriesValueField2) {
        clickAndChoose(frameAnalysisFieldLocator("Main Field:", 2), frameDivTextLocator(mainField, 2), mainField, "Main Field");
        pause("low");
        clickAndChoose(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), frameSeriesValueField(multipleSeriesValueField1), multipleSeriesValueField1, "Multiple Series Value Field 1");
        pause("low");
        reportPass("User successfully entered the plot details.");
        return this;
    }


}
