package com.pages.Admin.RulesManagerPages;

import com.beust.ah.A;
import com.config.ConfigurationManager;
import com.data.dynamic.FakerDataFactory;
import com.microsoft.playwright.Locator;
import com.page.base.DashboardPage;
import com.pages.Admin.AdminPage;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class RulesManagerPage extends AdminPage {

    private String rulesManagerText = "//h1[text()='Rules Manager']";
    private String firstRuleNameText = "((((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[6])[position() < last()])[1]";
    private String actionRuleLocator = "(//td[@class='action-edit-delete-column']/a)[1]";
    private String enableCheckBoxLocator = "((((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[2])[position() < last()]//div)[1]//span[@class='dx-checkbox-icon']";


    public RulesManagerPage validateRulesManagerPage() {
        waitForElementToLoad(rulesManagerText);
        if (isVisible(rulesManagerText, "Rules Manager Text")) {
            reportPass("User successfully reached the correct Rules Manager page");
        } else {
            reportFailAndThrowException("Rules Manager Page is not loaded.");
        }
        return this;
    }

    public AddNewRulePage clickOnAddNewRuleBtn() {
        pause("low");
        click(frameSpanTextLocator("Add New Rule"), "Add New Rule");
        return new AddNewRulePage();
    }

    public String frameRuleNameLocator(String placeholder) {
        return "//span[text()='" + placeholder + "']/ancestor::label/following-sibling::div//input";
    }

    public String frameRuleDescriptionLocator(String placeholder) {
        return "//span[text()='" + placeholder + "']/ancestor::label/following-sibling::div//textarea";
    }

    public String frameCalculatedColumnLocator(String placeholder, int index) {
        return "(//span[text()='" + placeholder + "']/ancestor::label/following-sibling::div//input)[" + index + "]";
    }

    public String frameAddNewcalculatedColumnLocator(String placeholder, int index) {
        return "(//div[@data-dx_placeholder='" + placeholder + "']/ancestor::div//input)[" + index + "]";
    }

    public String frameCalculatedColumnDropDownLocator(String placeholder) {
        return "//div[@class='dx-item dx-list-item']/div[text()='" + placeholder + "']";
    }

    public String frameFilterGroupLocator(String Placeholder, int index) {
        return "((//div[text()='" + Placeholder + "'])//ancestor::table/parent::div/parent::div/following-sibling::div//input[@class='dx-texteditor-input'])[" + index + "]";
    }

    public String frameCalculationLocator(String placeholder) {
        return "//div[@data-dx_placeholder='" + placeholder + "']/..//input";
    }

    public AddNewRulePage validateTickMark(String fieldName) {
        pause("low");
        boolean isTickMarkVisisble = isVisible("//span[text()='" + fieldName + "']/ancestor::label/following-sibling::div//div[@class='dx-placeholder dx-state-invisible']");
        Assert.assertTrue(isTickMarkVisisble, "Tick Mark is Enabled");
        return new AddNewRulePage();
    }

    //Get first rule name text
    public AddNewRulePage getFirstRuleNametext() {
        pause("medium");
        if (isVisibleWithMinimumTime(firstRuleNameText)) {
            String innerText = getInnerText(firstRuleNameText);
            System.out.println("firstRuleNameText :" + innerText);
            getFirstRuleNametext.set(innerText);
        } else {
            clickOnAddNewRuleBtn()
                    .validateAddRulePage()
                    .createANewRule(ruleName.get(), FakerDataFactory.getRuleDescription())
                    .defineTheNewColumn(true, FakerDataFactory.getFieldName(), "")
                    .addFilters("Broker Name", "=")
                    .defineTheCalculation("Local Price", "*", "Local Price")
                    .validateNewRuleNameIsEnabled(ruleName.get())
                    .getFirstRuleNametext();
        }
        return new AddNewRulePage();
    }

    //Rule Name
    public RulesManagerPage validateNewRuleNameIsEnabled(String ruleName) {
        waitForLoaderToDisappear();
        pause("medium");
        waitForLoaderToDisappear();
        pause("low");

        int ruleNameCount = getLocatorsCount("(((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[6])[position() < last()]");
        boolean ruleFound = false; // Flag to track if rule is found

        for (int i = 1; i <= ruleNameCount; i++) {
            String ruleNameInnerText = getInnerText("((((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[6])[position() < last()])[" + i + "]");
            String enableCheckBox = "((((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[2])[position() < last()])[" + i + "]";
            String actionLocatorStr = "(//td[@class='action-edit-delete-column']/a)[" + i + "]";

            if (ruleNameInnerText.equals(ruleName)) {
                click(actionLocatorStr, "Action", "Action Icon");
                click(frameSpanTextLocator("Enable"), "Enable", "Action Item");
                waitForLoaderToDisappear();
                pause("low");

                boolean isRuleEnabled = isEnabled(enableCheckBox, "Enable Check Box");
                Assert.assertTrue(isRuleEnabled, "Rule is Enabled");
                reportPass("User successfully validated the new rule " + ruleName + "  and Rule is Enabled as Expected.");

                ruleFound = true; // Mark that rule is found
                break;
            }
        }

        // If no rule matched, report fail after the loop completes
        if (!ruleFound) {
            reportFail("The " + ruleName + " rule was not created in the Rule Manager.");
        }

        return this;
    }


    //Rule Name
    public RulesManagerPage validateNewRuleNameIsEnabled(String ruleName, boolean isEnabled) {
        waitForLoaderToDisappear();
        pause("medium");
        waitForLoaderToDisappear();
        pause("low");

        int ruleNameCount = getLocatorsCount("(((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[6])[position() < last()]");
        boolean ruleFound = false; // Flag to track if rule is found

        for (int i = 1; i <= ruleNameCount; i++) {
            String ruleNameInnerText = getInnerText("((((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[6])[position() < last()])[" + i + "]");
            String enableCheckBox = "((((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[2])[position() < last()])[" + i + "]";
            String actionLocatorStr = "(//td[@class='action-edit-delete-column']/a)[" + i + "]";

            if (ruleNameInnerText.equals(ruleName)) {
                // Use switch case to decide whether to enable the rule
                switch (isEnabled ? "enable" : "disable") {
                    case "enable":
                        click(actionLocatorStr, "Action", "Action Icon");
                        click(frameSpanTextLocator("Enable"), "Enable", "Action Item");
                        waitForLoaderToDisappear();
                        pause("low");

                        boolean isRuleEnabled = isEnabled(enableCheckBox, "Enable Check Box");
                        Assert.assertTrue(isRuleEnabled, "Rule is Enabled");
                        reportPass("User successfully validated the new rule " + ruleName + " and Rule is Enabled as Expected.");
                        break;

                    case "disable":
                        // Logic for disable (can be added here if needed in future)
                        reportPass("Rule " + ruleName + " is created Successfully as expected.");
                        break;
                }

                ruleFound = true; // Mark that rule is found
                break;
            }
        }

        // If no rule matched, report fail after the loop completes
        if (!ruleFound) {
            reportFail("The " + ruleName + " rule was not created in the Rule Manager.");
        }

        return this;
    }



    //Validate the edit changes
    public RulesManagerPage validateEditRuleChanges(String ruleName, String description) {
        waitForLoaderToDisappear();
        pause("medium");
        waitForLoaderToDisappear();
        pause("low");
        int ruleNameCount = getLocatorsCount("(((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[6])[position() < last()]");

        for (int i = 1; i <= ruleNameCount; i++) {
            String ruleNameInnerText = getInnerText("((((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[6])[position() < last()])[" + i + "]");
            String descriptionInnerText = "((((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[3])[position() < last()])[" + i + "]";

            if (ruleNameInnerText.equals(ruleName)) {
                Assert.assertEquals(ruleNameInnerText, ruleName);
                reportPass("User successfully validated the edited rule as Expected.");
                if (descriptionInnerText.equals(description)) {
                    Assert.assertEquals(descriptionInnerText, description);
                    break;
                }
            }
        }

        return this;
    }

    //Delete Rule Manager
    public RulesManagerPage deleteRuleName() {
        waitForLoaderToDisappear();
        pause("medium");
        waitForLoaderToDisappear();
        pause("low");
        int ruleNameCount = getLocatorsCount("(((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[6])[position() < last()]");
        if (ruleNameCount > 0) {
            for (int i = 1; i <= ruleNameCount; i++) {
                if (i == 1) {
                    String actionLocatorStr = "(//td[@class='action-edit-delete-column']/a)[" + i + "]";
                    click(actionLocatorStr, "Action", "Action Icon");
                    click(frameSpanTextLocator("Delete"), "Delete", "Delete Item");
                    pause("low");
                    click(frameSpanTextLocator("Yes"), "Yes", "Button");
                    pause("low");
                    waitForLoaderToDisappear();
                    reportPass("User successfully deleted the rule.");
                    break;
                }
            }
        } else {
            reportWarning("User unable to  delete the rule because there is no rule items");
        }
        return this;
    }

    //Delete Rule Manager
    public RulesManagerPage deleteRuleName(String ruleName) {
        waitForLoaderToDisappear();
        pause("medium");
        waitForLoaderToDisappear();
        pause("low");
        int ruleNameCount = getLocatorsCount("(((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[6])[position() < last()]");

        for (int i = 1; i <= ruleNameCount; i++) {
            String ruleNameInnerText = getInnerText("((((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[6])[position() < last()])[" + i + "]");
            String actionLocatorStr = "(//td[@class='action-edit-delete-column']/a)[" + i + "]";

            if (ruleNameInnerText.contains(ruleName)) {
                click(actionLocatorStr, "Action", "Action Icon");
                click(frameSpanTextLocator("Delete"), "Delete", "Delete Item");
                pause("low");
                click(frameSpanTextLocator("Yes"), "Yes", "Button");
                pause("low");
                waitForLoaderToDisappear();
                pause("low");
                reportPass("User successfully deleted the rule " + ruleName);
                break;
            }
        }
        return this;
    }


    //Validate whether the rule is  Deleted
    public RulesManagerPage validateRuleDeletion(String ruleName) {
        List<String> ruleNameTexts = new ArrayList<>();
        waitForLoaderToDisappear();
        pause("medium");
        waitForLoaderToDisappear();
        pause("low");

        int ruleNameCount = getLocatorsCount("(((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[6])[position() < last()]");

        for (int i = 1; i <= ruleNameCount; i++) {
            String ruleNameInnerText = getInnerText("((((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[6])[position() < last()])[" + i + "]");
            ruleNameTexts.add(ruleNameInnerText);
        }

        boolean isRulePresent = ruleNameTexts.contains(ruleName);

        if (isRulePresent) {
            reportFail("Rule name '" + ruleName + "' is present in the grid, which is not expected.");
        } else {
            System.out.println("Rule name '" + ruleName + "' is not present in the grid, as expected.");
            reportPass("Rule name '" + ruleName + "' is not present in the grid, as expected.");
        }

        return this;
    }


    public EditRulePage clickOnEditRule() {
        if (isVisible(actionRuleLocator, "Action Icon")) {
            click(actionRuleLocator, "Action", "Link");
            click(frameSpanTextLocator("Edit"), "Edit", "Link");
        } else {
            reportWarning("User is unable to edit the rule because there are no rule items.");
        }
        return new EditRulePage();
    }

    //Enable And Disable Rule
    public RulesManagerPage enableAndDisableRule(String isEnable) {
        waitForLoaderToDisappear();
        pause("medium");
        waitForLoaderToDisappear();
        pause("low");

        String enabledLocator = "((((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[2])[position() < last()]//div)[1]";
        getFirstRuleNametext();
        String getOutputColumnFirstGridText = getInnerText("((((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[1])[position() < last()]//div)[1]");

        //Check if the rule is Enable or Not
        String checkRuleIsEnabledOrNot = getAttribute(enabledLocator, "aria-checked");
        System.out.println("Check Rule Is Enabled Or Not : ---->" + checkRuleIsEnabledOrNot);

        switch (isEnable) {
            case "Enable":
                if (checkRuleIsEnabledOrNot.equals("true")) {
                    System.out.println("If---Enable");
                    clickUsingJs(enableCheckBoxLocator, "Enable Check box", "Check Box");
                    waitForLoaderToDisappear();
                }
                pause("low");
                clickUsingJs(enableCheckBoxLocator, "Enable Check box", "Check Box");
                waitForLoaderToDisappear();

                String checkRuleIsEnabled = getAttribute(enabledLocator, "aria-checked");
                System.out.println("Check Rule Is Enabled : ---->" + checkRuleIsEnabled);

                boolean isRuleEnabled = Boolean.parseBoolean(checkRuleIsEnabled);
                System.out.println("Check Rule Is Enabled : ---->Boolean >>>>" + isRuleEnabled);

                Assert.assertTrue(isRuleEnabled);
                reportPass("User Successfully enabled the Existing rule");
                break;
            case "Disable":
                if (checkRuleIsEnabledOrNot.equals("false")) {
                    System.out.println("If---Disable");
                    clickUsingJs(enableCheckBoxLocator, "Enable Check box", "Check Box");
                    waitForLoaderToDisappear();
                }
                pause("low");
                clickUsingJs(enableCheckBoxLocator, "Enable Check box", "Check Box");
                waitForLoaderToDisappear();

                String checkRuleIsDisabled = getAttribute(enabledLocator, "aria-checked");
                System.out.println("Check Rule Is Disabled : ---->" + checkRuleIsDisabled);

                boolean isRuleDisabled = Boolean.parseBoolean(checkRuleIsDisabled);
                System.out.println("Check Rule Is Enabled : ---->Boolean >>>>" + isRuleDisabled);

                Assert.assertFalse(isRuleDisabled);
                reportPass("User Successfully disabled the Existing rule");
                break;
            default:
                break;
        }

        return this;
    }
}
