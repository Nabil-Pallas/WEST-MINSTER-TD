package com.pages.Admin.FileManagerPages;

import com.pages.Admin.DataManagerPages.AddNewRowPage;
import org.testng.Assert;

import java.util.Collections;
import java.util.List;

public class ErrorQueuePage extends FileManagerPage {

    private String ErrorQueueText = "//h1[text()='Error Queue']";

    //Locator For SelectAll CheckBox
    private String selectAll = "//div[@aria-label='Select all']";
    private static long invalidCellCount;
    private static long errorRows;
    private String editRow = "//span[text()='Edit']";
    private String actionBtn = "(//table)[4]//tr[1]//td[last()]";
    private String saveBtn = "(//a[contains(text(),'Save')])[2]";
    private String filterByLocator = "//span[text()='Filter by']/ancestor::div[1]/div//div[@class='dx-texteditor-container']";
    private String rowCountLocator = "//div[@id='error-queue-row-count']";
    public static long rCount;
    public static long rowCount;
    private static long duplicateRowCount;


    public ErrorQueuePage validateErrorQueuePage() {
        waitForElementToLoad(ErrorQueueText);
        if (isVisible(ErrorQueueText, "Error Queue Text")) {
            reportPass("User successfully reached the correct Error Queue page");
        } else {
            reportFailAndThrowException("ErrorQueue Page is not loaded.");
        }
        return this;
    }

    public ErrorQueuePage clickOnSelectAll() {
//        waitForLoaderToDisappear();
        pause("medium");
        pause("medium");
        waitForElementToLoad(selectAll);
        click(selectAll, "Select All", "CheckBox");
        return new ErrorQueuePage();
    }

    public BulkEditRowsPage clickOnBulkEditRows() {
        pause("low");
        click(frameSpanTextLocator("Bulk Edit Rows"), "Bulk Edit Rows");
        return new BulkEditRowsPage();
    }

    public ErrorQueuePage clickOnPromoteRows() {
        pause("low");
        waitForElementToLoad(frameSpanTextLocator("Promote"));
        click(frameSpanTextLocator("Promote"), "Promote Rows");
        //if promotion error
        if (isVisible("//span[text()='Promotion error']")) {
            clickOnSelectAll();
            clickOnBulkEditRows()
                    .validateBulkEditRowsPage()
                    .changeDuplicateStatus()
                    .clickOnApplyBtn()
                    .clickOnSelectAll()
                    .clickOnPromoteRows();
        } else {
            System.out.println("---Promoting is done---");
        }
        pause("low");
        reportPass("User successfully clicked 'Promote Rows'.");
        return this;
    }

    public ErrorQueuePage clickOnExitBtn() {
        pause("low");
        waitForLoaderToDisappear();
        click(frameSpanTextLocator("Exit"), "Exit");
        pause("low");
        return new ErrorQueuePage();
    }

    public ErrorQueuePage clickFilterAndSelectInvalidDateFormat() {
        waitForLoaderToDisappear();
        waitForLoaderToDisappear();
        pause("low");
        //click Filter drop down
        click("//span[text()='Filter by']/ancestor::div[1]/div//div[@class='dx-texteditor-container']", "Click Drop Down");
        pause("low");
        //select Invalid date value/format option from drop down
        waitForElementToLoad("//div[text()='Invalid date value/format']");
        forceClick("//div[text()='Invalid date value/format']", "click Invalid date value", "Select Option");
        pause("low");
        waitForLoaderToDisappear();
        return new ErrorQueuePage();
    }

    public ErrorQueuePage clickFilterAndSelectValidationErrors() {
        waitForLoaderToDisappear();
        waitForLoaderToDisappear();
        pause("low");
        //click Filter drop down
        click("//span[text()='Filter by']/ancestor::div[1]/div//div[@class='dx-texteditor-container']", "Click Drop Down");
        pause("low");
        //select Invalid date value/format option from drop down
        waitForElementToLoad("//div[text()='Validation errors']");
        forceClick("//div[text()='Validation errors']", "Validation errors", "Select Option");
        pause("low");
        waitForLoaderToDisappear();
        return new ErrorQueuePage();
    }

    public ErrorQueuePage clickFilterAndSelectValidatedRows() {
        waitForLoaderToDisappear();
        waitForLoaderToDisappear();
        pause("low");
        //click Filter drop down
        click("//span[text()='Filter by']/ancestor::div[1]/div//div[@class='dx-texteditor-container']", "Click Drop Down");
        pause("low");
        //select Invalid date value/format option from drop down
        waitForElementToLoad("//div[text()='Validated Rows']");
        forceClick("//div[text()='Validated Rows']", "Validated Rows", "Select Option");
        pause("low");
        waitForLoaderToDisappear();
        return new ErrorQueuePage();
    }

    public ErrorQueuePage getRowCountInErrorQueuePage() {
        waitForLoaderToDisappear();
        //get the Row Count value
        String rowCount = getInnerText("//div[@id='error-queue-row-count']");
        String replaceRowCount = rowCount.replaceAll("Row Count: ", "");
        invalidCellCount = Long.parseLong(replaceRowCount);
        System.out.println("Invalid cell count: " + invalidCellCount);
        waitForLoaderToDisappear();
        return new ErrorQueuePage();
    }

    public ErrorQueuePage getAmendErrorRowCount(String filename) {
        waitForLoaderToDisappear();
        waitForLoaderToDisappear();
        //Get error rows count from File Manager Page
        if (isVisible("(//tbody[@role='presentation']//tr[td[1] = '" + fileName + "']//td[5]//span)[2]")) {
            click("(//tbody[@role='presentation']//tr[td[1] = '" + fileName + "']//td[5]//span)[2]", "Invalid Row", "Invalid RowLink");
            pause("low");
            waitForElementToLoad(frameSpanTextLocator("Amend Errors"));
            String str = getInnerText("//span[text()='Error Rows']/following-sibling::span");
            errorRows = Long.parseLong(str);
            System.out.println("Error row count: " + errorRows);

        }
        return this;
    }


    public ErrorQueuePage validateErrorrowsAndInvalidcells() {
        if (invalidCellCount == errorRows) {
            reportPass("Successfully validated incorrect Date Format");
        }
        return new ErrorQueuePage();
    }

    public ErrorQueuePage clickActionBtn() {
//        waitForElementToLoad(actionBtn);
        pause("low");
        click(actionBtn, "Action Button");
        return this;
    }

    public ErrorQueuePage clickOnEdit() {
        waitForElementToLoad(editRow);
        click(editRow, "Edit");
        return this;
    }

    public ErrorQueuePage clickOnSaveBtn() {
        waitForElementToLoad(saveBtn);
        click(saveBtn, "Save");
        waitForLoaderToDisappear();
        return this;
    }

    public ErrorQueuePage editInformation(String verificationHeaders, String verificationHeaders1, String tradeDate, String settlementDate) {
        waitForLoaderToDisappear();
        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");

        for (int i = 1; i <= headerCount; i++) {
            String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
            String headerText = getInnerText(headerElement);

            if (verificationHeaders.contains(headerText)) {
                click("(//table)[3]//tr[1]//td[" + (i + 3) + "]", "Field");
                pause("low");
                typeWithType("(//table)[3]//tr[1]//td[" + (i + 3) + "]//input", tradeDate, "Field");
                reportPass("successfully edited Trade date");
            }
            if (verificationHeaders1.contains(headerText)) {
                click("(//table)[3]//tr[1]//td[" + (i + 3) + "]", "Field");
                pause("low");
                typeAndEnter("(//table)[3]//tr[1]//td[" + (i + 3) + "]//input", settlementDate, "Field");
                reportPass("successfully edited Settlement date");
            }
        }
        return this;
    }

    public ErrorQueuePage getAccountNameText(String verificationHeaders) {
        waitForLoaderToDisappear();
        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");

        for (int i = 1; i <= headerCount; i++) {
            String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
            String headerText = getInnerText(headerElement);

            if (verificationHeaders.contains(headerText)) {
                String getAccounttextName = getInnerText("(//table)[3]//tr[1]//td[" + (i + 3) + "]");
                List<String> list = Collections.singletonList(getAccounttextName);
                listItems.set(list);
                System.out.println("NAME :" + list);
                reportPass("Successfully Account Name plus Icon is clicked");
            }
        }
        return this;
    }

    public ErrorQueuePage addAccountNameAsList(String verificationHeaders) {
        waitForLoaderToDisappear();
        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");

        for (int i = 1; i <= headerCount; i++) {
            String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
            String headerText = getInnerText(headerElement);

            if (verificationHeaders.contains(headerText)) {
                isVisible("(//table)[3]//tr[1]//td[" + (i + 3) + "]//input/preceding::div[@aria-label='add']//i", "Account Name plus Icon");
                click("(//table)[3]//tr[1]//td[" + (i + 3) + "]//input/preceding::div[@aria-label='add']//i", "Account Name plus Icon");
                click(frameSpanTextLocator("Yes"), "Yes");
                pause("low");
                reportPass("Successfully Account Name plus Icon is clicked");
            }
        }
        return this;
    }


    public ErrorQueuePage selectEditedRow(String date) {
//        waitForElementToLoad("(//span[@class='dx-checkbox-icon'])[2]");
        waitForLoaderToDisappear();
        waitForLoaderToDisappear();
        click("//span[text()='Filter by']/ancestor::div[1]/div//div[@class='dx-texteditor-container']", "Drop Down");
//        waitForElementToLoad("");
        forceClick("//div[text()='Validated Rows']", "Validated Rows", "Select Option");
//        int maxAttempts = 20000;
//        int attempts = 0;
//        click("(//table)[3]//tr[1]//td[3]","click on first field");
//
//        while (!isVisibleWithMinimumTime("//td[text()='" + getTradeDate.get() + "']") && attempts < maxAttempts) {
//            pressDownArrow("(//table)[3]//tr[6]");
//            attempts++;
//        }
        pause("low");
        forceClick("(//td[text()='" + date + "']//preceding-sibling::td[last()])[1]", "checkbox", "Select Check box");
        return this;
    }

    public ErrorQueuePage errorQueuePageRowCount() {
        waitForLoaderToDisappear();
        pause("low");
        waitForElementToLoad("//div[@id='error-queue-row-count']");
        pause("low");
        waitForLoaderToDisappear();
        String rowCount = getInnerText("//div[@id='error-queue-row-count']");
        System.out.println(rowCount);
        String rowCountNum = rowCount.replaceAll("Row Count: ", "");
        rCount = Long.parseLong(rowCountNum);
        reportPass("Error Queue page row count: "+rCount);
        System.out.println(rCount);
        return this;
    }

    public ErrorQueuePage errorQueuePageRowCountAfterPromote() {
        waitForLoaderToDisappear();
        click("//span[text()='Filter by']/ancestor::div[1]/div//div[@class='dx-texteditor-container']", "Drop Down");
//        waitForElementToLoad("");
        forceClick("//div[text()='Show All']", "Show All", "Select Option");
        waitForLoaderToDisappear();
        String rCount = getInnerText("//div[@id='error-queue-row-count']");
        String rowCountNum = rCount.replaceAll("Row Count: ", "");
        rowCount = Long.parseLong(rowCountNum);
        reportPass("Error queue page final row count: "+rowCount);
        System.out.println("Error queue page final row count: "+rowCount);
        return this;
    }

    public ErrorQueuePage validateSingleEditRowCount() {
        System.out.println("final count: " + rowCount);
        System.out.println("initial count: " + rCount);
        if (rowCount == (rCount - 1)) {
            reportPass("Successfully edited and promoted the row");
        } else {
            reportFail("failed to validate. please check whether row has been promoted or not");
        }
        return this;
    }

    public ErrorQueuePage clickFilterAndSelectDuplicateRows() {
        waitForLoaderToDisappear();
        waitForLoaderToDisappear();
        click("//span[text()='Filter by']/ancestor::div[1]/div//div[@class='dx-texteditor-container']", "Drop Down");
        pause("low");
        //select Invalid date value/format option from drop down
        waitForElementToLoad("//div[text()='Duplicate Rows']");
        forceClick("//div[text()='Duplicate Rows']", "Duplicate Rows", "Option");
        pause("low");
        waitForLoaderToDisappear();
        //get the Row Count value
        String rowCount = getInnerText("//div[@id='error-queue-row-count']");
        String replaceRowCount = rowCount.replaceAll("Row Count: ", "");
        duplicateRowCount = Long.parseLong(replaceRowCount);
        System.out.println("Duplicate Rows count: " + duplicateRowCount);
        return this;
    }

    public ErrorQueuePage clickOnFilterBy() {
        waitForLoaderToDisappear();
        waitForLoaderToDisappear();
        click(filterByLocator, "Select Drop Down");
        pause("low");
        return this;
    }

    public ErrorQueuePage filterByOption(String options) {
        clickOnFilterBy();
        //select Invalid date value/format option from drop down
        waitForElementToLoad(frameDivTextLocator(options));
        forceClick(frameDivTextLocator(options), options, "Option");
        pause("low");
        waitForLoaderToDisappear();
        return this;
    }

    public ErrorQueuePage getErrorQueueRowCounts() {
        //get the Row Count value
        waitForLoaderToDisappear();
        waitForLoaderToDisappear();
        pause("low");
        String rowCount = getInnerText(rowCountLocator);
        String replaceRowCount = rowCount.replaceAll("Row Count: ", "");
        getRowCounts.set(Long.parseLong(replaceRowCount));
        System.out.println("Rows counts: " + getRowCounts.get());
        return this;
    }

    public ErrorQueuePage validateRowCount(long actualCount, long expectedCount) {
        Assert.assertEquals(actualCount, expectedCount);
        reportPass("Row count on the Error Queue page is as expected");
        return this;
    }

    public ErrorQueuePage validateDuplicateRowCount() {
        if (notificationDuplicateRowCount == duplicateRowCount) {
            reportPass("Successfully validated duplicate rows count");
        } else {
            reportFail("Failed to validate duplicate rows count");
        }
        return this;
    }

    public ErrorQueuePage validateRowCountForValidationError() {

        if (invalidCellCount == 0) {
            reportPass("Successfully promoted rows. Now row count in Validation errors: " + invalidCellCount);
        } else {
            reportFail("failed to promote. Now row count in Validation errors: " + invalidCellCount);
        }
        pause("medium");

        return this;
    }


    public ErrorQueuePage clickFilterAndValidateChanges(String filterFieldName, String expectedValues) {
        click(frameFieldFilterLocator(filterFieldName), filterFieldName, "Filter Icon");
        waitForElementToLoad(frameDivTextLocator(expectedValues));
        boolean verifyExactText = verifyExactText(frameDivTextLocator(expectedValues), expectedValues);
        Assert.assertTrue(verifyExactText);
        click(frameSpanExactTextLocator("Cancel", 2), "Cancel");
        return this;
    }



}
