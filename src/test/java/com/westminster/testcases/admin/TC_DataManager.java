package com.westminster.testcases.admin;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.pages.Admin.DataManagerPages.DataManagerPage;
import com.pages.Admin.DataManagerPages.EditDataManagerRow;
import com.pages.Admin.DataManagerPages.EditDataSchemaPage;
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
    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY, TestGroups.Type.REGRESSION})
    public void setReportValues() {
        testcaseName = "Login with " + xmlUserLevel;
        testDescription = "Login with " + xmlUserLevel;
        authors = "Automation Team";
        category = "Login for Data Manager Module";
    }

    // Adding the new row in the data manager
    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY})
    public void addNewRow() {
        testcaseName = "TC_5577";
        testDescription = "Add New Row And validate";
        authors = "Automation Team";
        category = "Add New Row";
        startTestCase();
        setNode();

        clientCompanyName.set(FakerDataFactory.getNameWithTimeStamp(""));
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnDataManager()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnClearAllFilters()
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
        System.out.println("TC-----------TC_5577");
    }

    // Editing the new row in the Data manager
    @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY})
    public void editRow() {
        testcaseName = "TC_4937";
        testDescription = "Edit a row and validate";
        authors = "Automation Team";
        category = "Edit Row";
        startTestCase();
        setNode();

        ruleName.set(FakerDataFactory.getNameWithTimeStamp(""));
        reportName.set(FakerDataFactory.getReportsName());
        titleone.set(FakerDataFactory.getTitle());
        titleTwo.set(FakerDataFactory.getTitle());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnDataManager()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnClearAllFilters()
                .clickOnActionsBtn()
                .clickOnEditBtn()
                .validateEditRowPage()
                .editInformation(Arrays.asList(ruleName.get(), reportName.get(), titleone.get()),
                        Arrays.asList("Broker  Name:", "Account  Name:", "Asset  Class:"))
                .clickOnApplyBtn()
                .searchValueToValidate(ruleName.get(), "Broker Name")
                .validateEditedRowInformation((ruleName.get()), ("Broker Name"));
//                .clickOnActionsBtn()
//                .clickOnDeleteBtn();
        // .deleteAddedRow((ruleName.get()), "Broker Name");
        System.out.println("TC-----------TC_4937");
    }

    @Test(priority = 3, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY})
    public void addNewColumnInEditDataSchema() {
        testcaseName = "TC_6587";
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
                .clickOnClearAllFilters()
                .clickOnEditDataSchema()
                .validateEditDataSchemaColumnsPage()
                .clickOnAddNewColumn()
                .validateAddNewColumnPage()
                .typeInformationInAddNewColumnsAndValidate(true, true, true)
                .deleteDataSchema();
        System.out.println("TC-----------TC_6587");
    }

    //Manoj
    //Ensure the "Add" button is disabled when there is an error in the required field, Trade Date.
    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void verifyAddButtonDisabledOnTradeDateError() {
        testcaseName = "TC_RE_5731";
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
                .validateTradeDateErrorMsg("Custom Validation: Trade Date must be before “Settlement Date”.")
                .validateAddBtnIsDisable();
        System.out.println("TC-----------TC_RE_5731");
    }

    //Manoj
    // Verify that the "Add" button disables when the required field "Broker Name" has an error.
    @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void verifyAddButtonDisabledOnBrokerNameError() {
        testcaseName = "TC_RE_5945";
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
        testcaseName = "TC_RE_5429";
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
    @Test(priority = 4, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void verifyNoBlankSpacesOnEditTradeDetailsPage() {
        testcaseName = "TC_RE_5235";
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
//                .validateChangeLog(Arrays.asList("Broker Name", "Trade Date", "Settlement Date"));
                .validateChangeLog(4);

        System.out.println("TC-----------TC_RE_5235");
    }

    // Sanjay Kumar
    @Test(priority = 5, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void checkApplyAndCancelBtnForEditAndBulkedit() {
        testcaseName = "TC_RE_4622";
        testDescription = "Check Apply And Cancel Btn For Edit And Bulk edit";
        authors = "Automation Team";
        category = "Check Apply And Cancel Btn For Edit And Bulk edit";
        startTestCase();
        setNode();

        clientCompanyName.set(FakerDataFactory.getBrokerNameWOTimeStamp());
        ruleName.set(FakerDataFactory.getCompanyName());
        reportName.set(FakerDataFactory.getReportsName());
        titleone.set(FakerDataFactory.getTitle());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnActionsBtn()
                .clickOnEditBtn()
                .validateEditRowPage()
                .editInformation(Arrays.asList(ruleName.get(), reportName.get(), titleone.get()),
                        Arrays.asList("Broker  Name:", "Account  Name:", "Asset  Class:"))
                .clickOnApplyBtn()
                .clickOnActionsBtn()
                .clickOnEditBtn()
                .validateEditRowPage()
                .clickOnCancelBtn()
                .clickOnSelectRow()
                .clickOnBulkEditBtn()
                .validateBulkEditRowsPage()
                .editRowInformation(clientCompanyName.get(), FakerDataFactory.getPastDate(), FakerDataFactory.get12DigitsBankAccountNumber(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(),
                        FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFTPFolderAcronym(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(),
                        FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getFutureDate(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric(), FakerDataFactory.getAlphanumeric())
                .clickOnApplyBtn()
                .clickOnSelectRow()
                .clickOnBulkEditBtn()
                .validateBulkEditRowsPage()
                .clickOnCancelBtn();
        System.out.println("TC-----------TC_RE_4622");
    }

    //Manoj
    //Verify if more than 10 rows can be added at once using the 'Add New Row' option in Data Manager.
    @Test(priority = 6, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void addMoreThan10Rows() {
        testcaseName = "TC_RE_5229";
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
                .setClient()
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
    //TC_1583 - Verify that all valid rows in the uploaded file are displayed in the Data Manager.
    @Test(priority = 7, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void verifyValidRowsInDataManager() {
        testcaseName = "TC_RE_1583";
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
        System.out.println("TC-----------TC_RE_1583");
    }

    //Phase - 2 - Manoj
    //TC_RE_5913 - Ensure adding a non-existing broker name via single row editing updates the list and assigns correctly to the validation field.
    @Test(priority = 8, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void addNonExistingBrokerNameAndValidate() {
        testcaseName = "TC_RE_5913";
        testDescription = "Add Non Existing Broker Name And Validate";
        authors = "Automation Team";
        category = "Data Manager";
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
                .clickOnEllipsisBtn()
                .clickOnEditBtn()
                .validateEditRowPage()
                .clickOnAddSymbolButton()
                .getBrokerNameText()
                .clickOnCancelBtn()
                .clickOnDataManager()
                .clickOnListManager()
                .validateListManagerPage()
                .validateListNameAndItem(newListName.get(), getTexts.get())
                .deleteListItem(newListName.get());
        System.out.println("TC-----------TC_RE_5913");
    }

    //Phase - 2 - Manoj
    //TC_RE_1767 - Check if a warning message appears when refreshing the page during bulk editing.
//    @Test(priority = 9, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void verifyWarningOnRefreshDuringBulkEdit() {
        testcaseName = "TC_RE_1767";
        testDescription = "Verify Duplicate Detection For Multiple Trades";
        authors = "Automation Team";
        category = "Data Manager";
        startTestCase();
        setNode();

        clientCompanyName.set(FakerDataFactory.getBrokerNameWOTimeStamp());
        ruleName.set(FakerDataFactory.getCompanyName());
        reportName.set(FakerDataFactory.getReportsName());
        titleone.set(FakerDataFactory.getTitle());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnSelectRow()
                .clickOnBulkEditBtn()
                .validateBulkEditRowsPage()
                .editRowInformation(clientCompanyName.get(), FakerDataFactory.getPastDate(), FakerDataFactory.get12DigitsBankAccountNumber(), FakerDataFactory.getFutureDate())
                .refreshPageAndCancelDialog();
//                .validateBulkEditRowsPage();

        System.out.println("TC-----------TC_RE_1767");
    }

    //Phase - 2 - Nabil
    //TC_RE_1683 -
    @Test(priority = 10, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void verifyRestoreAllColumns() {
        testcaseName = "TC_RE_1683";
        testDescription = "Verify restore all columns in Data Manager and Error Queue";
        authors = "Automation Team";
        category = "Data Manager";
        startTestCase();
        setNode();

        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnShowColumnChooser()
                .dragAndDropColumnInColumnChooser(Arrays.asList("Broker Name", "Settlement Date", "Trade Date"))
                .clickOnCloseColumnChooser()
                .getDataManagerGridColumnNames()
                .valiateColumnsAgainstColumnChooser()
                .clickOnShowColumnChooser()
                .clickOnRestoreAllColumns()
                .clickOnCloseColumnChooser()
                .getDataManagerGridColumnNames()
                .validateDataManagerGridColumn()
                .clickOnFileManager()
                .validateFileManagerPage()
                .clickOnOpenErrorQueue()
                .validateErrorQueuePage()
                .clickOnShowColumnChooser()
                .dragAndDropColumnInColumnChooser(Arrays.asList("Broker Name", "Settlement Date", "Trade Date"))
                .clickOnCloseColumnChooser()
                .getFileManagerGridColumnNames()
                .valiateColumnsAgainstColumnChooser()
                .clickOnShowColumnChooser()
                .clickOnRestoreAllColumns()
                .clickOnCloseColumnChooser()
                .getFileManagerGridColumnNames()
                .validateFileManagerGridColumn();

    }

    //Phase -3 - Manoj
    //TC_RE_5888 Verify the functionality of the delete icons in the change log.
    @Test(priority = 4, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void verifyDeleteIconsInChangeLog() {
        testcaseName = "TC_RE_5888";
        testDescription = "Verify Delete Icons In Change Log";
        authors = "Automation Team";
        category = "Data Manager";
        startTestCase();
        setNode();

        clientCompanyName.set(FakerDataFactory.getBrokerNameWithTimeStamp());
        userFirstName.set(FakerDataFactory.getBrokerNameWOTimeStamp());
        ruleName.set(FakerDataFactory.getCompanyName());
        reportName.set(FakerDataFactory.getReportsName());
        titleone.set(FakerDataFactory.getTitle());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnClearAllFilters()
                .clickOnSelectRow()
                .clickOnBulkEditBtn()
                .validateBulkEditRowsPage()
                .editInformation(Arrays.asList(clientCompanyName.get(), reportName.get(), titleone.get(), ruleName.get()),
                        Arrays.asList("Broker  Name:", "Account  Name:", "Asset  Class:", "Base  Currency:"))
                .clickOnApplyBtn()
                .validateDataManagerPage()
                .validateAddedRowInformation(clientCompanyName.get(), "Broker Name")
                .clickOnSelectRow()
                .clickOnBulkEditBtn()
                .validateBulkEditRowsPage()
                .editInformation(Arrays.asList(userFirstName.get(), "Account", "Asset", "Currency"),
                        Arrays.asList("Broker  Name:", "Account  Name:", "Asset  Class:", "Base  Currency:"));
        new EditDataManagerRow()
                .deleteAndValidateChangeLogItems()
                .clickOnCancelBtn()
                .validateDataManagerPage()
                .clickOnClearAllFilters()
                .validateAddedRowInformation(clientCompanyName.get(), "Broker Name");

        System.out.println("TC-----------TC_RE_5888");
    }

    //Phase -3 - Manoj
    //TC_RE_1686 Verify the functionality of the delete icons in the change log.
    @Test(priority = 4, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void verifyBulkDeleteRowsFunctionality() {
        testcaseName = "TC_RE_1686";
        testDescription = "Verify Bulk Delete Rows Functionality";
        authors = "Automation Team";
        category = "Data Manager";
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
                .getTotalRowCount("Initial")
                .clickOnSelectRow()
                .clickOnBulkDeleteBtn()
                .getTotalRowCount("Final")
                .getTotalRowCount()
                .validateRowCountAfterBulkDelete();

        System.out.println("TC-----------TC_RE_1686");
    }

    //Phase -3 - Manoj
    //TC_RE_5165 Removes associated rules from the rules manager and data manager grid when a calculated column is deleted from the schema.
    @Test(priority = 4, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void removeRuleOnCalculatedColumnDeletion() {
        testcaseName = "TC_RE_5165";
        testDescription = "Remove Rule On Calculated Column Deletion";
        authors = "Automation Team";
        category = "Data Manager";
        startTestCase();
        setNode();

        ruleName.set(FakerDataFactory.getNameWithTimeStamp("Rule"));
        titleone.set(FakerDataFactory.getStaticNameWithTimeStamp("Aapay"));
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnRulesManager()
                .validateRulesManagerPage()
                .clickOnAddNewRuleBtn()
                .validateAddRulePage()
                .createANewRule(ruleName.get(), FakerDataFactory.getRuleDescription())
                .defineTheNewColumn(true, titleone.get(), "")
                .addFilters("Broker Name", "=")
                .defineTheCalculation("Local Price", "*", "Local Price")
                .validateNewRuleNameIsEnabled(ruleName.get())
                .clickOnDataManager()
                .validateDataManagerPage()
                .verifyHeaderElementIsPresentOrNot(titleone.get())
                .clickOnEditDataSchema()
                .validateEditDataSchemaColumnsPage()
//                .validateNewlyAddedDataSchemaColumn(titleone.get())
                .clickOnRelevantFieldNameActionIcon(titleone.get())
                .clickOnDeleteBtn()
                .clickOnExit()
                .clickOnDataManager()
                .validateDataManagerPage()
                .verifyHeaderElementIsPresentOrNot(titleone.get())
                .clickOnRulesManager()
                .validateRulesManagerPage()
                .deleteRuleName(ruleName.get())
                .clickOnDataManager()
                .validateDataManagerPage()
                .verifyHeaderElementIsPresentOrNot(titleone.get());
        System.out.println("TC-----------TC_RE_5165");
    }

    //Phase -3 - Praveen
    //Phase -3 - Praveen
    //TC_RE_5161 Verify the duplicate file detection functionality when no unique trade identifier is selected
    @Test(priority = 5, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void verifyDuplicateFileDetectionWithoutUniqueTradeIdentifier() {
        testcaseName = "TC_RE_5161";
        testDescription = "Detect Duplicate File When No Unique Trade Identifier Selected";
        authors = "Automation Team";
        category = "Data Manager";
        startTestCase();
        setNode();

        DataMap.set(FakerDataFactory.getDataMapName());
        filePath.set("./src/test/resources/upload/Duplicate_File_One.csv");
        fileName.set("Duplicate_File_One.csv");
        filePath2.set("./src/test/resources/upload/Duplicate_File_Two.csv");
        fileName2.set("Duplicate_File_Two.csv");
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(filePath.get(), fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(DataMap.get(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .mappingFileAndSystemColumns(Arrays.asList("Broker Name", "Trade Date", "Account Name", "Trade ID", "Settlement Date"),
                        Arrays.asList("BROKER", "Trade Date", "ACCOUNT", "Trade ID", "Settlement Date"))
                .clickOnFinishMapping()
                .validateFileUpload(fileName.get())
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnEditDataSchema()
                .validateEditDataSchemaColumnsPage()
                .sortByFieldColumn()
                .clickOnUniqueTradeIdentifier("Trade ID", "true")
                .clickOnExit()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(filePath2.get(), fileName2.get())
                .selectExistingMapping(DataMap.get())
                .clickOnApplyMapping()
                .validateFileUpload(fileName2.get())
                .clickOnInvalidRowCountAndAmendErrors(fileName2.get())
                .validateErrorQueuePage()
                .clickFilterAndSelectDuplicateRows()
                .getErrorQueueRowCounts()
                .clickOnExitBtn()
                .validateRowCountIsZero()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnEditDataSchema()
                .validateEditDataSchemaColumnsPage()
                .sortByFieldColumn()
                .clickOnUniqueTradeIdentifier("Trade ID", "false")
                .clickOnExit();

        System.out.println("TC-----------TC_RE_5161");
    }

    //Phase -3 - Praveen
    @Test(priority = 6, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void checkDuplicateFileDetectionForMultipleUniqueIdentifiers() {
        testcaseName = "TC_RE_5162";
        testDescription = "Check Duplicate File Detection for Multiple Unique Identifiers";
        authors = "Automation Team";
        category = "Data Manager";
        startTestCase();
        setNode();

        DataMap.set(FakerDataFactory.getDataMapName());
        filePath.set("./src/test/resources/upload/Duplicate_File_One.csv");
        fileName.set("Duplicate_File_One.csv");
        filePath2.set("./src/test/resources/upload/Duplicate_File_Two.csv");
        fileName2.set("Duplicate_File_Two.csv");
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(filePath.get(), fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(DataMap.get(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .mappingFileAndSystemColumns(Arrays.asList("Broker Name", "Trade Date", "Account Name", "Trade ID", "Settlement Date"),
                        Arrays.asList("BROKER", "Trade Date", "ACCOUNT", "Trade ID", "Settlement Date"))
                .clickOnFinishMapping()
                .validateFileUpload(fileName.get())
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnEditDataSchema()
                .validateEditDataSchemaColumnsPage()
                .sortByFieldColumn()
                .clickOnUniqueTradeIdentifier("Trade ID", "false")
                .sortByFieldColumn()
                .clickOnUniqueTradeIdentifier("Account Name","false")
                .clickOnExit()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(filePath2.get(), fileName2.get())
                .selectExistingMapping(DataMap.get())
                .clickOnApplyMapping()
                .validateFileUpload(fileName2.get())
                .clickOnInvalidRowCountAndAmendErrors(fileName2.get())
                .validateErrorQueuePage()
                .clickFilterAndSelectDuplicateRows()
                .getErrorQueueRowCounts()
                .clickOnExitBtn()
                .validateRowCountIsNotZero()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnEditDataSchema()
                .validateEditDataSchemaColumnsPage()
                .sortByFieldColumn()
                .clickOnUniqueTradeIdentifier("Trade ID", "false")
                .sortByFieldColumn()
                .clickOnUniqueTradeIdentifier("Trade ID", "true")
                .clickOnExit();
        System.out.println("TC-----------TC_RE_5162");
    }
}



















