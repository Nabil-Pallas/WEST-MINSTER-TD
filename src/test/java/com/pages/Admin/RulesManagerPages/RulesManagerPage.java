package com.pages.Admin.RulesManagerPages;

import com.microsoft.playwright.Locator;
import com.page.base.DashboardPage;
import com.pages.Admin.AdminPage;
import org.testng.Assert;

public class RulesManagerPage extends AdminPage {

    private String rulesManagerText = "//h1[text()='Rules Manager']";
    private String enableCheckBox = "((((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[2])[position() < last()])";
    private String ruleNameText = "((//tbody//tr)[4]//td)[3]";
    private String descriptionText = "((//tbody//tr)[4]//td)[6]";
    private String outputColumnText = "((//tbody//tr)[4]//td)[8]";
    private String actionRuleLocator = "(//td[@class='action-edit-delete-column']/a)[1]";


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
    //Rule Name:

    public RulesManagerPage validateNewRuleNameIsEnabled(String ruleName) {
        waitForLoaderToDisappear();
        pause("medium");
        waitForLoaderToDisappear();
        pause("low");
        int ruleNameCount = getLocatorsCount("(((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[6])[position() < last()]");

        for (int i = 1; i <= ruleNameCount; i++) {
            String ruleNameInnerText = getInnerText("((((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[6])[position() < last()])[" + i + "]");
            String enableCheckBox = "((((//tbody[@role='presentation'])[3]//tr//td[9])/preceding-sibling::td[2])[position() < last()])[" + i + "]";
            String actionLocatorStr = "(//td[@class='action-edit-delete-column']/a)[" + i + "]";

            if (ruleNameInnerText.equals(ruleName)) {
                click(actionLocatorStr, "Action", "Action Icon");
                click(frameSpanTextLocator("Enable"), "Enable", "Action Item");
                waitForLoaderToDisappear();
                pause("low");
                boolean isRuleEnable = isEnabled(enableCheckBox, "Enable Check Box");
                Assert.assertTrue(isRuleEnable, "Rule is Enabled");
                reportPass("User successfully validated the new rule and Rule is Enabled as Expected.");
                break;
            }
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

    public EditRulePage clickOnEditRule() {
        if (isVisible(actionRuleLocator, "Action Icon")) {
            click(actionRuleLocator, "Action", "Link");
            click(frameSpanTextLocator("Edit"), "Edit", "Link");
        } else {
            reportWarning("User is unable to edit the rule because there are no rule items.");
        }
        return new EditRulePage();
    }
}
