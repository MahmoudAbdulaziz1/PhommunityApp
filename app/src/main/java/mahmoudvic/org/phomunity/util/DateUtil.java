package mahmoudvic.org.phomunity.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getDayName(int dayOfWeek) {

        String dayName = null;
        switch (dayOfWeek) {
            case 1:
                dayName = "Sunday";
                break;
            case 2:
                dayName = "Monday";
                break;
            case 3:
                dayName = "Tuesday";
                break;
            case 4:
                dayName = "Wednesday";
                break;
            case 5:
                dayName = "Thursday";
                break;
            case 6:
                dayName = "Friday";
                break;
            case 7:
                dayName = "Saturday";
                break;
        }
        return dayName;
    }

    public static Date convertToEntityAttribute(String stringDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return null == stringDate ? null : formatter.parse(stringDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String convertToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return null == date ? null : formatter.format(date);

    }

    public static String getPostDate(Date date) {
        SimpleDateFormat sdf4 = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        return sdf4.format(date);
    }
}
