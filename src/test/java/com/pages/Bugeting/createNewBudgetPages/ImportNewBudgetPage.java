package com.pages.Bugeting.createNewBudgetPages;

import com.pages.Bugeting.AllBudgetsPage;

public class ImportNewBudgetPage extends AllBudgetsPage {
    String importBudgetText = "//div[text()='Import Budget']";


    public ImportNewBudgetPage validateImportBudgetPage() {
        waitForElementToLoad(importBudgetText);
        if (isVisible(importBudgetText, "Import Budget Text")) {
            reportPass("User reaches to the right Import Budget page");
        } else {
            reportFailAndThrowException("Import Budget Page is not loaded.");
        }
        return this;
    }


}
