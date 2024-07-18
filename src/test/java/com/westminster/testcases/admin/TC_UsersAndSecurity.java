package com.westminster.testcases.admin;

import com.ui.utilities.TestGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.dynamic.FakerDataFactory;
import com.listener.TestListener;
import com.page.base.DashboardPage;
import com.page.base.WestMinsterPageHook;

@Listeners(TestListener.class)
public class TC_UsersAndSecurity extends WestMinsterPageHook {

    @BeforeTest(groups = {"Tier1Admin", "Tier1User", "Tier2Admin", TestGroups.Type.SANITY, TestGroups.Type.REGRESSION})
    public void setReportValues() {
        testcaseName = "TC_2136 " + xmlUserLevel;
        testDescription = "Create New User and Validate";
        authors = "Automation Team";
        category = "Create Client";
    }

    // Client creation - 2136
    @Test(priority = 22, groups = {"Tier1Admin", TestGroups.Type.SANITY})
    public void createClient() {
        clientCompanyName.set(FakerDataFactory.getCompanyNameWithoutSpace());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnUserSecurity()
                .validateUserSecurityPage()
                .clickOnaddNewClientBtn()
                .validateNewClientPage()
                .fillClientInformation(clientCompanyName.get(), FakerDataFactory.getFTPFolderAcronym(),
                        FakerDataFactory.getFirstName(), FakerDataFactory.getLastName(),
                        FakerDataFactory.getEmailAddress(), "MM/DD/YYYY", "", "", "", "")
                .clickOnApplyBtn()
                .validateClientInformation(clientCompanyName.get())
                .deleteClient(clientCompanyName.get());
        System.out.println("TC-----------22");
    }

    // Edit created client - 23
    @Test(priority = 23, groups = {"Tier1Admin", TestGroups.Type.SANITY})
    public void editClient() {
        testcaseName = "TC_23 " + xmlUserLevel;
        testDescription = "Edit Existing Client and Validate";
        authors = "Automation Team";
        category = "Edit Client";
        startTestCase();
        setNode();
        clientCompanyName.set(FakerDataFactory.getCompanyNameWithoutSpace());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnUserSecurity()
                .validateUserSecurityPage()
                .searchAndEditClientDetails("Z_Automation_QA_testing", "QA_testing_Automation_Z")
                .validateEditClientPage()
                .editClientInformation("Z_Automation_QA_testing", "QA_testing_Automation_Z", FakerDataFactory.getFTPFolderAcronym(),
                        "MM/DD/YYYY", "HST: Hawaii-Aleutian Standard Time (US, Hawaii)", "12-hour", "#,##0.00", "USD")
                .clickOnApplyBtn()
                .validateClientInformation("Z_Automation_QA_testing", "QA_testing_Automation_Z");
        System.out.println("TC-----------23");
    }

    // User creation - 5613
//    @Test(priority = 24, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", TestGroups.Type.SANITY})
    public void createUser() {
        testcaseName = "TC_5613 " + xmlUserLevel;
        testDescription = "Create User and Validate";
        authors = "Automation Team";
        category = "Create User";
        startTestCase();
        setNode();
        userFirstName.set(FakerDataFactory.getFirstName());
        userLastName.set(FakerDataFactory.getLastName());
        userMailId.set(FakerDataFactory.getEmailAddress());
        String displayUserName = userFirstName.get() + " " + userLastName.get();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnUserSecurity()
                .validateUserSecurityPage()
                .clickOnUserTab()
                .clickOnaddNewUsrBtn()
                .fillUserInformation(userFirstName.get(), userLastName.get(), userMailId.get(),
                        FakerDataFactory.getContactNumber(), "Tier 1", "Tier 2", "Sri_Role")
                .clickOnApplyBtn()
                .validateUserInformation(displayUserName, userMailId.get())
                .deleteUser(displayUserName);
        System.out.println("TC-----------24");
    }

    // Edit created user - 24
//    @Test(priority = 25, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", TestGroups.Type.SANITY})
    public void editUser() {
        testcaseName = "TC_24 " + xmlUserLevel;
        testDescription = "Edit Existing User and Validate";
        authors = "Automation Team";
        category = "Edit User";
        startTestCase();
        setNode();
        userFirstName.set("Automation");
        userLastName.set("QA Testing");
        String userName = userFirstName.get() + " " + userLastName.get();
        String userName1 = userLastName.get() + " " + userFirstName.get();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnUserSecurity()
                .validateUserSecurityPage()
                .clickOnUserTab()
                .searchUserAndClickFirstGrid(userName, userName1)
                .clickOnEditUser()
                .validateEditUserPage()
                .editUserInformation(userFirstName.get(), userLastName.get(), userFirstName.get(), userLastName.get(), FakerDataFactory.getContactNumber())
                .clickOnApplyBtn()
                .validateEditUserInformation(userName, userName1);
        System.out.println("TC-----------25");

    }

    // Role creation - 101
//    @Test(priority = 26, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", TestGroups.Type.SANITY})
    public void createRole() {
        testcaseName = "TC_101 " + xmlUserLevel;
        testDescription = "Create New Role and Validate";
        authors = "Automation Team";
        category = "Create Role";
        startTestCase();
        setNode();
        userFirstName.set(FakerDataFactory.getRoleName());
        userLastName.set(FakerDataFactory.getLastName());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnUserSecurity()
                .validateUserSecurityPage()
                .clickOnRolesTab()
                .clickOnaddNewRoleBtn()
                .fillRoleInformation(userFirstName.get(), userLastName.get(), "Tier 2", "my-new-test-environment",
                        "Select All")
                .clickOnApplyBtn()
                .validateRoleInformation(userFirstName.get())
                .deleteRole(userFirstName.get());
        System.out.println("TC-----------26");
    }

    // Edit created role - 621
//    @Test(priority = 27, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", TestGroups.Type.SANITY})
    public void editRole() {
        testcaseName = "TC_621 " + xmlUserLevel;
        testDescription = "Edit Existing Role and Validate";
        authors = "Automation Team";
        category = "Edit Role";
        startTestCase();
        setNode();

        userFirstName.set(FakerDataFactory.getRoleName());
        userLastName.set(FakerDataFactory.getLastName());
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnUserSecurity()
                .validateUserSecurityPage()
                .clickOnRolesTab()
                .searchAndEditRoleDetails("Automation_QA_testing", "QA_Automation_testing")
                .validateEditRolePage()
                .editRoleInformation("QA_Automation_testing", "Automation_QA_testing", userLastName.get(), "", "", "Manage Data")
                .clickOnApplyBtn()
                .validateRoleInformation("QA_Automation_testing", "Automation_QA_testing");
        System.out.println("TC-----------27");
    }

    //Srikanth
    //Regression
    //TC_25 - Impersonate User
//    @Test(priority = 28, groups = {"Tier1Admin", "Tier1User", TestGroups.Type.REGRESSION})
    public void impersonateUser() {
        testcaseName = "TC_RE_25 " + xmlUserLevel;
        testDescription = "Impersonate User And Validate";
        authors = "Automation Team";
        category = "Impersonate User";
        startTestCase();
        setNode();
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnUserSecurity()
                .validateUserSecurityPage()
                .clickOnUserTab()
                .searchUserAndClickFirstGrid("")
                .clickOnImpersonateUser()
                .clickOnProceedAsUser()
                .validateDashBoardElements()
                .validateImpersonatingAsUserText()
                .validateImpersonateInAccountSettings(userMailIdText.get());
        System.out.println("TC-----------27");
    }

    //Srikanth
    //TC_2004
    @Test(priority = 29, groups = {"Tier1Admin", "Tier1User", "Tier2Admin", TestGroups.Type.REGRESSION})
    public void roleCreationAndAssignRole() {
        testcaseName = "TC_2004 " + xmlUserLevel;
        testDescription = "Create Role And Assign that Role to User";
        authors = "Automation Team";
        category = "roleCreationAndAssignRole";
        startTestCase();
        setNode();

        userFirstName.set(FakerDataFactory.getRoleName());
        userLastName.set(FakerDataFactory.getLastName());
        userMailId.set(FakerDataFactory.getEmailAddress());
        String mail = userMailId.get();
        System.out.println("---" + mail);
        new DashboardPage()
                .validateDashBoardElements()
                .clickOnAdmin()
                .validateAdminPage()
                .clickOnUserSecurity()
                .validateUserSecurityPage()
                .clickOnRolesTab()
                .clickOnaddNewRoleBtn()
                .fillRoleInformation(userFirstName.get(), userLastName.get(), "Tier 2", "my-new-test-environment", "Select All")
                .clickOnApplyBtn()
                .validateRoleInformation(userFirstName.get())
                .clickOnUserTab()
                .clickOnaddNewUsrBtn()
                .fillUserInformation2(FakerDataFactory.getFirstName(), FakerDataFactory.getLastName(), userMailId.get(),
                        FakerDataFactory.getContactNumber(), "Tier 1", "Tier 2", userFirstName.get())
                .clickOnApplyBtn()
                .validateUserRoleAndMail(userMailId.get(), userFirstName.get());

    }

}
