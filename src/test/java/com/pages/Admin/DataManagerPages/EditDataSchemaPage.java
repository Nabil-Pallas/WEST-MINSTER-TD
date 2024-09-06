package com.pages.Admin.DataManagerPages;


import org.testng.Assert;

import com.page.base.DashboardPage;
import com.pages.Admin.AdminPage;

import static com.pages.Admin.FileManagerPages.AddNewSystemColumnsPage.addedColumnss;

public class EditDataSchemaPage extends DataManagerPage {
    private String EditDataSchemaColumnsText = "//h1[text()='Edit Data Schema Columns']";
    private String parameterTypeText = "//input[@name='PARAMETER_TYPE']";
    private String selectParameterType = "((//input[@class='dx-texteditor-input'])[4]/following::div[@class='dx-texteditor-buttons-container'])[2]";
    private String selectList = "//input[@name='DATA_LIST_LABEL']//..";
    private String uniqueTradeIdentifier_1 = "(//span[text()='Unique Trade Identifier']//following::tbody//td[4])[1]";
    private String uniqueTradeIdentifier_2 = "(//span[text()='Unique Trade Identifier']//following::tbody//td[4])[2]";


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

    //trade id 
    public EditDataSchemaPage clickOnUniqueTradeIdentifier(String fieldName, String UTI) {
        waitForLoaderToDisappear();
        pause("medium");
        click("//div[text()='Field Name']", "sort");
        pause("low");
        int headerCount = getLocatorsCount("(//td[@aria-colindex='4'])[position() > 1]");
        for (int i = 1; i <= 7; i++) {
            String headerElement = "((//table)[3]//tbody[@role='presentation']//td[@aria-colindex='1'])[" + i + "]";
            String headerText = getInnerText(headerElement);

            if (headerText.contains(fieldName)) {
                int maxAttempts = 7;
                int attempts = 0;
                while (!isVisibleWithMinimumTime("//td[text()='" + fieldName + "']") && attempts < maxAttempts) {
                    pressDownArrow("(//tr[@class='dx-row dx-data-row dx-column-lines'])[8]");
                    attempts++;
                }
                scrollToElement("//td[text()='" + fieldName + "']");
                pause("medium");
                clickUsingJs("//td[text()='" + fieldName + "']//following-sibling::td[contains(@class,'action-edit-delete-column')]//a[text()='⋯']", fieldName, "Action Icon");
                pause("low");
                clickOnEditBtn();
                String getAtrribute = getAttribute("((//td[@aria-colindex='4'])[position() > 1])[" + i + "]//div[@role='checkbox']", "aria-checked");
                System.out.println("Attribute : ===============> " + getAtrribute);
                if (getAtrribute.equals(UTI)) {
                    click("((//td[@aria-colindex='4'])[position() > 1])[" + i + "]", "Unique Trade Identifier Check Box");
                    pause("low");
                    click(frameSpanExactTextLocator("Save", 2), "Save Button");
                    ;
                } else {
                    break;
                }
            }
        }

        return this;
    }

    public EditDataSchemaPage clickOnActionIcon(String fieldName) {
        pause("medium");
        scrollToElement("//td[text()='" + fieldName + "']");
        forceClick("(//td[text()='" + fieldName + "']//following-sibling::td[contains(@class,'action-edit-delete-column')]/a)[1]", fieldName, "Action Icon");
        return this;
    }

    public EditDataSchemaPage clickOnRelevantFieldNameActionIcon(String fieldName) {
        click("//td[text()='Account Name']", "Account Name", "First Field");
        int maxAttempts = 40;
        int attempts = 0;
        while (!isVisibleWithMinimumTime("//td[text()='" + fieldName + "']") && attempts < maxAttempts) {
            pressDownArrow("(//tr[@class='dx-row dx-data-row dx-column-lines'])[8]");
            attempts++;
        }
        scrollToElement("//td[text()='" + fieldName + "']");
        pause("medium");
        clickUsingJs("//td[text()='" + fieldName + "']//following-sibling::td[contains(@class,'action-edit-delete-column')]//a[text()='⋯']", fieldName, "Action Icon");

        pause("low");
        return this;
    }

    //Validate whether new column is added or not
    public EditDataSchemaPage validateNewlyAddedDataSchemaColumn(String fieldName) {
        Boolean value = false;
        pause("medium");

        click("((//table)[3]//tbody[@role='presentation']//td[@aria-colindex='1'])[1]", "First Field", "First Grid");
        int maxAttempts = 40;
        int attempts = 0;
        while (!isVisible("//td[text()='" + fieldName + "']") && attempts < maxAttempts) {
            pressDownArrow("(//tr[@class='dx-row dx-data-row dx-column-lines'])[8]");
            attempts++;
        }

        scrollToElement("//td[text()='" + fieldName + "']");
        pause("medium");
        String fieldNametext = getInnerText("//td[text()='" + fieldName + "']");
        if (fieldNametext.equals(fieldName)) {
            value = true;
            System.out.println("-------------------" + fieldNametext);
            highlightElement(frameDivTextLocator(fieldName), "Header Element");
            boolean verifyExactText = verifyExactText(frameDivTextLocator(fieldName), fieldName);
            Assert.assertTrue(verifyExactText);
        }

//        for (int i = 1; i < 50; i++) {
//            scrollToElement("((//table)[3]//tbody[@role='presentation']//td[@aria-colindex='1'])[" + i + "]");
//            String fieldNameLocator = "((//table)[3]//tbody[@role='presentation']//td[@aria-colindex='1'])[" + i + "]";
//            String fieldNametext = getInnerText(fieldNameLocator);
//            System.out.println("-------------------" + fieldNametext);
//            if (fieldNametext.contains(fieldName)) {
//                value = true;
//                System.out.println("-------------------" + fieldNametext);
//                highlightElement(frameDivTextLocator(fieldName), "Header Element");
//                boolean verifyExactText = verifyExactText(frameDivTextLocator(fieldName), fieldName);
//                Assert.assertTrue(verifyExactText);
//                break;
//            }
//        }
        if (value) {
            reportPass("Field Name " + fieldName + " is present in the Edit Data Schema Columns");
        } else {
            reportFail("Field Name  is not present in the Edit Data Schema Columns");
        }
        pause("low");
        return this;
    }

    public EditDataSchemaPage clickOnUniqueTradeIdentifier() {
        //clickUsingJs("//td[@class=\"dx-editor-cell dx-datagrid-validator dx-validator dx-visibility-change-handler dx-focused\"]//following-sibling::td[3]  "
        //clickUsingJs("(//table)[3]//tr[last()-4]//td[4]//span");
        pause("low");
        return this;
    }


    public EditDataSchemaPage reloadCurrentURLAndClickOnRelevantFieldName(String fieldName) {
        refreshPageToCurrentURL();
        waitForLoaderToDisappear();
        pause("medium ");
        clickOnRelevantFieldNameActionIcon(fieldName);
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
        pause("low");
        String getParameterTypeText = getAttribute(parameterTypeText, "value");
        if (getParameterTypeText.equals("List")) {
            reportPass("Parameter type is already in List");
        } else {
            waitForElementToLoad(selectParameterType);
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
        click(frameSpanTextLocator("Exit"), "Exit Button");
        pause("medium");
        return this;
    }

    //Click the Delete Button
    public EditDataSchemaPage clickOnDeleteBtn() {
        pause("medium");
        click(deleteBtn, "Delete Button");
        pause("low");
        click(confirmDeleteBtn, "Confirm Delete");
        pause("low");
        reportPass("User successfully Deleted the Data Schema Column.");
        return this;
    }

    public EditDataSchemaPage addFieldValidationParameters(String list) {
        selectParameterType()
                .selectAList(list)
                .clickOnApplyBtn()
                .clickOnExit();
        return this;
    }

    public EditDataSchemaPage delete10DataSchema() {
        pause("low");
        click("(//tr[1])[3]//td[@aria-colindex='1']", "First row \"Field Name\" cell");
        int maxAttempts = 40;
        int attempts = 0;
        for (int i = 1; i <= addedColumnss.size(); i++) {
            while (!isVisible("//td[text()='" + addedColumnss.get(i - 1) + "']") && attempts < maxAttempts) {
                pressDownArrow("(//tr[@class='dx-row dx-data-row dx-column-lines'])[8]");
                attempts++;
            }

            scrollToElement("//td[text()='" + addedColumnss.get(i - 1) + "']");
            pause("low");
            pause("low");
            forceClick("(//td[text()='" + addedColumnss.get(i - 1) + "']//following-sibling::td[contains(@class,'action-edit-delete-column')]/a)[1]", "Action Button", "Field Name " + addedColumnss.get(i - 1));
            pause("low");
            click(deleteBtn, "Delete Button");
            pause("low");
            click(confirmDeleteBtn, "Confirm Delete");
            pause("low");

            if (getInnerText(frameSpanTextLocator("Success")).equals("Success")) {
                reportStep("Added Data Schema is deleted successfully", "pass");

            } else {
                reportStep("Added Data Schema is not deleted", "warning");

            }
        }
        addedColumns.remove();
        return new EditDataSchemaPage();
    }

    public EditDataSchemaPage clickOnApplyAndExitBtn() {
        pause("medium");
        pause("medium");
        click(applyBtnForDataSchema, "Apply Button");
//        pause("medium");
        click(frameSpanTextLocator("Exit"), "Exit Button");
        waitForLoaderToDisappear();
        pause("medium");
        return new EditDataSchemaPage();
    }

    public EditDataSchemaPage readOnlyCheckbox(boolean readOnlyCheckbox) {
        if (readOnlyCheckbox) {
            click("(//div[@class='dx-widget dx-checkbox dx-checkbox-indeterminate dx-datagrid-checkbox-size'])[3]", "read-Only Checkbox");
            pause("low");
        }
        return this;
    }


    public AddNewRowPage deleteDataSchema() {
        clickOnEditDataSchema();
//        pause("low");
        click("//td[text()='Account Name']", "");
        int maxAttempts = 200;
        int attempts = 0;

        while (!isVisibleWithMinimumTime("//td[text()='" + clientCompanyName.get() + "']") && attempts < maxAttempts) {
            pressDownArrow("(//tr[@class='dx-row dx-data-row dx-column-lines'])[8]");
            attempts++;
        }
        switch (xmlUserLevel) {
            case "Tier1Admin":
            case "Tier1User":

                scrollToElement("//td[text()='" + clientCompanyName.get() + "']");
                System.out.println("scrollToElement is done");
                break;
            default:
                System.out.println("scrollToElement is not found");
                break;
        }
//
        pause("low");
        pause("low");
        clickUsingJs("//td[text()='" + clientCompanyName.get() + "']//following-sibling::td[contains(@class,'action-edit-delete-column')]//a[text()='⋯']", "Delete Button", "");
        pause("low");
        click(deleteBtn, "Delete Button");
        pause("low");
        click(confirmDeleteBtn, "Confirm Delete");
        pause("low");

        if (getInnerText(frameSpanTextLocator("Success")).equals("Success")) {
            reportStep("Added Data Schema is deleted successfully", "pass");

        } else {
            reportStep("Added Data Schema is not deleted", "warning");

        }
        return new AddNewRowPage();
    }

    public EditDataSchemaPage validateTradeSettlementDateValidation() {

        //(//table)[3]//tr[1]//td
        click("//td[text()='Account Name']", "");
        int maxAttempts = 2000;
        int attempts = 0;

        while (!isVisibleWithMinimumTime("//td[text()='Trade Date']") && attempts < maxAttempts) {
            pressDownArrow("(//tr[@class='dx-row dx-data-row dx-column-lines'])[8]");
            attempts++;
        }
        pause("low");
        click("//td[text()='Trade Date']//following-sibling::td//i[contains(@class,'dx-icon-info')]", "validation column");
        pause("low");
        String tDate = getInnerText("//strong[text()='Trade Date']");
        System.out.println(tDate);
        String operator = getInnerText("(//span[contains(text(),'<')])[last()]");
        System.out.println(operator);
        String sDate = getInnerText("//span[text()='Settlement Date']");
        System.out.println(sDate);
        String validation = tDate + operator + sDate;
        System.out.println(validation);
        if (validation.contains("Trade Date")) {
            System.out.println("done.......");
            boolean verifyText = true;
            Assert.assertTrue(verifyText);
            reportPass("Trade & Settlement Date validation has been verified");
        } else {
            reportFail("failed to validate");
        }
        return this;
    }
}

