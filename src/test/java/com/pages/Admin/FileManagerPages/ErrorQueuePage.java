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
        pause("medium");
        waitForLoaderToDisappear();
        //click Filter drop down
        click("//span[text()='Filter by']/ancestor::div[1]/div//div[@class='dx-texteditor-container']", "Click Drop Down");
        pause("low");
        //select Invalid cells option from drop down
        click("//div[text()='Invalid Cells']", "click Invalid cells");
        pause("low");
        if (isVisible("//div[text()='Trade Date']")) {
            mouseOver("(//td[contains(@class,'dx-required-field-heading tabular-nums')])[3]", "mouse hovered in Trade Date Column");
            pause("medium");
            String str = getInnerText("//strong[text()='Error']/following-sibling::span[contains(text(),'Invalid date value or format')]");
            if (str.contains("Invalid date value or format")) {
                reportPass("Invalid Date Format found successfully");
                invalidCellCount = getLocatorsCount("//td[contains(@class,'dx-required-field-heading tabular-nums')]") - 3;
                System.out.println("Invalid cell count: " + invalidCellCount);
            }
        }
        clickOnExitBtn();
        pause("low");
        waitForLoaderToDisappear();
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
    public ErrorQueuePage validateErrorrowsAndInvalidcells(){
        if(invalidCellCount==errorRows){
            reportPass("Successfully validated incorrect Date Format");
        }
        return new ErrorQueuePage();
    }

}
