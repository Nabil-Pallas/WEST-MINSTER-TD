package com.pages.bugeting;

import com.page.base.DashboardPage;
import com.page.base.MenuPage;
import com.pages.Admin.AdminPage;
import com.pages.Admin.DataManagerPages.DataManagerPage;
import com.pages.Admin.DataManagerPages.EditDataManagerRow;
import com.pages.bugeting.createNewBudgetPages.CreateNewBudgetPage;
import com.pages.bugeting.editBudgetPages.EditBudgetPage;
import com.pages.bugeting.importBudgetPages.ImportBudgetPage;
import org.testng.Assert;

import static com.pages.bugeting.importBudgetPages.ImportBudgetPage.readUpdatedValueInCsv;

public class AllBudgetsPage extends MenuPage {
    protected String allBudgetsText = "//h1[text()='All Budgets']";
    private String actionBtn = "(//td[@class='action-edit-delete-column']/a)[1]";
    private String editBtn = "//span[text()='Edit']";
    private String clientSelectDrpDown = "(//div[@class='dx-dropdowneditor-icon'])[1]";

    public String frameClientDetailsLocator(String placeholder) {
        return "//span[text()='" + placeholder + "']/ancestor::label/following-sibling::div//input";
    }

    public String searchValueWithHeader(int index) {
        return "((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])/ancestor::tr[2]//td[@aria-label='Filter cell'][" + index + "]//input  | //td[@aria-label='Filter cell'][" + index + "]//input";
    }

    public String frameClientDetailsLocator(String placeholder, int index) {
        return "(//span[text()='" + placeholder + "']/ancestor::label/following-sibling::div//input)[" + index + "]";
    }

    public String frameDivTextLocator(String placeholder, int index) {
        return "(//div[text()='" + placeholder + "'])[" + index + "]";
    }

    public String frameDivTextLocator(String placeholder) {
        return "//div[text()='" + placeholder + "']";
    }

    public String frameATagTextLocator(String placeholder) {
        return "//a[text()='" + placeholder + "']";
    }

    public String selectBudgetByCheckBox(String placeholder, int index) {
        return "(//div[text()='" + placeholder + "']/preceding-sibling::div)[" + index + "]";
    }

    public String frameSpanTextLocator(String placeholder) {
        return "//span[text()='" + placeholder + "']";
    }

    public AllBudgetsPage validateAllBudgetsPage() {
        waitForElementToLoad(allBudgetsText);
        if (isVisible(allBudgetsText, "All Budgets Text")) {
            reportPass("User successfully reached the correct All Budgets page");
        } else {
            reportFailAndThrowException("Admin Page is not loaded.");
        }
        return this;
    }

    public CreateNewBudgetPage clickOnCreateNewBudget() {
        click("(//span[contains(text(),'Create New Budget')])[1]", "Create New Budget");
        waitForLoaderToDisappear();
        return new CreateNewBudgetPage();

    }

    public ImportBudgetPage clickOnImportNewBudget() {
        readUpdatedValueInCsv();
        click("(//span[contains(text(),'Import New Budget')])[1]", "Import New Budget");
        waitForLoaderToDisappear();
        return new ImportBudgetPage();

    }

    public AllBudgetsPage clickOnActionsBtn() {
        pause("medium");
        click(actionBtn, "Action button");
        waitForLoaderToDisappear();
        return new AllBudgetsPage();
    }

    public DashboardPage clickOnDeleteBtn() {
        click(frameSpanTextLocator("Delete"), "Delete button");
        pause("low");
        click(frameSpanTextLocator("Yes"), "Confirm Delete button");
        pause("medium");
        return new DashboardPage();
    }

    public EditBudgetPage clickOnEditBtn() {
        click(frameSpanTextLocator("Edit"), "Edit button");
        pause("low");
        return new EditBudgetPage();
    }

    public AllBudgetsPage searchValueToBeEdit(String nameToSearch, String verificationHeader) {
        waitForLoaderToDisappear();

        int headerCount = getLocatorsCount("(//td[contains(@class,'dx-datagrid-action dx')])");
        for (int i = 1; i < headerCount; i++) {

            String headerElement = "(//td[contains(@class,'dx-datagrid-action dx')])[" + i + "]";
            String headerText = getInnerText(headerElement);
            if (headerText.equalsIgnoreCase(verificationHeader)) {
                click(searchValueWithHeader(i), "Search Filter", "Search Box");
                type(searchValueWithHeader(i), nameToSearch, nameToSearch);
            }

        }
        return new AllBudgetsPage();
    }

    public AllBudgetsPage searchValueOfImportedBudget(String verificationHeader) {
        waitForLoaderToDisappear();
        String budgetName = ImportBudgetPage.updatedCsvValue;
        int headerCount = getLocatorsCount("(//td[contains(@class,'dx-datagrid-action dx')])");
        for (int i = 1; i < headerCount; i++) {

            String headerElement = "(//td[contains(@class,'dx-datagrid-action dx')])[" + i + "]";
            String headerText = getInnerText(headerElement);
            if (headerText.equalsIgnoreCase(verificationHeader)) {
                click(searchValueWithHeader(i), "Search Filter", "Search Box");
                type(searchValueWithHeader(i), budgetName, budgetName);
                break;
            }

        }
        pause("medium");
        return new AllBudgetsPage();
    }

    public DashboardPage validateImportedBudget() {
        String name = ImportBudgetPage.updatedCsvValue;
        String budgetNameElement = "//div[@class='wdx-interactive-cell dx-command-view-budget-cell']";
        boolean verifyExactBudgetNameText = verifyExactText(budgetNameElement, name);
        Assert.assertTrue(verifyExactBudgetNameText);
        return new DashboardPage();
    }

    public AllBudgetsPage setClient(String clientName) {

        //select client

        switch (xmlUserLevel) {
            case "Tier2Admin":
                break;
            case "Tier2User":
                break;
            case "Tier3EndUser":
                break;
            default:
                try {
                    clickAndChoose(clientSelectDrpDown, "//div[contains(text(),'" + clientName + "')]", "Client Name", clientName);
                    pause("low");
                    reportPass("User successfully selected the Client.");
                } catch (Exception e) {
                    reportWarning("Client is not selected as it is set as default");
                }
                break;
        }
        return new AllBudgetsPage();
    }

}
