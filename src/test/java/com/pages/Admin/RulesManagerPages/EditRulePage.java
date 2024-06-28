package com.pages.Admin.RulesManagerPages;

import com.page.base.DashboardPage;
import com.pages.Report.CreateNewReportPage;
import org.testng.Assert;

public class EditRulePage extends RulesManagerPage {
    private String editRuleText = "//h1[text()='Edit Rule']";
    private String columnApplyBtn = "(//span[text()='Apply'])[2]";
    private String formulaTextArea = "//textarea[@class='dx-texteditor-input' and @name='OutputColumn']";

    public EditRulePage validateEditRulePage() {
        waitForElementToLoad(editRuleText);
        if (isVisible(editRuleText, "Add Edit Rule Text")) {
            reportPass("User successfully reached the correct Add New Rule page");
        } else {
            reportFailAndThrowException("Add Edit Page is not loaded.");
        }
        return this;
    }

    public EditRulePage typeRuleName(String ruleName) {
        typeAndEnter(frameRuleNameLocator("Rule Name:"), ruleName, "ruleName");
        return this;
    }

    public EditRulePage typeRuleDescription(String ruleDescription) {
        type(frameRuleDescriptionLocator("Rule Description:"), ruleDescription, "ruleDescription");
        return this;
    }

    public EditRulePage validate(String locator, String text) {
        boolean verifyExactText = verifyExactText(locator, text);
        Assert.assertTrue(verifyExactText);
        return this;
    }

    //Edit Rule Step 1
    public EditRulePage editRuleAndDescription(String ruleName, String ruleDescription) {
        typeRuleName(ruleName);
        typeRuleDescription(ruleDescription);
        clickOnNextBtn();
        reportPass("User successfully edited the rule & description and proceeded to the next step.");
        return this;
    }

    public EditRulePage addNewColumn() {
        click(frameSpanTextLocator("Add new column"), "Add New Column");
        return this;
    }

    public EditRulePage validateAddNewCalculatedColumnPage() {
        waitForElementToLoad(frameDivTextLocator("Add New calculated Column"));
        if (isVisible(frameDivTextLocator("Add New calculated Column"), "Add New Calculated Column Text")) {
            reportPass("User reaches to the right Add New Calculated Column page");
        } else {
            reportFailAndThrowException("Add New Calculated Column Page is not loaded.");
        }
        return this;
    }

    public EditRulePage typeFieldName(String fieldName) {
        typeAndSpace(frameAddNewcalculatedColumnLocator("Field Name here…", 1), fieldName, "Field Name");
        pause("low");
        randomDoubleClick();
        randomDoubleClick();
        pause("medium");
        return this;
    }


    //DEFINE THE NEW COLUMN
    public EditRulePage editDefineTheNewColumn(boolean calculatedColumn, String fieldName) {
        if (calculatedColumn) {
            addNewColumn()
                    .validateAddNewCalculatedColumnPage() //Validate the New Calculated Column
                    .typeFieldName(fieldName)  //Fill the Filed Name here
                    .clickOnApplyBtn()  //Click On Apply button
                    .clickOnNextBtn();  //ClickOn Next Button
            reportPass("User successfully edited the new column definition and proceeded to the next step.");
        } else {
            clickOnNextBtn();
        }
        return this;
    }

    //EDIT FILTER(S)
    public EditRulePage editAddFilters() {
        pause("low");
        clickOnNextBtn();
        return this;
    }

    public EditRulePage clearFormulaField() {
        clear(formulaTextArea);
        return this;
    }


    public EditRulePage selectCalculationColumn(String calculationValue) {
        clickAndChoose(frameCalculationLocator("Choose Calculation Column"), frameSpanTextLocator(calculationValue), calculationValue, "Choose Calculation Column");
        pause("low");
        return this;
    }

    public EditRulePage selectFuction(String operator) {
        clickAndChoose(frameCalculationLocator("Choose Function"), frameDivTextLocator(operator), operator, "Select Function");
        pause("low");
        return this;
    }

    public EditRulePage clickOnSaveBtn() {
        click(frameSpanTextLocator("Save"), "Save");
        return this;
    }

    //DEFINE THE CALCULATION
    public EditRulePage editDefineTheCaluculation(String calculationValue1, String operator, String calculationValue2) {
        pause("medium");
        clearFormulaField()
                .selectCalculationColumn(calculationValue1)
                .selectFuction(operator)   //Symbol[+,-,*,/]
                .selectCalculationColumn(calculationValue2)
                .clickOnSaveBtn();
        pause("low");
        reportPass("User successfully edited the define The Calculation and clicked save Button.");
        return this;
    }

    public DashboardPage validateDefineTheCaluculation(String formulaText) {
        pause("medium");
        String getFormula = getInnerText(formulaTextArea);
        System.out.println("Formula : " + getFormula);
        validate(formulaTextArea, formulaText);
        return new DashboardPage();
    }

    public EditRulePage clickOnNextBtn() {
        pause("low");
        click(frameSpanTextLocator("Next"), "Next Button");
        return this;
    }

    public EditRulePage clickOnApplyBtn() {
        click(columnApplyBtn, "Apply Button");
        pause("medium");
        return this;
    }


}
