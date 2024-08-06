package com.pages.Admin;

import com.config.ConfigurationManager;
import com.page.base.MenuPage;
import com.page.base.WestMinsterPageHook;
import com.pages.Admin.DataManagerPages.DataManagerPage;
import com.pages.Admin.FileManagerPages.FileManagerPage;
import com.pages.Admin.ListManagerPages.ListManagerPage;
import com.pages.Admin.RulesManagerPages.RulesManagerPage;
import com.pages.Admin.UsersAndSecurityPages.UserSecurityPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminPage extends MenuPage {

    protected String userAndSecurity = "//span[contains(text(),'Users & Security')]";
    protected String fileManager = "//span[contains(text(),'File Manager')]";
    protected String adminText = "//h1[text()='Admin']";
    protected String dataManager = "//span[contains(text(),'Data Manager')]";
    protected String rulesManager = "//span[contains(text(),'Rules Manager')]";
    private String clientSelectDrpDown = "(//div[@class='dx-dropdowneditor-icon'])[1]";
    public String deleteBtn = "//span[text()='Delete']";
    public String confirmDeleteBtn = "//span[text()='Yes']";
    public String closeIcon = "//i[@class='dx-icon dx-icon-close']";


    public String frameClientDetailsLocator(String placeholder) {
        return "//span[text()='" + placeholder + "']/ancestor::label/following-sibling::div//input";
    }

    public String frameClientDetailsLocator(String placeholder, int index) {
        return "(//span[text()='" + placeholder + "']/ancestor::label/following-sibling::div//input)[" + index + "]";
    }

    public String frameClientDetailsLocator(int index) {
        return "(//div[contains(@class,'dx-item dx-list-item')])[" + index + "]";
    }

    public String frameClientSelectDropDownLocator(String placeholder) {
        return "//div[@class='wdx-custom-dropdown__text']/div[text()='" + placeholder + "']";
    }

    public String selectRolesCheckBox(String tier, String role) {
        // return "//span[contains(., '" + placeholder + "')]/ancestor::div[contains(@class, 'dx-item')]/div[contains(@class, 'dx-list-item-before-bag')]//div[contains(@class, 'dx-checkbox-container')]/span[@class='dx-checkbox-icon']";
        // return "//span[text()='"+tier+" | ' and strong='"+role+"']";
        return "//span[contains(text(),'" + tier + " | ') and contains(strong/text(),'" + role + "')]";
    }

    public String selectRolesCheckBox(int index) {
        return "(//div[@role='option'])[" + index + "]";
    }

    public String searchValueWithHeader(int index) {
        return "((//td[contains(@class,'dx-cell-focus-disabled dx-datagrid-drag-action')])[position() > 1])/ancestor::tr[2]//td[@aria-label='Filter cell'][" + index + "]//input  | //td[@aria-label='Filter cell'][" + index + "]//input";
    }

    public String selectPermissionCheckBox(String placeholder) {
        if (placeholder.equals("Select All")) {
            return "//div[contains(@class,'dx-list-select-all-checkbox')]";
        } else {
            return "//span[contains(., '" + placeholder + "')]/ancestor::div[contains(@class, 'dx-item')]/div[contains(@class, 'dx-list-item-before-bag')]//div[contains(@class, 'dx-checkbox-container')]/span[@class='dx-checkbox-icon']";
        }
    }

    public String searchFieldName(String placeholder) {
        waitForLoaderToDisappear();
        return "//td[text()='" + placeholder + "']";
    }

    public String frameUserSelectDropDownLocator(String placeholder) {
        return "//div[@class='dx-item dx-list-item']/div[text()='" + placeholder + "']";
    }

    public String frameUserSelectDropDownLocator(String placeholder, int index) {
        return "(//div[@class='dx-item dx-list-item']/div[text()='" + placeholder + "'])[" + index + "]";
    }

    public String frameUserSearchResult(String placeholder) {
        return "//span[text()='" + placeholder + "']";
    }

    public String frameSpanTextLocator(String placeholder) {
        pause("low");
        return "//span[contains(text(),'" + placeholder + "')]";
    }

    public String frameSpanExactTextLocator(String placeholder) {
        pause("low");
        return "//span[text()='" + placeholder + "']";
    }

    public String frameSpanExactTextLocator(String placeholder, int index) {
        pause("low");
        return "(//span[text()='" + placeholder + "'])[" + index + "]";
    }

    public String frameSelectMappingLocator(String placeholder) {
        return "//span[text()='" + placeholder + "']/ancestor::label/following-sibling::div";
    }

    public String frameRowCountText(String placeholder) {
        return "//span[text()='" + placeholder + "']/following-sibling::span";
    }


    public String frameDivTextLocator(String placeholder) {
        return "//div[contains(text(),'" + placeholder + "')]";
    }

    public String frameDivTextLocator(String placeholder, int index) {
        return "(//div[text()='" + placeholder + "'])[" + index + "]";
    }

    public AdminPage validateAdminPage() {
        waitForElementToLoad(adminText);
        if (isVisible(adminText, "Admin Text")) {
            reportPass("User successfully navigated to the Admin tab.");
        } else {
            reportFailAndThrowException("Admin Page is not loaded.");
        }
        return this;
    }

    public AdminPage typeCompanyName(String companyName) {
        typeWithType(frameClientDetailsLocator("Company Name"), companyName, "companyName");
        return this;
    }


    public AdminPage typeWRAClientID(String wraClientID) {
        type(frameClientDetailsLocator("WRA Client ID"), wraClientID, "WRAClientID");
        return this;
    }

    public AdminPage typeFTPFolderAcronym(String ftpFolderAcronym) {
        typeWithType(frameClientDetailsLocator("FTP Folder Acronym"), ftpFolderAcronym, "FTPFolderAcronym");
        return this;
    }


    public AdminPage typeAdminFirstName(String adminFirstName) {
        type(frameClientDetailsLocator("Admin Contact First Name"), adminFirstName, "AdminFirstName");
        return this;
    }

    public AdminPage typeAdminLastName(String adminLastName) {
        type(frameClientDetailsLocator("Admin Contact Last Name"), adminLastName, "AdminLastName");
        return this;
    }

    public AdminPage typeAdminContactEmail(String adminContactEmail) {
        typeWithType(frameClientDetailsLocator("Admin Contact Email"), adminContactEmail, "AdminContactEmail");
        return this;
    }

    public AdminPage typeAdminContactPhoneNo(String adminContactPhoneNo) {
        type(frameClientDetailsLocator("Admin Contact Phone Number", 2), adminContactPhoneNo, "AdminContactPhoneNo");
        return this;
    }

    public AdminPage clickAndselectDateFormating(String dateFormat) {
        clickAndChoose(frameClientDetailsLocator("Date Formatting", 2), frameClientSelectDropDownLocator(dateFormat), dateFormat, "Date Format");
        return this;
    }

    public AdminPage clickAndselectLocalTimeZone(String localTimeZone) {
        clickAndChoose(frameClientDetailsLocator("Local Timezone", 2), localTimeZone, localTimeZone, "Local TimeZone");
        return this;
    }

    public AdminPage clickAndselectTimeFormating(String timeFormat) {
        clickAndChoose(frameClientDetailsLocator("Time Formatting", 2), timeFormat, timeFormat, "Time Format");
        return this;
    }

    public AdminPage clickAndselectNumberFormating(String numberFormat) {
        clickAndChoose(frameClientDetailsLocator("Number Formatting", 2), numberFormat, numberFormat, "Number Format");
        return this;
    }

    public AdminPage clickAndselectMasterCurrency(String masterCurrency) {
        clickAndChoose(frameClientDetailsLocator("Master Currency", 2), masterCurrency, masterCurrency, "Master Currency");
        return this;
    }


    public AdminPage setClient() {

        switch (xmlUserLevel) {
            case "Tier2Admin":
                break;
            case "Tier2User":
                break;
            case "Tier3EndUser":
                break;
            case "Tier1User":
                try {
                    clickAndChoose(clientSelectDrpDown, "//div[contains(text(),'" + ConfigurationManager.configuration().appClientNameTier1User() + "')]", "Client Name", ConfigurationManager.configuration().appClientNameTier1User());
                    pause("low");
                    reportPass("User successfully selected the Client.");
                } catch (Exception e) {
                    reportWarning("Client is not selected as it is set as default");
                }
                break;
            default:
                try {
                    clickAndChoose(clientSelectDrpDown, "//div[contains(text(),'" + ConfigurationManager.configuration().appClientNameTier1Admin() + "')]", "Client Name", ConfigurationManager.configuration().appClientNameTier1Admin());
                    pause("low");
                    reportPass("User successfully selected the Client.");
                } catch (Exception e) {
                    reportWarning("Client is not selected as it is set as default");
                }
                break;
        }
        return new AdminPage();
    }

    public DataManagerPage clickOnDataManager() {
//        pause("medium");
        click(dataManager, "Data Manager");
//        waitForLoaderToDisappear();
        pause("medium");
        return new DataManagerPage();
    }

    public UserSecurityPage clickOnUserSecurity() {
        click(userAndSecurity, "User & Security");
        waitForLoaderToDisappear();
        return new UserSecurityPage();
    }

    public FileManagerPage clickOnFileManager() {
        click(fileManager, "FileManager");
        waitForLoaderToDisappear();
        return new FileManagerPage();
    }

    public RulesManagerPage clickOnRulesManager() {
        click(rulesManager, "Rules Manager");
        waitForLoaderToDisappear();
        return new RulesManagerPage();
    }

    public FileManagerPage clickOnViewDataMaps() {
        click(frameSpanTextLocator("View Data Maps"), "View Data Maps");
        return new FileManagerPage();
    }

    public ListManagerPage clickOnListManager() {
        click(frameSpanTextLocator("List Manager"), "List Manager");
        waitForLoaderToDisappear();
        return new ListManagerPage();
    }

    public DataManagerPage getTotalRowCount(String state) {
        pause("medium");
        String rowCount = getInnerText("//span[contains(text(),'Total Row Count:')]");

        // Regex to extract the main number part (ignoring the filtered part if present)
        String regex = "Total Row Count: ([\\d,]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rowCount);

        if (matcher.find()) {
            String numberString = matcher.group(1); // Get the matched number part
            String cleanNumberString = numberString.replaceAll(",", ""); // Remove commas

            if (state.equalsIgnoreCase("Initial")) {
                InitRowCounts = Long.parseLong(cleanNumberString);
                reportPass("Initial Row Counts : " + InitRowCounts + "Noted.");
                System.out.println("InitRowCounts: " + InitRowCounts);
            } else {
                FinRowCounts = Long.parseLong(cleanNumberString);
                reportPass("Final Row Counts : " + FinRowCounts + "Noted.");
                System.out.println("FinRowCounts: " + FinRowCounts);
            }
        } else {
            System.out.println("No matching row count found.");
        }

        return new DataManagerPage();
    }

//    public DataManagerPage getTotalRowCount(String state) {
//        pause("low");
//        String rowCount = getInnerText("//span[contains(text(),'Total Row Count:')]");
//        String replacedRowCount = rowCount.replaceAll("Total Row Count: ", "");
//        String reReplaceRowCount = replacedRowCount.replaceAll(",","");
//        if (state.equalsIgnoreCase("Initial")) {
//            InitRowCounts = Long.parseLong(reReplaceRowCount);
//            //InitRowCounts.set(Long.parseLong(replacedRowCount));
//            System.out.println("InitRowCounts: " + InitRowCounts);
//        } else {
//            // FinRowCounts.set(Long.parseLong(replacedRowCount));
//            FinRowCounts = Long.parseLong(reReplaceRowCount);
//            System.out.println("FinRowCounts: " + FinRowCounts);
//        }
//
//        return new DataManagerPage();
//    }

}
