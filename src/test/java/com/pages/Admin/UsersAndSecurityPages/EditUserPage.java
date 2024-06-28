package com.pages.Admin.UsersAndSecurityPages;

public class EditUserPage extends UserSecurityPage {

    private String editUserText = "//div[text()='Edit user']";
    private String applyBtn = "//span[text()='Apply']";


    public EditUserPage validateEditUserPage() {
        waitForElementToLoad(editUserText);
        if (isVisible(editUserText, "Edit User Text")) {
            reportPass("User reaches to the Correct Edit User page");
        } else {
            reportFailAndThrowException("Edit User Page is not loaded.");
        }
        return this;
    }

    public EditUserPage typeFirstName(String userFirstName) {
        type(frameClientDetailsLocator("First Name"), userFirstName, "UserFirstName");
        return this;
    }

    public EditUserPage typeLastName(String userLastName) {
        pause("low");
        type(frameClientDetailsLocator("Last Name"), userLastName, "UserLastName");
        return this;
    }


    public EditUserPage typePhoneNo(String phoneNo) {
        type(frameClientDetailsLocator("Phone Number", 2), phoneNo, "PhoneNo");
        return this;
    }

    public EditUserPage clickAndselectRole(String role) {
        pause("low");
        clickAndChoose(frameClientDetailsLocator("Roles", 1), selectRolesCheckBox(role), role, "Roles");
        return this;
    }

    public EditUserPage clickAndselectRole(int index) {
        pause("low");
        clickAndChoose(frameClientDetailsLocator("Roles", 1), selectRolesCheckBox(index), "Second", "Roles");
        return this;
    }


    public EditUserPage editUserInformation(String firstName, String lastName, String phoneNo) {
        waitForElementToLoad(frameClientDetailsLocator("First Name"));
        typeFirstName(firstName)
                .typeLastName(lastName)
                .typePhoneNo(phoneNo);
        reportPass("User successfully edited User Information");
        return this;
    }

    public UserSecurityPage clickOnApplyBtn() {
        click(applyBtn, "Apply Button");
        pause("medium");
        return new UserSecurityPage();
    }


}
