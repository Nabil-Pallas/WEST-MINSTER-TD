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
        typeAndEnter(frameInputLocator("List Title…", 1), listTitle, "listTitle");
        return this;
    }

    public AddNewListPage clickOnAddListItem() {
//        click(frameSpanTextLocator("Add list item"), "Add list item");
        try {
            pause("low");
            click("//div[@id='add-list-item-button']//i", "Add list item", "Button");
        } catch (Exception e) {
            pause("low");
            programmaticClick(frameSpanTextLocator("Add list item"), "Add list item", "Button");
        }
        return this;
    }

    public AddNewListPage typeListItems(List<String> listItems) {
        for (String listItem : listItems) {
            clickOnAddListItem();
            typeAndSpace(frametextareaLocator(2), listItem, "List Item " + listItem);
            pause("low");
        }
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
