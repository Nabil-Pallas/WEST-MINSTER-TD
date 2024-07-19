package com.pages.Admin.DataManagerPages;

import com.data.dynamic.FakerDataFactory;

public class AddNewColumnPage extends EditDataSchemaPage {
    String AddNewColumnText = "//div[text()='Add New Column(s)']";
    String confirmBtn = "//span[text()='Confirm']";



    public AddNewColumnPage validateAddNewColumnPage() {
        waitForElementToLoad(AddNewColumnText);
        if (isVisible(AddNewColumnText, "Add New Column(s)")) {
            reportPass("User successfully reached the correct Add New Column Page.");
        } else {
            reportFailAndThrowException("Add new Column(s) Page is not loaded.");
        }
        return this;
    }


    public AddNewColumnPage typeFieldName(String fieldName) {
//        click("(//td[contains(@class,'dx-required-field-heading dx-datagrid-validator dx-validator dx-visibility-change-handler')])[1]", "Field Name");
        pause("medium");
        click("(//td[contains(@class,'dx-required-field-heading dx-datagrid-validator')])[1]", "Field Name");
        pause("medium");
        pause("low");
        typeAndSpace("(//input[@class='dx-texteditor-input'])[3]", fieldName, "Field Name");
        pause("low");
        return this;
    }

    public AddNewColumnPage selectFieldDataType(int i) {
        String fieldDataTypeElement = "//div[contains(@class,'dx-item dx-list-item')]";

        int index = i + 1;
        pause("low");
        click("//td[@class='dx-required-field-heading dx-datagrid-validator dx-validator dx-visibility-change-handler']", "selectFieldDataType");
        pause("low");
        clickAndChoose("(//div[@aria-label='Select'])[last()]", frameClientDetailsLocator(index), "fieldDataType", "Field Data Type");
        pause("medium");
        return this;
    }


    public AddNewColumnPage uniqueTradeIdentifierCheckbox(boolean uniqueTradeIdentifierCheckbox) {
        if (uniqueTradeIdentifierCheckbox) {
            click("(//div[@class='dx-widget dx-checkbox dx-checkbox-indeterminate dx-datagrid-checkbox-size'])[1]", "unique Trade Identifier");
            pause("low");
        }
        return this;
    }

    public AddNewColumnPage calculatedCheckbox(boolean calculatedCheckbox) {
        pause("low");
        if (calculatedCheckbox) {
            click("(//div[@class='dx-widget dx-checkbox dx-checkbox-indeterminate dx-datagrid-checkbox-size'])[2]", "calculated Checkbox");
            pause("low");
        }
        return this;
    }

    public AddNewColumnPage requiredCheckbox(boolean requiredCheckbox) {
        if (requiredCheckbox) {
            click("(//div[@class='dx-widget dx-checkbox dx-checkbox-indeterminate dx-datagrid-checkbox-size'])[3]", "required Checkbox");
            pause("low");
        }

        return this;
    }

    public AddNewColumnPage readOnlyCheckbox(boolean readOnlyCheckbox) {
        if (readOnlyCheckbox) {
            click("(//div[@class='dx-widget dx-checkbox dx-checkbox-indeterminate dx-datagrid-checkbox-size'])[4]", "read-Only Checkbox");
            pause("low");
        }
        return this;
    }

    public AddNewRowPage clickOnConfirmBtn() {
        click(confirmBtn, "Confirm Button");
        pause("low");
        waitForLoaderToDisappear();
        return new AddNewRowPage();
    }


    public AddNewColumnPage typeInformationInAddNewColumnsAndValidate(boolean uniqueTradeIdentifierCheckbox, boolean calculatedCheckbox, boolean readOnlyCheckbox) {
        clientCompanyName.set(FakerDataFactory.getFieldNameWithTimeStamp(""));
        for (int i = 0; i < 1; i++) {

            typeFieldName(clientCompanyName.get());
            selectFieldDataType(i);
            pause("medium");
            pause("medium");
            pause("medium");
            switch (i) {
//                case 0:
//                    calculatedCheckbox(calculatedCheckbox);
//                    break;
//                case 1:
//                    uniqueTradeIdentifierCheckbox(uniqueTradeIdentifierCheckbox);
//                    break;
                case 0:
                    pause("medium");
                    readOnlyCheckbox(readOnlyCheckbox);
                    break;
//                case 3:
//                    requiredCheckbox(requiredCheckbox)
//                            .clickOnConfirmBtn();
//                    break;
            }

            clickOnApplyAndExitBtn()
                    .validateAddedNewColumn(clientCompanyName.get());
            if (i > 0) {
                clickOnEditDataSchema();
                clickOnAddNewColumn();
            }
        }
        return this;
    }

//    public AddNewRowPage deleteDataSchema() {
//        clickOnEditDataSchema();
////        pause("low");
//        click("//td[text()='Account Name']", "");
//        int maxAttempts = 40;
//        int attempts = 0;
//
//        while (!isVisibleWithMinimumTime("//td[text()='" + clientCompanyName.get() + "']") && attempts < maxAttempts) {
//            pressDownArrow("(//tr[@class='dx-row dx-data-row dx-column-lines'])[8]");
//            attempts++;
//        }
//
//        scrollToElement("//td[text()='" + clientCompanyName.get() + "']");
//        pause("low");
//        pause("low");
//        forceClick("(//td[text()='" + clientCompanyName.get() + "']//following-sibling::td[contains(@class,'action-edit-delete-column')]/a)[1]", "Delete Button", "");
//        pause("low");
//        click(deleteBtn, "Delete Button");
//        pause("low");
//        click(confirmDeleteBtn, "Confirm Delete");
//        pause("low");
//
//        if (getInnerText(frameSpanTextLocator("Success")).equals("Success")) {
//            reportStep("Added Data Schema is deleted successfully", "pass");
//
//        } else {
//            reportStep("Added Data Schema is not deleted", "warning");
//
//        }
//        return new AddNewRowPage();
//    }

}
