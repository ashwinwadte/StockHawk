/**
 * Created by Ashwin on 10-Feb-17
 */
package io.github.ashwinwadte.stockhawk.utils;

public class Constants {
    public static final String INTENT_TAG = "TAG";
    public static final String INTENT_STOCK_SYMBOL = "symbol";
    public static final String ADD = "add";
    public static final String INIT = "init";
    public static final String PERIODIC = "periodic";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    //YAHOO Stock API JSON keys
    public static final String JSON_QUERY = "query";
    public static final String JSON_COUNT = "count";
    public static final String JSON_RESULTS = "results";
    public static final String JSON_QUOTE = "quote";
    public static final String JSON_CHANGE = "Change";
    public static final String JSON_SYMBOL = "symbol";
    public static final String JSON_BID = "Bid";
    public static final String JSON_CHANGE_IN_PERCENT = "ChangeinPercent";

    //NullPointerException message
    public static final String EXCEPTION_WRONG_SYMBOL = "wrong_symbol";

    //initial quotes
    public static final String INITIAL_QUOTES = "\"YHOO\",\"AAPL\",\"GOOG\",\"MSFT\")";
}
