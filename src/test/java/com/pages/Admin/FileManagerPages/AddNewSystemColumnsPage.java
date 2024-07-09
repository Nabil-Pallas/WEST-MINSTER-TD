package com.pages.Admin.FileManagerPages;

import com.beust.ah.A;
import com.data.dynamic.FakerDataFactory;
import com.pages.Admin.DataManagerPages.AddNewColumnPage;
import com.pages.Admin.DataManagerPages.DataManagerPage;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddNewSystemColumnsPage  extends FileManagerPage {

    private String addNewSystemColumnsText = "//div[text()='Add New system Column(s)']";
    protected String applyBtn = "//span[text()='Apply']";


    public AddNewSystemColumnsPage validateAddNewSystemColumnsPage() {
        waitForElementToLoad(addNewSystemColumnsText);
        if (isVisible(addNewSystemColumnsText, "Add New system Column(s)")) {
            reportPass("User successfully reached the Add New system Column(s) page");
        } else {
            reportFailAndThrowException("Add New system Column(s) Page is not loaded.");
        }
        return new AddNewSystemColumnsPage();
    }
////td[@class='dx-required-field-heading']

    public AddNewSystemColumnsPage typeFieldName(String fieldName) {
//        click("(//td[contains(@class,'dx-required-field-heading dx-datagrid-validator dx-validator dx-visibility-change-handler')])[1]", "Field Name");
        pause("low");
        click("(//td[contains(@class,'dx-required-field-heading dx-datagrid-validator')])[1]", "Field Name");
        clickAndType("(//td[contains(@class,'dx-required-field-heading dx-datagrid-validator')])[1]", fieldName, "Field Name");
        pause("low");
        return new AddNewSystemColumnsPage();
    }
    public AddNewSystemColumnsPage selectFieldDataType(int i) {
        String fieldDataTypeElement = "//div[contains(@class,'dx-item dx-list-item')]";

        int index = i + 1;
        click("(//td[@class='dx-required-field-heading dx-datagrid-validator dx-validator dx-visibility-change-handler'])[2]", "selectFieldDataType");
        clickAndChoose("(//div[@class='dx-item dx-list-item'])[1]", frameClientDetailsLocator(index), "fieldDataType", "Field Data Type");

        pause("medium");
        return this;
    }

    public AddNewSystemColumnsPage readOnlyCheckbox(boolean readOnlyCheckbox) {
        if (readOnlyCheckbox) {
            click("(//div[@class='dx-widget dx-checkbox dx-checkbox-indeterminate dx-datagrid-checkbox-size'])[3]", "read-Only Checkbox");
            pause("low");
        }
        return this;
    }

    public AddNewSystemColumnsPage clickOnApplyBtn() {
//        waitForLoaderToDisappear();
        pause("medium//");
        click(applyBtn, "Apply Button");
        pause("medium");
        reportPass("User successfully clicked the Apply button.");
        return new AddNewSystemColumnsPage();
    }


    public FileMappingPage typeInformationInAddNewSystemColumns(boolean uniqueTradeIdentifierCheckbox, boolean calculatedCheckbox, boolean readOnlyCheckbox) {
        clientCompanyName.set(FakerDataFactory.getFieldNameWithTimeStamp(""));
        for (int i = 0; i < 1; i++) {

            typeFieldName(clientCompanyName.get());
            selectFieldDataType(i);
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
                    readOnlyCheckbox(readOnlyCheckbox);
                    break;
//                case 3:
//                    requiredCheckbox(requiredCheckbox)
//                            .clickOnConfirmBtn();
//                    break;
            }

            clickOnApplyBtn();
        }
        return new FileMappingPage();
    }

}
