package com.westminster.testcases.accountSettings;

import com.data.dynamic.FakerDataFactory;
import com.listener.TestListener;
import com.page.base.DashboardPage;
import com.page.base.WestMinsterPageHook;
import com.ui.utilities.TestGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

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
    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
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
                .validateAddedRowWDecimalPrecision(clientCompanyName.get(), "Broker Name", "Base Gross Commission");
    }

    //Phase -02 --Nabil
    //TC_RE_5814
    @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
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
                .validateAddedRowWDecimalPrecision(clientCompanyName.get(), "Broker Name", "Base Gross Commission");

    }
}
