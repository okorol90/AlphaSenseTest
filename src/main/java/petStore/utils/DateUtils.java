package petStore.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String getDateInFuture(int days) {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        DateTimeFormatter df =DateTimeFormatter.ofPattern(pattern);
        String date = ZonedDateTime.now().plusDays(days).format(df);
        return date;
    }
}
