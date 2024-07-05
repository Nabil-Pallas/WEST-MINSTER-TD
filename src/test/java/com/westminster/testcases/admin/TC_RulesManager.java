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

    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User"})
    public void setReportValues() {
        testcaseName = "TC_5587 " + xmlUserLevel;
        testDescription = "Add New Rule and validate";
        authors = "Automation Team";
        category = "Add New Rule";
    }

    @Test(priority = 6, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User"})
    public void addNewRule() {
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
                .validateNewRuleNameIsEnabled(ruleName.get());
        System.out.println("TC-----------06");
    }


    @Test(priority = 7, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User"})
    public void editRule() {
        testcaseName = "TC_50 " + xmlUserLevel;
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
                .validateEditRuleChanges(ruleName.get(), ruleDescription)
                .deleteRuleName();
        System.out.println("TC-----------07");
    }

}
