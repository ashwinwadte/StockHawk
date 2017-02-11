package io.github.ashwinwadte.stockhawk.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

@Database(version = QuoteDatabase.VERSION)
public class QuoteDatabase {
    public static final int VERSION = 8;
    @Table(QuoteColumns.class)
    public static final String QUOTES = "quotes";

    private QuoteDatabase() {
    }
}