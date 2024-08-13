package com.pages.Admin.DataManagerPages;

import com.page.base.DashboardPage;
import org.testng.Assert;

import java.util.Collections;
import java.util.List;

public class EditDataManagerRow extends DataManagerPage {

    private String brokerNameAddBtn = "((//span[text()='Broker  Name:']/ancestor::label/following-sibling::div//input)[2]/preceding::i[@class='dx-icon dx-icon-add'])[2]";


    //Edit The Data Manager Row
    public EditDataManagerRow editInformation(List<String> verificationValues, List<String> verificationHeaders) {
//        waitForLoaderToDisappear();
        for (int i = 0; i < verificationValues.size(); i++) {
            try {
                type(frameClientDetailsLocator(verificationHeaders.get(i)), verificationValues.get(i), verificationValues.get(i));
            } catch (Exception e) {
                type(frameClientDetailsLocator(verificationHeaders.get(i), 2), verificationValues.get(i), verificationValues.get(i));
            }
        }
        reportPass("User successfully edited the row information.");
        return new EditDataManagerRow();

    }

    public EditDataManagerRow typeBrokerName(String brokerName) {
        isVisible(frameClientDetailsLocator("Broker  Name:"), "Broker Name");
        clear(frameClientDetailsLocator("Broker  Name:"));
        typeWithType(frameClientDetailsLocator("Broker  Name:"), brokerName, "Broker Name");
        return this;
    }

    public EditDataManagerRow clickOnAddSymbolButton() {
        isVisible(frameAddButtonSymbol("Broker  Name:", 2), "Plus Symbol Button");
        click(frameAddButtonSymbol("Broker  Name:", 2), "Add Symbol Button");
        click(frameSpanTextLocator("Yes"), "Yes");
        pause("low");
        return this;
    }

    public EditDataManagerRow getBrokerNameText() {
        click(frameClientDetailsLocator("Broker  Name:", 2), "Broker Name", "Select Drop Down");
        pause("low");
        String getTextContent = getInnerText("//div[@aria-selected='true']");
        List<String> list = Collections.singletonList(getTextContent);
        getTexts.set(list);
        System.out.println("NAME :" + list);
        click("//div[@aria-selected='true']", getTextContent, "Select Drop Down");
        return this;
    }

    // Type The Trade Date
    public EditDataManagerRow typeTradeDate(String tradeDate) {
        isVisible(frameClientDetailsLocator("Trade  Date:", 2), "tradeDate");
        clear(frameClientDetailsLocator("Trade  Date:", 2));
        typeWithType(frameClientDetailsLocator("Trade  Date:", 2), tradeDate, "tradeDate");
        return this;
    }


    // Type The Account Name
    public EditDataManagerRow typeAccountName(String accountName) {
        try {
            type(frameClientDetailsLocator("Account  Name:", 2), accountName, "accountName");
        } catch (Exception e) {
            type(frameClientDetailsLocator("Account  Name:"), accountName, "accountName");
        }
        return this;
    }

    // Type The Asset Class
    public EditDataManagerRow typeAssetClass(String AssetClass) {
        isVisible(frameClientDetailsLocator("Asset  Class:"), "AssetClass");
        clear(frameClientDetailsLocator("Asset  Class:"));
        typeWithType(frameClientDetailsLocator("Asset  Class:"), AssetClass, "AssetClass");
        return this;
    }

    // Type The Base Currency
    public EditDataManagerRow typeBaseCurrency(String BaseCurrency) {
        isVisible(frameClientDetailsLocator("Base  Currency:"), "BaseCurrency");
        clear(frameClientDetailsLocator("Base  Currency:"));
        typeWithType(frameClientDetailsLocator("Base  Currency:"), BaseCurrency, "BaseCurrency");
        return this;
    }

    // Type The Base Executed Value
    public EditDataManagerRow typeBaseExpectedValue(String BaseExpectedValue) {
        isVisible(frameClientDetailsLocator("Base  Executed  Value:", 2), "BaseExpectedValue");
        clear(frameClientDetailsLocator("Base  Executed  Value:", 2));
        typeWithType(frameClientDetailsLocator("Base  Executed  Value:", 2), BaseExpectedValue, "BaseExpectedValue");
        return this;
    }

    // Type The Base Gross Commission
    public EditDataManagerRow typeBaseGrossCommission(String BaseGrossCommission) {
        isVisible(frameClientDetailsLocator("Base  Gross  Commission:", 2), "BaseGrossCommission");
        clear(frameClientDetailsLocator("Base  Gross  Commission:", 2));
        typeWithType(frameClientDetailsLocator("Base  Gross  Commission:", 2), BaseGrossCommission, "BaseGrossCommission");
        return this;
    }

    // Type The Broker Code
    public EditDataManagerRow typeBrokerCode(String BrokerCode) {
        isVisible(frameClientDetailsLocator("Broker  Code:"), "BrokerCode");
        clear(frameClientDetailsLocator("Broker  Code:"));
        typeWithType(frameClientDetailsLocator("Broker  Code:"), BrokerCode, "BrokerCode");
        return this;
    }

    // Type The CUSIP
    public EditDataManagerRow typeCusip(String Cusip) {
        isVisible(frameClientDetailsLocator("CUSIP:"), "Cusip");
        clear(frameClientDetailsLocator("CUSIP:"));
        typeWithType(frameClientDetailsLocator("CUSIP:"), Cusip, "Cusip");
        return this;
    }

    // Type The Exchange
    public EditDataManagerRow typeExchange(String Exchange) {
        isVisible(frameClientDetailsLocator("Exchange:"), "Exchnage");
        clear(frameClientDetailsLocator("Exchange:"));
        typeWithType(frameClientDetailsLocator("Exchange:"), Exchange, "Exchnage");
        return this;
    }

    // Type The Executed Quantity
    public EditDataManagerRow typeExecutedQuantity(String ExecutedQuantity) {
        isVisible(frameClientDetailsLocator("Executed  Quantity:", 2), "ExecutedQuantity");
        clear(frameClientDetailsLocator("Executed  Quantity:", 2));
        typeWithType(frameClientDetailsLocator("Executed  Quantity:", 2), ExecutedQuantity, "ExecutedQuantity");
        return this;
    }

    // Type The Fund
    public EditDataManagerRow typeFund(String Fund) {
        isVisible(frameClientDetailsLocator("Fund:"), "Fund");
        clear(frameClientDetailsLocator("Fund:"));
        typeWithType(frameClientDetailsLocator("Fund:"), Fund, "Fund");
        return this;
    }

    // Type The FX Rate
    public EditDataManagerRow typeFxRate(String FxRate) {
        isVisible(frameClientDetailsLocator("FX Rate:", 2), "FxRate");
        clear(frameClientDetailsLocator("FX Rate:", 2));
        typeWithType(frameClientDetailsLocator("FX Rate:", 2), FxRate, "FxRate");
        return this;
    }

    // Type The Instrument Type
    public EditDataManagerRow typeInstrumentType(String InstrumentType) {
        isVisible(frameClientDetailsLocator("Instrument  Type:"), "InstrumentType");
        clear(frameClientDetailsLocator("Instrument  Type:"));
        typeWithType(frameClientDetailsLocator("Instrument  Type:"), InstrumentType, "InstrumentType");
        return this;
    }

    // Type The ISIN
    public EditDataManagerRow typeIsin(String Isin) {
        isVisible(frameClientDetailsLocator("ISIN:"), "Isin");
        clear(frameClientDetailsLocator("ISIN:"));
        typeWithType(frameClientDetailsLocator("ISIN:"), Isin, "Isin");
        return this;
    }

    // Type The Local Currency
    public EditDataManagerRow typeLocalCurrency(String LocalCurrency) {
        isVisible(frameClientDetailsLocator("Local  Currency:"), "LocalCurrency");
        clear(frameClientDetailsLocator("Local  Currency:"));
        typeWithType(frameClientDetailsLocator("Local  Currency:"), LocalCurrency, "LocalCurrency");
        return this;
    }

    // Type The Local Executed Value
    public EditDataManagerRow typeLocalExecutedValue(String LocalExecutedValue) {
        isVisible(frameClientDetailsLocator("Local  Executed  Value:", 2), "LocalExecutedValue");
        clear(frameClientDetailsLocator("Local  Executed  Value:", 2));
        typeWithType(frameClientDetailsLocator("Local  Executed  Value:", 2), LocalExecutedValue, "LocalExecutedValue");
        return this;
    }

    // Type The Local Gross Commission
    public EditDataManagerRow typeLocalGrossCommission(String LocalGrossCommission) {
        isVisible(frameClientDetailsLocator("Local  Gross  Commission:", 2), "LocalGrossCommission");
        clear(frameClientDetailsLocator("Local  Gross  Commission:", 2));
        typeWithType(frameClientDetailsLocator("Local  Gross  Commission:", 2), LocalGrossCommission, "LocalGrossCommission");
        return this;
    }

    // Type The Local Price
    public EditDataManagerRow typeLocalPrice(String LocalPrice) {
        isVisible(frameClientDetailsLocator("Local  Price:", 2), "LocalPrice");
        clear(frameClientDetailsLocator("Local  Price:", 2));
        typeWithType(frameClientDetailsLocator("Local  Price:", 2), LocalPrice, "LocalPrice");
        return this;
    }

    // Type The Master Broker
    public EditDataManagerRow typeMasterBroker(String MasterBroker) {
        isVisible(frameClientDetailsLocator("Master  Broker:"), "MasterBroker");
        clear(frameClientDetailsLocator("Master  Broker:"));
        type(frameClientDetailsLocator("Master  Broker:"), MasterBroker, "MasterBroker");
        return this;
    }

    // Type The Portfolio
    public EditDataManagerRow typePortfolio(String Portfolio) {
        isVisible(frameClientDetailsLocator("Portfolio:"), "Portfolio");
        clear(frameClientDetailsLocator("Portfolio:"));
        typeWithType(frameClientDetailsLocator("Portfolio:"), Portfolio, "Portfolio");
        return this;
    }

    // Type The Security Description
    public EditDataManagerRow typeSecurityDescription(String SecurityDescription) {
        isVisible(frameClientDetailsLocator("Security  Description:"), "SecurityDescription");
        clear(frameClientDetailsLocator("Security  Description:"));
        typeWithType(frameClientDetailsLocator("Security  Description:"), SecurityDescription, "SecurityDescription");
        return this;
    }

    // Type The Security Type
    public EditDataManagerRow typeSecurityType(String SecurityType) {
        isVisible(frameClientDetailsLocator("Security  Type:"), "SecurityType");
        clear(frameClientDetailsLocator("Security  Type:"));
        typeWithType(frameClientDetailsLocator("Security  Type:"), SecurityType, "SecurityType");
        return this;
    }

    // Type The SEDOL
    public EditDataManagerRow typeSedol(String Sedol) {
        isVisible(frameClientDetailsLocator("SEDOL:"), "Sedol");
        clear(frameClientDetailsLocator("SEDOL:"));
        typeWithType(frameClientDetailsLocator("SEDOL:"), Sedol, "Sedol");
        return this;
    }

    // Type The Settlement Date
    public EditDataManagerRow typeSettlementDate(String SettlementDate) {
        isVisible(frameClientDetailsLocator("Settlement  Date:", 2), "SettlementDate");
        clear(frameClientDetailsLocator("Settlement  Date:", 2));
        typeWithType(frameClientDetailsLocator("Settlement  Date:", 2), SettlementDate, "SettlementDate");
        return this;
    }

    // Type The Side
    public EditDataManagerRow typeSide(String Side) {
        isVisible(frameClientDetailsLocator("Side:"), "Side");
        clear(frameClientDetailsLocator("Side:"));
        typeWithType(frameClientDetailsLocator("Side:"), Side, "Side");
        return this;
    }

    // Type The Status
    public EditDataManagerRow typeStatus(String Status) {
        isVisible(frameClientDetailsLocator("Status:"), "Status");
        clear(frameClientDetailsLocator("Status:"));
        typeWithType(frameClientDetailsLocator("Status:"), Status, "Status");
        return this;
    }

    // Type The Ticker
    public EditDataManagerRow typeTicker(String Ticker) {
        isVisible(frameClientDetailsLocator("Ticker:"), "Ticker");
        clear(frameClientDetailsLocator("Ticker:"));
        typeWithType(frameClientDetailsLocator("Ticker:"), Ticker, "Ticker");
        return this;
    }

    // Type The Trade ID
    public EditDataManagerRow typeTradeId(String TradeId) {
        isVisible(frameClientDetailsLocator("Trade  ID:"), "TradeId");
        clear(frameClientDetailsLocator("Trade  ID:"));
        typeWithType(frameClientDetailsLocator("Trade  ID:"), TradeId, "TradeId");
        return this;
    }

    public EditDataManagerRow editRowInformation(String brokerName, String tradeDate, String accountName, String assetClass, String baseCurrency,
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
        reportPass("User successfully verified all fields and entered values");
        return this;
    }

    public EditDataManagerRow editRowInformation(String brokerName, String tradeDate, String assetClass,
                                                 String settlementDate) {
        typeBrokerName(brokerName)
                .typeTradeDate(tradeDate)
                .typeAssetClass(assetClass)
                .typeSettlementDate(settlementDate);
        reportPass("User successfully verified all fields and entered values");
        return this;
    }

    public EditDataManagerRow validateChangeLog(int changeLog) {

        for (int i = 1; i <= changeLog; i++) {
            String getInnerText = getInnerText("(//table)[last()-1]//tbody//tr[" + i + "]//td[1]");
            boolean verifyExactText = verifyPartialText("//td[text()='" + getInnerText + "']", getInnerText);
            Assert.assertTrue(verifyExactText);
        }
        reportPass("User successfully verified that edited fields are displayed in change logs.");
        return this;
    }

    public EditDataManagerRow refreshPageAndCancelDialog() {
//        reLoadPage();
//        refreshPageToCurrentURL();
        pause("medium");
        pause("medium");
        refreshPageUsingJavaScript();
        pause("medium");
        pause("medium");
        handleDialogCancel();
        return this;
    }

}
