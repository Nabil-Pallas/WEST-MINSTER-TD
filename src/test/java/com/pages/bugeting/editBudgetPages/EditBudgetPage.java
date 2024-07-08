package com.pages.bugeting.editBudgetPages;

import com.page.base.DashboardPage;
import com.pages.Admin.DataManagerPages.EditDataManagerRow;
import com.pages.bugeting.AllBudgetsPage;
import com.pages.bugeting.createNewBudgetPages.CreateNewBudgetPage;
import org.testng.Assert;

import java.util.List;

public class EditBudgetPage extends AllBudgetsPage {
    String editBudgetText = "//h1[text()='Edit Budget']";

    public EditBudgetPage validateEditBudgetPage() {
        waitForElementToLoad(editBudgetText);
        if (isVisible(editBudgetText, "Edit Budget Text")) {
            reportPass("User successfully reached the correct Edit Budget page");
        } else {
            reportFailAndThrowException("Edit Budget Page is not loaded.");
        }
        return this;
    }

    public EditBudgetPage clickAndSelectTargetColumn(String targetColumn) {
        clickAndChoose("(//div[@class='dx-dropdowneditor-input-wrapper dx-selectbox-container'])[3]", frameDivTextLocator(targetColumn, 2), targetColumn, targetColumn);
        pause("medium");
        return this;
    }

    public EditBudgetPage editInformation(List<String> verificationValues, List<String> verificationHeaders) {
        for (int i = 0; i < verificationValues.size(); i++) {
            switch (verificationHeaders.get(i)) {
                case "Budget Name":
                    typeAndSpace(frameClientDetailsLocator("Budget Name"), verificationValues.get(i), "BudgetName");
                    pause("medium");
                    break;
                case "Begin Date":
                    type(frameClientDetailsLocator("Begin Date", 2), verificationValues.get(i), "BeginDate");
                    pause("medium");
                    break;
                case "End Date":
                    type(frameClientDetailsLocator("End Date", 2), verificationValues.get(i), "End Date");
                    pause("medium");
                    break;
                case "Target Column":
                    pause("low");
                    try {
                        clickAndChoose("(//div[@class='dx-dropdowneditor-input-wrapper dx-selectbox-container'])[3]", frameDivTextLocator(verificationValues.get(i), 2), verificationValues.get(i), verificationValues.get(i));
                    } catch (Exception e) {
                        clickAndChoose("(//div[@class='dx-dropdowneditor-input-wrapper dx-selectbox-container'])[3]", frameDivTextLocator(verificationValues.get(i)), verificationValues.get(i), verificationValues.get(i));
                    }
                    pause("medium");
                    break;
                case "Budget by":
                    click(selectBudgetByCheckBox(verificationValues.get(i), 1), "Budget By");
                    pause("medium");
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected header: " + verificationHeaders.get(i));
            }

        }
        reportPass("User successfully edited the Budget");
        return new EditBudgetPage();

    }

    public AllBudgetsPage validateEditedBudget(String budgetName, String beginDate, String endDate, String targetColumn) {
        waitForLoaderToDisappear();
        String budgetNameElement = "//h2[text()='" + budgetName + "']";
        String beginDateElement = frameSpanTextLocator(beginDate);
        String endDateElement = frameSpanTextLocator(endDate);
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

    public EditBudgetPage clickSaveBudgetBtn() {
        click(frameSpanTextLocator("Save Budget"), "Save Budget");
        pause("medium");
        return this;
    }
}
