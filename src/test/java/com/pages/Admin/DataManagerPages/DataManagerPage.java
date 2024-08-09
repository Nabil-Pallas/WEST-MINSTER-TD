package com.pages.Admin.DataManagerPages;

import com.pages.Admin.AdminPage;
import org.testng.Assert;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.*;

public class DataManagerPage extends AdminPage {
    public static String valuee;

    //Locator for Data Manager
    private String dataManagerText = "//h1[text()='Data Manager']";

    //Locator for  Add New Row Button
    private String addNewRowBtn = "//span[text()='Add new row']";

    //Locator for  Edit Row Button
    private String editRowBtn = "//div[text()='Edit Trade Details']";

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
//       waitForLoaderToDisappear();
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
        waitForElementToLoad(actionBtn);
        click(actionBtn, "Action button");
        waitForLoaderToDisappear();
        return new DataManagerPage();
    }

    public DataManagerPage clickOnEllipsisBtn() {
        waitForElementToLoad(actionBtn);
        click(actionBtn, "Action button");
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
                    boolean verifyExactText = verifyExactText("(//td[text()='" + verificationValue + "'])[" + j + "]", list.get(j - 1));
                    Assert.assertTrue(verifyExactText);
                }
            }
        }
        System.out.println("Size of list: " + list.size());
        return new DataManagerPage();
    }

    //Validate the Add New Row Information
    public DataManagerPage getInnerTextOfTheField(String verificationValue, String verificationHeader) {
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
                    boolean verifyExactText = verifyExactText("(//td[text()='" + verificationValue + "'])[" + j + "]", list.get(j - 1));
                    Assert.assertTrue(verifyExactText);
                }
            }
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
        pause("medium");
        click(applyBtn, "Apply Button");
        pause("medium");
        pause("low");
        reportPass("User successfully clicked the Apply button.");
        return new DataManagerPage();
    }

    //click the Cancel Button
    public DataManagerPage clickOnCancelBtn() {
        waitForElementToLoad(cancelBtn);
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
        mouseOver(frameSpanTextLocator("Edit Data Schema"), "Edit Data Schema");
        return new DataManagerPage();
    }

    //click row select option on Data Manager Grid
    public DataManagerPage clickOnSelectRow() {
        waitForLoaderToDisappear();
//        pause("medium");
        for (int i = 1; i < 9; i++) {
            waitForLoaderToDisappear();
            forceClick("(//div[@aria-label='Select row'])[" + i + "]", "select option", "`checkbox");
//            pause("medium");

        }
        return this;
    }

    //click select single Check Box
    public DataManagerPage clickOnSingleCheckBox() {
        waitForLoaderToDisappear();
        pause("medium");
        click(singleCheckBox, "Single Check Box", "CheckBox");
        mouseOver(frameSpanTextLocator("Edit Data Schema"), "Edit Data Schema");
        pause("low");
        return new DataManagerPage();
    }

    //validate Edit row page
    public EditDataManagerRow validateEditRowPage() {
        waitForElementToLoad(editRowBtn);
        if (isVisible(editRowBtn, "Edit Trade Details Page")) {
            reportPass("Successfully navigated to Edit Row Page");
        } else {
            reportFail("Failed");
        }
        return new EditDataManagerRow();
    }

    //validate Bulk Edit rows page
    public EditDataManagerRow validateBulkEditRowsPage() {
        waitForLoaderToDisappear();
        if (isVisible("//div[text()='Bulk Edit Rows']", "Bulk Edit Rows Page")) {
            reportPass("Successfully navigated to Bulk edit rows page");
        } else {
            reportFail("failed to navigate to bulk edit rows page");
        }
        return new EditDataManagerRow();
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

    public DataManagerPage validateAddedRowWDecimalPrecision(String VerificationValues, String verificationHeader1, String verificationHeader2) {
        pause("medium");
        pause("medium");
        System.out.println("printing:---->" + decimalPrecision);
        switch (decimalPrecision) {
            case "3 decimals":
                if (numberFormat.equals("# ##0,00")) {
                    double d = Double.parseDouble(number.get());
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setDecimalSeparator(',');
                    DecimalFormat df = new DecimalFormat("####.###", symbols);
                    valuee = df.format(d);
                } else {
                    double d = Double.parseDouble(number.get());
                    DecimalFormat df = new DecimalFormat("####.###");
                    valuee = df.format(d);
                }
                break;
            case "4 decimals":
                if (numberFormat.equals("# ##0,00")) {
                    double d = Double.parseDouble(number.get());
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setDecimalSeparator(',');
                    DecimalFormat df = new DecimalFormat("####.####", symbols);
                    valuee = df.format(d);
                } else {
                    double d = Double.parseDouble(number.get());
                    DecimalFormat df = new DecimalFormat("####.####");
                    valuee = df.format(d);
                }
                break;
            case "5 decimals":
                if (numberFormat.equals("# ##0,00")) {
                    double d = Double.parseDouble(number.get());
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setDecimalSeparator(',');
                    DecimalFormat df = new DecimalFormat("####.#####", symbols);
                    valuee = df.format(d);
                } else {
                    double d = Double.parseDouble(number.get());
                    DecimalFormat df = new DecimalFormat("####.#####");
                    valuee = df.format(d);
                }
                break;
            case "2 decimals":
                if (numberFormat.equals("# ##0,00")) {
                    double d = Double.parseDouble(number.get());
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setDecimalSeparator(',');
                    DecimalFormat df = new DecimalFormat("####.##", symbols);
                    valuee = df.format(d);
                } else {
                    double d = Double.parseDouble(number.get());
                    DecimalFormat df = new DecimalFormat("####.##");
                    valuee = df.format(d);
                }
                break;
            default:
                if (numberFormat.equals("# ##0,00")) {
                    double d = Double.parseDouble(number.get());
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setDecimalSeparator(',');
                    DecimalFormat df = new DecimalFormat("####.#", symbols);
                    valuee = df.format(d);
                } else {
                    double d = Double.parseDouble(number.get());
                    DecimalFormat df = new DecimalFormat("####.#");
                    valuee = df.format(d);
                }
                break;
        }

        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");
        for (int i = 1; i < headerCount; i++) {
            String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
            String headerText = getInnerText(headerElement);
            if (headerText.equalsIgnoreCase(verificationHeader1)) {
//                click(searchValueWithHeader(i), "Search Filter", "Search Box");
//                typeAndEnter(searchValueWithHeader(i), VerificationValues, VerificationValues);
                clickAndChoose("//span[contains(@aria-label,'Show filter options for column') and contains(@aria-label,'Broker Name')]", "//div[text()='" + VerificationValues + "']", VerificationValues, VerificationValues);
                pause("low");
                click("//span[text()='OK']", "filter OK button");
                pause("medium");
                pause("medium");
                for (i = 1; i < headerCount; i++) {
                    String header2Element = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
                    String header2Text = getInnerText(header2Element);
                    if (header2Text.equalsIgnoreCase(verificationHeader2)) {
//                    click("//div[text()='"+verificationHeader2+"']",verificationHeader2);
                        scrollToElement("//div[text()='" + verificationHeader2 + "']");
                        pause("low");
                        String value = getInnerText("//td[text()='" + valuee + "']");
                        boolean verifyExactText = verifyInnerText(value, valuee);
                        Assert.assertTrue(verifyExactText);
                        reportPass("Successfully validated Decimal Precision");

                    }
                }

            }
        }
        pause("medium");
        click("//span[text()='Clear All Filters']", "Clear All Filters");
        pause("medium");
        reportPass("Successfully cleared all filters for Broker Name column");
        return new DataManagerPage();
    }

}

