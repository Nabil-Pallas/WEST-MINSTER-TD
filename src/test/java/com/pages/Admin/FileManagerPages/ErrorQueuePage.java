package com.pages.Admin.FileManagerPages;

import com.pages.Admin.DataManagerPages.AddNewRowPage;
import org.testng.Assert;

public class ErrorQueuePage extends FileManagerPage {

    private String ErrorQueueText = "//h1[text()='Error Queue']";

    //Locator For SelectAll CheckBox
    private String selectAll = "//div[@aria-label='Select all']";


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


}
