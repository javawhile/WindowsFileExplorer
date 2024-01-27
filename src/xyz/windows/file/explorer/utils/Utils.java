package xyz.windows.file.explorer.utils;

import java.util.Date;
import java.util.Locale;

public class Utils {

    private static final double KB = 1024F;
    private static final String FLOAT_PRECISION_2 = "%.2f";
    private static final String FLOAT_PRECISION_3 = "%.3f";

    //YYYY-MMM-DD HH:mm AM/PM
    private static final String DATE_TIME_FORMAT = "%04d-%s-%02d %02d:%02d";
    private static final String[] MONTHS = {
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };
    
    private static final int READABLE_NAME_MAX_LENGTH = 50;
    private static final String READABLE_NAME_OVERFLOW_STRING = "...";

    public static String getReadableBytes(long B) {
        double inKB = B / KB;
        if (inKB < KB) {
            return String.format(FLOAT_PRECISION_2, inKB) + " Kb";
        }

        double inMB = inKB / KB;
        if (inMB < KB) {
            return String.format(FLOAT_PRECISION_2, inMB) + " Mb";
        }

        double inGB = inMB / KB;
        if (inGB < KB) {
            return String.format(FLOAT_PRECISION_2, inGB) + " Gb";
        }

        double inTB = inGB / KB;
        return String.format(FLOAT_PRECISION_2, inTB) + " Tb";
    }

    public static String getReadableTimestamp(long timeInMillis) {
        //ORIGINAL TIMESTAMP
        Date date = new Date(timeInMillis);

        return String.format(Locale.US,
                DATE_TIME_FORMAT,
                date.getYear() + 1900,
                MONTHS[date.getMonth()],
                date.getDay(),
                date.getHours(),
                date.getMinutes());
    }

    public static String getReadablePercent(double sizePercentRelativeToParent) {
        return String.format(
                FLOAT_PRECISION_3,
                sizePercentRelativeToParent
        ) + " %";
    }
    
    public static String getReadableNumber(long number) {
        return String.valueOf(number);
    }

    public static String getReadableString(String string) {
        String readableString;
        if (string != null && string.length() > READABLE_NAME_MAX_LENGTH) {
            readableString = string.substring(0, READABLE_NAME_MAX_LENGTH + 1) + READABLE_NAME_OVERFLOW_STRING;
        } else {
            readableString = string;
        }
        return readableString;
    }
    
}
