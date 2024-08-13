package com.westminster.testcases.accountSettings;

import com.data.dynamic.FakerDataFactory;
import com.listener.TestListener;
import com.page.base.DashboardPage;
import com.page.base.WestMinsterPageHook;
import com.ui.utilities.TestGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Listeners(TestListener.class)
public class TC_AccountSettings extends WestMinsterPageHook {
    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY, TestGroups.Type.REGRESSION})
    public void setReportValues() {
        testcaseName = "Login with " + xmlUserLevel;
        testDescription = "Login with " + xmlUserLevel;
        authors = "Automation Team";
        category = "Login for Account Settings Module";
    }

    //Phase -02 --Nabil
    //TC_RE_5813
    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.REGRESSION})
    public void userPreferenceDecimalCheck() {
        testcaseName = "TC_RE_5813";
        testDescription = "Set user preference Decimal Precision";
        authors = "Automation Team";
        category = "Account Settings";
        startTestCase();
        setNode();

        clientCompanyName.set(FakerDataFactory.getBrokerNameWithTimeStamp());
        getTradeDate.set(FakerDataFactory.getPastDate());
        getSettlementDate.set(FakerDataFactory.getFutureDate());
        number.set(FakerDataFactory.getNumericWDecimal());
        System.out.println(number.get());
        new DashboardPage()
                .validateDashBoardElements()
                .setDecicalPrecisionInPreferences()
                .validateDecimalPrecision()
                .clickOnHomePage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnAddNewRowBtn()
                .validateAddNewRowPage()
                .addNewRow(clientCompanyName.get(), getTradeDate.get(), getSettlementDate.get(), number.get())
                .clickOnAddBtn()
                .clickOnApplyBtn()
                .clickOnDataManager()
                .validateAddedRowWDecimalAndNumberFormat(clientCompanyName.get(), "Broker Name", "Base Gross Commission")
                .clickOnClearAllFilters();
    }

    //Phase -02 --Nabil
    //TC_RE_5814
    @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.REGRESSION})
    public void userPreferenceNumberFormat() {
        testcaseName = "TC_RE_5814";
        testDescription = "Set user preference Number Format";
        authors = "Automation Team";
        category = "Account Settings";
        startTestCase();
        setNode();

        clientCompanyName.set(FakerDataFactory.getBrokerNameWithTimeStamp());
        getTradeDate.set(FakerDataFactory.getPastDate());
        getSettlementDate.set(FakerDataFactory.getFutureDate());
        number.set(FakerDataFactory.getNumericWDecimal());
        System.out.println(number.get());
        new DashboardPage()
                .validateDashBoardElements()
                .setDecicalPrecisionInPreferences()
                .setNumberFormatInPreferences()
                .validateDecimalPrecision()
                .validateNumberFormat()
                .clickOnHomePage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnAddNewRowBtn()
                .validateAddNewRowPage()
                .addNewRow(clientCompanyName.get(), getTradeDate.get(), getSettlementDate.get(), number.get())
                .clickOnAddBtn()
                .clickOnApplyBtn()
                .clickOnDataManager()
                .validateAddedRowWDecimalAndNumberFormat(clientCompanyName.get(), "Broker Name", "Base Gross Commission")
                .clickOnClearAllFilters();

    }

    //Phase -02 --Nabil
    //TC_RE_5812
    @Test(priority = 3, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.REGRESSION})
    public void userPreferenceDateFormat() throws ParseException {
        testcaseName = "TC_RE_5812";
        testDescription = "Set user preference Date Format";
        authors = "Automation Team";
        category = "Account Settings";
        startTestCase();
        setNode();

        clientCompanyName.set(FakerDataFactory.getBrokerNameWithTimeStamp());
        getTradeDate.set(FakerDataFactory.getPastDate());
        getSettlementDate.set(FakerDataFactory.getFutureDate());
        number.set(FakerDataFactory.getNumericWDecimal());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnAddNewRowBtn()
                .validateAddNewRowPage()
                .addNewRow(clientCompanyName.get(), getTradeDate.get(), getSettlementDate.get(), number.get())
                .clickOnAddBtn()
                .clickOnApplyBtn()
                .clickOnDataManager()
                .validateAddedRowWDateFormat(clientCompanyName.get(),"Broker Name","Trade Date")
                .clickOnClearAllFilters()
                .setDateFormatInPreferences()
                .validateDateFormat()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnDataManager()
                .validateDataManagerPage()
                .validateAddedRowWDateFormat(clientCompanyName.get(),"Broker Name","Trade Date")
                .clickOnClearAllFilters()
                .setDateFormatInPreferences()
                .validateDateFormat()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnDataManager()
                .validateDataManagerPage()
                .validateAddedRowWDateFormat(clientCompanyName.get(),"Broker Name","Trade Date")
                .clickOnClearAllFilters();
    }

    //Phase -02 --Nabil
    //TC_RE_5815
    @Test(priority = 4, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.REGRESSION})
    public void userPreferenceTimeFormat() {
        testcaseName = "TC_RE_5815";
        testDescription = "Set user preference Time Format";
        authors = "Automation Team";
        category = "Account Settings";
        startTestCase();
        setNode();

        filePath.set("./src/test/resources/upload/1000_rows_file_mapping.csv");
        fileName.set("1000_rows_file_mapping.csv");
        DataMap.set(FakerDataFactory.getDataMapName());
        new DashboardPage()
                .validateDashBoardElements()
                .setTimeFormatInPreferences()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(filePath.get(), fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(DataMap.get(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .mappingFileAndSystemColumns(Arrays.asList("Broker Name", "Trade Date", "Account Name", "Trade ID", "Settlement Date"), Arrays.asList("BROKER", "Trade Date", "ACCOUNT", "Trade ID", "Settle Date"))
                .clickOnFinishMapping()
                .validateFileUpload(fileName.get())
                .clickOnDataManager()
                .validateDataManagerPage()
                .getUploadDateFromDataManager("Upload Date")
                .validateTimeFormatInDataManager()
                .setTimeFormatInPreferences()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnDataManager()
                .validateDataManagerPage()
                .getUploadDateFromDataManager("Upload Date")
                .validateTimeFormatInDataManager();




    }

    //Phase -02 --Nabil
    //TC_RE_6175
    @Test(priority = 5, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.REGRESSION})
    public void userPreferenceLocalTimezone() {
        testcaseName = "TC_RE_6175";
        testDescription = "Set user preference Local Timezone";
        authors = "Automation Team";
        category = "Account Settings";
        startTestCase();
        setNode();

        filePath.set("./src/test/resources/upload/1000_rows_file_mapping.csv");
        fileName.set("1000_rows_file_mapping.csv");
        DataMap.set(FakerDataFactory.getDataMapName());
        new DashboardPage()
                .validateDashBoardElements()
                .setLocalTimezoneInPreferences()
                .getTimeInLocalTimezoneBeforeFileUpload()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnFileManager()
                .validateFileManagerPage()
                .fileUpload(filePath.get(), fileName.get())
                .clickOnCreateNewMapping()
                .createNewDataMap(DataMap.get(), "MM/dd/yyyy")
                .validateFileMappingPage()
                .mappingFileAndSystemColumns(Arrays.asList("Broker Name", "Trade Date", "Account Name", "Trade ID", "Settlement Date"), Arrays.asList("BROKER", "Trade Date", "ACCOUNT", "Trade ID", "Settle Date"))
                .clickOnFinishMapping()
                .validateFileUpload(fileName.get())
                .getUploadDateInFileManager(fileName.get())
                .getTimeInLocalTimezoneAfterFileUpload()
                .validateLocalTimezonevalue();
    }

}
