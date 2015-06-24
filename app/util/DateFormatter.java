package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFormatter {

    public static String format(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat tf = new SimpleDateFormat("hh:mm");

        Calendar calendar = GregorianCalendar.getInstance(play.i18n.Lang.defaultLang().toLocale());
        calendar.setTime(new Date());
        int day1 = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTime(date);
        int day2 = calendar.get(Calendar.DAY_OF_MONTH);

        if (day1 == day2 && System.currentTimeMillis() - date.getTime() < 3600 * 24 * 1000)
            return "today at " + tf.format(date);

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        day2 = calendar.get(Calendar.DAY_OF_MONTH);
        if (day1 == day2 && System.currentTimeMillis() - date.getTime() < 2 * 3600 * 24 * 1000)
            return "yesterday at " + tf.format(date);

        return "on " + df.format(date);
    }
}
