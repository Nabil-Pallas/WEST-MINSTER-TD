package com.pages.Admin.FileManagerPages;

import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileMappingPage extends FileManagerPage {

    private String fileMappingText = "//h1[text()='File Mapping']";
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

        //int systemColumnCount = getLocatorsCount("((//tbody[@role='presentation'])[8])/tr");
        int systemColumnCount = sysColumns.size();

       /* for (String sysColumn : sysColumns){
            click(frameSpanTextLocator(sysColumn), "System Column :" + sysColumn, "System Column");
            pause("low");
            if(fileColumnCount ==1){
                fileColumnCount = getLocatorsCount("((//tbody[@role='presentation'])[6])/tr");
            }
            for (int i = 1; i < fileColumnCount; i++) {
                String fileElement = "(((// tbody[@role='presentation'])[6])/ tr)[" + i + "]";
                String fileText = getInnerText(fileElement);
                if(sysColumn.equalsIgnoreCase(fileText)){
                    click("(//tbody[@role='presentation']//tr[td='"+fileText+"'])","File Column :"+fileText,"File Column");
                    pause("low");
                    //verifying MapColumns Enabled
                    Assert.assertTrue(isEnabled(mapColumnsBtn,"Map Column"));
                    pause("low");
                    click(mapColumnsBtn, "Map Columns");
                    pause("low");
                    break;
                }
            }
        }*/
        Set<String> fileColumn = new HashSet<>();
        for (int i = 1; i <= fileColumnCount; i++) {
            fileColumn.add(getInnerText("(((// tbody[@role='presentation'])[6])/ tr)[" + i + "]"));
        }

        for (String sysColumn : sysColumns) {
            click(frameSpanTextLocator(sysColumn), "System Column :" + sysColumn, "System Column");
            pause("low");
         /*   if(fileColumnCount ==1){
                fileColumnCount = getLocatorsCount("((//tbody[@role='presentation'])[6])/tr");
            }*/

            if (fileColumn.contains(sysColumn)) {
                click("(//tbody[@role='presentation']//tr[td='" + sysColumn + "'])", "File Column :" + sysColumn, "File Column");
                pause("low");
                //verifying MapColumns Enabled
                Assert.assertTrue(isEnabled(mapColumnsBtn, "Map Column"));
                pause("low");
                click(mapColumnsBtn, "Map Columns");
                pause("low");
            }

        }



      /*  for (int i = 1; i < systemColumnCount; i++) {
            String systemElement = "(((// tbody[@role='presentation'])[8])/ tr)[" + i + "]";
            String systemText = getInnerText(systemElement).toLowerCase();

            for (int j = 1; j < fileColumnCount; j++) {
                String fileElement = "(((// tbody[@role='presentation'])[6])/ tr)[" + i + "]";
                String fileText = getInnerText(fileElement).toLowerCase();

                if (fileText.equals(systemText) || systemText.contains(fileText)) {
                    click(fileElement, "File Column :" + fileText, "Column Value");
                    click(systemElement, "System Column :" + systemText, "Column Value");
                    //verifying MapColumns Enabled
                    *//*Assert.assertTrue(isEnabled(mapColumnsBtn,"Map Column"));*//*
                    click(mapColumnsBtn, "Map Columns");
                    break;
                } else {
                    System.out.println("ELSE");
                }
            }
        }
*/
        reportPass("User successfully mapped file and system columns.");
        return this;
    }

    public FileManagerPage clickOnFinishMapping() {
        click(finishMapping, "Finish Mapping");
        waitForAppearance("//div[text()='Confirm Data Mapping']","Confirm Data Mapping");
        click(confirmAndUpload, "Confirm & Upload");
        pause("medium");
        pause("medium");
        pause("medium");
        pause("medium");
        return new FileManagerPage();
    }


}
