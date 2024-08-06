package com.westminster.testcases.login;

import com.page.base.DashboardPage;
import com.page.base.WestMinsterPageHook;
import com.ui.utilities.TestGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC_Login extends WestMinsterPageHook {
    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", "Tier2User", TestGroups.Type.REGRESSION})
    public void setReportValues() {
        testcaseName = "Login with " + xmlUserLevel;
        testDescription = "Login with " + xmlUserLevel;
        authors = "Automation Team";
        category = "Login Page";
    }

    // Phase -2 - Manoj
    // Validate invalid login attempts.
    @Test(priority = 3, groups = {"Tier1Admin", TestGroups.Type.REGRESSION})
    public void validateInvalidLogin() {
        testcaseName = "TC_RE_34";
        testDescription = "Validate invalid login";
        authors = "Automation Team";
        category = "Validate invalid login";
        startTestCase();
        setNode();

        new DashboardPage()
                .validateDashBoardElements()
                .clickOnLogout()
                .navigateURL()
                .doLogin("tier_2_admin@ChrisQA.com", "TEST AUTHENTICATION PROVIDE")
                .validateLoginErrorMsg("Invalid Login Information");
        System.out.println("TC-----------TC_RE_34");
    }
}
