package com.pages.Bugeting.createNewBudgetPages;

import org.testng.Assert;

import com.pages.Bugeting.AllBudgetsPage;

public class CreateNewBudgetPage extends AllBudgetsPage {

    String viewBudgetText = "//h1[text()='View Budget']";

    public CreateNewBudgetPage validateViewBudget() {
        waitForElementToLoad(viewBudgetText);
        if (isVisible(viewBudgetText, "View Budget Text")) {
            reportPass("User successfully reached the correct View Budget page");
        } else {
            reportFailAndThrowException("View Budget Page is not loaded.");
        }
        return this;
    }

    public AllBudgetsPage validateCreatedBudget(String budgetName, String beginDate, String endDate, String targetColumn) {
        waitForLoaderToDisappear();
        String budgetNameElement = "//h2[text()='" + budgetName + "']";
        String beginDateElement = "//span[text()='Start Date:']/following-sibling::span/time";
        String endDateElement = "//span[text()='End Date:']/following-sibling::span/time";
        String targetColumnElement = frameSpanTextLocator(targetColumn);

        boolean verifyExactBudgetNameText = verifyExactText(budgetNameElement, budgetName);
        boolean verifyExactBeginDateText = verifyExactText(beginDateElement, beginDate);
        boolean verifyExactEndDateText = verifyExactText(endDateElement, endDate);
        boolean verifyExactTargetColumnText = verifyExactText(targetColumnElement, targetColumn);

        Assert.assertTrue(verifyExactBudgetNameText);
        Assert.assertTrue(verifyExactBeginDateText);
        Assert.assertTrue(verifyExactEndDateText);
        Assert.assertTrue(verifyExactTargetColumnText);


        return new AllBudgetsPage();
    }

    public CreateNewBudgetPage typeBudgetName(String brokerName) {
        typeAndSpace(frameClientDetailsLocator("Budget Name"), brokerName, "BudgetName");
        pause("medium");
        return this;
    }

    public CreateNewBudgetPage typeBeginDate(String tradeDate) {
        pause("medium");
        type(frameClientDetailsLocator("Begin Date", 2), tradeDate, "BeginDate");
        pause("medium");
        return this;
    }

    public CreateNewBudgetPage typeEndDate(String tradeDate) {

        type(frameClientDetailsLocator("End Date", 2), tradeDate, "EndDate");
        pause("medium");
        return this;
    }

    public CreateNewBudgetPage clickAndSelectTargetColumn(String targetColumn) {
        try {
//            clickAndChoose("(//div[@class='dx-dropdowneditor-input-wrapper dx-selectbox-container'])[3]", frameDivTextLocator(targetColumn, 2), targetColumn, targetColumn);
            click("//span[text()='Target Column']//ancestor::label//following::div[5]//input","drop down");
            pause("low");
            click(frameDivTextLocator(targetColumn, 2),"option");
        } catch (Exception e) {
//            clickAndChoose("(//div[@class='dx-dropdowneditor-input-wrapper dx-selectbox-container'])[3]", frameDivTextLocator(targetColumn), targetColumn, targetColumn);
            click("//span[text()='Target Column']//ancestor::label//following::div[5]//input","drop down");
            pause("low");
            click(frameDivTextLocator(targetColumn),"option");
        }

        pause("medium");
        return this;
    }

    public CreateNewBudgetPage selectBudgetBy(String budgetBy) {

        click(selectBudgetByCheckBox(budgetBy, 1), "Budget By");
        pause("medium");
        return new CreateNewBudgetPage();
    }

    public CreateNewBudgetPage selectBrokerNameSection(String budgetBy, String brokerCode, String targetAmount) {
        selectBrokerName();
        typeTargetAmount(targetAmount);
        pause("low");
        if (budgetBy.equalsIgnoreCase("Broker Code")) {
            selectBrokerCode(brokerCode);
        }
        return new CreateNewBudgetPage();
    }

    public CreateNewBudgetPage selectBrokerName() {
        pause("medium");
        clickAndChoose("(//div[text()='Target Amount']/ancestor::div[2]/following::div[1]//input[@role='combobox'])[1]", "(//div[@class='dx-scrollview-content']/div[1])[2]", "brokerName", "Broker Name");
        pause("medium");
        return this;
    }

    public CreateNewBudgetPage selectBrokerCode(String brokerCode) {
        clickAndChoose("(//div[text()='Target Amount']/ancestor::div[2]/following::div[1]//input[@role='combobox'])[2]", frameDivTextLocator(brokerCode), brokerCode, "Broker Code");
        pause("medium");
        return this;
    }

    public CreateNewBudgetPage typeTargetAmount(String targetAmount) {

        type("//div[text()='Target Amount']/ancestor::div[2]/following::div[1]//input[@role='spinbutton']", targetAmount, "Target Amount");
        return this;
    }

    public CreateNewBudgetPage clickSaveItemBtn() {
        pause("medium");
        click(frameATagTextLocator("Save Item"), "Save Item", "Save Item");
//        click(frameATagTextLocator("Save Item"), "Save Item");
//        System.out.println("after Clicked");
        pause("medium");

        return this;
    }

    public CreateNewBudgetPage clickSaveBudgetBtn() {
        click(frameSpanTextLocator("Save Budget"), "Save Budget");
        pause("medium");
        reportPass("User successfully clicked the save button.");
        return this;
    }

    public CreateNewBudgetPage addNewBudget(String budgetName, String beginDate, String endDate, String targetColumn, String budgetBy, String brokerCode, String targetAmount) {
        pause("medium");
        clickAndSelectTargetColumn(targetColumn)
                .selectBudgetBy(budgetBy)
                .selectBrokerNameSection(budgetBy, brokerCode, targetAmount)
                .typeBudgetName(budgetName)
                .typeBeginDate(beginDate)
                .typeEndDate(endDate)
                .selectBudgetBy(budgetBy);
        reportPass("User successfully created the New Budget");
        return this;
    }
}
