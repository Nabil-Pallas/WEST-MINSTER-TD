package com.pages.Admin.DataManagerPages;

import com.pages.Admin.AdminPage;
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

    protected String cancelBtn = "//span[text()='Cancel']";
    //Locator for Action Button
    private String actionBtn = "(//td[@class='action-edit-delete-column']/a)[1]";

    //Locator for Edit Button
    private String editBtn = "//span[text()='Edit']";

    //Locator for Edit Button
    private String deleteRowsBtn = "//span[text()='Delete Rows']";

    //Locator for Edit Button
    private String deleteRowBtn = "//span[text()='Delete Row']";

    //Locator for select single Check Box
    private String singleCheckBox = "(//div[@class='dx-checkbox-container'])[2]";

    //Locator for Bulk Edit Rows
    private String bulkEditBtn = "(//span[text()='Bulk Edit Rows'])[1]";

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

    //Click the Delete Button
    public EditDataManagerRow clickOnDeleteBtn() {
        pause("medium");
        click(deleteBtn, "Delete button");
        pause("low");
        reportPass("User successfully clicked the Delete button.");
        if (isVisible("//div[contains(text(),'Delete')]")) {
            click("//span[text()='Yes']", "Click Yes Button");
            reportPass("User successfully clicked Yes button.");
            typeAndEnter("//input[@class='confirm-delete-text-input dx-texteditor-input']", "delete", "Enter Delete in input box");
            click("//span[text()='Confirm Delete']", "Click Delete button");
        }
        return new EditDataManagerRow();
    }

    //Click the Bulk Delete Button - Multiple Rows Delete
    public EditDataManagerRow clickOnBulkDeleteBtn() {
        pause("medium");
        click(deleteRowsBtn, "Delete button");
        pause("low");
        reportPass("User successfully clicked the Delete button.");
        if (isVisible("//div[contains(text(),'Delete')]")) {
            click("//span[text()='Yes']", "Click Yes Button");
            typeAndEnter("//input[@class='confirm-delete-text-input dx-texteditor-input']", "DELETE", "Enter Delete in input box");
            click("//span[text()='Confirm Delete']", "Click Delete button");
            reportPass("User successfully clicked Confirm Delete button.");
        }
        return new EditDataManagerRow();
    }

    //Click the Delete Row Button - Single Row Delete
    public EditDataManagerRow clickOnDeleteRowBtn() {
        pause("medium");
        click(deleteRowBtn, "Delete button");
        pause("low");
        reportPass("User successfully clicked the Delete button.");
        if (isVisible("//div[contains(text(),'Delete')]")) {
            click("//span[text()='Yes']", "Click Yes Button");
            typeAndEnter("//input[@class='confirm-delete-text-input dx-texteditor-input']", "DELETE", "Enter Delete in input box");
            click("//span[text()='Confirm Delete']", "Click Delete button");
            reportPass("User successfully clicked Confirm Delete button.");
        }
        return new EditDataManagerRow();
    }

    //Click the Bulk Edit Rows Button
    public EditDataManagerRow clickOnBulkEditBtn() {
        click(bulkEditBtn, "Bulk Edit button");
        pause("medium");
//        waitForLoaderToDisappear();
//        waitForAppearance(editBtn);
        reportPass("User successfully clicked the Bulk Edit Rows button.");
        return new EditDataManagerRow();
    }

    //Validate the Add New Row Information
    public DataManagerPage validateAddedRowInformation(String verificationValue, String verificationHeader) {
//        waitForLoaderToDisappear();
        List<String> list = new ArrayList<>();
        pause("medium");
        pause("medium");
        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");

        for (int i = 1; i < headerCount; i++) {
            String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
            String headerText = getInnerText(headerElement);
            if (headerText.equalsIgnoreCase(verificationHeader)) {
                click(searchValueWithHeader(i), "Search Filter", "Search Box");
                type(searchValueWithHeader(i), verificationValue, verificationValue);
                pause("medium");
                pause("medium");
                int count = getLocatorsCount("//td[text()='" + verificationValue + "']");
                System.out.println("Locators count: " + count);
                for (int j = 1; j <= count; j++) {
                    if (j <= count) {
                        list.add(getInnerText("(//td[text()='" + verificationValue + "'])[" + j + "]"));
                    }
                    System.out.println(list.size());
                    boolean verifyExactText = verifyExactText("(//td[text()='" + verificationValue + "'])[" + j + "]", list.get(j - 1));
                    Assert.assertTrue(verifyExactText);
                }
                // String brokerElement = "//td[text()='" + verificationValue + "']";
//               boolean verifyExactText = verifyExactText(li, verificationValue);
//               Assert.assertTrue(verifyExactText);
//                break;
            }
            // System.out.println("Size of list: "+list.size());
        }
        System.out.println("Size of list: " + list.size());
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

    //click the Cancel Button
    public DataManagerPage clickOnCancelBtn() {
//        waitForLoaderToDisappear();
        pause("medium//");
        click(cancelBtn, "Cancel Button");
        pause("medium");
        reportPass("User successfully clicked the Cancel button.");
        return new DataManagerPage();
    }

    //click select all option
    public DataManagerPage clickOnSelectAll() {
        String selectAll = "//div[@aria-label='Select all']";
        waitForLoaderToDisappear();
        pause("medium");
        click(selectAll, "Select All", "CheckBox");
        mouseOver(addNewRowBtn, "Add New Row Btn");
        return new DataManagerPage();
    }

    //click select single Check Box
    public DataManagerPage clickOnSingleCheckBox() {
        waitForLoaderToDisappear();
        pause("medium");
        click(singleCheckBox, "Single Check Box", "CheckBox");
        mouseOver(addNewRowBtn, "Add New Row Btn");
        pause("low");
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


    public DataManagerPage CompareTotalRowCountAndValidRows() {

        long totalRows = FinRowCounts - InitRowCounts;

        Assert.assertEquals(totalRows, validRows);

        return this;
    }


}

