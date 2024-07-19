package com.pages.Admin.FileManagerPages;

import com.pages.Admin.AdminPage;
import com.pages.Admin.DataManagerPages.AddNewRowPage;
import org.testng.Assert;


public class FileManagerPage extends AdminPage {

    //Locator For File Manager Option
    private String fileManagerText = "//h1[text()='File Manager']";

    //Locator For Create New Mapping Button
    private String createNewMapping = "//span[contains(text(),'or Create New Mapping')]";

    //Locator For Create Button
    private String createNewDataMapBtn = "//span[text()='Create']";

    private static int size;
    private static int size2;


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
        reportPass("User successfully Uploaded the File.");
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
        click("//div[contains(@title,'" + dataMap + "')]", "Existing DataMap");
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
        String listLocator = "((((//tbody[@role='presentation'])[3])//tr))//td[1]";
        int filesCount = getLocatorsCount(listLocator);
        for (int i = 1; i <= filesCount; i++) {
            if (fileListLocator(i).equals(fileName)){
                boolean verifyExactText = verifyExactText(fileListLocator(i), fileName);
                Assert.assertTrue(verifyExactText);
            }
        }
        
        return this;
    }

    public ErrorQueuePage clickOnInvalidRowCountAndAmendErrors(String fileName) {

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
        Assert.assertFalse(isVisible("(//tbody[@role='presentation']//tr[td[1] = '" + fileName + "']//td[5]//span)[2]"));
        reportPass("User successfully validated the promoted rows.");
        return this;
    }

    public AddNewRowPage deleteDataMaps(String dataMapName) {
        System.out.println("dataMapName----2----" + dataMapName);
        clickOnViewDataMaps();
        int maxAttempts = 200;
        int attempts = 0;
        pause("medium");
        click("(//td[text()='MM/dd/yyyy'])[1]/preceding-sibling::td[3]", "");
        String textToVisible = "//td[text()='" + dataMapName + "']";
        while (!isVisible(textToVisible) && attempts < maxAttempts) {
            pressDownArrow("(//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines'])[8]");
            attempts++;
        }
        scrollToElement("//td[text()='" + dataMapName + "']");
        pause("low");
        pause("low");
        forceClick("(//td[text()='" + dataMapName + "']//following-sibling::td[contains(@class,'action-edit-delete-column')]/a)[1]", "Delete Button", "");
        pause("low");
        click(deleteBtn, "Delete Button");
        pause("low");
        click(confirmDeleteBtn, "Confirm Delete");
        pause("low");
        typeAndSpace(frameClientDetailsLocator("Please type the word \"DELETE\" to confirm deletion."), "DELETE", "Delete Field");
        click(frameSpanTextLocator("Confirm Delete"), "Confirm Delete button");
//        typeAndEnter(frameClientDetailsLocator("Please type the word \"DELETE\" to confirm deletion."), "DELETE", "Delete Field");
        pause("low");

        boolean verifyExactText = verifyExactText(frameSpanTextLocator("Success"), "Success");
        Assert.assertTrue(verifyExactText);
        click(closeIcon, "Close icon");
        return new AddNewRowPage();
    }


    public FileManagerPage getValidRowCount(String fileName) {
        //click Invalid Row Count for Given File Name
        if (isVisible("(//tbody[@role='presentation']//tr[td[1] = '" + fileName + "']//td[5]//span)[2]")) {
            click("(//tbody[@role='presentation']//tr[td[1] = '" + fileName + "']//td[5]//span)[2]", "Invalid Row", "Invalid RowLink");
            pause("low");
            String valid = getInnerText(frameRowCountText("Valid Rows"));
            //validRows.set(Long.parseLong(valid));
            validRows = Long.parseLong(valid);
            System.out.println("---" + validRows);
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

}


