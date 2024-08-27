package com.pages.Admin.ListManagerPages;

import java.util.ArrayList;
import java.util.List;

import static com.pages.Admin.ListManagerPages.AddNewListPage.addNewListBtn;
import static com.pages.Admin.ListManagerPages.AddNewListPage.listTable;

public class EditListPage extends ListManagerPage {
    private final String deleteIcon = "//a[@title='Delete List Item']";

    public EditListPage validateEditListPage() {

        waitForElementToLoad(frameSpanTextLocator("Add list item"));
        if (isVisible(frameSpanTextLocator("Add list item"), "Add Edit List Text")) {
            reportPass("User reaches to the right Edit List page");
        } else {
            reportFailAndThrowException("Edit List Page is not loaded.");
        }
        return this;
    }

    public EditListPage typeListTitle(String listTitle) {
        pause("low");
        typeAndSpace(frameInputLocator("List Title…", 1), listTitle, "listTitle");
        pause("low");
        return this;
    }

    public EditListPage clickOnAddListItem() {
        try {
            pause("low");
            forceClick(addNewListBtn, "Add list item", "Button");
        } catch (Exception e) {
            pause("medium");
            programmaticClick(frameSpanTextLocator("Add list item"), "Add list item", "Button");
        }
        return this;
    }

    public EditListPage typeListItems(String listItem) {
        typeAndSpace(frametextareaLocator(2), listItem, "List Item " + listItem);
        return this;
    }

    public EditListPage deleteListItem() {
        click(deleteIcon, "Delete");
        return this;
    }

    public EditListPage searchListItem(String itemName) {
        typeAndEnter(frameSearchLocator("Search List Items…"), itemName, "Search Item");
        return this;
    }

    public EditListPage clearSearchField() {
        clear(frameSearchLocator("Search List Items…"));
        return this;
    }

    public EditListPage editListItem(String addListItem) {

        int listItemCount = getLocatorsCount("(//table)[6]//td[@aria-colindex='2']");
        for (int i = 1; i <= listItemCount; i++) {
            String listItemText = getInnerText("((//table)[6]//td[@aria-colindex='2'])[" + i + "]");

            // Edit the first item
            if (i == 1) {
                searchListItem(listItemText);
                pause("low");
                doubleClick("//td[text()='" + listItemText + "']", "List Item", "Text");
                typeListItems(addListItem);
            }
            // Delete the second item
            if (i == 2) {
                searchListItem(listItemText);
                pause("low");
                deleteListItem();
            }
            if (i == 3) {
                break;
            }
            clearSearchField();
        }
        return this;
    }

//    public EditListPage typeListItems(List<String> listItems) {
//        pause("low");
//        for (String listItem : listItems) {
//            clickOnAddListItem();
//            typeAndSpace(frametextareaLocator(2), listItem, "List Item " + listItem);
//            pause("low");
//            click(listTable, "Next Row", "Empty Row");
//        }
//        return this;
//    }

    public EditListPage typeListItems(List<String> listItems) {
        if (listItems == null || listItems.isEmpty()) {
            return this; // Return early if the list is null or empty
        }
        pause("low");
        // Click on AddListItem before the loop starts
        clickOnAddListItem();

        for (int i = 0; i < listItems.size(); i++) {
            String listItem = listItems.get(i);
            typeAndSpace(frametextareaLocator(2), listItem, "List Item " + listItem);
            if (i < listItems.size() - 1) {
                // For all but the last item, press Enter
                pressEnter(frametextareaLocator(2));
                pause("low");
//                click(listTable, "Next Row", "Empty Row");
            }
        }

        return this;
    }


    public ListManagerPage clickOnSaveListBtn() {
        click(frameSpanTextLocator("Save List"), "Save List");
        waitForLoaderToDisappear();
        return new ListManagerPage();
    }


}
