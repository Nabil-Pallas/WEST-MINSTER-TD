package com.westminster.testcases.budgeting;

import java.util.Arrays;

import com.config.ConfigurationManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.dynamic.FakerDataFactory;
import com.listener.TestListener;
import com.page.base.DashboardPage;
import com.page.base.WestMinsterPageHook;

@Listeners(TestListener.class)
public class TC_Budgeting extends WestMinsterPageHook {


    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser"})
    public void setReportValues() {
        testcaseName = "TC_127 " + xmlUserLevel;
        testDescription = "Create New Budget and validate";
        authors = "Automation Team";
        category = "Create New Budget";

    }

//    @Test(priority = 10, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser"})
    public void createNewBudget() {
        clientCompanyName.set(FakerDataFactory.getCompanyNameWithoutSpace());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnBudgeting()
                .validateAllBudgetsPage()
                .setClient()
                .clickOnCreateNewBudget()
                .addNewBudget(clientCompanyName.get(), "05/21/2024", "05/27/2024", "FX Rate", "Broker Name", "BMCUS", "1000.01")
                .clickSaveItemBtn()
                .clickSaveBudgetBtn()
                .validateViewBudget()
                .validateCreatedBudget(clientCompanyName.get(), "05/21/2024", "05/27/2024", "FX Rate")
                .deleteBudget(clientCompanyName.get());
        System.out.println("TC-----------10");
    }

//    @Test(priority = 11, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser"})
    public void editBudget() {
        testcaseName = "TC_14 " + xmlUserLevel;
        testDescription = "Edit budget and validate";
        authors = "Automation Team";
        category = "Edit Budget";
        startTestCase();
        setNode();
        ruleName.set(FakerDataFactory.getCompanyNameWithoutSpace());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnBudgeting()
                .validateAllBudgetsPage()
                .setClient()
                .clickOnActionsBtn()
                .clickOnEditBtn()
                .validateEditBudgetPage()
                .editInformation(Arrays.asList("Local Price", "Broker Name", ruleName.get(), "05/01/2024", "05/29/2024"), Arrays.asList("Target Column", "Budget by", "Budget Name", "Begin Date", "End Date"))
                .clickSaveBudgetBtn()
                .validateEditedBudget(ruleName.get(), "05/01/2024", "05/29/2024", "Local Price")
                .deleteBudget(ruleName.get());
        System.out.println("TC-----------11");

    }

    @Test(priority = 12, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser"})
    public void importNewBudget() {

        testcaseName = "TC_126 " + xmlUserLevel;
        testDescription = "Import new budget and validate";
        authors = "Automation Team";
        category = "Import New Budget";
        startTestCase();
        setNode();
        ruleName.set(FakerDataFactory.getCompanyNameWithoutSpace());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnBudgeting()
                .validateAllBudgetsPage()
                .setClient()
                .clickOnImportNewBudget()
                .importBudget("Budget_Import_Chrome.csv")
                .clickImportBtn()
                .searchValueOfImportedBudget("Name")
                .validateImportedBudget()
                .deleteImportedBudget();
        System.out.println("TC-----------12");

    }


}
