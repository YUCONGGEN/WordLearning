package cn.lhf223.word.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: word
 * @description:
 * @author: lhf
 * @create: 2021-02-14 22:02
 */
public class DateUtils {
    public static boolean dateCompare(Date date1, Date date2) {
        if (date1==null || date2==null) {
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date1);
        String format2 = format.format(date2);
        return !format1.equals(format2);
    }

    public static Date timeToDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}