package com.pages.Analytics;

public class EditAnalysisPage extends NewAnalysisPage{

    public EditAnalysisPage validateEditAnalysisPage(){
        pause("low");
        String innerText = getInnerText("//h1[contains(@class,'page-title')]");
        if (innerText.contains("EDIT ANALYSIS")){
            reportPass("Successfully validated Edit Analysis page");
        }else {
            reportFail("Failed to validate Edit Analysis page");
        }

        return this;
    }

}
