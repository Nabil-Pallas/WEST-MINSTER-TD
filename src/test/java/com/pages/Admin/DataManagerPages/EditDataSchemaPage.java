package com.pages.Admin.DataManagerPages;


import com.page.base.DashboardPage;

import java.util.List;

import org.testng.Assert;

public class EditDataSchemaPage extends DataManagerPage {
    String EditDataSchemaColumnsText = "//h1[text()='Edit Data Schema Columns']";

    public EditDataSchemaPage validateEditDataSchemaColumnsPage() {
        waitForElementToLoad(EditDataSchemaColumnsText);
        if (isVisible(EditDataSchemaColumnsText, "Edit Data Schema")) {
            reportPass("User successfully reached the correct Edit Data Schema Columns page.");
        } else {
            reportFailAndThrowException("Edit Data Schema Columns Page is not loaded.");
        }
        return this;
    }


    public AddNewColumnPage clickOnAddNewColumn() {
        click(frameSpanTextLocator("Add new column"), "Add new column");
        pause("low");
        return new AddNewColumnPage();
    }

    public DashboardPage mouseHover() {
        if (mouseOver("(//table)[3]//tbody/tr[1]/td[1]", "Edit Data Schema mouse hover ")) {
            reportPass("Mouse Hover on Edit Data Schema is success.");
        } else {
            reportFailAndThrowException("Mouse Hover on Edit Data Schema is failed");
        }
        return new DashboardPage();
    }

    public AddNewColumnPage validateAddedNewColumn(String fieldName) {
        pause("medium");
        int headerCount = getLocatorsCount("((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])");
        for (int i = 1; i <= headerCount; i++) {
            String headerElement = "(((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1]))[" + i + "]";
            String headerText = getInnerText(headerElement);

            if (headerText.contains(fieldName)) {
                boolean verifyExactText = verifyExactText(headerElement, fieldName);
                Assert.assertTrue(verifyExactText);
                break;
                // Exit once the match is found and validated
            }
        }
        return new AddNewColumnPage();
    }

    public EditDataSchemaPage clickOnApplyAndExitBtn() {
        pause("medium");
        pause("medium");
        click(applyBtnForDataSchema, "Apply Button");
        pause("medium");
        click(frameSpanTextLocator("< Exit"), "Exit Button");
        waitForLoaderToDisappear();
        pause("medium");
        return new EditDataSchemaPage();
    }
}

