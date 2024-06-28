package com.pages.Admin.RulesManagerPages;

import com.pages.Admin.UsersAndSecurityPages.AddNewClientPage;
import com.pages.Admin.UsersAndSecurityPages.UserSecurityPage;
import org.testng.Assert;

public class AddNewRulePage extends RulesManagerPage {

    private String addNewRuleText = "//h1[text()='Add Rule']";
    private String applyFilterBtn = "(//span[text()='Apply'])[1]";
    private String filterGroup = "(//input[@class='dx-texteditor-input'])[7]";
    private String operatorField = "(//input[@class='dx-texteditor-input'])[9]";
    private String valueField = "(//input[@class='dx-texteditor-input'])[10]";
    private String rowFields = "(//table[@class='dx-datagrid-table dx-datagrid-table-fixed'])[4]/tbody/tr[last()-1]";
    private String brokerNameText = "(//td[@class='dx-required-field-heading'])[1]";


    public AddNewRulePage validateAddRulePage() {
        waitForElementToLoad(addNewRuleText);
        if (isVisible(addNewRuleText, "Add New Rule Text")) {
            reportPass("User successfully reached the correct Add New Rule page");
        } else {
            reportFailAndThrowException("Add New Rule Page is not loaded.");
        }
        return this;
    }

    public AddNewRulePage typeRuleName(String ruleName) {
        typeAndEnter(frameRuleNameLocator("Rule Name:"), ruleName, "ruleName");
        return this;
    }

    public AddNewRulePage typeRuleDescription(String ruleDescription) {
        type(frameRuleDescriptionLocator("Rule Description:"), ruleDescription, "ruleDescription");
        return this;
    }

    //Create New Rule Step 1
    public AddNewRulePage createANewRule(String ruleName, String ruleDescription) {
        typeRuleName(ruleName)
                .typeRuleDescription(ruleDescription)
//                .validateTickMark("Rule Name:")
                .clickOnNextBtn();
        reportPass("User successfully created the new rule and proceeded to the next step.");
        return this;
    }

    public AddNewRulePage clickOnCalculatedColumn() {
        click(frameCalculatedColumnLocator("Calculated Column:", 2), "Calculated Column");
        return this;
    }

    public AddNewRulePage selectCalculateColumnValue(String columnValue) {
        click(frameCalculatedColumnDropDownLocator(columnValue), "Calculated Column", "Select Type");
        return this;
    }

    public AddNewRulePage addNewColumn() {
        click(frameSpanTextLocator("Add new column"), "Add New Column");
        return this;
    }

    public AddNewRulePage validateAddNewCalculatedColumnPage() {
        waitForElementToLoad(frameDivTextLocator("Add New calculated Column"));
        if (isVisible(frameDivTextLocator("Add New calculated Column"), "Add New Calculated Column Text")) {
            reportPass("User reaches to the right Add New Calculated Column page");
        } else {
            reportFailAndThrowException("Add New Calculated Column Page is not loaded.");
        }
        return this;
    }

    public AddNewRulePage typeFieldName(String fieldName) {
        typeAndSpace(frameAddNewcalculatedColumnLocator("Field Name here…", 1), fieldName, "Field Name");
        pause("low");
        randomDoubleClick();
        pause("medium");
        return this;
    }

    //DEFINE THE NEW COLUMN
    public AddNewRulePage defineTheNewColumn(boolean calculatedColumn, String fieldName, String columnValue) {
        if (calculatedColumn) {
            addNewColumn()
                    .validateAddNewCalculatedColumnPage() //Validate the New Calculated Column
                    .typeFieldName(fieldName)  //Fill the Filed Name here
                    .clickOnApplyBtn()  //Click On Apply button
                    .clickOnNextBtn();  //ClickOn Next Button
            reportPass("User successfully created the Define The New Column and proceeded to the next step.");
        } else {
            clickOnCalculatedColumn()
                    .selectCalculateColumnValue(columnValue)
                    .clickOnNextBtn();
        }
        return this;
    }


    public AddNewRulePage clickOnCreateAFilter() {
        pause("low");
        click(frameSpanTextLocator("Create A Filter"), "Create a Filter");
        return this;
    }

    public AddNewRulePage typeFilterFieldName(String field) {
        pause("medium");
        pause("low");
        typeAndEnter(frameFilterGroupLocator("Field", 1), field, "Field");
        return this;
    }

    public AddNewRulePage typeFilterOperator(String operator) {
        pause("medium");
        typeAndEnter(frameFilterGroupLocator("Operator", 3), operator, "Operator");
        return this;
    }

    public AddNewRulePage typeFilterValue() {
        String getBrokerName = getInnerText(brokerNameText);
        pause("low");
        typeWithType(frameFilterGroupLocator("Value", 4), getBrokerName, "Value");
        click(applyFilterBtn, "Apply");
        pause("medium");
        return this;
    }

    public AddNewRulePage validateDataFilter() {
        int tableRowCount = getLocatorsCount(rowFields);
        Assert.assertEquals(tableRowCount, 1);
        return this;
    }

    //EDIT FILTER(S)
    public AddNewRulePage addFilters(String fieldName, String operator) {
//        clickOnCreateAFilter()
//                .typeFilterFieldName(fieldName) //Broker Name
//                .typeFilterOperator(operator) //Symbol[=,!=,>,<,>=,<=]
//                .typeFilterValue()
//                .validateDataFilter()
        pause("low");
        clickOnNextBtn();
        return this;
    }

    public AddNewRulePage selectCalculationColumn(String calculationValue) {
        pause("medium");
        clickAndChoose(frameCalculationLocator("Choose Calculation Column"), frameSpanTextLocator(calculationValue), calculationValue, "Choose Calculation Column");
        pause("low");
        return this;
    }

    public AddNewRulePage selectFuction(String operator) {
        clickAndChoose(frameCalculationLocator("Choose Function"), frameDivTextLocator(operator), operator, "Select Function");
        pause("low");
        return this;
    }

    public AddNewRulePage clickOnSaveBtn() {
        click(frameSpanTextLocator("Save"), "Save");
        return this;
    }

    //DEFINE THE CALCULATION
    public AddNewRulePage defineTheCalculation(String calculationValue1, String operator, String calculationValue2) {
        selectCalculationColumn(calculationValue1)
                .selectFuction(operator)   //Symbol[+,-,*,/]
                .selectCalculationColumn(calculationValue2)
//                .validateTickMark("Formula")
                .clickOnSaveBtn();
        pause("low");
        reportPass("User successfully created the define The Calculation and clicked save Button.");
        return this;
    }

    public AddNewRulePage clickOnNextBtn() {
        click(frameSpanTextLocator("Next"), "Next Button");
        return this;
    }

    public AddNewRulePage clickOnApplyBtn() {
        waitForAppearance(frameSpanTextLocator("Apply"));
        click(frameSpanTextLocator("Apply"), "Apply Button");
        pause("medium");
        return this;
    }

}
