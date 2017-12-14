package com.red.dargon.nsbaselibrary.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    // private static final Logger log = LoggerFactory.getLogger
    // (DateUtil.class);

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String MM_DD_HH = "MM_dd_HH";
    public static final String MM_DD_HH_MM = "MM-dd  HH:mm";
    public static final String M_D_H = "M_d_H";


    public static final String M_DD = "M_dd";
    public static final String MM_DD = "MM月dd日";
    public static final String MM_DD_SIMPLE = "MM-dd";
    public static final String MM_SS= "mm:ss";

    /**
     * 2015-5-26 to 2015-05-26
     *
     * @param date
     * @since 2015年5月27日下午12:00:29
     */
    public static String stringToString(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        String[] items = date.split("-");
        StringBuilder sb = new StringBuilder(items[0]);
        for (int i = 1, len = items.length; i < len; i++) {
            if (items[i].length() == 1) {
                items[i] = "0" + items[i];
            }
            sb.append("-" + items[i]);
        }
        return sb.toString();
    }

    /**
     * @return 当前小时
     * @since 2015年7月23日下午4:04:46
     */
    public static int getCurrentHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * @return 当前年
     * @since 2015年7月23日下午4:04:46
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * @param date
     * @return {@link #YYYY_MM_DD}格式的日期字符串
     * @since 2015年1月18日下午1:13:21
     */
    public static String getDateToString(Date date) {
        String res = null;
        if (date != null) {
            DateFormat format = new SimpleDateFormat(YYYY_MM_DD);
            res = format.format(date);
        }
        return res;
    }

    /**
     * @param time   时间
     * @param format 时间格式
     * @return
     * @since 2015年3月11日上午12:01:19
     */
    public static String getDateToString(long time, String format) {
        return getDateToString(format, new Date(time));
    }

    /**
     * 将ms转化为mm:ss
     *
     * @param l
     * @return
     * @since 2014年12月6日下午3:14:09
     */
    @SuppressLint("DefaultLocale")
    public static String formatLongToTimeStr(Long l) {
        // int hour = 0;
        int minute = 0;
        int second = 0;

        second = l.intValue() / 1000;

        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
            // hour = minute / 60;
            minute = minute % 60;
        }
        return String.format("%02d:%02d", minute, second);
        // return (getTwoLength (hour) + ":" + getTwoLength (minute) + ":" + getTwoLength (second));
    }

    // private static String getTwoLength(final int data){
    // if (data < 10) {
    // return "0" + data;
    // } else {
    // return "" + data;
    // }
    // }

    /**
     * 返回指定格式的日期（字符串形式）
     *
     * @param str
     * @return
     * @since 2012-7-12下午12:46:55
     */
    public static String getCurrentDate(String str) {
        DateFormat format = new SimpleDateFormat(str);
        String formatDate = format.format(new Date());
        return formatDate;
    }

    /**
     * @param str
     * @param date
     * @return
     * @since 2013-11-2下午05:49:32
     */
    public static String getDateToString(String str, Date date) {
        String res = null;
        if (date != null) {
            DateFormat format = new SimpleDateFormat(str);
            res = format.format(date);
        }
        return res;
    }

    /**
     * 根据整型月份获取字符串月份
     *
     * @param month
     * @return
     * @since 2012-7-10下午03:51:35
     */
    public static String getStrMonthByIntMonth(Integer month) {
        switch (month) {
            case 1:
                return "01";
            case 2:
                return "02";
            case 3:
                return "03";
            case 4:
                return "04";
            case 5:
                return "05";
            case 6:
                return "06";
            case 7:
                return "07";
            case 8:
                return "08";
            case 9:
                return "09";
            case 10:
                return "10";
            case 11:
                return "11";
            case 12:
                return "12";
        }
        return "";
    }

    /**
     * 获取月份名称
     *
     * @param month
     * @return
     * @since 2012-7-10下午03:51:35
     */
    public static String getYfMcByIntMonth(Integer month) {
        switch (month) {
            case 1:
                return "一月";
            case 2:
                return "二月";
            case 3:
                return "三月";
            case 4:
                return "四月";
            case 5:
                return "五月";
            case 6:
                return "六月";
            case 7:
                return "七月";
            case 8:
                return "八月";
            case 9:
                return "九月";
            case 10:
                return "十月";
            case 11:
                return "十一月";
            case 12:
                return "十二月";
        }
        return "";
    }

    /**
     * 将字符串转化为指定格式的日期
     *
     * @param date
     * @return
     * @since 2013-3-9下午02:39:24
     */
    public static Date stringToDate(String date, String formatStr) {
        SimpleDateFormat formatDate = new SimpleDateFormat(formatStr);
        Date time = null;
        try {
            time = formatDate.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * @param date
     * @param formatStr
     * @return
     * @since 2015年5月19日下午4:45:02
     */
    public static String stringToSpec(String date, String formatStr) {
        SimpleDateFormat formatDate = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Date time = null;
        try {
            time = formatDate.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getDateToString(formatStr, time);
    }

    /**
     * 将字符串日期转化为特定格式的字符串日期
     *
     * @param date
     * @param preFormat 之前格式
     * @param formatStr 现在格式
     * @return
     * @since 2015年5月27日上午11:38:59
     */
    public static String stringToSpec(String date, String preFormat, String formatStr) {
        SimpleDateFormat formatDate = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Date time = null;
        try {
            time = formatDate.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getDateToString(formatStr, time);
    }

    /**
     * 获取 当前年、半年、季度、月、日、小时 开始结束时间
     */

    private final static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
    ;
    private final static SimpleDateFormat longHourSdf = new SimpleDateFormat("yyyy-MM-dd HH");
    private final static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获得本周的第一天，周一
     *
     * @return
     */
    public static Date getCurrentWeekDayStartTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
            c.add(Calendar.DATE, -weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 当前时刻推迟一小时
     *
     * @return
     * @since 2014年12月17日下午4:13:26
     */
    public static Date delayOneHour(Date current) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.HOUR, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获得本周的最后一天，周日
     *
     * @return
     */
    public static Date getCurrentWeekDayEndTime() {
        Calendar c = Calendar.getInstance();
        try {
            int weekday = c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, 8 - weekday);
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 获得本天的开始时间，即2012-01-01 00:00:00
     *
     * @return
     */
    public static Date getCurrentDayStartTime() {
        Date now = new Date();
        try {
            now = longSdf.parse(shortSdf.format(now) + " 00:00:01");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本天的结束时间，即2012-01-01 23:59:59
     *
     * @return
     */
    public static Date getCurrentDayEndTime() {
        Date now = new Date();
        try {
            now = longSdf.parse(shortSdf.format(now) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取当天时间
     *
     * @return
     * @since 2013-11-2下午06:02:28
     */
    public static Date getCurrentDay() {
        Date now = new Date();
        try {
            now = shortSdf.parse(shortSdf.format(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本小时的开始时间，即2012-01-01 23:59:59
     *
     * @return
     */
    public static Date getCurrentHourStartTime() {
        Date now = new Date();
        try {
            now = longHourSdf.parse(longHourSdf.format(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取一小时前的时间
     *
     * @return
     */
    public static long getOneHourBefore() {
        return System.currentTimeMillis() - 60 * 60 * 1000;
    }

    /**
     * 获取一天前的时间
     *
     * @return
     */
    public static long getOneDayBefore() {
        return System.currentTimeMillis() - 24 * 60 * 60 * 1000;
    }

    /**
     * @param beforeNum 之前天数
     * @return n天前的时间
     * @since 2015年10月30日上午9:54:55
     */
    public static long getBeforeNDate(int beforeNum) {
        return System.currentTimeMillis() - 24 * 60 * 60 * 1000 * beforeNum;
    }

    /**
     * 获得本小时的结束时间，即2012-01-01 23:59:59
     *
     * @return
     */
    public static Date getCurrentHourEndTime() {
        Date now = new Date();
        try {
            now = longSdf.parse(longHourSdf.format(now) + ":59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本月的开始时间，即2012-01-01 00:00:00
     *
     * @return
     */
    public static Date getCurrentMonthStartTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前月的结束时间，即2012-01-31 23:59:59
     *
     * @return
     */
    public static Date getCurrentMonthEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 上个月的开始时间，即2012-01-01 00:00:00
     *
     * @return
     * @since 2013-9-18下午12:46:26
     */
    @SuppressWarnings("unused")
    public static Date getLastMonthStartTime() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String months = "";
        String days = "";
        if (month > 1) {
            month--;
        } else {
            year--;
            month = 12;
        }
        if (!(String.valueOf(month).length() > 1)) {
            months = "0" + month;
        } else {
            months = String.valueOf(month);
        }
        if (!(String.valueOf(day).length() > 1)) {
            days = "0" + day;
        } else {
            days = String.valueOf(day);
        }
        String firstDay = "" + year + "-" + months + "-01 00:00:00";

        return stringToDate(firstDay, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 上个月的结束时间，即2012-01-31 23:59:59
     *
     * @return
     * @since 2013-9-18下午12:47:14
     */
    public static Date getLastMonthEndTime() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String months = "";
        String days = "";
        if (month > 1) {
            month--;
        } else {
            year--;
            month = 12;
        }
        if (!(String.valueOf(month).length() > 1)) {
            months = "0" + month;
        } else {
            months = String.valueOf(month);
        }
        if (!(String.valueOf(day).length() > 1)) {
            days = "0" + day;
        } else {
            days = String.valueOf(day);
        }
        String lastDay = "" + year + "-" + months + "-" + days + " 23:59:59";
        return stringToDate(lastDay, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 当前年的开始时间，即2012-01-01 00:00:00
     *
     * @return
     */
    public static Date getCurrentYearStartTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前年的结束时间，即2012-12-31 23:59:59
     *
     * @return
     */
    public static Date getCurrentYearEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取某年某月的最后一天
     *
     * @param year
     * @param month
     * @return
     * @since 2013-9-27下午08:22:00
     */
    public static int getLastDayByYearAndMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        int endday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println(calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月月末日期:" + endday);
        return endday;
    }

    /**
     * 当前季度的开始时间，即2012-01-1 00:00:00
     *
     * @return
     */
    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6) c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9) c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12) c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     *
     * @return
     */
    public static Date getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取前/后半年的开始时间
     *
     * @return
     */
    public static Date getHalfYearStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth >= 7 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 6);
            }
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;

    }

    /**
     * 获取前/后半年的结束时间
     *
     * @return
     */
    public static Date getHalfYearEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 两个日期相差天数
     *
     * @since 2015年3月11日下午8:25:03
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = Math.abs((time2 - time1) / (1000 * 3600 * 24));

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 两个日期相差天数
     *
     * @since 2015年3月11日下午8:25:03
     */
    public static int daysBetween(long time1, long time2) {
        long between_days = Math.abs((time2 - time1) / (1000 * 3600 * 24));
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 两个日期相差天数 昨天晚上 跟今天早上 也算一天相差
     *
     * @return 相差时间
     * @since 2015年3月11日下午8:25:03
     */
    public static int daysOfTwo(Date fDate, Date oDate) {

        Calendar aCalendar = Calendar.getInstance();

        aCalendar.setTime(fDate);

        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

        aCalendar.setTime(oDate);

        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

        return day2 - day1;

    }

    /**
     * 返回指定格式(str)的日期字符串
     *
     * @param date
     * @param str
     * @return
     * @since 2013-1-4下午05:42:38
     */
    public static String getDateStr(Date date, String str) {
        String formatDate = "";
        DateFormat format = new SimpleDateFormat(str);
        if (date != null) {
            formatDate = format.format(date);
        }
        return formatDate;
    }

    /**
     * 日期比较 oldDate 在 newDate 之前，返回 -1 oldDate 和 newDate在同一天 ，返回 0
     * oldDate 在 newDate 之后，返回 1
     *
     * @param oldDate
     * @param newDate
     * @return
     * @since 2013-8-28下午04:46:07
     */
    public static int compareDatesByCompareTo(Date oldDate, Date newDate) {
        DateFormat df = new SimpleDateFormat(YYYY_MM_DD);
        String oldStr = getDateStr(oldDate, YYYY_MM_DD);
        String newstr = getDateStr(newDate, YYYY_MM_DD);
        try {
            oldDate = df.parse(oldStr);
            newDate = df.parse(newstr);
        } catch (ParseException e) {
            e.printStackTrace();
            // log.error (e.getStackTrace ().toString ());
        }
        return oldDate.compareTo(newDate);

    }

    /**
     * 用于判断是否超期 如果date在当前日期之前，返回true 否则返回 false
     *
     * @param date
     * @return 返回 true 表示超期，false表示未超期
     * @since 2013-8-28下午04:02:56
     */
    public static boolean compareNowDate(Date date) {
        boolean flag = false;
        int num = DateUtil.compareDatesByCompareTo(new Date(), date);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 获取上周的今天 返回字符格式的日期，如2013-11-14
     *
     * @return
     * @since 2013-11-21下午02:56:15
     */
    public static String getPreDayOfWeek() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.WEEK_OF_YEAR, -1);
        return shortSdf.format(c.getTime());
    }

    /**
     * 获取上个月的今天 返回字符格式的日期，如2013-10-21
     *
     * @return
     * @since 2013-11-21下午02:57:25
     */
    public static String getPreDayOfMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        return shortSdf.format(c.getTime());
    }

    public static void main(String[] args) throws ParseException {

        // SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Date d1=sdf.parse("2012-09-08 10:10:10");
        // Date d2=sdf.parse("2012-09-15 00:00:00");
        // System.out.println(daysBetween(d1,d2));
        //
        // System.out.println (stringToDate("2013-11-02", "yyyy-MM-dd"));
        //
        // System.out.println ("start:"+getCurrentDayStartTime());
        //
        // System.out.println ("end:"+getCurrentDayEndTime());
        //
        // System.out.println ("==="+getCurrentDay());
        String old = "191";
        String code = "312";
        String zbxCode = "191001";

        System.out.println(zbxCode.replace(old, code));

    }

    /**
     * 现在
     */
    private static Date now = new Date();

    /**
     * 显示特定的时间
     *
     * @param createTime
     * @return
     * @since 2015年4月15日下午5:28:02
     */
    public static String getShowTime(long createTime) {
        now.setTime(System.currentTimeMillis());
        // 比较的时间
        Date time = new Date(createTime);
        try {
            // 日期相差时间
            int between = daysBetween(time, now);
            if (between == 0) {// 今天
                return getDateToString("HH:mm", time);
            } else if (between == 1) {// 昨天
                return getDateToString("昨天 HH:mm", time);
            } else if (between < 7) {// 一周
                return getDateToString("EEE HH:mm", time);
            }
            // 年初时间
            long startYear = getCurrentYearStartTime().getTime();
            if (createTime > startYear) {// 今年
                return getDateToString("MM-dd HH:mm", time);
            } else {// 超出今年
                return getDateToString("yyyy-MM-dd HH:mm", time);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 显示特定的时间
     *
     * @param createTime
     * @return
     * @since 2015年4月15日下午5:28:02
     */
    public static String getShowTimeSimple(long createTime) {
        now.setTime(System.currentTimeMillis());
        // 比较的时间
        Date time = new Date(createTime);
        try {
            // 日期相差时间
            int between = daysBetween(time, now);
            if (between == 0) {// 今天
                return getDateToString("HH:mm", time);
            } else if (between == 1) {// 昨天
                return getDateToString("昨天 ", time);
            } else if (between < 7) {// 一周
                return getDateToString("EEE", time);
            }
            // 年初时间
            long startYear = getCurrentYearStartTime().getTime();
            if (createTime > startYear) {// 今年
                return getDateToString("MM-dd ", time);
            } else {// 超出今年
                return getDateToString("yyyy-MM-dd ", time);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getWeekOfCurrentDay() {
        String[] weeks = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        int index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weeks[index];
    }

    /**
     * @param n 天数
     * @return
     */
    public static String getNDayTime(int n) {
        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.YEAR, cld.get(Calendar.YEAR));
        cld.set(Calendar.MONTH, cld.get(Calendar.MONTH));
        cld.set(Calendar.DATE, cld.get(Calendar.DATE));
        cld.add(Calendar.DATE, n);
        String month = cld.get(Calendar.MONTH) < 9 ? "0" + (cld.get(Calendar.MONTH)+1) : "" + (cld.get(Calendar.MONTH)+1);
        String day = cld.get(Calendar.DATE) < 10 ? "0" + cld.get(Calendar.DATE) : "" + cld.get(Calendar.DATE);


        return cld.get(Calendar.YEAR) + "-" + month + "-" + day;
    }

}
