package com.westminster.testcases.admin;

import java.util.Arrays;

import com.config.ConfigurationManager;
import com.ui.utilities.TestGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.dynamic.FakerDataFactory;
import com.listener.TestListener;
import com.page.base.DashboardPage;
import com.page.base.WestMinsterPageHook;

@Listeners(TestListener.class)
public class TC_DataManager extends WestMinsterPageHook {
    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User"})
    public void setReportValues() {
        testcaseName = "TC_5577 " + xmlUserLevel;
        testDescription = "Add New Row And validate";
        authors = "Automation Team";
        category = "Add New Row";
    }

    // Adding the new row in the data manager
    @Test(priority = 3, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User"})
    public void AddNewRow() {
        clientCompanyName.set(FakerDataFactory.getBrokerNameWOTimeStamp());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnDataManager()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnAddNewRowBtn()
                .validateAddNewRowPage()
                .addNewRowInformation(clientCompanyName.get(), FakerDataFactory.getPastDate(), FakerDataFactory.get12DigitsBankAccountNumber(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(),
                        FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(),
                        FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFutureDate(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric())
                .clickOnAddBtn()
                .clickOnApplyBtn()
                .validateAddedRowInformation(clientCompanyName.get(), "Broker Name")
                .deleteAddedRow(clientCompanyName.get(), "Broker Name");

        System.out.println("TC-----------03");
    }

    //    Editing the new row in the Data manager
    @Test(priority = 4, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User"})
    public void editRow() {
        ruleName.set(FakerDataFactory.getCompanyName());
        reportName.set(FakerDataFactory.getReportsName());
        titleone.set(FakerDataFactory.getTitle());
        titleTwo.set(FakerDataFactory.getTitle());
        testcaseName = "TC_4937 " + xmlUserLevel;
        testDescription = "Edit a row and validate";
        authors = "Automation Team";
        category = "Edit Row";
        startTestCase();
        setNode();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnDataManager()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnActionsBtn()
                .clickOnEditBtn()
                .editInformation(Arrays.asList(ruleName.get(), reportName.get(), titleone.get()),
                        Arrays.asList("Broker  Name:", "Account  Name:", "Asset  Class:"))
                .clickOnApplyBtn()
                .searchValueToValidate(ruleName.get(), "Broker Name")
                .validateEditedRowInformation((ruleName.get()), ("Broker Name"))
                .deleteAddedRow((ruleName.get()), "Broker Name");
        System.out.println("TC-----------04");
    }

    @Test(priority = 5, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User"})
    public void addNewColumnInEditDataSchema() {
        testcaseName = "TC_6587 " + xmlUserLevel;
        testDescription = "Add new System Column";
        authors = "Automation Team";
        category = "Add new column schema";
        startTestCase();
        setNode();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnEditDataSchema()
                .validateEditDataSchemaColumnsPage()
                .clickOnAddNewColumn()
                .validateAddNewColumnPage()
                .typeInformationInAddNewColumnsAndValidate(true, true, true)
                .deleteDataSchema();
        System.out.println("TC-----------05");
    }
}

