package com.pages.Admin.DataManagerPages;


import org.testng.Assert;

import java.util.Collections;
import java.util.List;

public class AddNewRowPage extends DataManagerPage {

    //Locator For Add New Row Button
    String addNewRowText = "//div[text()='Add New Row']";

    //Locator For Add Button
    String addBtn = "//span[text()='Add']";

    //Validate the Add New Row Page
    public AddNewRowPage validateAddNewRowPage() {
        waitForElementToLoad(addNewRowText);
        if (isVisible(addNewRowText, "AddNewRowPage")) {
            reportPass("User reaches to the right Add New Row Page");
        } else {
            reportFailAndThrowException("Add New Row Page is not loaded.");
        }
        return this;
    }


    // Type The Broker Name
    public AddNewRowPage typeBrokerName(String brokerName) {
        type(frameClientDetailsLocator("Broker  Name:"), brokerName, "brokerName");
        return this;
    }

    // Type The Trade Date
    public AddNewRowPage typeTradeDate(String tradeDate) {
        type(frameClientDetailsLocator("Trade  Date:", 2), tradeDate, "tradeDate");
        return this;
    }

    // Click The Trade Date
    public AddNewRowPage clickTradeDate() {
        click(frameClientDetailsLocator("Trade  Date:", 2), "Trade  Date", "Field");
        return this;
    }

    // Click The Broker Name
    public AddNewRowPage clickBrokerName() {
        click(frameClientDetailsLocator("Broker  Name:"), "Broker Name", "Broker Name");
        return this;
    }

    public AddNewRowPage selectBrokerName(String brokerName) {
        clickAndChoose(frameClientDetailsLocator("Broker  Name:", 2), frameDivTextLocator(brokerName), brokerName, "Broker Name");
        return this;
    }


    // Type The Account Name
    public AddNewRowPage typeAccountName(String accountName) {
        try {
            type(frameClientDetailsLocator("Account  Name:", 2), accountName, "accountName");
        } catch (Exception e) {
            type(frameClientDetailsLocator("Account  Name:"), accountName, "accountName");
        }
        return this;
    }

    // Type The Asset Class
    public AddNewRowPage typeAssetClass(String AssetClass) {
        type(frameClientDetailsLocator("Asset  Class:"), AssetClass, "AssetClass");
        return this;
    }

    // Type The Base Currency
    public AddNewRowPage typeBaseCurrency(String BaseCurrency) {
        type(frameClientDetailsLocator("Base  Currency:"), BaseCurrency, "BaseCurrency");
        return this;
    }

    // Type The Base Executed Value
    public AddNewRowPage typeBaseExpectedValue(String BaseExpectedValue) {
        type(frameClientDetailsLocator("Base  Executed  Value:", 2), BaseExpectedValue, "BaseExpectedValue");
        return this;
    }

    // Type The Base Gross Commission
    public AddNewRowPage typeBaseGrossCommission(String BaseGrossCommission) {
        typeWithType(frameClientDetailsLocator("Base  Gross  Commission:", 2), BaseGrossCommission, "BaseGrossCommission");
        return this;
    }

    // Type The Broker Code
    public AddNewRowPage typeBrokerCode(String BrokerCode) {
        type(frameClientDetailsLocator("Broker  Code:"), BrokerCode, "BrokerCode");
        return this;
    }

    // Type The CUSIP
    public AddNewRowPage typeCusip(String Cusip) {
        type(frameClientDetailsLocator("CUSIP:"), Cusip, "Cusip");
        return this;
    }

    // Type The Exchange
    public AddNewRowPage typeExchange(String Exchange) {
        type(frameClientDetailsLocator("Exchange:"), Exchange, "Exchnage");
        return this;
    }

    // Type The Executed Quantity
    public AddNewRowPage typeExecutedQuantity(String ExecutedQuantity) {
        type(frameClientDetailsLocator("Executed  Quantity:", 2), ExecutedQuantity, "ExecutedQuantity");
        return this;
    }

    // Type The Fund
    public AddNewRowPage typeFund(String Fund) {
        type(frameClientDetailsLocator("Fund:"), Fund, "Fund");
        return this;
    }

    // Type The FX Rate
    public AddNewRowPage typeFxRate(String FxRate) {
        type(frameClientDetailsLocator("FX Rate:", 2), FxRate, "FxRate");
        return this;
    }

    // Type The Instrument Type
    public AddNewRowPage typeInstrumentType(String InstrumentType) {
        type(frameClientDetailsLocator("Instrument  Type:"), InstrumentType, "InstrumentType");
        return this;
    }

    // Type The ISIN
    public AddNewRowPage typeIsin(String Isin) {
        type(frameClientDetailsLocator("ISIN:"), Isin, "Isin");
        return this;
    }

    // Type The Local Currency
    public AddNewRowPage typeLocalCurrency(String LocalCurrency) {
        type(frameClientDetailsLocator("Local  Currency:"), LocalCurrency, "LocalCurrency");
        return this;
    }

    // Type The Local Executed Value
    public AddNewRowPage typeLocalExecutedValue(String LocalExecutedValue) {
        type(frameClientDetailsLocator("Local  Executed  Value:", 2), LocalExecutedValue, "LocalExecutedValue");
        return this;
    }

    // Type The Local Gross Commission
    public AddNewRowPage typeLocalGrossCommission(String LocalGrossCommission) {
        type(frameClientDetailsLocator("Local  Gross  Commission:", 2), LocalGrossCommission, "LocalGrossCommission");
        return this;
    }

    // Type The Local Price
    public AddNewRowPage typeLocalPrice(String LocalPrice) {
        type(frameClientDetailsLocator("Local  Price:", 2), LocalPrice, "LocalPrice");
        return this;
    }

    // Type The Master Broker
    public AddNewRowPage typeMasterBroker(String MasterBroker) {
        type(frameClientDetailsLocator("Master  Broker:"), MasterBroker, "MasterBroker");
        return this;
    }

    // Type The Portfolio
    public AddNewRowPage typePortfolio(String Portfolio) {
        type(frameClientDetailsLocator("Portfolio:"), Portfolio, "Portfolio");
        return this;
    }

    // Type The Security Description
    public AddNewRowPage typeSecurityDescription(String SecurityDescription) {
        type(frameClientDetailsLocator("Security  Description:"), SecurityDescription, "SecurityDescription");
        return this;
    }

    // Type The Security Type
    public AddNewRowPage typeSecurityType(String SecurityType) {
        type(frameClientDetailsLocator("Security  Type:"), SecurityType, "SecurityType");
        return this;
    }

    // Type The SEDOL
    public AddNewRowPage typeSedol(String Sedol) {
        type(frameClientDetailsLocator("SEDOL:"), Sedol, "Sedol");
        return this;
    }

    // Type The Settlement Date
    public AddNewRowPage typeSettlementDate(String SettlementDate) {
        type(frameClientDetailsLocator("Settlement  Date:", 2), SettlementDate, "SettlementDate");
        return this;
    }

    // Type The Side
    public AddNewRowPage typeSide(String Side) {
        type(frameClientDetailsLocator("Side:"), Side, "Side");
        return this;
    }

    // Type The Status
    public AddNewRowPage typeStatus(String Status) {
        type(frameClientDetailsLocator("Status:"), Status, "Status");
        return this;
    }

    // Type The Ticker
    public AddNewRowPage typeTicker(String Ticker) {
        type(frameClientDetailsLocator("Ticker:"), Ticker, "Ticker");
        return this;
    }

    // Type The Trade ID
    public AddNewRowPage typeTradeId(String TradeId) {
        type(frameClientDetailsLocator("Trade  ID:"), TradeId, "TradeId");
        return this;
    }

    //Click  the Add Button
    public DataManagerPage clickOnAddBtn() {
        click(addBtn, "Add Button");
        pause("low");
//        waitForLoaderToDisappear();
//        waitForAppearance(addBtn);
        return new DataManagerPage();
    }

    //Validate the trade date error message
    public AddNewRowPage validateTradeDateErrorMsg(String errorMsg) {
        clickTradeDate();
        String errorMsgText = getInnerText("//*[contains(text(),'Custom Validation')]");
        Assert.assertEquals(errorMsgText, errorMsg);
        reportPass("The Error Msg is " + errorMsgText);
        return this;
    }

    //Validate the trade date error message
    public AddNewRowPage validateBrokerNameErrorMsg(String errorMsg) {
        clickBrokerName();
        String errorMsgText = getInnerText(frameDivTextLocator("required"));
        Assert.assertEquals(errorMsgText, errorMsg);
        reportPass("The Error Msg is " + errorMsgText);
        return this;
    }

    //Check Add Button is disabled
    public AddNewRowPage validateAddBtnIsDisable() {
        assertIsDisabled(addBtn, "Add Button");
        pause("low");
        reportPass("User Successfully validated that the add button is Disabled as Expected");
        return this;
    }


    //Enter the Values in Add New Row Page
    public AddNewRowPage addNewRowInformation(String brokerName, String tradeDate, String accountName, String assetClass, String baseCurrency,
                                              String baseExpectedValue, String baseGrossCommission, String brokerCode, String cusip, String exchange,
                                              String executedQuantity, String fund, String fxRate, String instrumentType, String isin,
                                              String localCurrency, String localExecutedValue, String localGrossCommission, String localPrice,
                                              String masterBroker, String portfolio, String securityDescription, String securityType, String sedol,
                                              String settlementDate, String side, String status, String ticker, String tradeId) {
        typeBrokerName(brokerName)
                .typeTradeDate(tradeDate)
//                .typeAccountName(accountName)
                .typeAssetClass(assetClass)
                .typeBaseCurrency(baseCurrency)
                .typeBaseExpectedValue(baseExpectedValue)
                .typeBaseGrossCommission(baseGrossCommission)
                .typeBrokerCode(brokerCode)
                .typeCusip(cusip)
                .typeExchange(exchange)
                .typeExecutedQuantity(executedQuantity)
                .typeFund(fund)
                .typeFxRate(fxRate)
                .typeInstrumentType(instrumentType)
                .typeIsin(isin)
                .typeLocalCurrency(localCurrency)
                .typeLocalExecutedValue(localExecutedValue)
                .typeLocalGrossCommission(localGrossCommission)
                .typeLocalPrice(localPrice)
                .typeMasterBroker(masterBroker)
                .typePortfolio(portfolio)
                .typeSecurityDescription(securityDescription)
                .typeSecurityType(securityType)
                .typeSedol(sedol)
                .typeSettlementDate(settlementDate)
                .typeSide(side)
                .typeStatus(status)
                .typeTicker(ticker)
                .typeTradeId(tradeId);
        reportPass("User successfully entered the values on the Add New Row page.");
        return this;
    }

    //Enter the Values in Add New Row Page 10 times
    public AddNewRowPage addNewRowInformation10Times(String brokerName, String tradeDate, String accountName, String assetClass, String baseCurrency,
                                                     String baseExpectedValue, String baseGrossCommission, String brokerCode, String cusip, String exchange,
                                                     String executedQuantity, String fund, String fxRate, String instrumentType, String isin,
                                                     String localCurrency, String localExecutedValue, String localGrossCommission, String localPrice,
                                                     String masterBroker, String portfolio, String securityDescription, String securityType, String sedol,
                                                     String settlementDate, String side, String status, String ticker, String tradeId) {

        for (int i = 0; i <= 11; i++) {
            if (i < 11) {

                typeBrokerName(brokerName)
                        .typeTradeDate(tradeDate)
//                .typeAccountName(accountName)
                        .typeAssetClass(assetClass)
                        .typeBaseCurrency(baseCurrency)
                        .typeBaseExpectedValue(baseExpectedValue)
                        .typeBaseGrossCommission(baseGrossCommission)
                        .typeBrokerCode(brokerCode)
                        .typeCusip(cusip)
                        .typeExchange(exchange)
                        .typeExecutedQuantity(executedQuantity)
                        .typeFund(fund)
                        .typeFxRate(fxRate)
                        .typeInstrumentType(instrumentType)
                        .typeIsin(isin)
                        .typeLocalCurrency(localCurrency)
                        .typeLocalExecutedValue(localExecutedValue)
                        .typeLocalGrossCommission(localGrossCommission)
                        .typeLocalPrice(localPrice)
                        .typeMasterBroker(masterBroker)
                        .typePortfolio(portfolio)
                        .typeSecurityDescription(securityDescription)
                        .typeSecurityType(securityType)
                        .typeSedol(sedol)
                        .typeSettlementDate(settlementDate)
                        .typeSide(side)
                        .typeStatus(status)
                        .typeTicker(ticker)
                        .typeTradeId(tradeId);
                clickOnAddBtn();
            } else {
                clickOnApplyBtn();
                reportPass("User successfully Added 10 rows in Add New Row");
                pause("low");
            }
        }
        return this;
    }

    public AddNewRowPage addNewRow(String brokerName, String tradeDate, String SettlementDate) {
        selectBrokerName(brokerName)
                .typeTradeDate(tradeDate)
                .typeSettlementDate(SettlementDate);
        return this;
    }

    public AddNewRowPage addNewRow(String brokerName, String tradeDate, String SettlementDate, String baseGrossCommission) {
        pause("medium");
        typeBrokerName(brokerName);
        pause("low");
                typeTradeDate(tradeDate);
                pause("low");
                typeBaseGrossCommission(baseGrossCommission);
                pause("low");
                typeSettlementDate(SettlementDate);

        return this;
    }
}


