package tmnt.example.onedaily.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tmnt on 2017/4/26.
 */

public class DateFormatUtil {
    private static SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("yyyyMMdd");

    public static String dateFormatForWeek(String date) {
        Calendar calendar = null;

        try {
            Date date1 = SIMPLEDATEFORMAT.parse(date);
            calendar = Calendar.getInstance();
            calendar.setTime(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((calendar.get(Calendar.MONTH) + 1) + "月");
        stringBuilder.append(calendar.get(Calendar.DAY_OF_MONTH) + "日");
        stringBuilder.append(getWeek(calendar));
        return stringBuilder.toString();

    }

    public static String nowDate() {
        Date date = new Date();
        return SIMPLEDATEFORMAT.format(date);
    }

    public static String dateFormatForSub(int sub) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -sub);

        return SIMPLEDATEFORMAT.format(calendar.getTime());
    }

    public static String dateFomeNomal() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return simpleDateFormat.format(new Date());
    }

    public static String getWeek(Calendar calendar) {
        String week = null;
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                week = "星期一";
                break;
            case 2:
                week = "星期二";
                break;
            case 3:
                week = "星期三";
                break;
            case 4:
                week = "星期四";
                break;
            case 5:
                week = "星期五";
                break;
            case 6:
                week = "星期六";
                break;
            case 7:
                week = "星期日";
                break;
        }
        return week;
    }

}
