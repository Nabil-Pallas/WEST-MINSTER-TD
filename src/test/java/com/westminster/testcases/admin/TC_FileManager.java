package com.westminster.testcases.admin;

import java.util.List;

import com.config.ConfigurationManager;
import com.pages.Admin.DataManagerPages.AddNewRowPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.dynamic.FakerDataFactory;
import com.listener.TestListener;
import com.page.base.DashboardPage;
import com.page.base.WestMinsterPageHook;

@Listeners(TestListener.class)
public class TC_FileManager extends WestMinsterPageHook {

    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User"})
    public void setReportValues() {
        testcaseName = "TC_5588 " + xmlUserLevel;
        testDescription = "Upload a file and validate";
        authors = "Automation Team";
        category = "File Upload";
    }

    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User"})
    public void fileUpload() {
        fileName.set("SampleFile_AU_Chrome.csv");
        List<String> systemColumnNames = FakerDataFactory.getSystemColumns();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(FakerDataFactory.getDataMapName(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .mappingFileAndSystemColumns(systemColumnNames)
                .clickOnFinishMapping()
                .validateFileUpload(fileName.get());
        System.out.println("TC-----------01");
    }

    @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User"})
    public void promotingRows() {
        testcaseName = "TC_4460 " + xmlUserLevel;
        testDescription = "Promoting Rows and Validate";
        authors = "Automation Team";
        category = "Promoting Rows";
        startTestCase();
        setNode();
        fileName.set("SampleFile_AU_Chrome.csv");
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnFileManager()
                .validateFileManagerPage()
                .clickOnInvalidRowCountAndAmendErrors(fileName.get())
                .validateErrorQueuePage()
                .clickOnSelectAll()
                .clickOnBulkEditRows()
                .validateBulkEditRowsPage()
                .editInvalidRowInfo("06/07/2023", "06/12/2023")
                .clickOnApplyBtn()
                .validateErrorQueuePage()
                .clickOnSelectAll()
                .clickOnPromoteRows()
                .clickOnExitBtn()
                .validateFileManagerPage()
                .validateRowPromote(fileName.get());
        System.out.println("TC-----------02");

    }


    @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User"})
    public void fileUpload_AddSystemColumn() {
        testcaseName = "TC_1304 " + xmlUserLevel;
        testDescription = "Validating whether the new system field can be added to an existing file mapping process/screen";
        authors = "Automation Team";
        category = "Add data schema";
        startTestCase();
        setNode();
        fileName.set("SampleFile_AU_Chrome.csv");
        ruleName.set(FakerDataFactory.getDataMapName());
        List<String> systemColumnNames = FakerDataFactory.getSystemColumns();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(ruleName.get(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .clickOnAddNewSystemColumns()
                .validateAddNewSystemColumnsPage()
                .typeInformationInAddNewSystemColumns(false, false, true)
                .mappingFileAndSystemColumns(systemColumnNames)
                .clickOnFinishMapping()
                .clickOnDataManager()
                .validateDataManagerPage()
                .validateAddedNewColumn(clientCompanyName.get())
                .clickOnFileManager()
                .validateFileNameInFileManagerGridAndDelete(fileName.get())
                .deleteDataMaps(ruleName.get())
                .clickOnDataManager()
                .clickOnEditDataSchema()
                .deleteDataSchema();

        System.out.println("TC-----------02");

    }

    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User"})
    public void mapColumns() {
        fileName.set("SampleFile_AU_Chrome.csv");
        List<String> systemColumnNames = FakerDataFactory.getSystemColumns();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(FakerDataFactory.getDataMapName(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .mappingFileAndSystemColumns(systemColumnNames);
        System.out.println("TC-----------01");
    }


}
