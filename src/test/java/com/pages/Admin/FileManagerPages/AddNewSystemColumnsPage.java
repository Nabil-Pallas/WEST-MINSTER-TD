package com.pages.Admin.FileManagerPages;

import com.beust.ah.A;
import com.data.dynamic.FakerDataFactory;
import com.pages.Admin.DataManagerPages.AddNewColumnPage;
import com.pages.Admin.DataManagerPages.DataManagerPage;
import org.testng.Assert;

import java.util.*;

public class AddNewSystemColumnsPage extends FileManagerPage {

    private String addNewSystemColumnsText = "//div[text()='Add New system Column(s)']";
    protected String applyBtn = "//span[text()='Apply']";
    private String mapColumnsBtn = "//span[text()='Map Columns']";
    private String systemColumnsText = "//span[@class='ml-half']//span[@title]";
    private String newColumnBtn = "//i[@class='dx-icon dx-icon-edit-button-addrow']";
    public static List<String> addedColumnss;



    public AddNewSystemColumnsPage validateAddNewSystemColumnsPage() {
        waitForElementToLoad(addNewSystemColumnsText);
        if (isVisible(addNewSystemColumnsText, "Add New system Column(s)")) {
            reportPass("User successfully reached the Add New system Column(s) page");
        } else {
            reportFailAndThrowException("Add New system Column(s) Page is not loaded.");
        }
        return new AddNewSystemColumnsPage();
    }

    public AddNewSystemColumnsPage typeFieldName(String fieldName) {
        pause("low");
        click("//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines dx-row-inserted']//td[1]", "Field Name");
        typeWithType("//div[@data-dx_placeholder='Field Name here…']//..//input", fieldName, "Field Name");
        pause("low");
        return new AddNewSystemColumnsPage();
    }

    public AddNewSystemColumnsPage selectFieldDataType(int i) {
        int index = i + 1;
        click("//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines dx-row-inserted']//td[2]", "selectFieldDataType");
        clickAndChoose("(//div[@aria-label='Select'])[last()]", frameClientDetailsLocator(index), "fieldDataType", "Field Data Type");
        pause("medium");
        return this;
    }

    public AddNewSystemColumnsPage readOnlyCheckbox(boolean readOnlyCheckbox) {
        if (readOnlyCheckbox) {
            click("(//div[@class='dx-widget dx-checkbox dx-checkbox-indeterminate dx-datagrid-checkbox-size'])[3]", "read-Only Checkbox");
            pause("low");
        }
        return this;
    }

    //Click On Apply Button
    public AddNewSystemColumnsPage clickOnApplyBtn() {
        pause("medium");
        click(applyBtn, "Apply Button");
        pause("medium");
        reportPass("User successfully clicked the Apply button.");
        return new AddNewSystemColumnsPage();
    }

    //Click On New Column
    public AddNewSystemColumnsPage clickOnNewColumn() {
        pause("medium");
        click(newColumnBtn, "New Column");
        pause("medium");
        reportPass("User successfully clicked the Apply button.");
        return new AddNewSystemColumnsPage();
    }


    public FileMappingPage typeInformationInAddNewSystemColumns() {
        clientCompanyName.set(FakerDataFactory.getFieldNameWithTimeStamp(""));

        System.out.println("clientCompanyName--------" + clientCompanyName.get());
        pause("medium");
        pause("medium");
        typeFieldName(clientCompanyName.get());
        pause("medium");
        pause("medium");
        selectFieldDataType(1);
        pause("medium");
        pause("medium");
        pause("medium");
        pause("medium");

        clickOnApplyBtn();

        //Verifying Map column button disable
        Assert.assertTrue(isDisabled(mapColumnsBtn, "Map Column"));
        pause("medium");

        //Mapping the Values
        // File Column Count
        click(frameSpanTextLocator(clientCompanyName.get()), "System Column :" + clientCompanyName.get(), "System Column");

        pause("low");

        click("(//tbody[@role='presentation']//tr[td='Broker Code'])", "File Column :Broker Code", "File Column");

        pause("low");
        //verifying MapColumns Enabled
        Assert.assertTrue(isEnabled(mapColumnsBtn, "Map Column"));
        pause("low");

        click(mapColumnsBtn, "Map Columns");

        pause("low");

        reportPass("User successfully mapped file and system columns.");

        return new FileMappingPage();
    }


    public FileMappingPage typeInformationInAddNewSystemColumns(int noOfColumn) {
        newSystemColumnName.set(FakerDataFactory.getFieldNameWithTimeStamp(""));
        List<String> addedColumns = new ArrayList<>();
        for (int i = 1; i <= noOfColumn; i++) {
            pause("medium");
            waitForAppearance(frameSpanExactTextLocator("Delete", i), "Delete");
            pause("medium");
            if (isVisible(frameSpanExactTextLocator("Delete", i))) {
                System.out.println("Field Name --------" + i);
                String columnName = newSystemColumnName.get() + "_" + i;
                addedColumns.add(columnName);
                typeFieldName(columnName);
                pause("medium");
                selectFieldDataType(new Random().nextInt(3));
                pause("medium");
                if (i < noOfColumn) { // Change the condition to < noOfColumn - 1
                    clickOnNewColumn();
                    System.out.println("New column Clicked --- " + i);
                    pause("medium");
                }
            }
        }
        pause("medium");
        clickOnApplyBtn();
        pause("low");
        reportPass("User successfully Added " + noOfColumn + " New System Columns");

        return new FileMappingPage();
    }

}
