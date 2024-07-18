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

    public EditUserPage typeFirstName(String userFirstName, String userFirstName1) {
        pause("low");
        String getInnerText = getInputText(frameClientDetailsLocator("First Name"));
        if (userFirstName.equals(getInnerText)) {
            type(frameClientDetailsLocator("First Name"), userFirstName1, "UserFirstName");
        } else {
            type(frameClientDetailsLocator("First Name"), userFirstName, "UserFirstName");
        }
        return this;
    }

    public EditUserPage typeLastName(String userLastName, String userLastName1) {
        pause("low");
        String getInnerText = getInputText(frameClientDetailsLocator("Last Name"));
        if (userLastName.equals(getInnerText)) {
            type(frameClientDetailsLocator("Last Name"), userLastName1, "UserLastName");
        } else {
            type(frameClientDetailsLocator("Last Name"), userLastName, "UserLastName");
        }
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
        pause("low");
        click(frameClientDetailsLocator("Roles", 1), "Roles", "Select Field");
        return this;
    }


    public EditUserPage editUserInformation(String firstName, String firstName1, String lastName, String lastName1, String phoneNo) {
        waitForElementToLoad(frameClientDetailsLocator("First Name"));
        typeFirstName(firstName, firstName1)
                .typeLastName(lastName, lastName1)
                .typePhoneNo(phoneNo);
        reportPass("User successfully edited User Information");
        return this;
    }

    public UserSecurityPage clickOnApplyBtn() {
        pause("medium");
        click(applyBtn, "Apply Button");
        pause("low");
        if (isVisible(frameSpanTextLocator("YES"))) {
            pause("low");
            click(frameSpanTextLocator("YES"), "YES");
        }
        return new UserSecurityPage();
    }


}
