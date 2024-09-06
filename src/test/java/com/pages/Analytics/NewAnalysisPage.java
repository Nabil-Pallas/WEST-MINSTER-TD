package com.pages.Analytics;

import org.testng.Assert;

public class NewAnalysisPage extends AnalyticsPage {

    private String newAnalysisText = "//h1[text()='New Analysis']";
    private String viewAnalysisText = "//h1[text()='View Analysis']";
    private String brokerNameFirstGrid = "(//td[@class='dx-required-field-heading'])[1]";
    private String rowFields = "(//table[@class='dx-datagrid-table dx-datagrid-table-fixed'])[4]/tbody/tr[last()-1]";
    private String pivotGrandTotalPath = "(//table[contains(@class,'dx-pivotgrid-border')]//tr[@class='dx-bottom-row']//td[2]//tr[last()]//td[last()])[2]";
    private String pivotGrandTotalInReviewPage = "";
    private String pivotGrandTotalInViewPage = "";
    private String innertext = "";


    public NewAnalysisPage validateNewAnalysisPage() {
        waitForElementToLoad(newAnalysisText);
        if (isVisible(newAnalysisText, "New Analysis Text")) {
            reportPass("User successfully reached the correct New Analysis page");
        } else {
            reportFailAndThrowException("New Analysis Page is not loaded.");
        }
        pause("low");
        return this;
    }

    public NewAnalysisPage validateViewAnalysisPage() {
        waitForElementToLoad(viewAnalysisText);
        if (isVisible(viewAnalysisText, "View Analysis Text")) {
            reportPass("User successfully reached the correct View Analysis page");
        } else {
            reportFailAndThrowException("View Analysis Page is not loaded.");
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

    public NewAnalysisPage typeFilterValue(String value, int index) {
        pause("low");
        typeAndEnter(frameFilterGroupLocator("Value", index), value, "Value");
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
        waitForLoaderToDisappear();
        click(frameSpanTextLocator("Next"), "Next");
        return this;
    }

    public NewAnalysisPage clickOnSaveBtn() {
        click(frameSpanTextLocator("Save"), "Save");
        pause("low");
        return this;
    }

    public AnalyticsPage clickOnBackBtn() {
        pause("low");
        waitForElementToLoad(frameSpanTextLocator("Back"));
        click(frameSpanTextLocator("Back"), "Back");
        pause("low");
        return new AnalyticsPage();
    }

    public NewAnalysisPage clickOnFilter() {

        pause("low");
        click("//span[text()='+ Filter']", "Filter");
        return new NewAnalysisPage();
    }

    public NewAnalysisPage clickOnFilterSave() {

        pause("low");
        click("//a[@title='Save']", "Save");
        return new NewAnalysisPage();
    }


    public NewAnalysisPage addFilterAndValidate(String fieldName, String operator) {
        pause("medium");
        clickOnCreateAFilter()
                .typeFilterFieldName(fieldName) //Broker Name
                .typeFilterOperator(operator) //Symbol[=,!=,>,<,>=,<=]
                .typeFilterValue()
                .validateDataFilter();
        reportPass("User Successfully Added the Filter & validated");
        return this;
    }

    public NewAnalysisPage addFilterAndValidate(String fieldName, String operator, String value, int index) {
        switch (xmlUserLevel) {
            case "Tier3EndUser":
                pause("medium");
                waitForLoaderToDisappear();
                int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");
                for (int i = 1; i < headerCount; i++) {
                    String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
                    String headerText = getInnerText(headerElement);
                    if (headerText.equalsIgnoreCase("Local Executed Value")) {
                        click("(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]", "Field sort");
                        pause("low");
                        click("(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]", "Field sort");
                        pause("low");
                        innertext = getInnerText("((//table)[4]//tr//td[" + (i + 1) + "])[1]");
                        pause("low");
                    }
                }
                clickOnCreateAFilter()
                        .typeFilterFieldName(fieldName) //Broker Name
                        .typeFilterOperator(operator) //Symbol[=,!=,>,<,>=,<=]
                        .typeFilterValue(innertext, index);
                reportPass("User Successfully Added the Filter");
                break;
            default:
                pause("medium");
                clickOnCreateAFilter()
                        .typeFilterFieldName(fieldName) //Broker Name
                        .typeFilterOperator(operator) //Symbol[=,!=,>,<,>=,<=]
                        .typeFilterValue(value, index);
                reportPass("User Successfully Added the Filter");
                break;
        }

        return this;
    }

    public NewAnalysisPage selectVisualizationType(String visualizationType) {
        pause("low");
        click(frameVisualizationTypeLocator(visualizationType), visualizationType, "visualization Type :" + visualizationType);
        reportPass("User successfully Selected Visualization Type");
        return this;
    }

    public NewAnalysisPage reviewYourAnalysis(String analysisName, String color) {
        pause("low");
        typeWithType(frameAnalysisFieldLocator("Analysis Name:"), analysisName, "Analysis Name Field");
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
        pause("low");
        clickAndChoose(frameAnalysisFieldLocator("Main Field:", 2), frameDivTextLocator(mainField, 2), mainField, "Main Field");
        pause("low");
        clickAndChoose(frameAnalysisFieldLocator("Single Series Value Field:", 2), frameSeriesValueField(singleSeriesValueField), singleSeriesValueField, "Single Series Value Field");
        pause("low");
        typeWithType(frameAnalysisFieldLocator("Define the maximum number of items appearing in this chart:", 2), numOfItems, "number of items appearing in this chart");
//        clickOnNextBtn();
//        if(isVisible("//div[text()='Invalid Field'")){
//            clickAndChoose(frameAnalysisFieldLocator("Single Series Value Field:", 2), frameSeriesValueField(singleSeriesValueField), singleSeriesValueField, "Single Series Value Field");
//            pause("low");
//            clickOnNextBtn();
//        }else{
//            reportStep("Selected option for column \"Single Series Value Field\"","info");
//        }
        reportPass("User successfully entered the plot details.");
        return this;
    }

    public NewAnalysisPage plotDetailsBarChart(String mainField, String multipleSeriesValueField1, String multipleSeriesValueField2) {
        pause("low");
        clickAndChoose(frameAnalysisFieldLocator("Main Field:", 2), frameDivTextLocator(mainField, 2), mainField, "Main Field");
//        clickAndChoose(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), frameSeriesValueField(multipleSeriesValueField1), multipleSeriesValueField1, "Multiple Series Value Field 1");
        waitForElementToLoad(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1));
        click(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), "Multiple Series Value Fields");
        pause("low");
        clickUsingJs(frameSeriesValueField(multipleSeriesValueField1), multipleSeriesValueField1, "Drop Down");
//        selectOptionFromDropdownUsingJSClick(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), "//div[@class='wdx-custom-dropdown__text']", multipleSeriesValueField1, "Drop Down");
        pause("low");
//        clickOnNextBtn();
//        if(isVisible("//div[text()='Invalid Field'")){
//            clickAndChoose(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), frameSeriesValueField(multipleSeriesValueField1), multipleSeriesValueField1, "Multiple Series Value Field 1");
//            pause("low");
//            clickOnNextBtn();
//        }else{
//            reportStep("Selected option for column \"Multiple Series Value Fields:\"","info");
//        }
//        clickAndChoose(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), frameDivTextLocator(multipleSeriesValueField1,2), multipleSeriesValueField2, "Multiple Series Value Field 2");
        reportPass("User successfully entered the plot details.");
        return this;
    }

    public NewAnalysisPage plotDetailsPivotTable(String multipleSeriesRowField, String multipleSeriesValueField, String multipleSeriesColumnField, String multipleSeriesFilterField) {
        pause("low");
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
        pause("low");
        clickAndChoose(frameAnalysisFieldLocator("Main Field:", 2), frameDivTextLocator(mainField, 2), mainField, "Main Field");
        pause("low");
//        clickAndChoose(frameAnalysisFieldLocator("Single Series Value Field:", 2), frameSeriesValueField(singleSeriesValueField), singleSeriesValueField, "Single Series Value Field");
        click(frameAnalysisFieldLocator("Single Series Value Field:", 2), "Single Series Value Field:");
        clickUsingJs(frameSeriesValueField(singleSeriesValueField), singleSeriesValueField, "Single Series Value Field");
        pause("low");
//        clickOnNextBtn();
//        if(isVisible("//div[text()='Invalid Field'")){
//            clickAndChoose(frameAnalysisFieldLocator("Single Series Value Field:", 2), frameSeriesValueField(singleSeriesValueField), singleSeriesValueField, "Single Series Value Field");
//            pause("low");
//            clickOnNextBtn();
//        }else{
//            reportStep("Selected option for column \"Single Series Value Field\"","info");
//        }
        reportPass("User successfully entered the plot details.");
        return this;
    }

    public NewAnalysisPage plotDetailsLineChart(String mainField, String multipleSeriesValueField1, String multipleSeriesValueField2) {
        pause("low");
        clickAndChoose(frameAnalysisFieldLocator("Main Field:", 2), frameDivTextLocator(mainField, 2), mainField, "Main Field");
        waitForElementToLoad(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1));
        click(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), "Multiple Series Value Fields");
        pause("low");
        clickUsingJs(frameSeriesValueField(multipleSeriesValueField1), multipleSeriesValueField1, "Drop Down");
//        clickAndChoose(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), frameSeriesValueField(multipleSeriesValueField1), multipleSeriesValueField1, "Multiple Series Value Field 1");
        pause("low");
//        selectOptionFromDropdownUsingJSClick(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), "//div[@class='wdx-custom-dropdown__text']", multipleSeriesValueField1, "Drop Down");

//        clickOnNextBtn();
//        if(isVisible("//div[text()='Invalid Field'")){
//            clickAndChoose(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), frameSeriesValueField(multipleSeriesValueField1), multipleSeriesValueField1, "Multiple Series Value Field 1");
//            pause("low");
//            clickOnNextBtn();
//        }else{
//            reportStep("Selected option for column \"Multiple Series Value Fields:\"","info");
//        }
        reportPass("User successfully entered the plot details.");
        return this;
    }

    public NewAnalysisPage plotDetailsSummaryTable(String mainField, String multipleSeriesValueField1, String multipleSeriesValueField2) {
        pause("low");
        clickAndChoose(frameAnalysisFieldLocator("Main Field:", 2), frameDivTextLocator(mainField, 2), mainField, "Main Field");
        pause("low");
        waitForElementToLoad(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1));
        click(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), "Multiple Series Value Fields");
        pause("low");
        clickUsingJs(frameSeriesValueField(multipleSeriesValueField1), multipleSeriesValueField1, "Drop Down");
//        selectOptionFromDropdownUsingJSClick(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), "//div[@class='wdx-custom-dropdown__text']", multipleSeriesValueField1, "Drop Down");
//        clickAndChoose(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), frameSeriesValueField(multipleSeriesValueField1), multipleSeriesValueField1, "Multiple Series Value Field 1");
        pause("low");
//        clickOnNextBtn();
//        if(isVisible("//div[text()='Invalid Field'")){
//            clickAndChoose(frameAnalysisFieldLocator("Multiple Series Value Fields:", 1), frameSeriesValueField(multipleSeriesValueField1), multipleSeriesValueField1, "Multiple Series Value Field 1");
//            pause("low");
//            clickOnNextBtn();
//        }else{
//            reportStep("Selected option for column \"Single Series Value Field\"","info");
//        }

        reportPass("User successfully entered the plot details.");
        return this;
    }


    public NewAnalysisPage getGrandTotalInReviwAnalysis() {
        pause("low");
        mouseOver("(//td[@class='dx-area-data-cell']//tr[1]//td[1])[2]", "Full data");
        pause("low");
        while (!isVisibleWithMinimumTime("(//span[text()='Grand Total'])[4]")) {
            scrollUsingMouseWheelVertically("//td[@class='dx-area-data-cell']//div[contains(@class,'dx-scrollbar-vertical')]//div[@class='dx-scrollable-scroll-content']");
        }
        String innertext = getInnerText("(//table[contains(@class,'dx-pivotgrid-border')]//tr[@class='dx-bottom-row']//td[1]//tr[last()])[2]");
        System.out.println(innertext);
        if (innertext.equals("Grand Total")) {
            pivotGrandTotalInReviewPage = getInnerText(pivotGrandTotalPath);
            reportPass("Grand Total in Review Analysis page: " + pivotGrandTotalInReviewPage);
        }


        return this;
    }

    public NewAnalysisPage getGrandTotalInViewAnalysis() {

        pause("medium");
        pause("low");
        mouseOver("(//td[@class='dx-area-data-cell']//tr[1]//td[1])[2]", "Full data");
        pause("low");
        while (!isVisibleWithMinimumTime("(//span[text()='Grand Total'])[4]")) {
            scrollUsingMouseWheelVertically("//td[@class='dx-area-data-cell']//div[contains(@class,'dx-scrollbar-vertical')]//div[@class='dx-scrollable-scroll-content']");
        }
        String innertext = getInnerText("(//table[contains(@class,'dx-pivotgrid-border')]//tr[@class='dx-bottom-row']//td[1]//tr[last()])[2]");
        System.out.println(innertext);
        if (innertext.equals("Grand Total")) {
            pivotGrandTotalInViewPage = getInnerText(pivotGrandTotalPath);
            reportPass("Grand Total in View Analysis page: " + pivotGrandTotalInViewPage);
        }

        return this;
    }

    public NewAnalysisPage validateGrandTotal() {
        if (pivotGrandTotalInReviewPage.equals(pivotGrandTotalInViewPage)) {
            reportPass("Successfully validated Grand Total in both Review and View Analysis Page");
        } else {
            reportFail("Failed to validate Grand Total");
        }

        return this;
    }

    public NewAnalysisPage validateFilteredDataInStep1(String verificationHeader, String value) {
        switch (xmlUserLevel) {
            case "Tier3EndUser":
                pause("low");
                int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");
                for (int i = 1; i < headerCount; i++) {
                    String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
                    String headerText = getInnerText(headerElement);
                    if (headerText.equalsIgnoreCase(verificationHeader)) {
                        scrollToElement("((//table)[4]//tr//td[" + (i + 1) + "])[1]");
                        pause("low");
                        String valuee = getInnerText("((//table)[4]//tr//td[" + (i + 1) + "])[1]");
                        double number1 = Double.parseDouble(innertext);      // Convert str1 to int
                        double number2 = Double.parseDouble(valuee);
                        System.out.println("number1====> " + number1 + " number2=====> " + number2);
                        // Compare the converted numbers
                        if (number1 == number2) {
                            reportPass("Successfully verified the Numeric value in filter: " + number2);
                        } else {
                            reportFail("Failed to validate");
                        }

                    }
                }
                break;
            default:
                pause("low");
                int headCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");
                for (int i = 1; i < headCount; i++) {
                    String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
                    String headerText = getInnerText(headerElement);
                    if (headerText.equalsIgnoreCase(verificationHeader)) {
                        scrollToElement("((//table)[4]//tr//td[" + (i + 1) + "])[1]");
                        pause("low");
                        String valuee = getInnerText("((//table)[4]//tr//td[" + (i + 1) + "])[1]");
                        System.out.println(valuee);
                        int number1 = Integer.parseInt(value);      // Convert str1 to int
                        double number2 = Double.parseDouble(valuee);
                        System.out.println("number1====> " + number1 + " number2=====> " + number2);
                        // Compare the converted numbers
                        if (number1 == number2) {
                            reportPass("Successfully verified the Numeric value in filter: " + number2);
                        } else {
                            reportFail("Failed to validate");
                        }

                    }
                }
                break;
        }

        return this;
    }


    public NewAnalysisPage checkCompareDDIsPresentOrNot() {

        pause("medium");
        if (isVisible(frameSpanTextLocator("Compare"), "Compare")) {
            click("//span[text()='Compare']/following::div[@aria-label='Select'][1]", "Compare", "Drop Down");

            if (isVisible("//span[text()='Compare']/following::div[@aria-label='Select'][1]", "Comparison Dropdown")) {
                reportPass("Successfully validated that the Comparison Drop Down is present in View Analysis Page.");
            } else {
                reportFail("The Comparison Drop Down is not present after clicking.");
            }
        } else {
            reportFail("The 'Compare' element is not visible on the page.");
        }
        return this;
    }

    public NewAnalysisPage clickOnFavouriteIcon() {
        pause("low");
        click("//button[@id='analysis-quickaction-fav']//i", "Favorite Icon");
        return this;
    }


}
