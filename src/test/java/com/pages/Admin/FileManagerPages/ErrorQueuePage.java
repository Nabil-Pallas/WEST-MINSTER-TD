package com.pages.Admin.FileManagerPages;

import com.pages.Admin.DataManagerPages.AddNewRowPage;
import org.testng.Assert;

public class ErrorQueuePage extends FileManagerPage {

    private String ErrorQueueText = "//h1[text()='Error Queue']";

    //Locator For SelectAll CheckBox
    private String selectAll = "//div[@aria-label='Select all']";
    private static long invalidCellCount;
    private static long errorRows;

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
        waitForLoaderToDisappear();
        pause("medium");
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

    public FileManagerPage clickOnExitBtn() {
        pause("low");
        click(frameSpanTextLocator("Exit"), "Exit");
        pause("low");
        return new FileManagerPage();
    }

    public ErrorQueuePage clickFilterAndSelectInvalidCells(String fileName) {
        waitForLoaderToDisappear();
        waitForLoaderToDisappear();
        pause("low");
        //click Filter drop down
        click("//span[text()='Filter by']/ancestor::div[1]/div//div[@class='dx-texteditor-container']", "Click Drop Down");
        pause("low");
        //select Invalid date value/format option from drop down
        waitForElementToLoad("//div[text()='Invalid date value/format']");
        click("//div[text()='Invalid date value/format']", "click Invalid date value");
        pause("low");
        waitForLoaderToDisappear();
        //get the Row Count value
        String rowCount = getInnerText("//div[@id='error-queue-row-count']");
        String replaceRowCount = rowCount.replaceAll("Row Count: ", "");
        invalidCellCount = Long.parseLong(replaceRowCount);
        System.out.println("Invalid cell count: " + invalidCellCount);
        //click on exit
        clickOnExitBtn();
        pause("low");
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
        return new ErrorQueuePage();
    }

    public ErrorQueuePage validateErrorrowsAndInvalidcells() {
        if (invalidCellCount == errorRows) {
            reportPass("Successfully validated incorrect Date Format");
        }
        return new ErrorQueuePage();
    }

}
