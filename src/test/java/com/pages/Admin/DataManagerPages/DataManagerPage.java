package com.pages.Admin.DataManagerPages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.page.base.DashboardPage;
import com.pages.Admin.AdminPage;
import com.pages.Admin.UsersAndSecurityPages.AddNewUserPage;
import com.pages.Admin.UsersAndSecurityPages.UserSecurityPage;
import com.pages.bugeting.AllBudgetsPage;
import org.testng.Assert;

import java.util.*;

public class DataManagerPage extends AdminPage {

    //Locator for Data Manager
    private String dataManagerText = "//h1[text()='Data Manager']";

    //Locator for  Add New Row Button
    private String addNewRowBtn = "//span[text()='Add new row']";

    //Locator for Apply Button
    protected String applyBtnForDataSchema = "//div[@id='apply-new-col-changes-save']//span";

    protected String applyBtn = "//span[text()='Apply']";
    //Locator for Action Button
    private String actionBtn = "(//td[@class='action-edit-delete-column']/a)[1]";

    //Locator for Edit Button
    private String editBtn = "//span[text()='Edit']";


    //Validate Data Manager Page
    public AddNewColumnPage validateDataManagerPage() {
        waitForElementToLoad(dataManagerText);
        if (isVisible(dataManagerText, "DataManagerText")) {
            reportPass("User Navigated to the Data Manager page successfully");
        } else {
            reportFailAndThrowException("Data Manager Page is not loaded.");
        }
        pause("medium");
        return new AddNewColumnPage();
    }

    //Click the Add New Row Button
    public AddNewRowPage clickOnAddNewRowBtn() {
//        waitForLoaderToDisappear();
//        waitForElementToLoad(addNewRowBtn);
        pause("medium");
        pause("medium");
        pause("low");
        click(addNewRowBtn, "Add new row button");
        pause("low");
//
//        waitForAppearance(addNewRowBtn);
        return new AddNewRowPage();
    }

    //Click the Action Button
    public DataManagerPage clickOnActionsBtn() {
//        waitForLoaderToDisappear();
        pause("medium");
        pause("medium");
        click(actionBtn, "Action button");
//        waitForLoaderToDisappear();
        return new DataManagerPage();
    }

    //Click the Action Button
    public AddNewColumnPage clickOnEditDataSchema() {
        click(frameSpanTextLocator("Edit Data Schema"), "Edit Data Schema");
        waitForLoaderToDisappear();
        return new AddNewColumnPage();
    }

    //Click the Edit Button
    public EditDataManagerRow clickOnEditBtn() {
        click(editBtn, "Edit button");
        pause("low");
//        waitForLoaderToDisappear();
//        waitForAppearance(editBtn);
        reportPass("User successfully clicked the Edit button.");
        return new EditDataManagerRow();
    }

    //Validate the Add New Row Information
    public DataManagerPage validateAddedRowInformation(String verificationValue, String verificationHeader) {
//        waitForLoaderToDisappear();
        pause("medium");
        pause("medium");
        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");

        for (int i = 1; i < headerCount; i++) {
            String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
            String headerText = getInnerText(headerElement);
            if (headerText.equalsIgnoreCase(verificationHeader)) {
                click(searchValueWithHeader(i), "Search Filter", "Search Box");
                type(searchValueWithHeader(i), verificationValue, verificationValue);
                String brokerElement = "//td[text()='" + verificationValue + "']";
                boolean verifyExactText = verifyExactText(brokerElement, verificationValue);
                Assert.assertTrue(verifyExactText);
                break;
            }
        }
        return new DataManagerPage();
    }

    public DataManagerPage deleteAddedRow(String verificationValue, String verificationHeader) {
//        waitForLoaderToDisappear();
        pause("medium");
        pause("medium");
        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");

        for (int i = 1; i < headerCount; i++) {
            String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
            String headerText = getInnerText(headerElement);
            if (headerText.equalsIgnoreCase(verificationHeader)) {
                click(searchValueWithHeader(i), "Search Filter", "Search Box");
                type(searchValueWithHeader(i), verificationValue, verificationValue);
                forceClick("(//td[text()='" + verificationValue + "']//following-sibling::td[contains(@class,'action-edit-delete-column')]/a)[1]", "Action", "Button");
                pause("low");
                click(deleteBtn, "Delete Button");
                pause("low");
                click(confirmDeleteBtn, "Confirm Delete");
                pause("low");

                if (getInnerText(frameSpanTextLocator("Success")).equals("Success")) {
                    reportStep("Added Data Schema is deleted successfully", "pass");

                } else {
                    reportStep("Added Data Schema is not deleted", "warning");

                }
                break;
            }
        }
        return new DataManagerPage();
    }

    //Search The Value To Be Edited
    public DataManagerPage searchValueToValidate(String verificationValue, String verificationHeader) {

        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");

        for (int i = 1; i < headerCount; i++) {
            String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
            String headerText = getInnerText(headerElement);
            if (headerText.equalsIgnoreCase(verificationHeader)) {
                click(searchValueWithHeader(i), "Search Filter", "Search Box");
                type(searchValueWithHeader(i), verificationValue, verificationValue);
            }

        }
        return new DataManagerPage();
    }

    //Click The Apply Button
    public DataManagerPage clickOnApplyBtn() {
//        waitForLoaderToDisappear();
        pause("medium//");
        click(applyBtn, "Apply Button");
        pause("medium");
        reportPass("User successfully clicked the Apply button.");
        return new DataManagerPage();
    }

    //Validate The Edited Row Information
    public DataManagerPage validateEditedRowInformation(String verificationValues, String verificationHeaders) {
        waitForLoaderToDisappear();
        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");

        for (int i = 1; i <= headerCount; i++) {
            String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
            String headerText = getInnerText(headerElement);

            if (verificationHeaders.contains(headerText)) {
                String brokerElement = "//td[text()='" + verificationValues + "']";
                boolean verifyExactText = verifyExactText(brokerElement, verificationValues);
                Assert.assertTrue(verifyExactText);
                break;
            }
        }
        return new DataManagerPage();
    }


}

