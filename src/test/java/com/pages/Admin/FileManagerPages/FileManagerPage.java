package com.pages.Admin.FileManagerPages;

import com.pages.Admin.AdminPage;
import com.pages.Admin.DataManagerPages.AddNewRowPage;
import com.pages.Admin.DataManagerPages.DataManagerPage;
import org.testng.Assert;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.pages.Admin.FileManagerPages.ErrorQueuePage.rCount;
import static com.pages.Admin.FileManagerPages.ErrorQueuePage.rowCount;


public class FileManagerPage extends AdminPage {

    //Locator For File Manager Option
    private String fileManagerText = "//h1[text()='File Manager']";

    //Locator For Create New Mapping Button
    private String createNewMapping = "//span[contains(text(),'or Create New Mapping')]";

    //Locator For Create Button
    private String createNewDataMapBtn = "//span[text()='Create']";

    //Locator For Open Error Queue
    private String openErrorQueueBtn = "//span[text()='Open Error Queue']";

    //xpath for Duplicate Rows in Notification page
    private String duplicateCount = "//div[@id='current-notification-message__text']//ul//li[1]";

    //Duplicate count from Notification
    public static long notificationDuplicateRowCount;

    //upload time from File Manager
    public static String timeInFileManager;

    //list of data Manager grid column
    private static List<String> DatamanagerColumn = new ArrayList<>();

    //list of columns to be added in Column Chooser
    private static Set<String> chooserColumn = new HashSet<>();


    //Locator For Date Format
    public String frameDateFormatLocator(String placeholder, int index) {
        return "(//div[text()='" + placeholder + "']/ancestor::label/following-sibling::div//input)[" + index + "]";
    }

    //Locator For Date Format Select DropDown
    public String frameDateFormatSelectDropDownLocator(String placeholder) {
        return "//div[contains(@class,'dx-item dx-list-item')]/div[text()='" + placeholder + "']";
    }

    //Validate The File Manager Page
    public FileManagerPage validateFileManagerPage() {
        waitForElementToLoad(fileManagerText);
        if (isVisible(fileManagerText, "FileManagerText")) {
            reportPass("User successfully reached the correct File Manager page");
        } else {
            reportFailAndThrowException("File Manager Page is not loaded.");
        }
        return this;
    }

    //Upload The File
    public FileManagerPage fileUpload(String filePath, String fileName) {
        //delete
        validateFileNameInFileManagerGridAndDelete(fileName);
        //upload
        uploadFile(filePath);
        reportPass("User successfully Uploaded the File Before Mapping.");
        return this;
    }


    public FileManagerPage validateFileNameInFileManagerGridAndDelete(String fileName) {
        waitForLoaderToDisappear();
        pause("low");
        int fileNameCount = getLocatorsCount("((//tbody[@role='presentation'])[3])//tr");
        for (int i = 1; i < fileNameCount; i++) {
            String actualText = getInnerText("((((//tbody[@role='presentation'])[3])//tr)[" + i + "])//td[1]").trim();
            if (actualText.equalsIgnoreCase(fileName)) {
                //if equals we are deleting it.
                //Action Link
                click("((((//tbody[@role='presentation'])[4])//td)[" + (i * 2) + "])//a", "Action Link", "Action Item");
                pause("low");
                //delete
                click(frameSpanTextLocator("Delete"), "Delete Link", "Action Item popup");
                //click Yes popup
                click(frameSpanTextLocator("Yes"), "Yes");
                pause("low");
                typeAndEnter(frameClientDetailsLocator("Please type the word \"DELETE\" to confirm deletion."), "DELETE", "Delete Field");
                pause("low");
                click(frameSpanTextLocator("Confirm Delete"), "Confirm Delete");
                break;
            }
        }

        return this;
    }

    public FileManagerPage clickOnCreateNewMapping() {
        waitForAppearance(createNewMapping, "Create New Mapping");
        click(createNewMapping, "Create New Mapping", "Link");
        return this;
    }

    //Selecting Existing DataMap
    public FileManagerPage selectExistingMapping(String dataMap) {
        pause("low");
        //Click Select mapping DropDown
        click(frameSelectMappingLocator("Select a mapping:"), "Select Mapping DropDown", "DropDown");
        pause("low");
        //click Existing Map From DropDown
        forceClick("//div[contains(@title,'" + dataMap + "')]", "Existing DataMap", "Drop down Value");
        pause("low");
        return this;
    }

    public FileManagerPage clickOnApplyMapping() {
        pause("low");
        click(frameSpanTextLocator("Apply Mapping"), "Apply Mapping Button");
        pause("low");
        return this;
    }

    public FileManagerPage verifyErrorMessage() {
        String ExpectedText = "Duplicate files are not permitted.";
        boolean AssertStatus = verifyPartialText("//ul[@class='error-list']//li", ExpectedText);
        Assert.assertTrue(AssertStatus);
        return this;
    }

    //Click The Create Button
    public FileManagerPage clickOnCreateBtn() {
        click(createNewDataMapBtn, "Create Button");
        return this;
    }

    public String fileListLocator(int index) {
        return "((((//tbody[@role='presentation'])[3])//tr))[" + index + "]//td[1]";
    }

    //Create The New Data Map
    public FileMappingPage createNewDataMap(String dataMapName, String dateFormat) {
        typeAndSpace(frameClientDetailsLocator("Data Map Name"), dataMapName, "Data Map Name");
        clickAndChoose(frameDateFormatLocator("Date Format", 2), frameDateFormatSelectDropDownLocator(dateFormat), dateFormat, "Date Format");
        clickOnCreateBtn();
        reportPass("User successfully created a new data map.");
        return new FileMappingPage();
    }

    //Validate The File Upload
    public FileManagerPage validateFileUpload(String fileName) {
        waitForLoaderToDisappear();
        pause("medium");
        pause("medium");
        waitForLoaderToDisappear();
        String fileLocator = "//a[text()='" + fileName + "']";
        waitForElementToDisappear("//p[text()='Uploading…']");
//        waitForElementToLoadWithHighTime(fileLocator);
        boolean verifyExactText = verifyExactText(fileLocator, fileName);
        Assert.assertTrue(verifyExactText);

        return this;
    }

    public ErrorQueuePage clickOnInvalidRowCountAndAmendErrors(String fileName) {
        pause("low");
        waitForLoaderToDisappear();
        //click Invalid Row Count for Given File Name
        if (isVisible("(//tbody[@role='presentation']//tr[td[1] = '" + fileName + "']//td[5]//span)[2]")) {
            click("(//tbody[@role='presentation']//tr[td[1] = '" + fileName + "']//td[5]//span)[2]", "Invalid Row", "Invalid RowLink");
            pause("low");
            waitForElementToLoad(frameSpanTextLocator("Amend Errors"));

            //click Amend Errors
            click(frameSpanTextLocator("Amend Errors"), "Amend Errors");
            pause("low");
        } else {
            //Else need to upload file with error rows
            validateFileNameInFileManagerGridAndDelete(fileName);
            uploadFile(fileName);
        }
        pause("low");
        reportPass("User successfully amended the errors.");
        return new ErrorQueuePage();
    }

    public FileManagerPage validateRowPromote(String fileName) {
        waitForLoaderToDisappear();
        pause("medium");
        pause("low");
        Assert.assertFalse(isVisible("(//tbody[@role='presentation']//tr[td[1] = '" + fileName + "']//td[5]//span)[2]"));
        reportPass("User successfully validated the promoted rows.");
        return this;
    }

//    public AddNewRowPage deleteDataMaps(String dataMapName) {
//        System.out.println("dataMapName----2----" + dataMapName);
//        clickOnViewDataMaps();
//        int maxAttempts = 200;
//        int attempts = 0;
//        pause("medium");
//        click("(//td[text()='MM/dd/yyyy'])[1]/preceding-sibling::td[3]", "");
//        String textToVisible = "//td[text()='" + dataMapName + "']";
//        while (!isVisible(textToVisible) && attempts < maxAttempts) {
//            pressDownArrow("(//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines'])[8]");
//            attempts++;
//        }
//        scrollToElement("//td[text()='" + dataMapName + "']");
//        pause("low");
//        pause("low");
//        forceClick("(//td[text()='" + dataMapName + "']//following-sibling::td[contains(@class,'action-edit-delete-column')]/a)[1]", "Delete Button", "");
//        pause("low");
//        click(deleteBtn, "Delete Button");
//        pause("low");
//        click(confirmDeleteBtn, "Confirm Delete");
//        pause("low");
//        typeAndSpace(frameClientDetailsLocator("Please type the word \"DELETE\" to confirm deletion."), "DELETE", "Delete Field");
//        click(frameSpanTextLocator("Confirm Delete"), "Confirm Delete button");
////        typeAndEnter(frameClientDetailsLocator("Please type the word \"DELETE\" to confirm deletion."), "DELETE", "Delete Field");
//        pause("low");
//
//        boolean verifyExactText = verifyExactText(frameSpanTextLocator("Success"), "Success");
//        Assert.assertTrue(verifyExactText);
//        click(closeIcon, "Close icon");
//        return new AddNewRowPage();
//    }


    public FileManagerPage getValidRowCount(String fileName) {
        //click Invalid Row Count for Given File Name
        if (isVisible("(//tbody[@role='presentation']//tr[td[1] = '" + fileName + "']//td[5]//span)[2]")) {
            click("(//tbody[@role='presentation']//tr[td[1] = '" + fileName + "']//td[5]//span)[2]", "Invalid Row", "Invalid RowLink");
            pause("low");
            String valid = getInnerText(frameRowCountText("Valid Rows"));
            //validRows.set(Long.parseLong(valid));
            validRows = Long.parseLong(valid);
            reportPass("Valid Rows Counts :" + validRows);
            System.out.println("---" + validRows);
        }
        return this;
    }

    public FileManagerPage getErrorRowCount(String fileName) {
        //click Invalid Row Count for Given File Name
        waitForLoaderToDisappear();
        if (isVisible("(//tbody[@role='presentation']//tr[td[1] = '" + fileName + "']//td[5]//span)[2]")) {
            click("(//tbody[@role='presentation']//tr[td[1] = '" + fileName + "']//td[5]//span)[2]", "Invalid Row", "Invalid RowLink");
            pause("low");
            String errorrows = getInnerText(frameRowCountText("Error Rows"));
            //validRows.set(Long.parseLong(valid));
            errorRows = Long.parseLong(errorrows);
            reportPass("Error Rows Count: " + errorRows);
            System.out.println("---" + errorRows);
        }else {
            click("(//tbody[@role='presentation']//tr[td[1] = '" + fileName + "']//td[5]//span)[1]","Invalid Row");
            pause("low");
            String errorrows = getInnerText(frameRowCountText("Error Rows"));
            //validRows.set(Long.parseLong(valid));
            errorRows = Long.parseLong(errorrows);
            reportPass("Error Rows Count: " + errorRows);
            System.out.println("---" + errorRows);
        }
        return this;
    }


    public FileMappingPage clickOnModifyInvalidMap() {
        pause("low");
        if (isVisible(frameSpanTextLocator("Modify Invalid Map"))) {
            click(frameSpanTextLocator("Modify Invalid Map"), "Modify Invalid Map Button");
            pause("low");
        } else if (isVisible(frameSpanTextLocator("Define Mapping"))) {

        }

        return new FileMappingPage();
    }


    public FileMappingPage clickOnDefineMapping() {
        waitForAppearance(frameSpanTextLocator("Define Mapping"), "Define Mapping Button");
        if (isVisible(frameSpanTextLocator("Define Mapping"))) {
            click(frameSpanTextLocator("Define Mapping"), "Define Mapping Button");
        } else {
            reportFail("Define Mapping is not Visible to Perform Click Action");
        }
        return new FileMappingPage();
    }


    public AddNewRowPage deleteDataMaps(String dataMapName) {
        System.out.println("dataMapName----2----" + dataMapName);
        // clickOnViewDataMaps();
        forceClick("(//table)[last()-1]//tr[1]/td[1]", "View data map", "Action Button");
        while (!isVisibleWithMinimumTime("//td[text()='" + dataMapName + "']")) {
            pressDownArrow("(//div[@class='dx-scrollable-container'])[4]");
        }

        forceClick("(//td[text()='" + dataMapName + "']//following-sibling::td[contains(@class,'action-edit-delete-column')]/a)[1]", "Action Button", "Matched Data Map");
        pause("low");
        click(deleteBtn, "Delete Button", "");
        pause("low");
        click(confirmDeleteBtn, "Confirm Delete");
        pause("low");
        typeAndSpace(frameClientDetailsLocator("Please type the word \"DELETE\" to confirm deletion."), "DELETE", "Delete Field");
        click(frameSpanTextLocator("Confirm Delete"), "Confirm Delete button");

        boolean verifyExactText = verifyExactText(frameSpanTextLocator("Success"), "Success");
        Assert.assertTrue(verifyExactText);
        click(closeIcon, "Close icon");
        return new AddNewRowPage();
    }

    public ErrorQueuePage clickOnOpenErrorQueue() {
        waitForElementToLoad(openErrorQueueBtn);
        click(openErrorQueueBtn, "Click Open Error Queue");
        return new ErrorQueuePage();
    }

    public FileManagerPage clickNotificationBadge() {
        waitForLoaderToDisappear();
        click("//div[@id='header_notifications']", "Notification");
        reportPass("Successfully clicked notification Badge");
        return this;
    }

    public FileManagerPage validateNotificationPage() {
        waitForLoaderToDisappear();
        if (isVisible("//h1[text()='Notifications']")) {
            reportPass("Successfully navigated to Notification page");
        } else {
            reportFailAndThrowException("failed to Navigate");
        }
        return new FileManagerPage();
    }

    public FileManagerPage getDuplicateRowsCount() {
        waitForLoaderToDisappear();
        String count = getInnerText(duplicateCount);
        String dCount = count.replaceAll("Duplicate Rows: ", "");
        notificationDuplicateRowCount = Long.parseLong(dCount);
        return new FileManagerPage();
    }

    public FileManagerPage getUploadDateInFileManager(String fileName){
        pause("medium");
        waitForLoaderToDisappear();
        String innertext=getInnerText("//tbody[@role='presentation']//tr[td[1] = '"+fileName+"']//td[3]//time");
        String[] parts=innertext.split(", ",2);
        timeInFileManager=parts[1];
        System.out.println(timeInFileManager);
//        if(preferenceTimeFormat.equals("24-hour")){
//            // Split the time into hours and minutes
//            String[] timeParts = timeInFileManager.split(":");
//            int hour24 = Integer.parseInt(timeParts[0]);
//            String minutes = timeParts[1];
//            String seconds = timeParts[2];
//
//            // Determine AM/PM
//            String amPm = hour24 >= 12 ? "PM" : "AM";
//
//            // Convert hour from 24-hour format to 12-hour format
//            int hour12 = hour24 % 12;
//            if (hour12 == 0) {
//                hour12 = 12; // Midnight or Noon
//            }

            // Format hours and minutes into 12-hour time
//            timeInFileManager = String.format("%02d:%s %s", hour12, minutes, seconds, amPm);
//            System.out.println("time in 24-hour converted into 12-hour "+timeInFileManager);
//            reportPass("Successfully converted 24-hour format time to 12-hour format");
//        }
        reportPass("Captured fileUpload time from File Manager: "+timeInFileManager);
        return this;
    }

//    public FileManagerPage validateLocalTimezonevalue(){
//
//        System.out.println("before time: "+currentTimeBeforeFileUpload);
//        System.out.println("upload time"+timeInFileManager);
//        System.out.println("After time: "+currentTimeAfterFileUpload);
//        return this;
//    }

    public FileManagerPage validateBulkEditRowCount() {
        System.out.println("final count: " + rowCount);
        System.out.println("error rows: " + errorRows);
        if (rowCount == errorRows) {
            reportPass("Successfully edited and promoted the row");
        } else {
            reportFail("failed to validate. please check whether row has been promoted or not");
        }
        return this;
    }

    public FileManagerPage getFileManagerGridColumnNames(){
        pause("medium");
        DatamanagerColumn.clear();
        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");
        for (int i = 1; i <=headerCount; i++) {
            String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
            String headerText = getInnerText(headerElement);
            DatamanagerColumn.add(headerText);
        }
        return this;
    }

    public FileManagerPage validateFileManagerGridColumn(){
        pause("low");
        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");
        for (String s : chooserColumn) {
            for (String string : DatamanagerColumn) {
                if (string.equals(s)) {
                    boolean result = true;
                    reportPass("Column: "+s+" - found in Error Queue Grid");
                    System.out.println("Column: "+s+" - found in Error Queue Grid");
                    Assert.assertTrue(result);
                    break;
                }
            }
        }
        return this;
    }

    public FileManagerPage clickOnShowColumnChooser(){
        pause("medium");
        waitForElementToLoad(frameSpanExactTextLocator("Show Column Chooser"));
        waitForLoaderToDisappear();
        click(frameSpanExactTextLocator("Show Column Chooser"),"Show Column Chooser");
        return this;
    }

    public FileManagerPage clickOnCloseColumnChooser(){
        pause("low");
        click("//i[contains(@class,'dx-icon-close')]","Close icon");
        pause("low");
        return this;
    }

    public FileManagerPage dragAndDropColumnInColumnChooser(List<String> columnName){
        pause("low");
        for (int index = 0; index < columnName.size(); index++) {
            dragAndDrop("//div[text()='" + columnName.get(index) + "']", frameDivTextLocator("Column Chooser"));
            chooserColumn.add(columnName.get(index));
            reportPass("Successfully dropped column: "+columnName.get(index)+" in show column chooser");
            System.out.println("successfully dropped in column chooser");
        }

        return this;
    }

    public FileManagerPage valiateColumnsAgainstColumnChooser(){
        System.out.println("DataMnager list size: "+DatamanagerColumn);
        System.out.println("chooser column list size: "+chooserColumn);

        for (String s : chooserColumn) {
            for (String string : DatamanagerColumn) {
                if (string.equals(s)) {
                    boolean result = true;
                    reportFail("Failed to move column: " +s+" to column chooser");
                    System.out.println("Failed to move column: " +s+" to column chooser");
                    Assert.assertFalse(result);
                    break;
                }
            }
        }
        reportPass("Successfully validated. Columns \"moved to Show Column Chooser\" are not visible in Error Queue Grid.");
        return this;
    }

    public FileManagerPage clickOnRestoreAllColumns(){
        pause("low");
        click(frameSpanExactTextLocator("Restore All Columns"),"Restore all columns button");
        reportPass("Columns restored back to Error Queue Grid");

        pause("low");
        return this;
    }

}


