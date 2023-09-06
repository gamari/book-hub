package gamari.app.features.base.libs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {
    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, new Locale("ja", "JP"));
        return sdf.format(date);
    }
}
