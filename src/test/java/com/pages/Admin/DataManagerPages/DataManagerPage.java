package com.pages.Admin.DataManagerPages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.page.base.DashboardPage;
import com.pages.Admin.AdminPage;
import com.pages.Admin.UsersAndSecurityPages.AddNewUserPage;
import com.pages.Admin.UsersAndSecurityPages.UserSecurityPage;
import org.testng.Assert;

import java.util.*;

public class DataManagerPage extends AdminPage {

    //Locator for Data Manager
    private String dataManagerText = "//h1[text()='Data Manager']";

    //Locator for  Add New Row Button
    private String addNewRowBtn = "//span[text()='Add new row']";

    //Locator for Apply Button
    protected String applyBtn = "//div[@id='apply-new-col-changes-save']//span";


    //Locator for Action Button
    private String actionBtn = "(//td[@class='action-edit-delete-column']/a)[1]";

    //Locator for Edit Button
    private String editBtn = "//span[text()='Edit']";


    //Validate Data Manager Page
    public EditDataSchemaPage validateDataManagerPage() {
        waitForElementToLoad(dataManagerText);
        if (isVisible(dataManagerText, "DataManagerText")) {
            reportPass("User Navigated to the Data Manager page successfully");
        } else {
            reportFailAndThrowException("Data Manager Page is not loaded.");
        }
        return new EditDataSchemaPage();
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
        waitForLoaderToDisappear();
        pause("medium");
        click(actionBtn, "Action button");
//        waitForLoaderToDisappear();
        return new DataManagerPage();
    }

    //Click the Action Button
    public EditDataSchemaPage clickOnEditDataSchema() {
        click(frameSpanTextLocator("Edit Data Schema"), "Edit Data Schema");
        waitForLoaderToDisappear();
        return new EditDataSchemaPage();
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
    public DashboardPage validateAddedRowInformation(String verificationValue, String verificationHeader) {
        waitForLoaderToDisappear();
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
        return new DashboardPage();
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
        click(applyBtn, "Apply Button");
        pause("medium");
        reportPass("User successfully clicked the Apply button.");
        return new DataManagerPage();
    }

    //Validate The Edited Row Information
    public DashboardPage validateEditedRowInformation(String verificationValues, String verificationHeaders) {
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
        return new DashboardPage();
    }


}

