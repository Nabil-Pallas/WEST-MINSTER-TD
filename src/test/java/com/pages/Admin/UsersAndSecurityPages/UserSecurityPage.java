package com.pages.Admin.UsersAndSecurityPages;

import org.testng.Assert;

import com.page.base.DashboardPage;
import com.pages.Admin.AdminPage;

public class UserSecurityPage extends AdminPage {

    private String userAndsecurityText = "//h1[text()='Users & Security']";
    private String usersTab = "//span[text()='Users']";
    private String rolesTab = "// span[text()='Roles']";
    private String addNewUserBtn = "//span[text()='Add a new user']";
    private String addNewRoleBtn = "//span[text()='Add a new role']";
    private String addNewClientBtn = "//span[text()='Add a new client']";
    private String addNewUserText = "//div[text()='Add New user']";
    private String clientGridFirstRow = "(//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines'])[1]/td[1]";
    private String searchClient = "(//div[@class='dx-icon dx-icon-search']/following-sibling::input)[1]";
    private String searchUser1 = "(//div[@class='dx-icon dx-icon-search']/following-sibling::input)[2]";
    private String searchUser = "//div[@data-dx_placeholder='Search users…']/..//input";
    private String searchRole = "//div[@data-dx_placeholder='Search roles…']/..//input";
    private String clientActionLink = "(//tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines'])[2]/td[2]//a";
    private String userActionLink = "((((//tbody[@role='presentation'])[8])//tr)//td[2])//a";
    private String userActionLinkTier2Admin = "((((//tbody[@role='presentation'])[4])//tr)//td[2])//a";
    private String roleActionTier1Admin = "((((((//tbody[@role='presentation'])[8])//tr)[1])//td)[3])//a";
    private String roleActionTier1User = "((((((//tbody[@role='presentation'])[7])//tr)[1])//td)[3])//a";
    private String roleActionLinkTier2Admin = "((((((//tbody[@role='presentation'])[4])//tr)[1])//td)[3])//a";
    private String applyBtn = "//span[text()='Apply']";
    private String firstName1 = "Automation_QA_testing";
    private String firstName2 = "QA_Automation_testing";

    public UserSecurityPage validateUserSecurityPage() {
        waitForElementToLoad(userAndsecurityText);
        if (isVisible(adminText, "Admin Text")) {
            reportPass("User successfully reached the correct User Security Page");
        } else {
            reportFailAndThrowException("User Security Page is not loaded.");
        }
        return this;
    }

    public String frameEditClientDetailsResult(String placeholder) {
        return "//span[text()='" + placeholder + "']";
    }

    public UserSecurityPage clickOnUserTab() {
        click(usersTab, "User Tab");
        pause("medium");
        waitForLoaderToDisappear();
        pause("medium");
        waitForLoaderToDisappear();
        waitForAppearance(addNewUserBtn, "Add a new user");
        return this;
    }

    public UserSecurityPage clickOnRolesTab() {
        click(rolesTab, "Roles Tab");
        pause("low");
        waitForLoaderToDisappear();
        waitForAppearance(addNewRoleBtn, "Add a new Role");
        return this;
    }

    public AddNewUserPage clickOnaddNewUsrBtn() {
        pause("low");
        waitForLoaderToDisappear();
        // waitForElementToLoad(addNewUserBtn);
        waitForAppearance(addNewUserBtn, "Add a new user");
        click(addNewUserBtn, "Add New User");
        return new AddNewUserPage();
    }

    public AddNewRolePage clickOnaddNewRoleBtn() {
        pause("low");
        waitForLoaderToDisappear();
        waitForAppearance(addNewRoleBtn, "Add a new role");
        click(addNewRoleBtn, "Add New Role");
        return new AddNewRolePage();
    }

    public AddNewClientPage clickOnaddNewClientBtn() {
        click(addNewClientBtn, "Add New Client");
        return new AddNewClientPage();
    }

    public UserSecurityPage validateAddNewUserPage() {
        waitForElementToLoad(addNewUserText);
        if (isVisible(addNewUserText, "Add New User Text")) {
            reportPass("User reaches to the right Add New User page");
        } else {
            reportFailAndThrowException("Add New User Page is not loaded.");
        }
        return this;
    }


    public UserSecurityPage typeSearchClient(String clientName) {
        waitForLoaderToDisappear();
        type(searchClient, clientName, "Search Clients");
        pause("low");
        return this;
    }

    public UserSecurityPage typeSearchUser(String userDisplayName) {
        type(searchUser, userDisplayName, "Search User");
        pause("low");
        return this;
    }

    public UserSecurityPage typeSearchRoles(String roleDisplayName) {
        type(searchRole, roleDisplayName, "Search Roles");
        pause("low");
        return this;
    }

    public UserSecurityPage validateClientInformation(String clientName) {
        waitForLoaderToDisappear();
        typeSearchClient(clientName);
        pause("medium");
        boolean verifyExactText = verifyExactText(clientGridFirstRow, clientName);
        Assert.assertTrue(verifyExactText);
        return this;
    }

    public UserSecurityPage validateUserInformation(String userName, String emailId) {
        waitForLoaderToDisappear();
        pause("medium");
        waitForLoaderToDisappear();
        typeSearchUser(emailId);
        waitForElementToLoad(frameUserSearchResult(userName));
        boolean verifyExactText = verifyExactText(frameUserSearchResult(userName), userName);
        Assert.assertTrue(verifyExactText);

        return this;
    }


    public UserSecurityPage validateRoleInformation(String roleName) {
        waitForLoaderToDisappear();
        pause("medium");
        waitForLoaderToDisappear();
        typeSearchRoles(roleName);
        waitForElementToLoad(frameUserSearchResult(roleName));
        boolean verifyExactText = verifyExactText(frameUserSearchResult(roleName), roleName);
        Assert.assertTrue(verifyExactText);
        return this;
    }

    public UserSecurityPage validateRoleInformation(String roleName, String roleName2) {
        if(roleName.equals("Automation_QA_testing")) {
            waitForLoaderToDisappear();
            pause("medium");
            waitForLoaderToDisappear();
            typeSearchRoles(roleName);
            waitForElementToLoad(frameUserSearchResult(roleName));
            boolean verifyExactText = verifyExactText(frameUserSearchResult(roleName), roleName);
            Assert.assertTrue(verifyExactText);
        }else{
            waitForLoaderToDisappear();
            pause("medium");
            waitForLoaderToDisappear();
            typeSearchRoles(roleName2);
            waitForElementToLoad(frameUserSearchResult(roleName2));
            boolean verifyExactText = verifyExactText(frameUserSearchResult(roleName2), roleName2);
            Assert.assertTrue(verifyExactText);

        }
        return this;
    }

    public UserSecurityPage validateUserRoleAndMail(String emailId,String role) {
        waitForLoaderToDisappear();
        pause("medium");
        waitForLoaderToDisappear();
        System.out.println("--validateUserRoleAndMail--"+emailId);
        typeSearchUser(emailId);
        waitForElementToLoad(frameUserSearchResult(emailId));
        boolean verifyExactText = verifyExactText(frameUserSearchResult(emailId), emailId);
        Assert.assertTrue(verifyExactText);
        if(verifyExactText(frameUserSearchResult(emailId), emailId)){
            boolean verifyExactRole = verifyExactText("//td[text()='"+role+"']", role);
            Assert.assertTrue(verifyExactRole);
        }

        return this;
    }

    public EditClientPage searchAndEditClientDetails() {
        waitForLoaderToDisappear();
        String firstGridName = getInnerText("(//table)[3]//tr[8]/td[1]");
        typeSearchClient(firstGridName);
        pause("low");
        click(clientActionLink, "First Grid Action Item", "Link");
        waitForAppearance(frameEditClientDetailsResult("Edit Client Details"), "Edit Client Details");
        click(frameEditClientDetailsResult("Edit Client Details"), "Edit Client Details Button");
        return new EditClientPage();
    }

    public UserSecurityPage searchUserAndClickFirstGrid(String FirstName) {
        waitForLoaderToDisappear();
//        String firstGridName = getInnerText("(//tr[1])[7]//td[@aria-colindex='1']");
        switch (xmlUserLevel) {
            case "Tier2Admin":
                userMailIdText.set(getInnerText("(//tr[1])[3]//td[@aria-colindex='5']"));
                break;
            default:
                userMailIdText.set(getInnerText("(//tr[1])[7]//td[@aria-colindex='6']"));
                break;
        }
        typeSearchUser(userMailIdText.get());
        pause("medium");
        switch (xmlUserLevel) {
            case "Tier2Admin":
                click(userActionLinkTier2Admin, "First Grid Action Item", "Link");
                break;
            default:
                click(userActionLink, "First Grid Action Item", "Link");
                break;
        }

      return this;
    }
    public EditUserPage clickOnEditUser(){
        waitForAppearance(frameEditClientDetailsResult("Edit User"), "Edit User");
        click(frameEditClientDetailsResult("Edit User"), "Edit User Button");
        return new EditUserPage();
    }

    public UserSecurityPage clickOnImpersonateUser(){
        waitForAppearance(frameEditClientDetailsResult("Impersonate User"), "Impersonate User");
        click(frameEditClientDetailsResult("Impersonate User"), "Impersonate User Button");
        return this;
    }
    public DashboardPage clickOnProceedAsUser(){
        waitForAppearance(frameEditClientDetailsResult("Proceed as User"), "Proceed as User");
        click(frameEditClientDetailsResult("Proceed as User"), "Proceed as User Button");
        return new DashboardPage();
    }



    public EditRolePage searchAndEditRoleDetails(String roleName, String roleName2) {
        waitForLoaderToDisappear();
        String firstName1 = roleName;
        String firstName2 = roleName2;
        switch (xmlUserLevel) {
            case "Tier2Admin":
//                String firstGridName1 = getInnerText("(//tr[1])[3]//td[@aria-colindex='2']");
                typeSearchRoles(firstName1);
                String firstGridName1 = getInnerText("(//tr[1])[3]//td[@aria-colindex='2']");
                if(firstName1.equals(firstGridName1)){
               //String firstGridName1 = getInnerText("(//tr[1])[3]//td[@aria-colindex='2']");
                pause("medium");
                click(roleActionLinkTier2Admin, "First Grid Action Item", "Link");
                pause("medium");
                click(frameEditClientDetailsResult("Edit Role"), "Edit Role Button");
            } else {
                    typeSearchRoles(firstName2);
                    pause("medium");
                    click(roleActionLinkTier2Admin, "First Grid Action Item", "Link");
                    pause("medium");
                    click(frameEditClientDetailsResult("Edit Role"), "Edit Role Button");
                }
                break;

            case "Tier1User":
//                String firstGridName1 = getInnerText("(//tr[1])[3]//td[@aria-colindex='2']");
                typeSearchRoles(firstName1);
                pause("low");
                String firstGridName2 = getInnerText("(//tr[1])[7]//td[@aria-colindex='2']");
                if(firstName1.equals(firstGridName2)){
                    //String firstGridName1 = getInnerText("(//tr[1])[3]//td[@aria-colindex='2']");
                    pause("medium");
                    click(roleActionTier1User, "First Grid Action Item", "Link");
                    pause("medium");
                    click(frameEditClientDetailsResult("Edit Role"), "Edit Role Button");
                } else {
                    typeSearchRoles(firstName2);
                    pause("medium");
                    click(roleActionTier1User, "First Grid Action Item", "Link");
                    pause("medium");
                    click(frameEditClientDetailsResult("Edit Role"), "Edit Role Button");
                }
                break;
            default:
//                String firstGridName1 = getInnerText("(//tr[1])[3]//td[@aria-colindex='2']");
                typeSearchRoles(firstName1);
                String firstGridName3 = getInnerText("(//tr[1])[7]//td[@aria-colindex='2']");
                if(firstName1.equals(firstGridName3)){
                    //String firstGridName1 = getInnerText("(//tr[1])[3]//td[@aria-colindex='2']");
                    pause("medium");
                    click(roleActionTier1Admin, "First Grid Action Item", "Link");
                    pause("medium");
                    click(frameEditClientDetailsResult("Edit Role"), "Edit Role Button");
                } else {
                    typeSearchRoles(firstName2);
                    pause("medium");
                    click(roleActionTier1Admin, "First Grid Action Item", "Link");
                    pause("medium");
                    click(frameEditClientDetailsResult("Edit Role"), "Edit Role Button");
                }
//                //String firstGridName = getInnerText("(//tr[1])[7]//td[@aria-colindex='2']");
//                typeSearchRoles(firstGridName);
//                pause("medium");
//                click(roleActionTier1Admin, "First Grid Action Item", "Link");
                break;
        }
        //pause("medium");
       // waitForAppearance(frameEditClientDetailsResult("Edit Role"), "Edit Role");
//        click(frameEditClientDetailsResult("Edit Role"), "Edit Role Button");
        return new EditRolePage();
    }


    public UserSecurityPage clickOnApplyBtn() {
        pause("medium");
        if(isVisible("// span[text()='Apply']")) {
            click(applyBtn, "Apply Button");
        }else{
            click("(//span[text()='Apply'])[2]", "Apply Button");
        }
        pause("medium");
        if(isVisible(frameSpanTextLocator("YES"))){
            pause("low");
            click(frameSpanTextLocator("YES"),"YES");
        }else {
            System.out.println("Tier And Role Selected Successfully");
        }
        return new UserSecurityPage();
    }

    public UserSecurityPage deleteClient(String clientName){
        waitForLoaderToDisappear();
        pause("medium");
        click("(// tr[@class='dx-row dx-data-row dx-row-lines dx-column-lines'])[2]/ td[2]// a","Action Link");
        pause("low");
        click("//span[text()='Delete Client']","Delete Link","Action Item Popup");
        //click Yes popup
        click(frameSpanTextLocator("Yes"), "Yes");
        pause("low");
        typeAndEnter(frameClientDetailsLocator("Please type the word \"DELETE\" to confirm deletion."), "DELETE", "Delete Field");
        pause("low");
        click(frameSpanTextLocator("Confirm Delete"), "Confirm Delete");
        return this;
    }

    public UserSecurityPage deleteUser(String UserName){
        waitForLoaderToDisappear();
        pause("medium");

        switch (xmlUserLevel) {
            case "Tier2Admin":
                click(userActionLinkTier2Admin, "First Grid Action Item", "Link");
                break;
            default:
                click(userActionLink, "First Grid Action Item", "Link");
                break;
        }
        pause("low");
        click("//span[text()='Delete User']","Delete Link","Action Item Popup");
        //click Yes popup
        click(frameSpanTextLocator("Yes"), "Yes");
        pause("low");
        typeAndEnter(frameClientDetailsLocator("Please type the word \"DELETE\" to confirm deletion."), "DELETE", "Delete Field");
        pause("low");
        click(frameSpanTextLocator("Confirm Delete"), "Confirm Delete");
        return this;
    }

    public UserSecurityPage deleteRole(String RoleName){
        waitForLoaderToDisappear();
        pause("medium");

        switch (xmlUserLevel) {
            case "Tier2Admin":
                click(roleActionLinkTier2Admin, "First Grid Action Item", "Link");
                break;
            default:
                click(roleActionTier1Admin, "First Grid Action Item", "Link");
                break;
        }
        pause("low");
        click("//span[text()='Delete Role']","Delete Link","Action Item Popup");
        //click Yes popup
        click(frameSpanTextLocator("Yes"), "Yes");
        pause("low");
        typeAndEnter(frameClientDetailsLocator("Please type the word \"DELETE\" to confirm deletion."), "DELETE", "Delete Field");
        pause("low");
        click(frameSpanTextLocator("Confirm Delete"), "Confirm Delete");
        return this;
    }



}


