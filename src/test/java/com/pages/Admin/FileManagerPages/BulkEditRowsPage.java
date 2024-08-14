package com.pages.Admin.FileManagerPages;

import com.pages.Admin.DataManagerPages.AddNewRowPage;

public class BulkEditRowsPage extends ErrorQueuePage {

    private String bulkEditRowsText = "//div[text()='Bulk Edit Rows']";
    private String applyBtn = "//span[text()='Apply']";

    public BulkEditRowsPage validateBulkEditRowsPage() {
        waitForElementToLoad(bulkEditRowsText);
        if (isVisible(bulkEditRowsText, "Bulk Edit Rows Text")) {
            reportPass("User successfully reached the correct Bulk Edit Rows page");
        } else {
            reportFailAndThrowException("Bulk Edit Rows Page is not loaded.");
        }
        return this;
    }

    // Type The Trade Date
    public BulkEditRowsPage typeTradeDate(String tradeDate) {
        type(frameClientDetailsLocator("Trade  Date:", 2), tradeDate, "tradeDate");
        return this;
    }

    // Type The Trade Date
    public BulkEditRowsPage typeSettlementDate(String settlementDate) {
        type(frameClientDetailsLocator("Settlement  Date:", 2), settlementDate, "Settlement Date");
        return this;
    }

    public BulkEditRowsPage editInvalidRowInfo(String tradeDate, String settlementDate) {
        typeTradeDate(tradeDate);
        pause("low");
        typeSettlementDate(settlementDate);
        pause("low");
        reportPass("User successfully edited the invalid row.");
        return this;
    }

    public BulkEditRowsPage changeDuplicateStatus() {
        pause("low");
        clickAndChoose("(//div[text()=' Duplicate Status: ']/ancestor::label/following-sibling::div//input)[2]", frameDivTextLocator("Override"), "Override", "Override");
        return this;
    }

    public ErrorQueuePage clickOnApplyBtn() {
        pause("low");
        click(applyBtn, "Apply Btn");
        reportPass("User successfully clicked the Apply button.");
        return new ErrorQueuePage();
    }


}
