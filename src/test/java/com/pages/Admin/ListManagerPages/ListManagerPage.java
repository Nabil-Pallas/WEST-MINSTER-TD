package com.pages.Admin.ListManagerPages;

import com.page.base.DashboardPage;
import com.pages.Admin.AdminPage;
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
        pause("medium");
        if (isVisible(actionIcon, "Action Icon")) {
            click(actionIcon, "Action Icon");
        } else {
            reportStep("The WebElement is not visible ", "warning");
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

    public ListManagerPage deleteListItem(String listName) {
        searchListName(listName)
                .clickOnActionIcon()
                .clickOnDeleteList();
        return this;
    }
}
