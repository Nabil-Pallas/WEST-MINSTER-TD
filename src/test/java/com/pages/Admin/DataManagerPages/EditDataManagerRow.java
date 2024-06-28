package com.pages.Admin.DataManagerPages;

import com.page.base.DashboardPage;
import org.testng.Assert;

import java.util.List;

public class EditDataManagerRow extends DataManagerPage {

    //Edit The Data Manager Row
    public EditDataManagerRow editInformation(List<String> verificationValues, List<String> verificationHeaders) {
//        waitForLoaderToDisappear();
        for (int i = 0; i < verificationValues.size(); i++) {
            try {
                type(frameClientDetailsLocator(verificationHeaders.get(i)), verificationValues.get(i), verificationValues.get(i));
            }
           catch (Exception e){
               type(frameClientDetailsLocator(verificationHeaders.get(i),2), verificationValues.get(i), verificationValues.get(i));
           }
        }
        reportPass("User successfully edited the row information.");
        return new EditDataManagerRow();

    }


}
