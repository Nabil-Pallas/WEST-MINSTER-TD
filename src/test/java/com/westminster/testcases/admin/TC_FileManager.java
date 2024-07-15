package com.westminster.testcases.admin;

import java.util.Arrays;
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
import com.ui.utilities.TestGroups;

@Listeners(TestListener.class)
public class TC_FileManager extends WestMinsterPageHook {

    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY, TestGroups.Type.REGRESSION})
    public void setReportValues() {
        testcaseName = "TC_5588 " + xmlUserLevel;
        testDescription = "Upload a file and validate";
        authors = "Automation Team";
        category = "File Upload";
    }

    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY})
    public void fileUpload() {
        filePath.set("./src/test/resources/upload/SampleFile_AU_Chrome.csv");
        fileName.set("SampleFile_AU_Chrome.csv");
        List<String> systemColumnNames = FakerDataFactory.getSystemColumns();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(filePath.get(), fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(FakerDataFactory.getDataMapName(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .mappingFileAndSystemColumns(systemColumnNames)
                .clickOnFinishMapping()
                .validateFileUpload(fileName.get());
        System.out.println("TC-----------01");
    }

    @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY})
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
                .editInvalidRowInfo("07/10/2023", "07/15/2023")
                .clickOnApplyBtn()
                .validateErrorQueuePage()
                .clickOnSelectAll()
                .clickOnPromoteRows()
                .clickOnExitBtn()
                .validateFileManagerPage()
                .validateRowPromote(fileName.get());
        System.out.println("TC-----------02");

    }

    //Srikanth
    //TC-961 - Upload a file which already uploaded with same data but with different file name
    @Test(priority = 6, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void uploadSameContentFileWDiffName() {
        testcaseName = "TC_961 " + xmlUserLevel;
        testDescription = "Upload Duplicate File with Different Name";
        authors = "Automation Team";
        category = "uploadDpFile";
        startTestCase();
        setNode();
        DataMap.set(FakerDataFactory.getDataMapName());
        filePath.set("./src/test/resources/upload/SampleFile_AU_Chrome.csv");
        filePath2.set("./src/test/resources/upload/SampleFile_AU_Chrome2.csv");
        fileName.set("SampleFile_AU_Chrome.csv");
        fileName2.set("SampleFile_AU_Chrome2.csv");
        List<String> systemColumnNames = FakerDataFactory.getSystemColumns();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(filePath.get(), fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(DataMap.get(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .mappingFileAndSystemColumns(systemColumnNames)
                .clickOnFinishMapping()
                .validateFileUpload(fileName.get())
                .fileUpload(filePath2.get(), fileName2.get())
                .selectExistingMapping(DataMap.get())
                .clickOnApplyMapping()
                .verifyErrorMessage();

    }
    //Srikanth
    //TC-1603 - Test to verify whether multiple files with same name but different content can be uploaded or not. To do that we have to upload two different files with different contents in the same name. Expected outcome is, warning message should be displayed saying that "duplicate files are not allowed" and the file should not get uploaded.
    @Test(priority = 7, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void uploadDiffContentFileWSameName() {
        testcaseName = "TC_1603 " + xmlUserLevel;
        testDescription = "Upload Different Content File with Same Name";
        authors = "Automation Team";
        category = "upload Different File SameName";
        startTestCase();
        setNode();
        DataMap.set(FakerDataFactory.getDataMapName());
        filePath.set("./src/test/resources/upload/SampleFile_AU_Edge.csv");
        filePath2.set("./src/test/resources/upload/upload2/SampleFile_AU_Edge.csv");

        fileName.set("SampleFile_AU_Edge.csv");
        fileName2.set("SampleFile_AU_Edge(1).csv");
        List<String> systemColumnNames = FakerDataFactory.getSystemColumns();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(filePath.get(), fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(DataMap.get(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .mappingFileAndSystemColumns(systemColumnNames)
                .clickOnFinishMapping()
                .validateFileUpload(fileName.get())
                .fileUpload(filePath2.get(), fileName2.get())
                .selectExistingMapping(DataMap.get())
                .clickOnApplyMapping()
                .validateFileUpload(fileName2.get());
    }

    @Test(priority = 7, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void fileUploadAddSystemColumn() {
        testcaseName = "TC_1304 " + xmlUserLevel;
        testDescription = "Validating whether the new system field can be added to an existing file mapping process/screen";
        authors = "Automation Team";
        category = "uploadDpFile";
        startTestCase();
        setNode();
        DataMap.set(FakerDataFactory.getDataMapName());
        filePath.set("./src/test/resources/upload/SampleFile_AU_Chrome.csv");
        filePath2.set("./src/test/resources/upload/SampleFile_AU_Chrome2.csv");
        fileName.set("SampleFile_AU_Chrome.csv");
        fileName2.set("SampleFile_AU_Chrome2.csv");
        List<String> systemColumnNames = FakerDataFactory.getSystemColumns();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload("./src/test/resources/upload/", fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(DataMap.get(), "MM/dd/yyyy")
                .validateFileMappingPage()
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

    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void mapColumns() {
        testcaseName = "TC_5622 " + xmlUserLevel;
        testDescription = "Validating whether the new system field can be added to an existing file mapping process/screen";
        authors = "Automation Team";
        category = "Add data schema";
        startTestCase();
        setNode();
        fileName.set("SampleFile_AU_Chrome.csv");
        fileName2.set("SampleFile_AU_Chrome2.csv");
        List<String> systemColumnNames = FakerDataFactory.getSystemColumns();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload("./src/test/resources/upload/", fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(FakerDataFactory.getDataMapName(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .mappingFileAndSystemColumns(systemColumnNames);
        System.out.println("TC-----------01");
    }

    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void upload6000DataFile() {
        testcaseName = "TC_1563 " + xmlUserLevel;
        testDescription = "Validating whether the new system field can be added to an existing file mapping process/screen";
        authors = "Automation Team";
        category = "Add data schema";
        startTestCase();
        setNode();
        filePath.set("./src/test/resources/upload/6000DataFile.csv");
        fileName.set("6000DataFile.csv");
        List<String> systemColumnNames = FakerDataFactory.getSystemColumns();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(filePath.get(), fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(FakerDataFactory.getDataMapName(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .mappingFileAndSystemColumns(Arrays.asList("Broker Name", "Trade Date", "Account Name", "Base Currency", "Base Executed Value", "Base Gross Commission", "Broker Code", "ISIN", "FX Rate", "Trade ID", "Ticker", "Local Gross Commission", "Local Executed Value", "Local Currency", "Instrument Type", "Fund", "Security Description", "Local Price", "Executed Quantity"),
                        Arrays.asList("BROKER", "Trade Date", "ACCOUNT", "Base Currency", "Base Executed Value", "Base Gross Commission", "Broker Code", "ISIN", "FX Spot Rate", "Trade ID", "Ticker", "COMMISSION", "Local Executed Value", "CURRENCY", "Instrument Type", "Fund", "Security Description", "PRICE", "QUANTITY"))
                .clickOnFinishMapping()
                .validateFileUpload(fileName.get());
        System.out.println("TC-----------01");
    }

    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void upload500KDataFile() {
        testcaseName = "TC_1543 " + xmlUserLevel;
        testDescription = "Validating whether the new system field can be added to an existing file mapping process/screen";
        authors = "Automation Team";
        category = "Add data schema";
        startTestCase();
        setNode();
        filePath.set("./src/test/resources/upload/500kFile.csv");
        fileName.set("500kFile.csv");
        List<String> systemColumnNames = FakerDataFactory.getSystemColumns();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(filePath.get(),fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(FakerDataFactory.getDataMapName(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .mappingFileAndSystemColumns(Arrays.asList("Broker Name", "Trade Date", "Account Name", "Base Currency", "Base Executed Value", "Base Gross Commission", "Broker Code", "ISIN", "FX Rate", "Trade ID", "Ticker", "Local Gross Commission", "Local Executed Value", "Local Currency", "Instrument Type", "Fund", "Security Description", "Local Price", "Executed Quantity"),
                        Arrays.asList("BROKER", "Trade Date", "ACCOUNT", "Base Currency", "Base Executed Value", "Base Gross Commission", "Broker Code", "ISIN", "FX Spot Rate", "Trade ID", "Ticker", "COMMISSION", "Local Executed Value", "CURRENCY", "Instrument Type", "Fund", "Security Description", "PRICE", "QUANTITY"))
                .clickOnFinishMapping()
                .validateFileUpload(fileName.get());
    }

    //Manoj
    //Verify adding more than 10 rows in 'Add New System Columns' on the file mapping page allows unlimited columns.
    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void addSystemColumnsMoreThan10Rows() {
        testcaseName = "TC_RE_1623 " + xmlUserLevel;
        testDescription = "Verify adding more than 10 rows in 'Add New System Columns' on the file mapping page";
        authors = "Automation Team";
        category = "Add System Columns More Than 10 Rows";
        startTestCase();
        setNode();
        filePath.set("./src/test/resources/upload/SampleFile_AU_Chrome.csv");
        fileName.set("SampleFile_AU_Chrome.csv");
        ruleName.set(FakerDataFactory.getDataMapName());
        List<String> systemColumnNames = FakerDataFactory.getSystemColumns();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(filePath.get(), fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(ruleName.get(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .clickOnAddNewSystemColumns()
                .validateAddNewSystemColumnsPage()
                .typeInformationInAddNewSystemColumns(3)
                .validateFileMappingPage()
                .verifyAddedColumnsInSystemColumnName(3);

        System.out.println("TC-----------TC_RE_1623");
    }


}
