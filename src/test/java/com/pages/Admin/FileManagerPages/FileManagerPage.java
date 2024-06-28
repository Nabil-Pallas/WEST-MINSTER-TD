package com.pages.Admin.FileManagerPages;

import com.page.base.DashboardPage;
import com.pages.Admin.AdminPage;
import org.testng.Assert;

import java.util.stream.Stream;


public class FileManagerPage extends AdminPage {

    //Locator For File Manager Option
    private String fileManagerText = "//h1[text()='File Manager']";

    //Locator For Create New Mapping Button
    private String createNewMapping = "//span[contains(text(),'or Create New Mapping')]";

    //Locator For Create Button
    private String createNewDataMapBtn = "//span[text()='Create']";


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
    public FileManagerPage fileUpload(String fileName) {
        //delete
        validateFileNameInFileManagerGridAndDelete(fileName);
        //upload
        uploadFile(fileName);
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
                click("((((//tbody[@role='presentation'])[4])//td)[" + (i + 1) + "])//a", "Action Link", "Action Item");
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
        waitForAppearance(createNewMapping);
        click(createNewMapping, "Create New Mapping", "Link");
        return this;
    }

    //Click The Create Button
    public FileManagerPage clickOnCreateBtn() {
        click(createNewDataMapBtn, "Create Button");
        return this;
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
	public DashboardPage validateFileUpload(String fileName)
	{
		waitForLoaderToDisappear();
		pause("medium");
		waitForLoaderToDisappear();
		String actualElement = "((((//tbody[@role='presentation'])[3])//tr)[1])//td[1]";
		String actualText = getInnerText("((((//tbody[@role='presentation'])[3])//tr)[1])//td[1]");
		boolean verifyExactText = verifyExactText(actualElement, fileName);
		Assert.assertTrue(verifyExactText);
		return new DashboardPage();
	}

    public ErrorQueuePage clickOnInvalidRowCountAndAmendErrors(String fileName) {

        //click Invalid Row Count for Given File Name
        if (isVisible("(//tbody[@role='presentation']//tr[td[1] = '" + fileName + "']//td[5]//span)[2]")) {
            click("(//tbody[@role='presentation']//tr[td[1] = '" + fileName + "']//td[5]//span)[2]", "Invalid Row", "Invalid RowLink");
            pause("low");
            waitForElementToLoad(frameSpanTextLocator("Amend Errors"));
            //Amend Errors
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


}


