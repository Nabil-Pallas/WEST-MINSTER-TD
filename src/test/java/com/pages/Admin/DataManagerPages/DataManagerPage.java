package com.pages.Admin.DataManagerPages;

import com.pages.Admin.AdminPage;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataManagerPage extends AdminPage {
    // variable for "alidateAddedRowWDecimalAndNumberFormat" method
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

    //variable used for validate date format
    public static String validDate;

    //variable for upload date in data manager
    public static String timeInDataManager;

    //list of data Manager grid column
    private static List<String> DatamanagerColumn = new ArrayList<>();

    //list of columns to be added in Column Chooser
    private static Set<String> chooserColumn = new HashSet<>();

    //Validate Data Manager Page
    public AddNewColumnPage validateDataManagerPage() {
        switch (xmlUserLevel) {
            case "Tier3EndUser":
                break;
            default:
                waitForElementToLoad(dataManagerText);
                if (isVisible(dataManagerText, "DataManagerText")) {
                    reportPass("User Navigated to the Data Manager page successfully");
                } else {
                    reportFailAndThrowException("Data Manager Page is not loaded.");
                }
                break;
        }

        pause("medium");
        return new AddNewColumnPage();
    }

    //Click the Add New Row Button
    public AddNewRowPage clickOnAddNewRowBtn() {
        switch (xmlUserLevel) {
            case "Tier3EndUser":
                break;
            default:
                pause("medium");
                pause("medium");
                pause("low");
                click(addNewRowBtn, "Add new row button");
                pause("low");
                break;
        }

        return new AddNewRowPage();
    }

    //Click the Action Button
    public DataManagerPage clickOnActionsBtn() {
        pause("medium");
        waitForAppearance(actionBtn, "Action button");
        click(actionBtn, "Action button");
        pause("low");
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
        waitForElementToLoad(editBtn);
        click(editBtn, "Edit button");
        pause("low");
        reportPass("User successfully clicked the Edit button.");
        return new EditDataManagerRow();
    }

    //Click the Delete Button
    public EditDataSchemaPage clickOnDeleteBtn() {
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
        return new EditDataSchemaPage();
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
    public DataManagerPage verifyHeaderElementIsPresentOrNot(String verificationHeader) {
        List<String> list = new ArrayList<>();
        Boolean value = false;
        pause("medium");
        pause("medium");
        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");
        for (int i = 1; i < headerCount; i++) {
            String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
            String headerText = getInnerText(headerElement);
            System.out.println("-------------------" + headerText);
            if (headerText.contains(verificationHeader)) {
                value = true;
//                highlightElement(frameDivTextLocator(verificationHeader), "Header Element");
//                boolean verifyExactText = verifyPartialText(frameDivTextLocator(verificationHeader), verificationHeader);
                Assert.assertTrue(value);
                break;
            }
        }
        if (value) {
            reportPass("Header Column " + verificationHeader + " is present in the Data Manager Grid");
        } else {
            reportFail("Header Column is not present in the Data Manager Grid");
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
        switch (xmlUserLevel) {
            case "Tier3EndUser":
                break;
            default:
                pause("medium");
                waitForElementToLoad(applyBtn);
                click(applyBtn, "Apply Button");
                pause("medium");
                pause("low");
                reportPass("User successfully clicked the Apply button.");
                break;
        }

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
        pause("medium");
        for (int i = 1; i < 9; i++) {
            waitForElementToLoad("(//div[@aria-label='Select row'])[" + i + "]");
            forceClick("(//div[@aria-label='Select row'])[" + i + "]", "select option", "`checkbox");
        }
        return this;
    }

    //click select single Check Box
    public DataManagerPage clickOnSingleCheckBox() {
        pause("medium");
        waitForElementToLoad(singleCheckBox);
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
        pause("medium");
        waitForAppearance("//div[text()='Bulk Edit Rows']", "Bulk Edit Rows Page");
        if (isVisible("//div[text()='Bulk Edit Rows']", "Bulk Edit Rows Page")) {
            reportPass("Successfully navigated to Bulk edit rows page");
        } else {
            reportFail("Failed to navigate to bulk edit rows page");
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
        reportPass("User Successfully validated that the " + validRows + " is equal to " + totalRows);
        return this;
    }

    public DataManagerPage getTotalRowCount() {
        pause("medium");
        pause("medium");
//        waitForLoaderToDisappear();
        String rowCount = getInnerText("//span[contains(text(),'Total Row Count:')]");

        // Clean the string by removing any non-digit characters, including non-breaking spaces
        String cleanNumberString = rowCount.replaceAll("[^\\d]", ""); // Removes all non-digit characters

        if (!cleanNumberString.isEmpty()) {
            getRowCounts.set(Long.parseLong(cleanNumberString)); // Set the parsed number
            reportPass("Final Row Counts: " + getRowCounts.get() + " noted.");
            System.out.println("Final Row Counts: " + getRowCounts.get());
        } else {
            System.out.println("No matching row count found.");
        }

        return new DataManagerPage();
    }


    public DataManagerPage getTotalRowCount(String state) {
        pause("medium");
        pause("medium");
//        waitForLoaderToDisappear();
        String rowCount = getInnerText("//span[contains(text(),'Total Row Count:')]");

        // Remove any non-digit character including any type of space
        String cleanNumberString = rowCount.replaceAll("[^\\d]", ""); // Removes all non-digit characters

        if (!cleanNumberString.isEmpty()) {
            long rowCountNumber = Long.parseLong(cleanNumberString);

            if (state.equalsIgnoreCase("Initial")) {
                InitRowCounts = rowCountNumber;
                reportPass("Initial Row Counts: " + InitRowCounts + " noted.");
                System.out.println("InitRowCounts: " + InitRowCounts);
            } else {
                FinRowCounts = rowCountNumber;
                reportPass("Final Row Counts: " + FinRowCounts + " noted.");
                System.out.println("FinRowCounts: " + FinRowCounts);
            }
        } else {
            System.out.println("No matching row count found.");
        }

        return new DataManagerPage();
    }


    public DataManagerPage validateRowCountAfterBulkDelete() {
        Assert.assertEquals(FinRowCounts, getRowCounts.get());
        reportPass("Final Row Counts: " + FinRowCounts + " is equal to the final row count after bulk delete: " + getRowCounts.get());
        return this;
    }

    public DataManagerPage validateAddedRowWDecimalAndNumberFormat(String VerificationValues, String verificationHeader1, String verificationHeader2) {
        pause("medium");
        pause("medium");
        System.out.println("printing:---->" + decimalPrecision);
        switch (decimalPrecision) {
            case "3 decimals":
                if (preferenceNumberFormat.equals("# ##0,00")) {
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
                if (preferenceNumberFormat.equals("# ##0,00")) {
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
                if (preferenceNumberFormat.equals("# ##0,00")) {
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
                if (preferenceNumberFormat.equals("# ##0,00")) {
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
                if (preferenceNumberFormat.equals("# ##0,00")) {
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
                pause("low");
                click("//span[contains(@aria-label,'Show filter options for column') and contains(@aria-label,'Broker Name')]", "Drop Down");
                pause("low");
                mouseOver("//div[@aria-label='Select All']","Broker Name filter");
                while (!isVisibleWithMinimumTime("//div[text()='" + VerificationValues + "']")) {
                    scrollUsingMouseWheelVertically("(//div[@class='dx-scrollable-scroll-content'])[2]");
                }
                pause("low");
                click("//div[text()='" + VerificationValues + "']", verificationHeader1, "Broker Name");
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
                        reportPass("Successfully validated Decimal Precision & Number Format");
                    }
                }

            }
        }
        return new DataManagerPage();
    }

    public DataManagerPage clickOnClearAllFilters() {
        pause("medium");
        switch (xmlUserLevel) {
            case "Tier3EndUser":
                break;
            default:
                // Check if the button is attached and clickable
                boolean isClickable = isEnabled("//span[text()='Clear All Filters']", "Clear All Filters") &&
                        isVisible("//span[text()='Clear All Filters']", "Clear All Filters");
                if (isClickable) {
                    click("//span[text()='Clear All Filters']", "Clear All Filters");
                    pause("medium");
                    reportPass("Successfully cleared all filters for Broker Name column");
                } else {
                    reportPass("There is no Clear All Filters is enabled");
                }
                break;
        }
        return this;
    }

    public DataManagerPage validateAddedRowWDateFormat(String VerificationValues, String verificationHeader1, String verificationHeader2) throws ParseException {
        pause("medium");
        pause("medium");
        System.out.println("printing:---->" + preferenceDateFormat);
        switch (preferenceDateFormat) {
            case "DD/MM/YYYY":
                String str = getTradeDate.get();
                // Define the input date format
                SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
                // Define the output date format
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                // Parse the input date string
                Date date = inputFormat.parse(str);
                // Format the date to the desired output format
                validDate = outputFormat.format(date);
                break;

            case "YYYY-MM-DD":
                String str2 = getTradeDate.get();
                // Define the input date format
                SimpleDateFormat inputFormat2 = new SimpleDateFormat("MM/dd/yyyy");
                // Define the output date format
                SimpleDateFormat outputFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                // Parse the input date string
                Date date2 = inputFormat2.parse(str2);
                // Format the date to the desired output format
                validDate = outputFormat2.format(date2);
                break;

            default:
                validDate = getTradeDate.get();
                break;
        }
        // to locate broker name column and click filter
        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");
        for (int i = 1; i < headerCount; i++) {
            String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
            String headerText = getInnerText(headerElement);
            if (headerText.equalsIgnoreCase(verificationHeader1)) {
                pause("low");
//                clickAndChoose("//span[contains(@aria-label,'Show filter options for column') and contains(@aria-label,'Broker Name')]", "//div[text()='" + VerificationValues + "']", verificationHeader1, "Broker Name");
//                click("//span[contains(@aria-label,'Show filter options for column') and contains(@aria-label,'Broker Name')]", "Drop Down");
//                forceClick("//div[text()='" + VerificationValues + "']", verificationHeader1, "Broker Name");
                click("//span[contains(@aria-label,'Show filter options for column') and contains(@aria-label,'Broker Name')]", "Drop Down");
                pause("low");
                mouseOver("//div[@aria-label='Select All']","Broker Name filter");
                while (!isVisibleWithMinimumTime("//div[text()='" + VerificationValues + "']")) {
                    scrollUsingMouseWheelVertically("(//div[@class='dx-scrollable-scroll-content'])[2]");
                }
                pause("low");
                click("//div[text()='" + VerificationValues + "']", verificationHeader1, "Broker Name");
                pause("low");
                click("//span[text()='OK']", "filter OK button");
                pause("medium");
                pause("medium");
                // scroll to desire column to get value
                for (i = 1; i < headerCount; i++) {
                    String header2Element = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
                    String header2Text = getInnerText(header2Element);
                    if (header2Text.equalsIgnoreCase(verificationHeader2)) {
//                    click("//div[text()='"+verificationHeader2+"']",verificationHeader2);
                        scrollToElement("//div[text()='" + verificationHeader2 + "']");
                        String value = getInnerText("//td[text()='" + validDate + "']");
                        boolean verifyExactText = verifyInnerText(value, validDate);
                        Assert.assertTrue(verifyExactText);
                        reportPass("Successfully validated Date Format");

                    }
                }

            }
        }
        return this;
    }

    public DataManagerPage getUploadDateFromDataManager(String verificationHeader1) {
        pause("medium");
        waitForElementToLoad("//div[@aria-label='Select all']");
        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");
        for (int i = 1; i < headerCount; i++) {
            String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
            String headerText = getInnerText(headerElement);
            if (headerText.equalsIgnoreCase(verificationHeader1)) {
                scrollToElement("//div[contains(@class,'dx-datagrid-rowsview')]//table[1]//tr[1]//td[" + (i + 2) + "]//time");
                pause("low");
                reportPass("Successfully Scrolled to " + verificationHeader1 + " Column in Data Manager");
                String str = getInnerText("//div[contains(@class,'dx-datagrid-rowsview')]//table[1]//tr[1]//td[" + (i + 2) + "]//time");
                // Split the string at the first occurrence of ", "
                String[] parts = str.split(", ", 2);
                // Get the time part which is the second element in the array
                timeInDataManager = parts[1];
            }
        }
        return this;
    }

    public DataManagerPage validateTimeFormatInDataManager() {
//        waitForLoaderToDisappear();
        pause("medium");
        if (preferenceTimeFormat.equals("12-hour")) {

            // Regular expression for 12-hour time format (hh:mm:ss AM/PM)
            String regex = "^(0[1-9]|1[0-2]):[0-5][0-9]:[0-5][0-9] (AM|PM)$";
            // Compile the regex into a pattern
            Pattern pattern = Pattern.compile(regex);
            // Match the time string against the pattern
            Matcher matcher = pattern.matcher(timeInDataManager);
            boolean result = matcher.matches();
            Assert.assertTrue(result);
            reportPass("Successfully Validated 12-hour Time Format: " + timeInDataManager);
        } else {
            // Regular expression for 24-hour time format (HH:mm:ss)
            String regex = "^(?:[01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";
            // Compile the regex into a pattern
            Pattern pattern = Pattern.compile(regex);
            // Match the time string against the pattern
            Matcher matcher = pattern.matcher(timeInDataManager);
            boolean result = matcher.matches();
            Assert.assertTrue(result);
            reportPass("Successfully Validated 24-hour Time Format: " + timeInDataManager);
        }
        return this;
    }

    public DataManagerPage getDataManagerGridColumnNames() {
        pause("medium");
        DatamanagerColumn.clear();
        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");
        for (int i = 1; i <= headerCount; i++) {
            String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
            String headerText = getInnerText(headerElement);
            DatamanagerColumn.add(headerText);
        }
        return this;
    }

    public DataManagerPage validateDataManagerGridColumn() {
        pause("low");
        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");
        for (String s : chooserColumn) {
            for (String string : DatamanagerColumn) {
                if (string.equals(s)) {
                    boolean result = true;
                    reportPass("Column: " + s + " - found in Data Manager Grid");
                    System.out.println("Column: " + s + " - found in Data Manager Grid");
                    Assert.assertTrue(result);
                    break;
                }
            }
        }
        return this;
    }

    public DataManagerPage clickOnShowColumnChooser() {
        pause("medium");
        waitForElementToLoad(frameSpanExactTextLocator("Show Column Chooser"));
        pause("medium");
        click(frameSpanExactTextLocator("Show Column Chooser"), "Show Column Chooser");
        return this;
    }

    public DataManagerPage clickOnCloseColumnChooser() {
        pause("low");
        click("//i[contains(@class,'dx-icon-close')]", "Close icon");
        pause("low");
        return this;
    }

    public DataManagerPage dragAndDropColumnInColumnChooser(List<String> columnName) {
        pause("low");
        for (int index = 0; index < columnName.size(); index++) {
            dragAndDrop("//div[text()='" + columnName.get(index) + "']", frameDivTextLocator("Column Chooser"));
            chooserColumn.add(columnName.get(index));
            reportPass("Successfully dropped column: " + columnName.get(index) + " in show column chooser");
            System.out.println("successfully dropped in column chooser");
        }

        return this;
    }

    public DataManagerPage valiateColumnsAgainstColumnChooser() {
        System.out.println("DataMnager list size: " + DatamanagerColumn);
        System.out.println("chooser column list size: " + chooserColumn);

        for (String s : chooserColumn) {
            for (String string : DatamanagerColumn) {
                if (string.equals(s)) {
                    boolean result = true;
                    reportFail("Failed to move column: " + s + " to column chooser");
                    System.out.println("Failed to move column: " + s + " to column chooser");
                    Assert.assertFalse(result);
                    break;
                }
            }
        }
        reportPass("Successfully validated. Columns \"moved to Show Column Chooser\" are not visible in Data Manager Grid.");
        return this;
    }

    public DataManagerPage clickOnRestoreAllColumns() {
        pause("low");
        click(frameSpanExactTextLocator("Restore All Columns"), "Restore all columns button");
        reportPass("Columns restored back to Data Manager Grid");

        pause("low");
        return this;
    }

}

