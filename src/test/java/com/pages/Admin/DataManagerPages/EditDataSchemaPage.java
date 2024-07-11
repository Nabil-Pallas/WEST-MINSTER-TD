package com.pages.Admin.DataManagerPages;


import com.page.base.DashboardPage;

import java.util.List;

import com.pages.Admin.AdminPage;
import org.testng.Assert;

public class EditDataSchemaPage extends DataManagerPage {
    private String EditDataSchemaColumnsText = "//h1[text()='Edit Data Schema Columns']";
    private String parameterTypeText = "(//input[@class='dx-texteditor-input'])[4]";
    private String selectParameterType = "((//input[@class='dx-texteditor-input'])[4]/following::div[@class='dx-texteditor-buttons-container'])[2]";
    private String selectList = "//input[@name='DATA_LIST_LABEL']//..";

    public EditDataSchemaPage validateEditDataSchemaColumnsPage() {
        waitForElementToLoad(EditDataSchemaColumnsText);
        if (isVisible(EditDataSchemaColumnsText, "Edit Data Schema")) {
            reportPass("User successfully reached the correct Edit Data Schema Columns page.");
        } else {
            reportFailAndThrowException("Edit Data Schema Columns Page is not loaded.");
        }
        return this;
    }

    public EditDataSchemaPage validateEditFieldValidationParametersPage() {
        waitForElementToLoad(frameDivTextLocator("Edit Field Validation Parameters"));
        if (isVisible(frameDivTextLocator("Edit Field Validation Parameters"), "Edit Field Validation Parameters")) {
            reportPass("User successfully reached the correct Edit Field Validation Parameters page.");
        } else {
            reportFailAndThrowException("Edit Data Schema Columns Page is not loaded.");
        }
        return this;
    }

    public EditDataSchemaPage validateAddFieldValidationParametersPage() {
        waitForElementToLoad(frameDivTextLocator("Add Field Validation Parameters"));
        if (isVisible(frameDivTextLocator("Add Field Validation Parameters"), "Add Field Validation Parameters")) {
            reportPass("User successfully reached the correct Add Field Validation Parameters page.");
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

    public AdminPage validateAddedNewColumn(String fieldName) {
        System.out.println("fieldName-------" + fieldName);
        pause("medium");
        pause("medium");
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
        return new AdminPage();
    }

    public EditDataSchemaPage clickOnActionIcon(String fieldName) {
        pause("medium");
        scrollToElement("//td[text()='" + fieldName + "']");
        forceClick("(//td[text()='" + fieldName + "']//following-sibling::td[contains(@class,'action-edit-delete-column')]/a)[1]", fieldName, "Action Icon");
        return this;
    }

    public EditDataSchemaPage clickOnEditValidation() {
        click(frameSpanTextLocator("Edit Validation"), "Edit Validation", "Edit Validation");
        return this;
    }

    public EditDataSchemaPage clickOnAddValidation() {
        click(frameSpanTextLocator("Add Validation"), "Add Validation", "Add Validation");
        return this;
    }

    public EditDataSchemaPage selectParameterType() {
        String getParameterTypeText = getInnerText(parameterTypeText);
        if (getParameterTypeText.equals("List")) {
            reportPass("Parameter type is already in List");
        } else {
            clickAndChoose(selectParameterType, frameDivTextLocator("List"), "List", "Parameter Type");
        }
        return this;
    }

    public EditDataSchemaPage selectAList(String list) {
        clickAndChoose(selectList, frameUserSearchResult(list), list, "Select A List");
        return this;
    }

    public EditDataSchemaPage clickOnApplyBtn() {
        click(frameSpanTextLocator("Apply"), "Apply", "Button");
        return this;
    }

    public EditDataSchemaPage clickOnExit() {
        click(frameSpanTextLocator("< Exit"), "Exit Button");
        pause("medium");
        return this;
    }

    public EditDataSchemaPage addFieldValidationParameters(String list) {
        selectParameterType()
                .selectAList(list)
                .clickOnApplyBtn()
                .clickOnExit();
        return this;
    }

    public EditDataSchemaPage clickOnApplyAndExitBtn() {
        pause("medium");
        pause("medium");
        click(applyBtnForDataSchema, "Apply Button");
//        pause("medium");
        click(frameSpanTextLocator("< Exit"), "Exit Button");
        waitForLoaderToDisappear();
        pause("medium");
        return new EditDataSchemaPage();
    }
}

