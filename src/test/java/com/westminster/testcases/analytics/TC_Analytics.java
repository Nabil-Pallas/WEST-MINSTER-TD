package com.westminster.testcases.analytics;

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
public class TC_Analytics extends WestMinsterPageHook {

    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY})
    public void setReportValues() {
        testcaseName = "Login with " + xmlUserLevel;
        testDescription = "Login with " + xmlUserLevel;
        authors = "Automation Team";
        category = "Login for Analytics Module";
    }

    //Ring chart - 5774
    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY})
    public void createRingChart() {
        testcaseName = "TC_5774";
        testDescription = "Create New Analytics - Ring chart and validate";
        authors = "Automation Team";
        category = "Create Ring Chart";
        startTestCase();
        setNode();

        analysisName.set(FakerDataFactory.getNameWithTimeStamp("Analysis"));
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAnalytics()
                .validateAnalyticsPage()
                .setClient()
                .clickOnCreateNewDataAnalysis()
                .validateNewAnalysisPage()
                .addFilterAndValidate("Broker Name", "=")
                .clickOnNextBtn()
                .selectVisualizationType("Ring Chart")
                .clickOnNextBtn()
                .plotDetailsRingChart("Broker Name", "Base Gross Commission", "5")
                .clickOnNextBtn()
                .reviewYourAnalysis(analysisName.get(), "Blue")
                .clickOnSaveBtn()
                .clickOnBackBtn()
                .validateAnalysis(analysisName.get())
                .deleteAnalytics(analysisName.get());
        System.out.println("TC-----------TC_5774");
    }

    //Bar chart - 6044
    @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY})
    public void createBarChart() {
        testcaseName = "TC_6044";
        testDescription = "Create New Analytics - Bar chart and validate";
        authors = "Automation Team";
        category = "Create Bar Chart";
        startTestCase();
        setNode();

        analysisName.set(FakerDataFactory.getNameWithTimeStamp("Analysis"));
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAnalytics()
                .validateAnalyticsPage()
                .setClient()
                .clickOnCreateNewDataAnalysis()
                .validateNewAnalysisPage()
                .addFilterAndValidate("Broker Name", "=")
                .clickOnNextBtn()
                .selectVisualizationType("Bar Chart")
                .clickOnNextBtn()
                .plotDetailsBarChart("Broker Name", "Local Gross Commission", "Base Gross Commission")
                .clickOnNextBtn()
                .reviewYourAnalysis(analysisName.get(), "Indigo")
                .clickOnSaveBtn()
                .clickOnBackBtn()
                .validateAnalysis(analysisName.get())
                .deleteAnalytics(analysisName.get());
        System.out.println("TC-----------TC_6044");
    }

    //Pivot table - 6045
    @Test(priority = 3, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY})
    public void createPivotTable() {
        testcaseName = "TC_6045";
        testDescription = "Create New Analytics - Pivot table and validate";
        authors = "Automation Team";
        category = "Create Pivot Table";
        startTestCase();
        setNode();

        analysisName.set(FakerDataFactory.getNameWithTimeStamp("Analysis"));
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAnalytics()
                .validateAnalyticsPage()
                .setClient()
                .clickOnCreateNewDataAnalysis()
                .validateNewAnalysisPage()
                .addFilterAndValidate("Broker Name", "=")
                .clickOnNextBtn()
                .selectVisualizationType("Pivot Table")
                .clickOnNextBtn()
                .plotDetailsPivotTable("Broker Name", "Base Gross Commission", "Trade Date", "Settlement Date")
                .clickOnNextBtn()
                .reviewYourAnalysis(analysisName.get(), "Black")
                .clickOnSaveBtn()
                .clickOnBackBtn()
                .validateAnalysis(analysisName.get())
                .deleteAnalytics(analysisName.get());
        System.out.println("TC-----------TC_6045");
    }

    //Scatter plot - 4734
    @Test(priority = 4, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY})
    public void createScatterPlot() {
        testcaseName = "TC_4734";
        testDescription = "Create New Analytics - Scatter plot and validate";
        authors = "Automation Team";
        category = "Create Scatter Plot";
        startTestCase();
        setNode();

        analysisName.set(FakerDataFactory.getNameWithTimeStamp("Analysis"));
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAnalytics()
                .validateAnalyticsPage()
                .setClient()
                .clickOnCreateNewDataAnalysis()
                .validateNewAnalysisPage()
                .addFilterAndValidate("Broker Name", "=")
                .clickOnNextBtn()
                .selectVisualizationType("Scatter Plot")
                .clickOnNextBtn()
                .plotDetailsScatterPlot("Trade Date", "Executed Quantity")
                .clickOnNextBtn()
                .reviewYourAnalysis(analysisName.get(), "Green")
                .clickOnSaveBtn()
                .clickOnBackBtn()
                .validateAnalysis(analysisName.get())
                .deleteAnalytics(analysisName.get());
        System.out.println("TC-----------TC_4734");
    }

    //Line chart - 5594
    @Test(priority = 5, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY})
    public void createLineChart() {
        testcaseName = "TC_5594";
        testDescription = "Create New Analytics - Line chart and validate";
        authors = "Automation Team";
        category = "Create Line Chart";

        startTestCase();
        setNode();
        analysisName.set(FakerDataFactory.getNameWithTimeStamp("Analysis"));
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAnalytics()
                .validateAnalyticsPage()
                .setClient()
                .clickOnCreateNewDataAnalysis()
                .validateNewAnalysisPage()
                .addFilterAndValidate("Broker Name", "=")
                .clickOnNextBtn()
                .selectVisualizationType("Line Chart")
                .clickOnNextBtn()
                .plotDetailsLineChart("Trade Date", "Local Gross Commission", "Base Gross Commission")
                .clickOnNextBtn()
                .reviewYourAnalysis(analysisName.get(), "Indigo")
                .clickOnSaveBtn()
                .clickOnBackBtn()
                .validateAnalysis(analysisName.get())
                .deleteAnalytics(analysisName.get());
        System.out.println("TC-----------TC_5594");
    }

    //Summary table - 5773
    @Test(priority = 6, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY})
    public void createSummaryTable() {
        testcaseName = "TC_5773";
        testDescription = "Create New Analytics - Summary Table and validate";
        authors = "Automation Team";
        category = "Create Summary Table";
        startTestCase();
        setNode();

        analysisName.set(FakerDataFactory.getNameWithTimeStamp("Analysis"));
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAnalytics()
                .validateAnalyticsPage()
                .setClient()
                .clickOnCreateNewDataAnalysis()
                .validateNewAnalysisPage()
                .addFilterAndValidate("Broker Name", "=")
                .clickOnNextBtn()
                .selectVisualizationType("Summary Table")
                .clickOnNextBtn()
                .plotDetailsSummaryTable("Broker Name", "Local Gross Commission", "Base Gross Commission")
                .clickOnNextBtn()
                .reviewYourAnalysis(analysisName.get(), "Indigo")
                .clickOnSaveBtn()
                .clickOnBackBtn()
                .validateAnalysis(analysisName.get())
                .deleteAnalytics(analysisName.get());
        System.out.println("TC-----------TC_5773");
    }

    //Phase -03 --Nabil
    //TC_RE_6104
    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.REGRESSION})
    public void verifyPivotTableToggleOptionForFullData(){
        testcaseName = "TC_RE_6104";
        testDescription = "Verify Pivot table toggle option by selecting sample data to full data";
        authors = "Automation Team";
        category = "Verification of Pivot Table";
        startTestCase();
        setNode();

        analysisName.set(FakerDataFactory.getNameWithTimeStamp("Analysis"));
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAnalytics()
                .validateAnalyticsPage()
                .setClient()
                .clickOnCreateNewDataAnalysis()
                .validateNewAnalysisPage()
                .clickOnNextBtn()
                .selectVisualizationType("Pivot Table")
                .clickOnNextBtn()
                .plotDetailsPivotTable("Broker Name", "Base Gross Commission", "Trade Date", "Settlement Date")
                .clickOnNextBtn()
                .reviewYourAnalysis(analysisName.get(), "Black")
                .getGrandTotalInReviwAnalysis()
                .clickOnSaveBtn()
                .getGrandTotalInViewAnalysis()
                .validateGrandTotal()
                .clickOnBackBtn();

    }

    //Phase -03 --Nabil
    //TC_RE_6
    @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.REGRESSION})
    public void verifyEditAnalysis() {
        testcaseName = "TC_RE_6";
        testDescription = "Verify whether the analytics can be edited using edit analysis";
        authors = "Automation Team";
        category = "Edit Analysis";
        startTestCase();
        setNode();

        analysisName.set(FakerDataFactory.getNameWithTimeStamp("Analysis"));
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAnalytics()
                .validateAnalyticsPage()
                .setClient()
                .clickOnCreateNewDataAnalysis()
                .validateNewAnalysisPage()
                .clickOnNextBtn()
                .selectVisualizationType("Bar Chart")
                .clickOnNextBtn()
                .plotDetailsBarChart("Broker Name", "Local Gross Commission", "Base Gross Commission")
                .clickOnNextBtn()
                .reviewYourAnalysis(analysisName.get(), "Indigo")
                .clickOnSaveBtn()
                .clickOnBackBtn()
                .validateAnalysis(analysisName.get())
                .editAnalytics(analysisName.get())
                .validateEditAnalysisPage()
                .clickOnNextBtn()
                .clickOnNextBtn()
                .clickOnNextBtn()
                .reviewYourAnalysis("Demo-test", "Green")
                .clickOnSaveBtn()
                .clickOnBackBtn()
                .validateAnalysis("Demo-test")
                .deleteAnalytics("Demo-test");
    }

    //Phase -03 --Nabil
    //TC_RE_5019
    @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.REGRESSION})
    public void verifyNumericValuesInAnalysisStep1() {
        testcaseName = "TC_RE_5019";
        testDescription = "Verify whether numeric values can be filtered in step 1 in analytics";
        authors = "Automation Team";
        category = "Create Analysis";
        startTestCase();
        setNode();

        clientCompanyName.set(FakerDataFactory.getNameWithTimeStamp(""));
        number.set(FakerDataFactory.get4DigitNumber());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnClearAllFilters()
                .clickOnAddNewRowBtn()
                .validateAddNewRowPage()
                .addNewRow(clientCompanyName.get(),FakerDataFactory.getPastDate(),FakerDataFactory.getFutureDate(),"5432",number.get())
                .clickOnAddBtn()
                .clickOnApplyBtn()
                .mouseHoverOnDashboard()
                .clickOnAnalytics()
                .validateAnalyticsPage()
                .clickOnCreateNewDataAnalysis()
                .addFilterAndValidate("Local Executed Value","=",number.get(),4)
                .validateFilteredDataInStep1("Local Executed Value",number.get());



    }


}
