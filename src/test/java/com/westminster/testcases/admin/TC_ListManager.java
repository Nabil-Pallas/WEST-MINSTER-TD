package com.westminster.testcases.admin;

import java.util.List;

import com.config.ConfigurationManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.dynamic.FakerDataFactory;
import com.listener.TestListener;
import com.page.base.DashboardPage;
import com.page.base.WestMinsterPageHook;

@Listeners(TestListener.class)
public class TC_ListManager extends WestMinsterPageHook {

    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User"})
    public void setReportValues() {
        testcaseName = "TC_5591 " + xmlUserLevel;
        testDescription = "Create New List and Validate";
        authors = "Automation Team";
        category = "Add New List";
    }

    @Test(priority = 8, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User"})
    public void addNewList() {
        newListName.set(FakerDataFactory.getListName());
        List<String> listItems = FakerDataFactory.getListOfItems(8);
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnListManager()
                .validateListManagerPage()
                .clickOnAddNewListBtn()
                .validateAddListItemPage()
                .typeListTitle(newListName.get())
                .typeListItems(listItems)
                .clickOnSaveListBtn()
                .validateListNameAndItem(newListName.get(), listItems);
        System.out.println("TC-----------08");

    }

   // @Test(priority = 9, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User"})
    public void editList() {
        testcaseName = "TC_5357 " + xmlUserLevel;
        testDescription = "Edit List and Validate";
        authors = "Automation Team";
        category = "Edit List";
        startTestCase();
        setNode();
        newListName.set(FakerDataFactory.getListName());
        String listItem = FakerDataFactory.getListItem();
        List<String> listItems = FakerDataFactory.getListOfItems(1);
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnListManager()
                .validateListManagerPage()
                .clickOnActionIcon()
                .clickOnEditList()
                .typeListTitle(newListName.get())
                .editListItem(listItem)
                .typeListItems(listItems)
                .clickOnSaveListBtn()
                .validateListNameAndItem(newListName.get(), listItems)
                .deleteListItem(newListName.get());
        System.out.println("TC-----------09");

    }
}
