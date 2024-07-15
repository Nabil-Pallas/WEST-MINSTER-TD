package com.pages.Admin.FileManagerPages;

import com.microsoft.playwright.ElementHandle;
import com.pages.Admin.DataManagerPages.AddNewColumnPage;
import org.testng.Assert;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileMappingPage extends FileManagerPage {

    private String fileMappingText = "//h1[text()='File Mapping']";
    private String systemColumnsText = "//span[@class='ml-half']//span[@title]";
    private String mapColumnsBtn = "//span[text()='Map Columns']";
    private String finishMapping = "//span[text()='Finish mapping']";
    private String confirmAndUpload = "//span[text()='Confirm & Upload']";


    public FileMappingPage validateFileMappingPage() {
        waitForElementToLoad(fileMappingText);
        if (isVisible(fileMappingText, "File Mapping Text")) {
            reportPass("User successfully reached the correct File Mapping page");
        } else {
            reportFailAndThrowException("File Mapping Page is not loaded.");
        }
        return this;
    }

    public FileMappingPage mappingFileAndSystemColumns(List<String> sysColumns) {

        //Verifying Map column button disable
        Assert.assertTrue(isDisabled(mapColumnsBtn, "Map Column"));

        pause("medium");
        //Mapping the Values
        // File Column Count
        int fileColumnCount = getLocatorsCount("((//tbody[@role='presentation'])[6])/tr");


        Set<String> fileColumn = new HashSet<>();
        for (int i = 1; i <= fileColumnCount; i++) {
            fileColumn.add(getInnerText("(((// tbody[@role='presentation'])[6])/ tr)[" + i + "]"));
        }

        for (String sysColumn : sysColumns) {
            click(frameSpanTextLocator(sysColumn), "System Column :" + sysColumn, "System Column");
            pause("low");
            // Initialize i outside the if block, but inside the loop to reset it for each sysColumn
            if (fileColumn.contains(sysColumn)) {
                click("(//tbody[@role='presentation']//tr[td='" + sysColumn + "'])", "File Column :" + sysColumn, "File Column");
                pause("low");
                // Verifying MapColumns Enabled
                Assert.assertTrue(isEnabled(mapColumnsBtn, "Map Column"));
                pause("low");
                click(mapColumnsBtn, "Map Columns");
                pause("low");


//                String element = "(//div[@class='match-names-span'])[" + i + "]";
//                pause("low");
////                String elementWithTrim = element.replaceAll("\\s+", "");
//                boolean isPresent = isVisible(element);
//                System.out.println("isPresent-----" + isPresent);
//                Assert.assertTrue(isPresent);
//                i++; // Increment i by 1 for each iteration
//                System.out.println("i---------" + i);


            }
        }

        reportPass("User successfully mapped file and system columns.");
        return this;
    }

    public FileMappingPage mappingFileAndSystemColumns(List<String> sysColumns, List<String> file) {

        //Verifying Map column button disable
        Assert.assertTrue(isDisabled(mapColumnsBtn, "Map Column"));

        pause("medium");
        //Mapping the Values
        // File Column Count
        int fileColumnCount = getLocatorsCount("((//tbody[@role='presentation'])[6])/tr");

        Set<String> fileColumn = new HashSet<>();
        for (int i = 1; i <= fileColumnCount; i++) {
            fileColumn.add(getInnerText("(((// tbody[@role='presentation'])[6])/ tr)[" + i + "]"));
        }

        for (int index = 0; index < sysColumns.size(); index++) {
            String sysColumn = sysColumns.get(index);
            String fileColumnValue = file.get(index);

            // Click on the system column
            click(frameSpanTextLocator(sysColumn), "System Column: " + sysColumn, "System Column");
            pause("low");

            // Click on the corresponding file column
            click("(//tbody[@role='presentation']//tr[td='" + fileColumnValue + "'])", "File Column: " + fileColumnValue, "File Column");
            pause("low");

            // Verifying MapColumns Enabled
            Assert.assertTrue(isEnabled(mapColumnsBtn, "Map Column"));
            pause("low");

            // Click on Map Columns button
            click(mapColumnsBtn, "Map Columns");
            pause("low");

            // Verify if the element is present
            String element = "(//div[@class='match-names-span'])[" + (index + 2) + "]";
            boolean isPresent = isVisible(element);
            Assert.assertTrue(isPresent);
            reportStep(sysColumn + " is mapped with " + fileColumnValue, "pass");

        }
        reportPass("User successfully mapped file and system columns.");
        return this;
    }

    public FileManagerPage clickOnFinishMapping() {
        click(finishMapping, "Finish Mapping");
        waitForAppearance("//div[text()='Confirm Data Mapping']", "Confirm Data Mapping");
        click(confirmAndUpload, "Confirm & Upload");
        System.out.println("-----file name-" + fileName.get());
        if (fileName.get().contains("500kFile.csv")) {
            pause("high");
            pause("high");
            pause("high");
        } else {
            pause("medium");
        }
        return new FileManagerPage();
    }

    public AddNewSystemColumnsPage clickOnAddNewSystemColumns() {
        click(frameSpanTextLocator("Add new system columns"), "Add new system columns");
        return new AddNewSystemColumnsPage();
    }


    // Method to verify added columns in the system column name
    public FileManagerPage verifyAddedColumnsInSystemColumnName(int noOfSysColumn) {
        pause("low");
        List<ElementHandle> systemColumnNames = getListElements(systemColumnsText);// Replace with the actual selector

        // Iterate over the elements and extract inner text
        int count = 0;
        for (ElementHandle element : systemColumnNames) {
            String innerText = element.innerText();
            count++;
        }

        //Getting the locator count
        System.out.println("Total System Column Name count: " + count);
        int systemColumnCount = getLocatorsCount(frameSpanTextLocator(newSystemColumnName.get()));
        System.out.println("Newly Added System Column Name Count: " + systemColumnCount);

        Assert.assertEquals(systemColumnCount, noOfSysColumn);
        if (systemColumnCount == noOfSysColumn) {
            reportPass("The number of added columns are displayed in System column Name for mapping ");
        } else {
            reportFail("The number of added columns does not match.");
        }
        return this;
    }

}
