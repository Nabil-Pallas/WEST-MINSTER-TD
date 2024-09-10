package com.pages.Report.allReportsPages;

import com.pages.Report.ReportsPage;
import com.pages.Report.myReportsPages.ViewReportPage;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AllReportsPage extends ReportsPage {
    protected String reportTitleSearchBox = "(//div[text()='Report Title']//following::tr//input)[1]";
    private String scheduledDeliveryContent = "//div[text()='Scheduled Delivery']/following::tr[2]//td[3]";
    private String reportTimeText = "(//tbody//tr)[5]//td[2]";


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
        waitForElementToLoad(reportTitleSearchBox);
        clickAndType(reportTitleSearchBox, reportTitle, "Report Title");
        pause("low");
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

    public ViewReportPage validateDateAndTime() {

        //Date
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy, HH:mm:ss");

        //Actual Report Time present in the Column
        String innerReportTimeText = getInnerText(reportTimeText);

        // End date for comparing
        String endDateStr = formatter.format(date);

        try {
            // Parse the date strings into Date objects
            Date createdReportTime = formatter.parse(getTime.get());
            Date actualReportTime = formatter.parse(innerReportTimeText);
            Date endReportTime = formatter.parse(endDateStr);

            // Print the parsed dates for debugging
            System.out.println("Created Report Time: " + formatter.format(createdReportTime));
            System.out.println("Actual Report Time: " + formatter.format(actualReportTime));
            System.out.println("End Report Time: " + formatter.format(endReportTime));

            // Validate if actualReportTime is within the range from createdReportTime to endReportTime
            if (actualReportTime.after(createdReportTime) && actualReportTime.before(endReportTime)) {
                System.out.println("The report time is within the expected range.");
                reportPass("The report time is within the expected range :" + actualReportTime);
            } else {
                System.out.println("The report time is not within the expected range.");
                reportFail("The report time is not within the expected range.");
            }

        } catch (ParseException e) {
            System.out.println("Date parsing error: " + e.getMessage());
        }


        return new ViewReportPage();
    }


}
