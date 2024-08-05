package com.pages.Report.myReportsPages;

import java.util.List;

import com.microsoft.playwright.PlaywrightException;
import com.page.base.DashboardPage;
import com.pages.Report.ReportsPage;
import org.testng.Assert;

public class ViewReportPage extends ReportsPage {

    private String defineRecipientTextBox = "(//span[contains(text(),'Define the recipients')]//following::input)[1]";
    private String recipientInnerText = "//div[@class='wdx-custom-dropdown__secondary']";
    private String reportPages = "(//div[@class='report-page__thumbnail'])[1]";
    private String repeat = "//div[@class='dx-dropdowneditor-input-wrapper dx-selectbox-container']//input";


    public String recipient = getInnerText("(//div[@class='wdx-custom-dropdown__secondary'])[1]");


    public ViewReportPage validateViewReportPage() {
        waitForElementToLoad(frameh1TextLocator("View Report"));
        pause("medium");
        if (isVisible(frameh1TextLocator("View Report"), "View Report Text")) {
            reportPass("User successfully reached the correct View Reports page");
        } else {
            reportFailAndThrowException("View Report Page is not loaded.");
        }
        return this;
    }

    public ViewReportPage validateReportDeliverySettingsPage() {
        pause("medium");
        if (isVisible(frameDivTextLocator("Report Delivery Settings"), "Report Delivery Settings")) {
            reportPass("User successfully reached the correct Report Delivery Settings page");
        } else {
            reportFailAndThrowException("Report Delivery Settings Page is not loaded.");
        }
        return this;
    }

    public ViewReportPage clickOnDeliveryBtn() {
        pause("low");
        click(frameSpanTextLocator("Delivery"), "Delivery");
        return this;
    }

    public ViewReportPage checkScheduleAutomaticDelivery(boolean onOrOff) {
        pause("low");
        if (onOrOff) {
            click("//div[@class='dx-switch-inner']", "Schedule automatic delivery");
            boolean isChecked = isEnabled("//div[@class='dx-switch-inner']","Schedule automatic delivery");
            Assert.assertTrue(isChecked);
        }
        return this;
    }

    public ViewReportPage chooseRepeat(String placeholder) {
        pause("low");
        clickAndChoose(frameDropDownlocator(3), frameDivTextLocator(placeholder), placeholder, "Repeat");
        return this;
    }

    public ViewReportPage typeRepeatEvery(String noOfDays) {
        pause("low");
        type("//input[@role='spinbutton']", noOfDays, "Repeat Every");
        return this;
    }

    public ViewReportPage clickRepeatOn(String day) {
        pause("low");
        click(frameSpanTextLocator(day), day, "Repeat On");
        return this;
    }

    public ViewReportPage setScheduledTime(String time) {
        pause("low");
        return this;
    }

    public ViewReportPage setTimeZone(String timeZone) {
        pause("low");
        clickAndChoose(frameDropDownlocator(6), frameDivTextLocator(timeZone), timeZone, "Repeat");
        return this;
    }//Kolkata

    public ViewReportPage setStartRepeatDate(String startDate) {
        pause("low");
        clickAndChoose(frameDropDownlocator(7), frameSpanTextLocator(startDate), startDate, "Repeat");
        return this;
    }

    public ViewReportPage chooseRecipient(List<String> recipientNames, Boolean selectAll) {
        pause("low");
        click(defineRecipientTextBox, "Define the Recipient", "Text Box");
        if (selectAll) {
            click(frameDivTextLocator("Select All"), "Select All", "Check Box");
        } else {
            for (String recipient : recipientNames) {
                click(frameDivTextLocator(recipient), "Recipient", "Check Box");
            }

        }
        return this;
    }

    public ViewReportPage chooseRecipient(Boolean selectAll) {
        pause("low");
        click(defineRecipientTextBox, "Define the Recipient", "Text Box");
        if (selectAll) {
            click(frameDivTextLocator("Select All"), "Select All", "Check Box");
        } else {
            recipientEmail.set(getInnerText("(//div[@class='wdx-custom-dropdown__secondary'])[1]"));
            click(frameDivTextLocator(recipientEmail.get()), "Recipient", "Check Box");
            click(defineRecipientTextBox, "Define the Recipient", "Text Box");
        }
        reportPass("User successfully choose recipient.");
        return this;
    }

    public ViewReportPage clickOnCCCheckBox() {
        pause("low");
        click(frameSpanTextLocator("CC me:"), "CC me:", "Check Box");
        return this;
    }

    public ViewReportPage clickOnSendNowBtn() {
        pause("low");
        click(frameSpanTextLocator("Send Now"), "Send Now");
        pause("medium");
        if (isVisible(frameSpanTextLocator("Delivery Error"))) {
            String errorMsg = getInnerText(frameSpanTextLocator("Delivery Error"));
            reportStep("Error Message : \n" + errorMsg, "fail");
        }
        reportPass("User successfully clicked the Send Now button.");

        return this;
    }

    public DashboardPage validateErrorMsg() {
        try {
            String errorMsg = getInnerText("//*[text(),'Delivery']");
            reportStep("Error Message : \n" + errorMsg, "fail");
        } catch (PlaywrightException e) {

        }
        return new DashboardPage();
    }

    public ViewReportPage clickOnActionBtn() {
        pause("low");
        waitForElementToLoad(frameSpanTextLocator("⋯"));
        click(frameSpanTextLocator("⋯"), "Action Icon");
        return this;
    }

    public ReportDeliveryHistoryPage clickOnHistory() {
        pause("medium");
        click(frameSpanTextLocator("History"), "History");
        return new ReportDeliveryHistoryPage();
    }

    public ViewReportPage clickOnSaveBtn() {
        pause("low");
        click(frameSpanTextLocator("Save"), "Save");
        return this;
    }

    public ViewReportPage validate(String locator, String text) {
        boolean verifyExactText = verifyExactText(locator, text);
        Assert.assertTrue(verifyExactText);
        return this;
    }


    public ViewReportPage addAnalytics() {
        click(frameAddBudgeOrAnalysisLocator("Analytics", 1), "Analytics", "visualization ");
        pause("medium");
        if (isVisible(frameSpanTextLocator("I understand"))) {
            click(frameSpanTextLocator("I understand"), "I understand");
        }
        return this;
    }


}
