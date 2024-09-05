package com.pages.Bugeting.editBudgetPages;

import com.pages.Bugeting.AllBudgetsPage;
import com.pages.Bugeting.importBudgetPages.ImportBudgetPage;
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
        pause("medium");
        for (int i = 0; i < verificationValues.size(); i++) {
            switch (verificationHeaders.get(i)) {
                case "Budget Name":
                    typeAndSpace(frameClientDetailsLocator("Budget Name"), verificationValues.get(i), "BudgetName");
//                    pause("medium");
                    pause("low");
                    break;
                case "Begin Date":
                    type(frameClientDetailsLocator("Begin Date", 2), verificationValues.get(i), "BeginDate");
//                    pause("medium");
                    pause("low");
                    break;
                case "End Date":
                    type(frameClientDetailsLocator("End Date", 2), verificationValues.get(i), "End Date");
//                    pause("medium");
                    pause("low");
                    break;
                case "Target Column":
                    pause("low");
                    try {
//                        clickAndChoose("//span[text()='Target Column']//ancestor::label//following::div[5]//input", frameDivTextLocator(verificationValues.get(i), 2), verificationValues.get(i), verificationValues.get(i));
                        selectOptionFromDropdownUsingJSClick("//span[text()='Target Column']//ancestor::label//following::div[5]//input", frameDivTextLocator(verificationValues.get(i), 2), verificationValues.get(i), verificationValues.get(i));
                    } catch (Exception e) {
//                        clickAndChoose("//span[text()='Target Column']//ancestor::label//following::div[5]//input", frameDivTextLocator(verificationValues.get(i)), verificationValues.get(i), verificationValues.get(i));
                        selectOptionFromDropdownUsingJSClick("//span[text()='Target Column']//ancestor::label//following::div[5]//input", "//div[@class='dx-list-items']//div[@class='dx-item-content dx-list-item-content']", verificationValues.get(i), verificationValues.get(i));
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


    public AllBudgetsPage validateEditedBudget(String budgetName, String targetColumn) {
        waitForLoaderToDisappear();
        String budgetNameElement = "//h2[text()='" + budgetName + "']";
        String targetColumnElement = frameSpanTextLocator(targetColumn);

        boolean verifyExactBudgetNameText = verifyExactText(budgetNameElement, budgetName);
        boolean verifyExactTargetColumnText = verifyExactText(targetColumnElement, targetColumn);

        Assert.assertTrue(verifyExactBudgetNameText);
        Assert.assertTrue(verifyExactTargetColumnText);

        return new AllBudgetsPage();
    }


    public EditBudgetPage clickSaveBudgetBtn() {
        click(frameSpanTextLocator("Save Budget"), "Save Budget");
        pause("medium");
        return this;
    }
}
