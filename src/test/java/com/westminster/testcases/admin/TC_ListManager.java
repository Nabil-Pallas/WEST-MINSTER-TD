package com.westminster.testcases.admin;

import java.util.List;

import com.config.ConfigurationManager;
import com.ui.utilities.TestGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.dynamic.FakerDataFactory;
import com.listener.TestListener;
import com.page.base.DashboardPage;
import com.page.base.WestMinsterPageHook;

@Listeners(TestListener.class)
public class TC_ListManager extends WestMinsterPageHook {

    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY, TestGroups.Type.REGRESSION})
    public void setReportValues() {
        testcaseName = "Login with  " + xmlUserLevel;
        testDescription = "Login with  " + xmlUserLevel;
        authors = "Automation Team";
        category = "Login for List Manager Module";
    }

//    @Test(priority = 8, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY})
    public void addNewList() {
        testcaseName = "TC_5591";
        testDescription = "Create New List and Validate";
        authors = "Automation Team";
        category = "Add New List";
        startTestCase();
        setNode();

        newListName.set(FakerDataFactory.getListName());
        listItems.set(FakerDataFactory.getListOfItems(4));
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
                .typeListItems(listItems.get())
                .clickOnSaveListBtn()
                .validateListNameAndItem(newListName.get(), listItems.get())
                .deleteListItem(newListName.get());
        System.out.println("TC-----------TC_5591");

    }

    @Test(priority = 9, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY})
    public void editList() {
        testcaseName = "TC_5357 ";
        testDescription = "Edit List and Validate";
        authors = "Automation Team";
        category = "Edit List";
        startTestCase();
        setNode();

        newListName.set(FakerDataFactory.getListName());
        listItems.set(FakerDataFactory.getListOfItems(1));
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
                .editListItem(listItems.get().get(0))
                .typeListItems(listItems.get())
                .clickOnSaveListBtn()
                .validateListNameAndItem(newListName.get(), listItems.get());
        System.out.println("TC-----------TC_5357");

    }
}



