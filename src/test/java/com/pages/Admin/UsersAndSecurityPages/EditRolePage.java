package com.pages.Admin.UsersAndSecurityPages;

public static class EditRolePage extends UserSecurityPage {

    private String editRoleText = "//div[text()='Edit role']";
    private String applyBtn = "//span[text()='Apply']";

    public EditRolePage validateEditRolePage() {
        waitForElementToLoad(editRoleText);
        if (isVisible(editRoleText, "Edit Role Text")) {
            reportPass("User successfully reached the correct Edit Role page");
        } else {
            reportFailAndThrowException("Edit Role Page is not loaded.");
        }
        return this;
    }

    public EditRolePage clearTypeRoleName(String roleName) {
        typeAndSpace(frameClientDetailsLocator("Role Name"), roleName, "RoleName");
        return this;
    }

    public EditRolePage clearTypeRoleDescription(String roleDescription) {
        pause("low");
        typeAndSpace(frameClientDetailsLocator("Role Description"), roleDescription, "RoleDescription");
        return this;
    }

    public EditRolePage clickAndselectTier(String tier) {
        clickAndChoose(frameClientDetailsLocator("Tier", 2), frameUserSelectDropDownLocator(tier), tier, "Tier");
        return this;
    }

    public EditRolePage clickAndselectClient(String client) {
        clickAndChoose(frameClientDetailsLocator("Client", 4), frameUserSelectDropDownLocator(client), client, "Client");
        return this;
    }

    public EditRolePage clickAndselectPermissions(String permission) {
        pause("low");
        clickAndChoose(frameClientDetailsLocator("Permissions", 1), selectPermissionCheckBox(permission), permission, "Permissions");
        return this;
    }


    public EditRolePage editRoleInformation(String roleName, String roleName2, String roleDescription, String tier, String client, String permission) {
        waitForElementToLoad(frameClientDetailsLocator("Role Name"));

        //  waitForElementToLoad(frameClientDetailsLocator("Role Name"));
        clearTypeRoleName(roleName2)
                .clearTypeRoleDescription(roleDescription)
//                .clickAndselectTier(tier)
//                .clickAndselectClient(client)
                .clickAndselectPermissions(permission);
        reportPass("User successfully edited Role Information");

        // waitForElementToLoad(frameClientDetailsLocator("Role Name"));
        clearTypeRoleName(roleName)
                .clearTypeRoleDescription(roleDescription)
//                .clickAndselectTier(tier)
//                .clickAndselectClient(client)
                .clickAndselectPermissions(permission);
        reportPass("User successfully edited Role Information");
    }
        return this;
}

//Validate Tickmark

public UserSecurityPage clickOnApplyBtn() {
    click(applyBtn, "Apply Button");
    pause("low");
    return new UserSecurityPage();
}

}
