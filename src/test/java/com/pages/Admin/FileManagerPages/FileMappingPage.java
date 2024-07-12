package com.pages.Admin.FileManagerPages;

import com.pages.Admin.DataManagerPages.AddNewColumnPage;
import org.testng.Assert;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileMappingPage extends FileManagerPage {

    private String fileMappingText = "//h1[text()='File Mapping']";
    private String addNewSystemColumnsText = "//div[text()='Add New system Column(s)']";
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
            int i = 2; // Initialize i outside the if block, but inside the loop to reset it for each sysColumn
            if (fileColumn.contains(sysColumn)) {
                click("(//tbody[@role='presentation']//tr[td='" + sysColumn + "'])", "File Column :" + sysColumn, "File Column");
                pause("low");
                // Verifying MapColumns Enabled
                Assert.assertTrue(isEnabled(mapColumnsBtn, "Map Column"));
                pause("low");
                click(mapColumnsBtn, "Map Columns");
                pause("low");


//                String element = "(//div[@class='match-names-span'])[" + i + "]";
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

}
