package eu.galkina.zonky.mpchecker.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Common methods for working with dates
 */
public class DateUtil {

    public static String toISO8601(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.toISO8601(LocalDateTime.of(2018,7,23,18,35, 43)));
    }
}
