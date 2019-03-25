package apps.my.p2017;

import java.util.Calendar;

public class Calendars {

    public static Calendar from(int year, int month, int date) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, date);

        return calendar;


    }


}
