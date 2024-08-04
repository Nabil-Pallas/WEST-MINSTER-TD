package com.pages.Report.myReportsPages;

import com.page.base.DashboardPage;
import com.pages.Report.ReportsPage;
import org.testng.Assert;

import java.util.List;

public class ReportDeliveryHistoryPage extends ReportsPage {

    //    private String recipientsText = "((//tbody[@role='presentation'])[3]//tr)[1]//td[3]";
    private String recipientsText = "((//tbody[@role='presentation'])[3]//tr)[1]//td[3]//span";


    //Method to Validate the Report Delivery History Page
    public ReportDeliveryHistoryPage validateReportDeliveryHistoryPage() {
        pause("medium");
        waitForLoaderToDisappear();
        if (isVisible(frameh1TextLocator("Report Delivery History"), "Report Delivery History Text")) {
            reportPass("User successfully reached the correct Report Delivery History page");
        } else {
            reportFailAndThrowException("Report Delivery History Page is not loaded.");
        }
        return this;
    }

    //Method to Validate the Recipients Name
    public DashboardPage validateRecipients(List<String> recipientNames) {
        pause("low");
        String text = getInnerText(recipientsText);
        System.out.println(text);
        for (String recipientName : recipientNames) {
            boolean verifyExactText = verifyPartialText(recipientsText, recipientName);
            System.out.println(recipientName);
            Assert.assertTrue(verifyExactText);
        }
        return new DashboardPage();
    }

    public DashboardPage validateRecipients() {
        pause("low");
        String text = getInnerText(recipientsText).replaceAll("<", "").replaceAll(">", "");
//        System.out.println("Recipient name " + text);
//        System.out.println("Recipient name " + recipientEmail.get());
        if (isVisible(recipientsText)) {
            Assert.assertTrue(getInnerText(recipientsText).toLowerCase().trim().contains(recipientEmail.get().toLowerCase().trim()));
            reportPass("User successfully validated the Recipient as expected");
        } else {
            reportFail("There is no Recipients found in the history");
        }

        return new DashboardPage();
    }

}
