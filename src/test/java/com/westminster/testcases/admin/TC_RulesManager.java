package com.westminster.testcases.admin;

import com.config.ConfigurationManager;
import com.data.dynamic.FakerDataFactory;
import com.listener.TestListener;
import com.page.base.DashboardPage;
import com.page.base.WestMinsterPageHook;
import com.ui.utilities.TestGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class TC_RulesManager extends WestMinsterPageHook {

    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY, TestGroups.Type.REGRESSION})
    public void setReportValues() {
        testcaseName = "Login with " + xmlUserLevel;
        testDescription = "Login with " + xmlUserLevel;
        authors = "Automation Team";
        category = "Login for Rules Manager Module";
    }

    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY})
    public void addNewRule() {
        testcaseName = "TC_5587";
        testDescription = "Add New Rule and validate";
        authors = "Automation Team";
        category = "Add New Rule";
        startTestCase();
        setNode();

        ruleName.set(FakerDataFactory.getNameWithTimeStamp("Rule"));
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnRulesManager()
                .validateRulesManagerPage()
                .clickOnAddNewRuleBtn()
                .validateAddRulePage()
                .createANewRule(ruleName.get(), FakerDataFactory.getRuleDescription())
                .defineTheNewColumn(true, FakerDataFactory.getFieldName(), "")
                .addFilters("Broker Name", "=")
                .defineTheCalculation("Local Price", "*", "Local Price")
                .validateNewRuleNameIsEnabled(ruleName.get())
                .deleteRuleName();
        System.out.println("TC-----------TC_5587");
    }


    @Test(priority = 2, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.SANITY})
    public void editRule() {
        testcaseName = "TC_50";
        testDescription = "Edit Rule and Validate";
        authors = "Automation Team";
        category = "Edit Rule";
        startTestCase();
        setNode();

        ruleName.set(FakerDataFactory.getNameWithTimeStamp("Rule"));
        String ruleDescription = FakerDataFactory.getRuleDescription();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnRulesManager()
                .validateRulesManagerPage()
                .clickOnEditRule()
                .validateEditRulePage()
                .editRuleAndDescription(ruleName.get(), ruleDescription)
                .editDefineTheNewColumn(true, FakerDataFactory.getFieldName())
                .editAddFilters()
                .editDefineTheCaluculation("FX Rate", "*", "FX Rate")
                .validateEditRuleChanges(ruleName.get(), ruleDescription);
        System.out.println("TC-----------TC_50");
    }

    //Phase - 1 --> Manoj
    //TC_RE_2825 - Ensure an error message appears when creating a rule with a duplicate name:
    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void verifyDuplicateRuleNameError() {
        testcaseName = "TC_RE_2825";
        testDescription = "Verify Duplicate Rule Name Error";
        authors = "Automation Team";
        category = "Verify Duplicate Rule Name Error";
        startTestCase();
        setNode();

        ruleName.set(FakerDataFactory.getNameWithTimeStamp("Rule"));
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnRulesManager()
                .validateRulesManagerPage()
                .getFirstRuleNametext()
                .clickOnAddNewRuleBtn()
                .validateAddRulePage()
                .createANewRule(getFirstRuleNametext.get(), FakerDataFactory.getRuleDescription())
                .clickOnNextBtn()
                .validateRuleNameError("Rule name already exists. Please use a unique name.");

        System.out.println("TC-----------TC_RE_2825");
    }

    //Phase - 3 --> Manoj
    //TC_RE_51 - RULES MANAGER - Enabling the existing rule
    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void enableExistingRule() {
        testcaseName = "TC_RE_51";
        testDescription = "Enable Existing Rule";
        authors = "Automation Team";
        category = "Rule Manager";
        startTestCase();
        setNode();


        ruleName.set(FakerDataFactory.getNameWithTimeStamp("Rule"));
        reportName.set(FakerDataFactory.getFieldName());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnRulesManager()
                .validateRulesManagerPage()
                .clickOnAddNewRuleBtn()
                .validateAddRulePage()
                .createANewRule(ruleName.get(), FakerDataFactory.getRuleDescription())
                .defineTheNewColumn(true, reportName.get(), "")
                .addFilters("Broker Name", "=")
                .defineTheCalculation("Base Gross Commission", "+", "Base Gross Commission")
                .validateNewRuleNameIsEnabled(ruleName.get(), true)
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnClearAllFilters()
                .verifyHeaderElementIsPresentOrNot(reportName.get())
                .validateAddedColumnHasValues(reportName.get(), true)
                .clickOnEditDataSchema()
                .validateEditDataSchemaColumnsPage()
                .clickOnRelevantFieldNameActionIcon(reportName.get())
                .clickOnDeleteBtn()
                .clickOnExit()
                .validateDataManagerPage();

        System.out.println("TC-----------TC_RE_51");


    }

    //Phase - 3 --> Manoj
    //TC_RE_49 - RULES MANAGER - Disabling the existing rule
    @Test(priority = 1, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void disableExistingRule() {
        testcaseName = "TC_RE_49";
        testDescription = "Disable Existing Rule";
        authors = "Automation Team";
        category = "Rule Manager";
        startTestCase();
        setNode();

        ruleName.set(FakerDataFactory.getNameWithTimeStamp("Rule"));
        reportName.set(FakerDataFactory.getFieldName());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .setClient()
                .clickOnRulesManager()
                .validateRulesManagerPage()
                .clickOnAddNewRuleBtn()
                .validateAddRulePage()
                .createANewRule(ruleName.get(), FakerDataFactory.getRuleDescription())
                .defineTheNewColumn(true, reportName.get(), "")
                .addFilters("Broker Name", "=")
                .defineTheCalculation("Base Gross Commission", "+", "Base Gross Commission")
                .validateNewRuleNameIsEnabled(ruleName.get(), false)
                .clickOnDataManager()
                .validateDataManagerPage()
                .clickOnClearAllFilters()
                .verifyHeaderElementIsPresentOrNot(reportName.get())
                .validateAddedColumnHasValues(reportName.get(), false)
                .clickOnEditDataSchema()
                .validateEditDataSchemaColumnsPage()
                .clickOnRelevantFieldNameActionIcon(reportName.get())
                .clickOnDeleteBtn()
                .clickOnExit()
                .validateDataManagerPage();

        System.out.println("TC-----------TC_RE_49");
    }

}
