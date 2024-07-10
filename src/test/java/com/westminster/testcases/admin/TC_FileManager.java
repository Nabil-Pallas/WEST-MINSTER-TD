package com.westminster.testcases.admin;

import java.util.List;

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
public class TC_FileManager extends WestMinsterPageHook {

    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User",TestGroups.Type.SANITY,TestGroups.Type.REGRESSION})
    public void setReportValues() {
        testcaseName = "TC_5588 " + xmlUserLevel;
        testDescription = "Upload a file and validate";
        authors = "Automation Team";
        category = "File Upload";
    }

    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User",TestGroups.Type.SANITY})
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

  //  @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User",TestGroups.Type.SANITY})
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

    //TC-961 - Upload a file which already uploaded with same data but with different file name
    @Test(priority = 4, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void uploadDpFileWDiffName(){
        testcaseName = "TC_961 " + xmlUserLevel;
        testDescription = "Upload Duplicate File with Different Name";
        authors = "Automation Team";
        category = "uploadDpFile";
        startTestCase();
        setNode();
        DataMap.set(FakerDataFactory.getDataMapName());
        fileName.set("SampleFile_AU_Chrome.csv");
        fileName2.set("SampleFile_AU_Chrome2.csv");
        List<String> systemColumnNames = FakerDataFactory.getSystemColumns();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(DataMap.get(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .mappingFileAndSystemColumns(systemColumnNames)
                .clickOnFinishMapping()
                .validateFileUpload(fileName.get())
                .fileUpload(fileName2.get())
                .selectExistingMapping(DataMap.get())
                .clickOnApplyMapping();

    }
}
