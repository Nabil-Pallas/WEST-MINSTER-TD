package com.page.base;

import com.pages.Admin.AdminPage;
import com.pages.Analytics.AnalyticsPage;
import com.pages.Report.ReportsPage;
import com.pages.bugeting.AllBudgetsPage;

public class DashboardPage extends MenuPage {

    //Locator For Mouse Hover On The Menu Section
    private String headerProfileMouseHover = "//label[@class='header__profile__dropdown__trigger__label']";

    //Locator For Admin Option
    private String adminLink = "//span[text()='Admin']";

    //Locator For Analytics Option
    private String analyticsLink = "//span[text()='Analytics']";

    //Locator ForBudgeting Option
    private String budgetingLink = "//span[text()='Budgeting']";

    //Locator For Save Button
    private String saveBtn = "//div[@aria-label='Save']";

    //Locator For Client Selection DropDown
    private String clientSelectDrpDown = "(//div[@class='dx-dropdowneditor-icon'])[1]";

    //Locator For Logout Button
    private String logout = "//span[text()='Logout']";

    //Logout Method
    private String reportsLink = "//span[text()='Reports']";


    public MenuPage clickOnLogout() {
        if (mouseOver(headerProfileMouseHover, "Header Profile MouseHover")) {
            click(logout, "Logout");
        } else {
            reportFailAndThrowException("Mouser Hover on Header Profile is failed");
        }
        return new MenuPage();
    }


    //     Validate Dashboard Element in Dashboard Page

    public DashboardPage validateDashBoardElements() {
        waitForLoaderToDisappear();
        waitForElementToLoad(dashboardHover);
        if (getPage().title().contains("Dashboard | Westminster")) {
            reportStep("User successfully reached the correct Dashboard page","info");
        } else {
            reportFailAndThrowException("Dashboard page is not loaded.");
        }
        return this;

    }

    // Click the Admin Option
    public AdminPage clickOnAdmin() {
        mouseHoverOnDashboard();
        click(adminLink, "Admin Link");
        return new AdminPage();
    }

    // Click the Analytics Option
    public AnalyticsPage clickOnAnalytics() {
        mouseHoverOnDashboard();
        click(analyticsLink, "Analytics Link");
        return new AnalyticsPage();
    }

    //Click the Budgeting Option
    public AllBudgetsPage clickOnBudgeting() {
        mouseHoverOnDashboard();
        click(budgetingLink, "Budgeting Link");
        return new AllBudgetsPage();
    }

    //Return to the Home Page
    public DashboardPage clickOnHomePage() {
        click(homeLink, "Home Link");
        waitForLoaderToDisappear();
        return this;

    }

    public ReportsPage clickOnReports() {
        mouseHoverOnDashboard();
        click(reportsLink, "Reports Link");
        return new ReportsPage();
    }


}
