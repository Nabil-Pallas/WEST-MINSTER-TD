package com.westminster.testcases.admin;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.dynamic.FakerDataFactory;
import com.listener.TestListener;
import com.page.base.DashboardPage;
import com.page.base.WestMinsterPageHook;
import com.ui.utilities.TestGroups;

@Listeners(TestListener.class)
public class TC_DataManager extends WestMinsterPageHook {
    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User",TestGroups.Type.SANITY, TestGroups.Type.REGRESSION})
    public void setReportValues() {
        testcaseName = "TC_5577 " + xmlUserLevel;
        testDescription = "Add New Row And validate";
        authors = "Automation Team";
        category = "Add New Row";
    }

    // Adding the new row in the data manager
    @Test(priority = 3, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY})
    public void addNewRow() {
        clientCompanyName.set(FakerDataFactory.getNameWithTimeStamp(""));
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
                .validateAddedRowInformation(clientCompanyName.get(), "Broker Name");
//                .clickOnActionsBtn();
//                .clickOnDeleteBtn();
//                .deleteAddedRow(clientCompanyName.get(), "Broker Name");
        System.out.println("TC-----------03");
    }

    // Editing the new row in the Data manager
    @Test(priority = 4, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY})
    public void editRow() {
        ruleName.set(FakerDataFactory.getNameWithTimeStamp(""));
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
                .clickOnActionsBtn()
                .clickOnDeleteBtn();
        // .deleteAddedRow((ruleName.get()), "Broker Name");
        System.out.println("TC-----------04");
    }

    @Test(priority = 5, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY})
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

    //Manoj
    //Ensure the "Add" button is disabled when there is an error in the required field, Trade Date.
    @Test(priority = 3, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void verifyAddButtonDisabledOnTradeDateError() {
        testcaseName = "TC_RE_5731 " + xmlUserLevel;
        testDescription = "Verify Add Button Disabled On TradeDate Error";
        authors = "Automation Team";
        category = "Verify Add Button Disabled On TradeDate Error";
        startTestCase();
        setNode();
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
                .addNewRowInformation(clientCompanyName.get(), FakerDataFactory.getFutureDate(), FakerDataFactory.get12DigitsBankAccountNumber(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(),
                        FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(),
                        FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getPastDate(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric())
                .validateTradeDateErrorMsg("Custom Validation: Trade Date must be before “Settlement Date.”")
                .validateAddBtnIsDisable();
        System.out.println("TC-----------TC_RE_5731");
    }

    //Manoj
    // Verify that the "Add" button disables when the required field "Broker Name" has an error.
    @Test(priority = 3, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void verifyAddButtonDisabledOnBrokerNameError() {
        testcaseName = "TC_RE_5945 " + xmlUserLevel;
        testDescription = "Add New Row Broker Name Error Validation";
        authors = "Automation Team";
        category = "Add New Row Broker Name Error Validation";
        startTestCase();
        setNode();
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
                .addNewRowInformation("", FakerDataFactory.getPastDate(), FakerDataFactory.get12DigitsBankAccountNumber(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(),
                        FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(),
                        FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFutureDate(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric())
                .validateBrokerNameErrorMsg("Broker Name is required.")
                .validateAddBtnIsDisable();
        System.out.println("TC-----------TC_RE_5945");
    }

    //Manoj
    // Verify dropdowns enable correctly for available lists when adding ad hoc rows in Data Manager.
    @Test(priority = 3, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void verifyDropdownsForAdHocRows() {
        testcaseName = "TC_RE_5429 " + xmlUserLevel;
        testDescription = "Verify Drop downs For AdHoc Rows";
        authors = "Automation Team";
        category = "Verify Drop downs For AdHoc Rows";
        startTestCase();
        setNode();
        newListName.set(FakerDataFactory.getListName());
        listItems.set(FakerDataFactory.getListOfItems(3));
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnListManager()
                .validateListManagerPage()
                .clickOnAddNewListBtn()
                .validateAddListItemPage()
                .typeListTitle(newListName.get())
                .typeListItems(listItems.get())
                .clickOnSaveListBtn()
                .validateListNameAndItem(newListName.get(), listItems.get())
                .clickOnDataManager()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnEditDataSchema()
                .validateEditDataSchemaColumnsPage()
                .clickOnRelevantFieldNameActionIcon("Broker Name")
                .clickOnAddValidation()
                .validateAddFieldValidationParametersPage()
                .addFieldValidationParameters(newListName.get())
                .clickOnAddNewRowBtn()
                .validateAddNewRowPage()
                .addNewRow(listItems.get().get(1), FakerDataFactory.getPastDate(), FakerDataFactory.getFutureDate())
                .clickOnAddBtn()
                .clickOnApplyBtn()
                .clickOnDataManager()
                .clickOnDataManager()
                .validateAddedRowInformation(listItems.get().get(1), "Broker Name")
//                .clickOnActionsBtn()
//                .clickOnDeleteBtn()
                .clickOnSingleCheckBox()
                .clickOnDeleteRowBtn()
                .clickOnListManager()
                .validateListManagerPage()
                .deleteListItem(newListName.get());
        System.out.println("TC-----------TC_RE_5429");
    }

    //Manoj
    // Ensure the Edit Trade Details page has no blank spaces under "Edit Trade Details" and "Change Log" headings when editing a row.
    @Test(priority = 3, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void verifyNoBlankSpacesOnEditTradeDetailsPage() {
        testcaseName = "TC_RE_5235 " + xmlUserLevel;
        testDescription = "Verify No Blank Spaces On Edit TradeDetails";
        authors = "Automation Team";
        category = "Verify No Blank Spaces On Edit TradeDetails";
        startTestCase();
        setNode();
        clientCompanyName.set(FakerDataFactory.getBrokerNameWOTimeStamp());
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
                .editRowInformation(clientCompanyName.get(), FakerDataFactory.getPastDate(), FakerDataFactory.get12DigitsBankAccountNumber(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(),
                        FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(),
                        FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFutureDate(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric())
                .validateChangeLog(Arrays.asList("Broker Name", "Trade Date", "Settlement Date"));

        System.out.println("TC-----------TC_RE_5235");
    }

    // Sanjay Kumar
    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void checkApplyAndCancelBtnForEditAndBulkedit() {
        testcaseName = "TC_RE_4622 " + xmlUserLevel;
        testDescription = "Adding Adhoc Rows Data Manager";
        authors = "Automation Team";
        category = "Adding Adhoc Rows Data Manager";
        startTestCase();
        setNode();
        ruleName.set(FakerDataFactory.getCompanyName());
        reportName.set(FakerDataFactory.getReportsName());
        titleone.set(FakerDataFactory.getTitle());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnDataManager()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnActionsBtn()
                .clickOnEditBtn()
                .editInformation(Arrays.asList(ruleName.get(), reportName.get(), titleone.get()),
                        Arrays.asList("Broker  Name:", "Account  Name:", "Asset  Class:"))
                .clickOnApplyBtn()
                .clickOnActionsBtn()
                .clickOnEditBtn()
                .clickOnCancelBtn()
                .clickOnSelectAll()
                .clickOnBulkEditBtn()
                .editInformation(Arrays.asList(ruleName.get(), reportName.get(), titleone.get()),
                        Arrays.asList("Broker  Name:", "Account  Name:", "Asset  Class:"))
                .clickOnApplyBtn()
                .clickOnSelectAll()
                .clickOnBulkEditBtn()
                .clickOnCancelBtn();
        System.out.println("TC-----------TC_RE_4622");
    }

    //Manoj
    //Verify if more than 10 rows can be added at once using the 'Add New Row' option in Data Manager.
    @Test(priority = 3, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void addMoreThan10Rows() {
        testcaseName = "TC_RE_5229 " + xmlUserLevel;
        testDescription = "Adding more than 10 more rows to whether it is adding successfully";
        authors = "Automation Team";
        category = "Adding Rows Data Manager";
        startTestCase();
        setNode();
        clientCompanyName.set(FakerDataFactory.getBrokerNameWithTimeStamp());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnAddNewRowBtn()
                .validateAddNewRowPage()
                .addNewRowInformation10Times(clientCompanyName.get(), FakerDataFactory.getPastDate(), FakerDataFactory.get12DigitsBankAccountNumber(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(),
                        FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(),
                        FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFutureDate(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric())
                .validateAddedRowInformation(clientCompanyName.get(), "Broker Name")
                .clickOnSelectAll()
                .clickOnBulkDeleteBtn();
        System.out.println("TC-----------TC_RE_5229");
    }

    //Srikanth
    //TC_1583 - Test to verify that all the valid rows in the uploaded file are displayed in the data manager. To do that we have to upload a file and then verify it in the data manager that all the valid rows are there or not.
//    The expected outcome is, all the valid rows should be there in the data manager.
    @Test(priority = 3, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void verifyValidRowsInDataManager() {

        testcaseName = "TC_RE_1583 " + xmlUserLevel;
        testDescription = "Verify valid Rows In Data Manager Module";
        authors = "Automation Team";
        category = "Verify valid Rows In Data Manager Module";
        startTestCase();
        setNode();

        filePath.set("./src/test/resources/upload/ValidRowCase.csv");
        fileName.set("ValidRowCase.csv");
        List<String> systemColumnNames = FakerDataFactory.getSystemColumns();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnDataManager()
                .setClient()
                .getTotalRowCount("Initial")
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(filePath.get(), fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(FakerDataFactory.getDataMapName(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .mappingFileAndSystemColumns(systemColumnNames)
                .clickOnFinishMapping()
                .validateFileUpload(fileName.get())
                .getValidRowCount(fileName.get())
                .clickOnDataManager()
                .getTotalRowCount("Final")
                .CompareTotalRowCountAndValidRows()
                .clickOnFileManager()
                .validateFileNameInFileManagerGridAndDelete(fileName.get());
    }

}



















