package com.pages.Admin.UsersAndSecurityPages;

public class AddNewRolePage extends UserSecurityPage {


    private String addNewRoleText = "//div[text()='Add New role']";
    private String applyBtn = "//span[text()='Apply']";


    public AddNewRolePage validateNewClientPage() {
        waitForElementToLoad(addNewRoleText);
        if (isVisible(addNewRoleText, "Add New Role Text")) {
            reportPass("User reaches to the right Add New Role page");
        } else {
            reportFailAndThrowException("Add New Role Page is not loaded.");
        }
        return this;
    }

    public AddNewRolePage typeRoleName(String roleName) {
        typeAndSpace(frameClientDetailsLocator("Role Name"), roleName, "RoleName");
        return this;
    }

    public AddNewRolePage typeRoleDescription(String roleDescription) {
        pause("low");
        type(frameClientDetailsLocator("Role Description"), roleDescription, "RoleDescription");
        return this;
    }

    public AddNewRolePage clickAndselectTier(String tier) {
        pause("low");
        clickAndChoose(frameClientDetailsLocator("Tier", 2), frameUserSelectDropDownLocator(tier), tier, "Tier");
        return this;
    }

    public AddNewRolePage clickAndselectClient(String client) {
        pause("low");
//        //div[@class='dx-item dx-list-item']//div
        click(frameClientDetailsLocator("Client", 4), "Client Drop Down");
        clickUsingJs(frameUserSelectDropDownLocator(client), client, "Client");
//        clickAndChoose(frameClientDetailsLocator("Client", 4), frameUserSelectDropDownLocator(client), client, "Client");
        if (isVisible("//div[text()='Invalid Field']") || isVisible("(//div[text()='Must have a client.'])[1]")) {
//            clickAndChoose(frameClientDetailsLocator("Client", 4), frameUserSelectDropDownLocator(client), client, "Client");
            click(frameClientDetailsLocator("Client", 4), "Client Drop Down");
            clickUsingJs(frameUserSelectDropDownLocator(client), client, "Client");
        }
        return this;
    }

    public AddNewRolePage clickAndselectPermissions(String permission) {
        pause("low");
        clickAndChoose(frameClientDetailsLocator("Permissions", 1), selectPermissionCheckBox(permission), permission, "Permissions");
        return this;
    }


    public AddNewRolePage fillRoleInformation(String roleName, String roleDescription, String tier, String client, String permission) {

        switch (xmlUserLevel) {
            case "Tier2Admin":
                waitForElementToLoad(frameClientDetailsLocator("Role Name"));
                typeRoleName(roleName)
                        .typeRoleDescription(roleDescription)
                        .clickAndselectTier(tier)
                        .clickAndselectPermissions(permission);
                reportPass("User successfully filled Role Information");
                break;
            default:
                waitForElementToLoad(frameClientDetailsLocator("Role Name"));
                typeRoleName(roleName)
                        .typeRoleDescription(roleDescription)
                        .clickAndselectTier(tier)
                        .clickAndselectClient(client)
                        .clickAndselectPermissions(permission);
                reportPass("User successfully filled Role Information");
                break;
        }

        return this;
    }

    //Validate Tickmark


}
