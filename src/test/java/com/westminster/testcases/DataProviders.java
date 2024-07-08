package com.westminster.testcases;

import org.testng.annotations.DataProvider;

import com.data.dynamic.FakerDataFactory;

public class DataProviders {

    @DataProvider(name = "getPhlSpecialBanks")
    public Object[][] getPhlSpecialBanks() {
        return new Object[][]{{FakerDataFactory.get12DigitsBankAccountNumber(), "PNBMPHMM", "PHILIPPINES", "Aklan"},
                {FakerDataFactory.getBankAccountNumber(), "BOPIPHMM", "PHILIPPINES", "Aklan"}};
    }

    @DataProvider(name = "searchVesselByName")
    public Object[][] searchVesselByName() {
        return new Object[][]{{"YASA EMIRHAN"}, {"YASA"}, {"YASA Golden"}, {"Star"}, {"dubai express"}};
    }

}
