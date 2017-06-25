package net.lsf.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Formatter {
    public Formatter() {
    }

    public DecimalFormat getDecimalFormat() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "#,##0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        return decimalFormat;
    }
}