package com.pages.Admin.ListManagerPages;

import java.util.List;


public class AddNewListPage extends ListManagerPage {
    private final String addNewRuleText = "";

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
        typeWithType(frameInputLocator("List Title…", 1), listTitle, "listTitle");
        pause("low");
        return this;
    }

    public AddNewListPage clickOnAddListItem() {
        try {
            pause("low");
            click("//div[@id='add-list-item-button']//i", "Add list item", "Button");
        } catch (Exception e) {
            pause("medium");
            programmaticClick(frameSpanTextLocator("Add list item"), "Add list item", "Button");
        }
        return this;
    }

    public AddNewListPage typeListItems(List<String> listItems) {
        int i = 2;
        for (String listItem : listItems) {
            clickOnAddListItem();
            typeWithType(frametextareaLocator(2), listItem, "List Item " + listItem);
            pause("low");
//            click("//div[@data-dx_placeholder='Search List Items…']", "Item Count", "Text");
            click("(//div[@class='dx-datagrid-content']/table[@class='dx-datagrid-table dx-datagrid-table-fixed'])[2]/tbody/tr[" + i + "]/td[2]", "Next Row", "Empty Row");
            i++;
        }
        //Different Methods
//        int size = listItems.size();
//        for (int i = 0; i < size; i++) {
//            String listItem = listItems.get(i);
//            if (i == 0) {
//                clickOnAddListItem(); // Call this method only once for the first iteration
//            }
//            typeAndEnter(frametextareaLocator(2), listItem, "List Item " + listItem);
//            pause("low");
//
//            if (i == size - 1) {
//                type(frametextareaLocator(2), listItem, "List Item " + listItem);
//            }
//        }

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
