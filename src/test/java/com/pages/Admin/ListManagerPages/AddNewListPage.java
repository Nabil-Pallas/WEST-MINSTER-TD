package com.pages.Admin.ListManagerPages;

import org.testng.Assert;

import java.util.List;


public class AddNewListPage extends ListManagerPage {
    public static final String addNewListBtn = "//div[@id='add-list-item-button']//i";
    public static final String listTable = "(//div[@class='dx-datagrid-content']/table[@class='dx-datagrid-table dx-datagrid-table-fixed'])[2]/tbody/tr[2]/td[2]";

    public AddNewListPage validateAddListItemPage() {
        waitForElementToLoad(frameSpanTextLocator("Add list item"));
        if (isVisible(frameSpanTextLocator("Add list item"), "Add New List Text")) {
            reportPass("User successfully reached the correct Add New Rule page");
        } else {
            reportFailAndThrowException("Add New List Page is not loaded.");
        }
        return this;
    }

    public AddNewListPage typeListTitle(String listTitle) {
        pause("low");
        typeWithType(frameInputLocator("List Title…", 1), listTitle, "List Title");
        pause("low");
        return this;
    }

    public AddNewListPage clickOnAddListItem() {
        try {
            pause("low");
            forceClick(addNewListBtn, "Add list item", "Button");
        } catch (Exception e) {
            pause("medium");
            programmaticClick(frameSpanTextLocator("Add list item"), "Add list item", "Button");
        }
        return this;
    }

//    public AddNewListPage typeListItems(List<String> listItems) {
//        for (String listItem : listItems) {
//            clickOnAddListItem();
//            pause("low");
//            doubleClick(frametextareaLocator(2), "List Item", "Text Box" );
//            typeAndSpace(frametextareaLocator(2), listItem, "List Item " + listItem);
//            pause("low");
//            doubleClick(listTable, "Next Row", "Empty Row");
//        }
//        return this;
//    }

    public AddNewListPage typeListItems(List<String> listItems) {
        if (listItems == null || listItems.isEmpty()) {
            return this; // Return early if the list is null or empty
        }
        // Click on AddListItem before the loop starts
        clickOnAddListItem();
        pause("low");

        for (int i = 0; i < listItems.size(); i++) {
            String listItem = listItems.get(i);

//            doubleClick(frametextareaLocator(2), "List Item", "Text Box");
            typeAndSpace(frametextareaLocator(2), listItem, "List Item " + listItem);

            if (i < listItems.size() - 1) {
                // For all but the last item, press Enter
                pressEnter(frametextareaLocator(2));
                pause("low");
//                doubleClick(listTable, "Next Row", "Empty Row");
            }
        }

        return this;
    }

    //Validate the List name error message
    public AddNewListPage validateListNameError(String errorMsg) {
        click(frameInputLocator("List Title…", 1), "List Name", "Input");
        String errorMsgText = getInnerText(frameDivTextLocator(errorMsg));
        Assert.assertEquals(errorMsgText, errorMsg);
        reportPass("User Successfully validated the Error Msg displayed as -- " + errorMsgText);
        return this;
    }


    public ListManagerPage clickOnSaveListBtn() {
        click(frameSpanTextLocator("Save List"), "Save List");
        waitForLoaderToDisappear();
        return new ListManagerPage();
    }


    //Add List Items
    public AddNewListPage addListItems(String listTitle, List<String> listItems) {
        typeListTitle(listTitle)
                .typeListItems(listItems);
        return this;
    }
}
