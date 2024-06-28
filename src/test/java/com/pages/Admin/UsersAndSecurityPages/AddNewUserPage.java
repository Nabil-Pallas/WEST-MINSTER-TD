package com.pages.Admin.UsersAndSecurityPages;


public class AddNewUserPage extends UserSecurityPage {


    private String addNewUserText = "//div[text()='Add New user']";

    public AddNewUserPage validateNewClientPage() {
        waitForElementToLoad(addNewUserText);
        if (isVisible(addNewUserText, "Add New User Text")) {
            reportPass("User reaches to the right Add New User page");
        } else {
            reportFailAndThrowException("Add New User Page is not loaded.");
        }
        return this;
    }

    public AddNewUserPage typeFirstName(String userFirstName) {
        type(frameClientDetailsLocator("First Name"), userFirstName, "UserFirstName");
        return this;
    }

    public AddNewUserPage typeLastName(String userLastName) {
        pause("low");
        type(frameClientDetailsLocator("Last Name"), userLastName, "UserLastName");
        return this;
    }

    public AddNewUserPage typeUserEmail(String email) {
        typeAndEnter(frameClientDetailsLocator("Email"), email, "Email");
        pause("low");
        return this;
    }

    public AddNewUserPage typePhoneNo(String phoneNo) {
        type(frameClientDetailsLocator("Phone Number", 2), phoneNo, "PhoneNo");
        return this;
    }

    public AddNewUserPage clickAndselectTier(String tier, String tier1) {
        switch (xmlUserLevel) {
            case "Tier2Admin":
                clickAndChoose(frameClientDetailsLocator("Tier", 2), frameUserSelectDropDownLocator(tier1), tier, "Tier");
                break;
            default:
                clickAndChoose(frameClientDetailsLocator("Tier", 2), frameUserSelectDropDownLocator(tier), tier, "Tier");
                break;
        }
        return this;
    }

    public AddNewUserPage clickAndselectRole(String role) {
        pause("low");
        clickAndChoose(frameClientDetailsLocator("Roles", 1), selectRolesCheckBox(role), role, "Roles");
        return this;
    }

    public AddNewUserPage clickAndselectRole(int index) {
        pause("low");
        clickAndChoose(frameClientDetailsLocator("Roles", 1), selectRolesCheckBox(index), "First", "Roles");
        return this;
    }


    public AddNewUserPage fillUserInformation(String firstName, String lastName, String email, String phoneNo, String tier, String tier1, int index) {
        waitForElementToLoad(frameClientDetailsLocator("First Name"));
        typeFirstName(firstName)
                .typeLastName(lastName)
                .typeUserEmail(email)
                .typePhoneNo(phoneNo)
                .clickAndselectTier(tier, tier1)
                .clickAndselectRole(index);
        reportPass("User successfully filled User Information");
        return this;
    }


}


