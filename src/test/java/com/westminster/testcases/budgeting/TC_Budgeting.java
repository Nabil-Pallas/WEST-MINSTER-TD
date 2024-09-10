package com.westminster.testcases.budgeting;

import java.util.Arrays;

import com.config.ConfigurationManager;
import com.pages.Bugeting.AllBudgetsPage;
import com.pages.Bugeting.createNewBudgetPages.CreateNewBudgetPage;
import com.pages.Bugeting.importBudgetPages.ImportBudgetPage;
import com.ui.utilities.TestGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.dynamic.FakerDataFactory;
import com.listener.TestListener;
import com.page.base.DashboardPage;
import com.page.base.WestMinsterPageHook;

@Listeners(TestListener.class)
public class TC_Budgeting extends WestMinsterPageHook {


    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY, TestGroups.Type.REGRESSION})
    public void setReportValues() {
        testcaseName = "Login with " + xmlUserLevel;
        testDescription = "Login with " + xmlUserLevel;
        authors = "Automation Team";
        category = "Login for Budgeting Module";
    }

    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY})
    public void createNewBudget() {
        testcaseName = "TC_127";
        testDescription = "Create New Budget and validate";
        authors = "Automation Team";
        category = "Create New Budget";
        startTestCase();
        setNode();

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
        System.out.println("TC-----------TC_127");
    }

    @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY})
    public void editBudget() {
        testcaseName = "TC_14";
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
                .validateEditedBudget(ruleName.get(), "05/01/2024", "05/29/2024", "Local Price");
        //.deleteBudget(ruleName.get());
        System.out.println("TC-----------TC_14");

    }

    @Test(priority = 3, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.SANITY})
    public void importNewBudget() {
        testcaseName = "TC_126";
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
                .importBudget("./src/test/resources/upload/Budget_Import_Chrome.csv")
                .clickImportBtn()
                .searchValueOfImportedBudget("Name")
                .validateImportedBudget()
                .deleteImportedBudget();
        System.out.println("TC-----------TC_126");

    }

    //Phase -02 --Manoj
    //TC_RE_4428 - Verify that whether the Budgeting system allows the uploading of duplicate budget files
    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.REGRESSION})
    public void checkDuplicateBudgetFileUpload() {
        testcaseName = "TC_RE_4428";
        testDescription = "Check Duplicate Budget File Upload";
        authors = "Automation Team";
        category = "Budget";
        startTestCase();
        setNode();

        ruleName.set(FakerDataFactory.getCompanyNameWithoutSpace());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnBudgeting()
                .validateAllBudgetsPage()
                .setClient()
                .clickOnImportNewBudget()
                .validateImportBudgetPage()
                .importBudget("./src/test/resources/upload/Budget_Import_Chrome.csv")
                .clickImportBtn()
                .searchValueOfImportedBudget("Name")
                .validateImportedBudget()
                .clickOnImportNewBudgetWithoutReadUpdatedValueInCsv()
                .validateImportBudgetPage()
                .importBudget("./src/test/resources/upload/Budget_Import_Chrome.csv")
                .clickImportBtn()
                .validateBudgetAlreadyExistsError("A Budget with the label and Target Column supplied already exists on the specified Client.");

        System.out.println("TC-----------TC_RE_4428");

    }

    //Phase -02 --Manoj
    //TC_RE_4429 - Verify that whether the system restricts Creating a new budget with invalid date range.
    @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.REGRESSION})
    public void validateBudgetCreationWithInvalidDateRange() {
        testcaseName = "TC_RE_4429";
        testDescription = "Validate Budget Creation With Invalid Date Range";
        authors = "Automation Team";
        category = "Budget";
        startTestCase();
        setNode();

        clientCompanyName.set(FakerDataFactory.getCompanyNameWithoutSpace());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnBudgeting()
                .validateAllBudgetsPage()
                .setClient()
                .clickOnCreateNewBudget()
                .addNewBudget(clientCompanyName.get(), "05/27/2024", "05/21/2024", "FX Rate", "Broker Name", "BMCUS", "1000.01")
                .clickSaveItemBtn()
                .clickSaveBudgetBtn()
                .validateInvalidBudgetDateError("Beginning date and ending date must be in chronological order.");
        System.out.println("TC-----------TC_RE_4429");

    }

    //Phase -03 --Manoj
    //TC_RE_6091 - Verifies that the system permits importing the same budget a second time after editing its name and target column.
    @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.REGRESSION})
    public void checkBudgetImportWithChanges() {
        testcaseName = "TC_RE_6091";
        testDescription = "Verify Budget Import With Edited Name And TargetColumn";
        authors = "Automation Team";
        category = "Import Budget";
        startTestCase();
        setNode();

        String folderPath = "./src/test/resources/downloadedFile";
        String filePath = "./src/test/resources/downloadedFile/Sample Budget 1.csv";
        ruleName.set(FakerDataFactory.getCompanyNameWithoutSpace());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnBudgeting()
                .validateAllBudgetsPage()
                .setClient()
                .deleteAllFilesInFolder(folderPath)
                .searchAndDeleteImportedBudget("Sample Budget 1")
                .clickOnImportNewBudgetWithoutReadUpdatedValueInCsv()
                .validateImportBudgetPage()
                .downloadSampleBudget(filePath)
                .importBudget(filePath)
                .clickImportBtn()
                .searchValueOfImportedBudget("Name", "Sample Budget 1")
                .validateImportedBudget("Sample Budget 1")
                .clickOnActionsBtn()
                .clickOnEditBtn()
                .validateEditBudgetPage()
                .editInformation(Arrays.asList("Local Price", ruleName.get()), Arrays.asList("Target Column", "Budget Name"))
                .clickSaveBudgetBtn()
                .validateEditedBudget(ruleName.get(), "Local Price")
                .clickOnExit()
                .validateAllBudgetsPage()
                .clickOnImportNewBudgetWithoutReadUpdatedValueInCsv()
                .validateImportBudgetPage()
                .importBudget(filePath)
                .clickImportBtn()
                .searchValueOfImportedBudget("Name", "Sample Budget 1")
                .validateImportedBudget("Sample Budget 1")
                .searchAndDeleteImportedBudget("Sample Budget 1")
                .searchAndDeleteImportedBudget(ruleName.get());
        System.out.println("TC-----------TC_RE_6091");

    }

    //Phase -03 -- Praveen
    //TC_RE_4445 - verifyNonPositiveTargetAmountIsRejectedForBudgetItem
    @Test(priority = 3, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", "Tier3EndUser", TestGroups.Type.REGRESSION})
    public void validateNonPositiveTargetAmountRejection() {
        testcaseName = "TC_RE_4445";
        testDescription = "Verify Non Positive Target Amount Is Rejected For BudgetItem";
        authors = "Automation Team";
        category = "Target Amount for Budget Item";
        startTestCase();
        setNode();

        budgetName.set(FakerDataFactory.getReportsName());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnDataManager()
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnClearAllFilters();
        new DashboardPage()
                .clickOnBudgeting()
                .validateAllBudgetsPage()
                .clickOnCreateNewBudget()
                .validateAddBudgetPage();
        new CreateNewBudgetPage()
                .addNewBudget(budgetName.get(), "01/01/2023", "08/31/2024", "Local Executed Value", "Broker Name", "BMCUS", "-2345.00")
                .clickSaveItemBtn()
                .validateOutofRangeErrorMsg("Target Amount is out of range");
        System.out.println("TC-----------TC_RE_4445");
    }
}
