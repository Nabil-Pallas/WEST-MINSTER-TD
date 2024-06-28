package com.pages.Admin.UsersAndSecurityPages;

public class EditClientPage extends UserSecurityPage {

    private String editClientText = "//div[text()='Edit client']";
    private String applyBtn = "//span[text()='Apply']";


    public EditClientPage validateEditClientPage() {
        waitForElementToLoad(editClientText);
        if (isVisible(editClientText, "Edit Client Text")) {
            reportPass("User successfully reached the correct Edit Client page");
        } else {
            reportFailAndThrowException("Edit Client Page is not loaded.");
        }
        return this;
    }

    public EditClientPage clearAndTypeCompanyName(String companyName) {
        typeAndSpace(frameClientDetailsLocator("Company Name"), companyName, "companyName");
        return this;
    }

    public String frameTimeFormatLocator(String placeholder) {
        return "//div[text()='" + placeholder + "]";
    }

    public EditClientPage clearAndTypeWRAClientID(String wraClientID) {
        type(frameClientDetailsLocator("WRA Client ID"), wraClientID, "WRAClientID");
        return this;
    }

    public EditClientPage clearAndTypeFTPFolderAcronym(String ftpFolderAcronym) {
        typeAndSpace(frameClientDetailsLocator("FTP Folder Acronym"), ftpFolderAcronym, "FTPFolderAcronym");
        return this;
    }

    public EditClientPage clearAndTypeAdminFirstName(String adminFirstName) {
        type(frameClientDetailsLocator("Admin Contact First Name"), adminFirstName, "AdminFirstName");
        return this;
    }

    public EditClientPage clearAndTypeAdminLastName(String adminLastName) {
        type(frameClientDetailsLocator("Admin Contact Last Name"), adminLastName, "AdminLastName");
        return this;
    }

    public EditClientPage clearAndTypeAdminContactEmail(String adminContactEmail) {
        typeWithType(frameClientDetailsLocator("Admin Contact Email"), adminContactEmail, "AdminContactEmail");
        return this;
    }

    public EditClientPage clearAndTypeAdminContactPhoneNo(String adminContactPhoneNo) {
        type(frameClientDetailsLocator("Admin Contact Phone Number", 2), adminContactPhoneNo, "AdminContactPhoneNo");
        return this;
    }

    public EditClientPage clickAndselectDateFormating(String dateFormat) {
        clickAndChoose(frameClientDetailsLocator("Date Formatting", 2), frameClientSelectDropDownLocator(dateFormat), dateFormat, "Date Format");
        return this;
    }

    public EditClientPage clickAndselectLocalTimeZone(String localTimeZone) {
        clickAndChoose(frameClientDetailsLocator("Local Timezone", 2), frameClientSelectDropDownLocator(localTimeZone), localTimeZone, "Local TimeZone");
        return this;
    }

    public EditClientPage clickAndselectTimeFormating(String timeFormat) {
        pause("low");
        clickAndChoose(frameSpanTextLocator("Time Formatting"), frameDivTextLocator(timeFormat), timeFormat, "Time Format");
        pause("low");
        return this;
    }

    public EditClientPage clickAndselectNumberFormating(String numberFormat) {
        clickAndChoose(frameClientDetailsLocator("Number Formatting", 2), frameDivTextLocator(numberFormat), numberFormat, "Number Format");
        return this;
    }

    public EditClientPage clickAndselectMasterCurrency(String masterCurrency) {
        clickAndChoose(frameClientDetailsLocator("Master Currency", 2), masterCurrency, masterCurrency, "Master Currency");
        return this;
    }

    public EditClientPage editClientInformation(String companyName, String ftpFolderAcronym, String dateFormat, String localTimeZone,
                                                String timeFormat, String numberFormat, String masterCurrency) {
        clearAndTypeCompanyName(companyName)
                .clearAndTypeFTPFolderAcronym(ftpFolderAcronym)
                .clickAndselectDateFormating(dateFormat)
                .clickAndselectLocalTimeZone(localTimeZone)
                .clickAndselectTimeFormating(timeFormat)
                .clickAndselectNumberFormating(numberFormat);
        /*.clickAndselectMasterCurrency(masterCurrency);*/
        reportPass("User successfully edited Client Information");
        return this;
    }

    public UserSecurityPage clickOnApplyBtn() {
        click(applyBtn, "Apply Button");
        return new UserSecurityPage();
    }

}
