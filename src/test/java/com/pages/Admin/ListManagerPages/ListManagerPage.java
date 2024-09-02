package com.pages.Admin.ListManagerPages;

import com.data.dynamic.FakerDataFactory;
import com.page.base.DashboardPage;
import com.pages.Admin.AdminPage;
import com.pages.Admin.RulesManagerPages.AddNewRulePage;
import com.pages.Admin.RulesManagerPages.RulesManagerPage;
import org.testng.Assert;

import java.util.List;

public class ListManagerPage extends AdminPage {

    private final String listManagerText = "//h1[text()='List Manager ']";
    private final String listNameSearch = "(//input[@class='dx-texteditor-input'])[3]";
    private final String listItemSearch = "(//input[@class='dx-texteditor-input'])[4]";
    private final String listNameText = "(// table[@role='presentation'])[3]/ tbody/ tr[1]/ td[1]";
    private final String listItemText = "(// table[@role='presentation'])[3]/ tbody/ tr[1]/ td[2]";
    private final String actionIcon = "(//td[@class='action-edit-delete-column']/a)[1]";
    private final String actionIcon_2 = "(//td[@class='action-edit-delete-column']/a)[2]";


    public String frameInputLocator(String placeholder, int index) {
        return "(//div[@data-dx_placeholder='" + placeholder + "']/ancestor::div//input)[" + index + "]";
    }

    public String frametextareaLocator(int index) {
        return "(//textarea[@role='textbox'])[" + index + "]";
    }

    public String frameSearchLocator(String searchItem) {
        return "//div[@data-dx_placeholder='" + searchItem + "']//..//textarea";
    }

    public ListManagerPage validateListManagerPage() {
        waitForElementToLoad(listManagerText);
        if (isVisible(listManagerText, "List Manager Text")) {
            reportPass("User successfully reached the correct List Manager page");
        } else {
            reportFailAndThrowException("List Manager Page is not loaded.");
        }
        return this;
    }

    public AddNewListPage clickOnAddNewListBtn() {
        pause("low");
        click(frameSpanTextLocator("Add New List"), "Add New List");
        return new AddNewListPage();
    }

    //Get first List name text
    public ListManagerPage getFirstListNametext() {
        waitForLoaderToDisappear();
        waitForElementToLoad(listNameText);
        if (isVisible(listNameText, "First List Name text")) {
            String innerText = getInnerText(listNameText);
            System.out.println("Inner Text " + innerText);
            getFirstRuleNametext.set(innerText);
            reportStep("User successfully retrieved the first list name : " + innerText, "info");
        } else {
            clickOnAddNewListBtn()
                    .validateAddListItemPage()
                    .typeListTitle(newListName.get())
                    .typeListItems(listItems.get())
                    .clickOnSaveListBtn()
                    .validateListNameAndItem(newListName.get(), listItems.get());
            reportStep("User successfully created new List.", "Pass");
        }

        return this;
    }


    public ListManagerPage searchListName(String listName) {
        clickAndType(listNameSearch, listName, "List Name");
        pause("low");
        return this;
    }

    public ListManagerPage validateListName(String listName) {
        boolean verifyExactText = verifyExactText(listNameText, listName);
        Assert.assertTrue(verifyExactText);
        return this;
    }

    public ListManagerPage validateListItem(List<String> listItems) {
        for (String listItem : listItems) {
            boolean verifyExactText = verifyPartialText(listItemText, listItem);
            Assert.assertTrue(verifyExactText);
        }
        return this;
    }

    public ListManagerPage validateListItem(String listItem) {
        boolean verifyExactText = verifyPartialText(listItemText, listItem);
        Assert.assertTrue(verifyExactText);
        return this;
    }


    //Edit List
    public ListManagerPage clickOnActionIcon() {
        waitForLoaderToDisappear();
        waitForElementToLoad(listNameText);
        if (getInnerText(listNameText).contains("Timezones")) {
            if (isVisible(actionIcon_2, "Action Icon 2")) {
                click(actionIcon_2, "Action Icon 2");
            } else {
                reportStep("The Action Icon 2 is not visible ", "warning");
            }
        } else {
            if (isVisible(actionIcon, "Action Icon 1")) {
                click(actionIcon, "Action Icon 1");
            } else {
                reportStep("The Action Icon 1 is not visible ", "warning");
            }
        }
        return this;
    }

    public EditListPage clickOnEditList() {
        click(frameSpanTextLocator("Edit List"), "Edit List", "Link");
        reportPass("User successfully clicked on Edit List button.");
        return new EditListPage();
    }

    public ListManagerPage clickOnDeleteList() {
        click(frameSpanTextLocator("Delete List"), "Delete List", "Link");
        pause("low");
        click(frameSpanTextLocator("Yes"), "Yes", "Button");
        pause("low");
        waitForLoaderToDisappear();
        reportPass("User successfully deleted the List.");
        return this;
    }

    public ListManagerPage validateListNameAndItem(String listName, List<String> listItems) {
        searchListName(listName)
                .validateListName(listName)
                .validateListItem(listItems);
        return this;
    }

    public ListManagerPage validateDeletedItem(String deleteItem) {
        if (isNotVisible("//*[text()='" + deleteItem + "']", deleteItem)) {
            reportStep("Added List is deleted successfully", "pass");
        } else {
            reportStep("Added List is not deleted", "warning");
        }
        return this;
    }

    public ListManagerPage deleteListItem(String listName) {
        searchListName(listName)
                .clickOnActionIcon()
                .clickOnDeleteList();
        return this;
    }
}
