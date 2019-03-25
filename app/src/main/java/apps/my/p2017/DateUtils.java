package apps.my.p2017;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class DateUtils {

    public static long date() {


        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTimeInMillis();


    }

    public static long daysBetween(Calendar c1, Calendar c2) {

        return TimeUnit.DAYS.convert(Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis()),
                TimeUnit.MILLISECONDS);

    }


}
