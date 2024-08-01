package com.pages.Report;

import com.page.base.MenuPage;
import com.pages.Admin.AdminPage;
import org.testng.Assert;

public class AllReportsPage extends ReportsPage {
    protected String reportTitleSearchBox = "(//div[text()='Report Title']//following::tr//input)[1]";
    private String scheduledDeliveryContent = "//div[text()='Scheduled Delivery']/following::tr[2]//td[3]";


    //Validate All Reports Page
    public AllReportsPage validateAllReportsPage() {
        waitForElementToLoad(allReportsText);
        if (isVisible(allReportsText, "All Reports Text")) {
            reportPass("User successfully reached the correct All Reports page");
        } else {
            reportFailAndThrowException("All Report Page is not loaded.");
        }
        return this;
    }

    public AllReportsPage searchReportTitle(String reportTitle) {
        clickAndType(reportTitleSearchBox, reportTitle, "Report Title");
        return this;
    }

    public AllReportsPage validateReportTitle(String reportTitle) {
        waitForAppearance(frameAnchorTag(reportTitle), reportTitle);
        boolean verifyExactText = verifyExactText(frameAnchorTag(reportTitle), reportTitle);
        Assert.assertTrue(verifyExactText);
        return this;
    }

    public AllReportsPage validateScheduledDeliveryContent(String expectedscheduledDeliveryContent) {
        String getInnerText = getInnerText(scheduledDeliveryContent);
        Assert.assertEquals(getInnerText, expectedscheduledDeliveryContent);
        return this;
    }

    public ViewReportPage clickOnReportTitle(String reportTitle) {
        click(frameAnchorTag(reportTitle), reportTitle, "Report Title");
        waitForLoaderToDisappear();
        return new ViewReportPage();
    }


}
