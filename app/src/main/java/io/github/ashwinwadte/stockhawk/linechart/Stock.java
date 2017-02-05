/**
 * Created by Ashwin on 23-Aug-16
 */
package io.github.ashwinwadte.stockhawk.linechart;

public class Stock {
    String Symbol;
    String Date;
    String Open;
    String High;
    String Low;
    String Close;
    String Volume;
    String Adj_Close;
    String Bid_Price;
    String Change;
    String Percent_Change;

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getOpen() {
        return Open;
    }

    public void setOpen(String open) {
        Open = open;
    }

    public String getHigh() {
        return High;
    }

    public void setHigh(String high) {
        High = high;
    }

    public String getLow() {
        return Low;
    }

    public void setLow(String low) {
        Low = low;
    }

    public String getClose() {
        return Close;
    }

    public void setClose(String close) {
        Close = close;
    }

    public String getVolume() {
        return Volume;
    }

    public void setVolume(String volume) {
        Volume = volume;
    }

    public String getAdj_Close() {
        return Adj_Close;
    }

    public void setAdj_Close(String adj_Close) {
        Adj_Close = adj_Close;
    }

    public String getBid_Price() {
        return Bid_Price;
    }

    public void setBid_Price(String bid_Price) {
        Bid_Price = bid_Price;
    }

    public String getChange() {
        return Change;
    }

    public void setChange(String change) {
        Change = change;
    }

    public String getPercent_Change() {
        return Percent_Change;
    }

    public void setPercent_Change(String percent_Change) {
        Percent_Change = percent_Change;
    }
}
