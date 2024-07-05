package com.westminster.testcases.report;

import com.config.Configuration;
import com.config.ConfigurationManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.dynamic.FakerDataFactory;
import com.listener.TestListener;
import com.page.base.DashboardPage;
import com.page.base.WestMinsterPageHook;

@Listeners(TestListener.class)
public class TC_Reports extends WestMinsterPageHook {

    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser"})
    public void setReportValues() {
        testcaseName = "TC_5785 " + xmlUserLevel;
        testDescription = "Create New Report and Validate";
        authors = "Srikanth";
        category = "Create Report";

    }

    //Create Report - 5785
    @Test(priority = 19, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser"})
    public void createReports() {

        reportName.set(FakerDataFactory.getNameWithTimeStamp("Report"));
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnReports()
                .validateReportsPage()
                .setClient()
                .clickOnCreateNewReport()
                .validateNewReportsPage()
                .fillReportDetail(reportName.get(), "Mosaic", "Blue")
                .selectSlideTemplate("Intro")
                .fillIntroPage("SanityTesting", "Sanity Test Description")
                .validateIntroPage("SanityTesting", "Sanity Test Description")
                .clickOnAddNewPageAndValidate("Add a budget or analysis", 1)
                .selectSlideTemplate("1-Column")
                .fillAnalyticsPage("This is Analytics Page", "Analytics Name")
                .validateAnalyticsPage("This is Analytics Page", "Analytics Name")
                .clickOnAddNewPageAndValidate("Add a budget or analysis", 2)
                .selectSlideTemplate("1-Column")
                .fillBudgetPage("This is Budget Page", "Budget Name")
                .validateBudgetingPage("This is Budget Page", "Budget Name")
                .clickOnViewReport()
                .clickOnExitBtn()
                .validateMyReportTitle(reportName.get());
        System.out.println("TC-----------19");

    }


//    @Test(priority = 20, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser"})
    public void editReports() {
        testcaseName = "TC_133 " + xmlUserLevel;
        testDescription = "Edit a report and validate";
        authors = "Srikanth";
        category = "Edit Reports";
        startTestCase();
        setNode();
        reportName.set(FakerDataFactory.getNameWithTimeStamp("Report"));
        titleone.set(FakerDataFactory.getTitle());
        titleTwo.set(FakerDataFactory.getTitle());
        descriptionOne.set(FakerDataFactory.getDescription());
        descriptionTwo.set(FakerDataFactory.getDescription());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnReports()
                .validateReportsPage()
                .setClient()
                .clickOnReport()
                .clickOnEditReport()
                .editReportTitle(reportName.get())
                .changeTheme("Simple")
                .changePalette("Cobalt")
                .addTitle(titleone.get())
                .addDescription(descriptionOne.get())
                .validateIntroPage(titleone.get(), descriptionOne.get())
                .fillAnalyticsPage(titleTwo.get(), descriptionTwo.get())
                .validateAnalyticsPage(titleTwo.get(), descriptionTwo.get())
                .clickOnViewReport()
                .clickOnExitBtn()
                .validateMyReportTitle(reportName.get());
        System.out.println("TC-----------20");

    }

    //Report Delivery -5674
    @Test(priority = 21, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser"})
    public void reportDelivery() {
        testcaseName = "TC_5674 " + xmlUserLevel;
        testDescription = "Report Delivery";
        authors = "Srikanth";
        category = "Report Delivery";
        startTestCase();
        setNode();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnReports()
                .validateReportsPage()
                .setClient()
                .clickOnReportToView()
                .validateViewReportPage()
                .clickOnDeliveryBtn()
                .validateReportDeliverySettingsPage()
                .clickOnCCCheckBox()
                .chooseRecipient(false)
                .clickOnSendNowBtn()
                .clickOnActionBtn()
                .clickOnHistory()
                .validateReportDeliveryHistoryPage()
                .validateRecipients();
        System.out.println("TC-----------21");
    }
}