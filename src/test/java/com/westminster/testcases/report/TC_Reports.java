package com.westminster.testcases.report;

import com.pages.Report.myReportsPages.ViewReportPage;
import com.ui.utilities.TestGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.dynamic.FakerDataFactory;
import com.listener.TestListener;
import com.page.base.DashboardPage;
import com.page.base.WestMinsterPageHook;

@Listeners(TestListener.class)
public class TC_Reports extends WestMinsterPageHook {

    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY, TestGroups.Type.REGRESSION})
    public void setReportValues() {
        testcaseName = "Login with " + xmlUserLevel;
        testDescription = "Login with " + xmlUserLevel;
        authors = "Automation Team";
        category = "Login for Report Module";

    }

    //Create Report - 5785
    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY})
    public void createReports() {
        testcaseName = "TC_5785";
        testDescription = "Create New Report and Validate";
        authors = "Automation Team";
        category = "Create Report";
        startTestCase();
        setNode();

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
                .validateMyReportTitle(reportName.get())
                .deleteReport(reportName.get());
        System.out.println("TC-----------TC_5785");

    }


    @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY})
    public void editReports() {
        testcaseName = "TC_133";
        testDescription = "Edit a report and validate";
        authors = "Automation Team";
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
                .validateEditReportsPage()
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
        System.out.println("TC-----------TC_133");

    }

    //Report Delivery -5674
    @Test(priority = 3, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY})
    public void reportDelivery() {
        testcaseName = "TC_5674";
        testDescription = "Report Delivery";
        authors = "Automation Team";
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
        System.out.println("TC-----------TC_5674");
    }

    //Phase -02 --Manoj
    //TC_RE_4690 - Verify whether a automatic delivery schedule for an report can be cancelled or disabled.
    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.REGRESSION})
    public void disableReportSchedule() {
        testcaseName = "TC_RE_4690";
        testDescription = "Report Delivery";
        authors = "Automation Team";
        category = "Report Delivery";
        startTestCase();
        setNode();

        reportName.set(FakerDataFactory.getNameWithTimeStamp("Report"));
        titleTwo.set(FakerDataFactory.getTitle());
        descriptionTwo.set(FakerDataFactory.getDescription());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnReports()
                .validateReportsPage()
                .setClient()
                .clickOnCreateNewReport()
                .validateNewReportsPage()
                .fillReportDetail(reportName.get(), "Mosaic", "Blue")
                .selectSlideTemplate("Intro")
                .fillIntroPage("RegressionTesting", "Automatic Delivery Schedule")
                .validateIntroPage("RegressionTesting", "Automatic Delivery Schedule")
                .clickOnAddNewPageAndValidate("Add a budget or analysis", 1)
                .selectSlideTemplate("1-Column")
                .fillAnalyticsPage(titleTwo.get(), descriptionTwo.get())
                .validateAnalyticsPage(titleTwo.get(), descriptionTwo.get());
        new ViewReportPage()
                .clickOnDeliveryBtn()
                .validateReportDeliverySettingsPage()
                .checkScheduleAutomaticDelivery(true)
                .clickOnCCCheckBox()
                .chooseRecipient(false)
                .clickRepeatOn("Sun")
                .clickOnSaveBtn()
                .clickOnAllReports()
                .validateAllReportsPage()
                .searchReportTitle(reportName.get())
                .validateReportTitle(reportName.get())
                .validateScheduledDeliveryContent("No")
                .clickOnReportTitle(reportName.get())
                .validateViewReportPage();

        System.out.println("TC-----------TC_RE_4690");
    }
}