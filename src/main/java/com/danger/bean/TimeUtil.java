package com.danger.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * Created by cocky on 2016/3/21.
 */
public class TimeUtil {

    public static final TimeZone defaultZone = TimeZone.getTimeZone("GMT");//测试和生产环境
    //public static final TimeZone defaultZone = TimeZone.getTimeZone("GMT+8:00");//开发环境
    public static final TimeZone Zone0 = TimeZone.getTimeZone("GMT");//0时区

    //获取服务器时区
    public static TimeZone getServerTimeZone(){
        Calendar cal = Calendar.getInstance();
        TimeZone timeZone = cal.getTimeZone();
        return timeZone;
    }

    //获取HDFS服务器时区
    public static TimeZone getHdfsTimeZone(){
        ResourceBundle rb = ResourceBundle.getBundle("app");
        String hdfszone=rb.getString("hdfszone");
        return  TimeZone.getTimeZone(hdfszone);
    }
    //传时间，获得第前N周的开始时间。也就是第前N周的第一天的开始时间。如7月20号，第一周，返回时间是7月14号0点时间。第二周是7月8号0点
    public static long getBeforeNWeekBeginTime(long time,int week){
        return getBeforeNWeekBeginTime(time, week, defaultZone);
    }

    public static long getBeforeNWeekBeginTime(long time,int week,TimeZone tz){
        int day=-(7*week)+1;
        long result=getNDayOn000000Ago(time,day,tz==null?defaultZone:tz);
        return result;
    }


    //传时间，获得第前N周的第一天的结束时间。如7月20号，第一周，返回时间是7月14号23点59分59秒时间。第二周是7月8号23点59分59秒
    public static long getBeforeNWeekDayEndTime(long time,int week){
        return getBeforeNWeekDayEndTime(time,week,defaultZone);
    }

    public static long getBeforeNWeekDayEndTime(long time,int week,TimeZone tz){
        int day=-(7*week)+1;
        long result=getNDayOn235959Ago(time, day,tz==null?defaultZone:tz);
        return result;
    }

    /**
     * 返回指定时间 正小时毫秒数
     * @param time
     * @return
     */
    public static long getHour( long time) {


        return getHour(time, defaultZone);
    }

    public static long getHour( long time,TimeZone tz) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(tz == null ? defaultZone : tz);
        c.setTimeInMillis(time);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTimeInMillis();
    }

    //给定时间，转换成指定时区时间  getSpecificTimeByTimeZone
    public static long getSpecificTimeByTimeZone( long time) {
        return getSpecificTimeByTimeZone(time, defaultZone);
    }
    public static long getSpecificTimeByTimeZone( long time,TimeZone tz) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(tz == null?defaultZone:tz);
        c.setTimeInMillis(time);
        return c.getTimeInMillis();
    }

    /**
     * 返回指定时间 天0时0分0秒 毫秒数
     * @param time
     * @return
     */
    public static long getDay( long time) {

        return getDay(time, defaultZone);
    }

    public static long getDay( long time,TimeZone tz) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(tz==null?defaultZone:tz);
        c.setTimeInMillis(time);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    /**
     * 获取本小时的时间戳
     * @param time
     * @return
     */
    public static long getHourThisTime( long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(defaultZone);
        c.setTimeInMillis(time);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    /**
     * 获取给定时间的小时数 24进制
     * @param time if == -1 then return now hour else truth
     * @return if(hour == 0) 24 else hour
     */
    public static int getDayOfHour(long time) {
        return getDayOfHour(time, defaultZone);
    }

    public static int getDayOfHour(long time,TimeZone tz) {

        Calendar c = Calendar.getInstance();
        c.setTimeZone(tz==null?defaultZone:tz);
        if (time == -1) {
            c.setTimeInMillis(System.currentTimeMillis());
        } else {
            c.setTimeInMillis(time);
        }

        int hour = c.get(Calendar.HOUR_OF_DAY);

        //return (hour == 0)?24: hour;
        //为0时需要特殊处理,取昨天的总共小时数
        if(hour==0) {
            Long yestodayTime = getNDayOn000000Ago(time, -1, tz);
            hour = getDayHours(yestodayTime, tz);
        }
        return hour;
    }

    //返回某个时间的小时值如 21:23:89 返回21
    public static int getRealDayOfHour(long time,TimeZone tz) {
        return getRealDayOfHour(time,0, tz);
    }

    public static int getRealDayOfHour(long time,int N,TimeZone tz) {

        Calendar c = Calendar.getInstance();
        c.setTimeZone(tz==null?defaultZone:tz);
        c.add(Calendar.DAY_OF_MONTH, N);
        if (time == -1) {
            c.setTimeInMillis(System.currentTimeMillis());
        } else {
            c.setTimeInMillis(time);
        }
        int hour = c.get(Calendar.HOUR_OF_DAY);

        return hour;
    }

    /**
     * 获取当前时间所在周的第一天时间 0时0分0秒
     * @param time
     * @return
     */
    public static long getWeekOfDay(long time){

        return getWeekOfDay(time, defaultZone);
    }

    public static long getWeekOfDay(long time,TimeZone tz){
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(tz==null?defaultZone:tz);
        cal.setTimeInMillis(time);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getNDayOn000000Ago(cal.getTimeInMillis(), 0, tz);
    }
    
    public static long getWeekOfLastDay(long time,TimeZone tz){
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(tz==null?defaultZone:tz);
        cal.setTimeInMillis(time);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return getNDayOn235959Ago(cal.getTimeInMillis(), 0, tz);
    }

    /**
     * 获取当前时间所在月份的第一天时间 0时0分0秒
     * @param time
     * @return
     */
    public static long getMonthOfDay(long time){

        return getMonthOfDay(time, defaultZone);
    }

    public static long getMonthOfDay(long time,TimeZone tz){
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(tz==null?defaultZone:tz);
        cal.setTimeInMillis(time);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getNDayOn000000Ago(cal.getTimeInMillis(), 0, tz);
    }





    //本月最后一天的开始时间
    public static long getLastDayOfMonthOn000000(long time,TimeZone tz){
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(tz == null ? defaultZone : tz);
        cal.setTimeInMillis(time);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        //cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)+1);
        // cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)-1);
        cal.add(Calendar.MONTH, 1);//下个月1号
        cal.add(Calendar.DAY_OF_MONTH, -1);//下个月1号减1，就是这个月最后一天
        return getNDayOn000000Ago(cal.getTimeInMillis(), 0, tz);
    }

    //本月最后一天的结束时间
    public static long getLastDayOfMonthOn235959(long time,TimeZone tz){
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(tz == null ? defaultZone : tz);
        cal.setTimeInMillis(time);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        //cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)+1);
       // cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)-1);
        cal.add(Calendar.MONTH, 1);//下个月1号
        cal.add(Calendar.DAY_OF_MONTH, -1);//下个月1号减1，就是这个月最后一天
        return getNDayOn235959Ago(cal.getTimeInMillis(), 0, tz);
    }




    /**
     * 获取几天前的时间 0时0分0秒
     * @param time
     * @param N
     * @return
     */
    public static long getNDayOn000000Ago( long time,int N) {

        return getNDayOn000000Ago(time, N, defaultZone);
    }

    public static long getNDayOn000000Ago( long time,int N,TimeZone tz) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(tz==null?defaultZone:tz);
        c.setTimeInMillis(time);
        c.add(Calendar.DAY_OF_MONTH, N);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    //获取某天一共有多少小时
    public static int getDayHours( long time,TimeZone tz) {
        long lastTime=getNDayOn235959Ago(time,0,tz);
        Calendar c = Calendar.getInstance();
        c.setTimeZone(tz == null ? defaultZone : tz);
        c.setTimeInMillis(lastTime);
        return c.get(Calendar.HOUR_OF_DAY)+1;
    }
    /**
     * 获取周小时数，截止到今天本周内有多少小时
     * @param time 今天的开始时间
     * @return
     */
    public static int getWeekHours( long time) {

        return getWeekHours(time, defaultZone);
    }

    public static int getWeekHours( long time,TimeZone tz) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(tz==null?defaultZone:tz);
        c.setTimeInMillis(time);
        int dayofweek=c.get(Calendar.DAY_OF_WEEK);
        return (dayofweek+1)*24;
    }

    /**
     * 获取月小时数，截止到今天本月内有多少小时
     * @param time
     * @return
     */
    public static int getMonthHours( long time) {
        return getMonthHours(time,defaultZone);
    }

    public static int getMonthHours( long time,TimeZone tz) {
        Calendar c = Calendar.getInstance();
        //c.setTimeZone(defaultZone);
        c.setTimeZone(tz==null?defaultZone:tz);
        c.setTimeInMillis(time);
        int dayofmonth=c.get(Calendar.DAY_OF_MONTH);
        return dayofmonth*24;
    }

    /**
     * 获取-N小时前的millis
     * @param time
     * @param N
     * @return
     */
    public static long getNHour( long time,int N) {

        return  getNHour(time, N, defaultZone);
    }

    public static long getNHour(long time, int N, TimeZone tz) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(tz == null ? defaultZone : tz);
        c.setTimeInMillis(time);
        c.add(Calendar.HOUR_OF_DAY, N);
        return c.getTimeInMillis();
    }



    /**
     * 获取几天前的时间 23时59分59秒
     * @param time
     * @param N
     * @return
     */
    public static long getNDayOn235959Ago( long time,int N) {

        return getNDayOn235959Ago(time, N,defaultZone);
    }

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    //change20161017 by huwj 由于夏令时、冬令时 的问题，某天的小时数可能是23或25，所以逻辑修改为取后一天毫秒数减1
    public static long getNDayOn235959Ago( long time,int N,TimeZone tz) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(tz==null?defaultZone:tz);
        c.setTimeInMillis(time);
        c.add(Calendar.DAY_OF_MONTH, N+1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis()-1;
    }

    public static String getFormatTime(long time){
        return getFormatTime(time,defaultZone);
    }

    public static String getFormatTime(long time,TimeZone tz){
        sdf.setTimeZone(tz==null?defaultZone:tz);
        return sdf.format(new Date(time));
    }

    public static long get0or5Minute(long time){
        return get0or5Minute(time, defaultZone);
    }

    public static long get0or5Minute(long time,TimeZone tz){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        c.setTimeZone(tz==null?defaultZone:tz);
        c.add(Calendar.MINUTE, -1*c.get(Calendar.MINUTE)%5);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    /**
     * 通过传：yyyyMMdd 参数，该天的00：00.000
     * @param
     * @return
     */
    public static long getStartTime(String strTime){
        SimpleDateFormat fmt =new SimpleDateFormat("yyyyMMdd");
        fmt.setTimeZone(defaultZone);
        long targetTime=0L;
        try{
            long m=fmt.parse(strTime).getTime();
            targetTime=TimeUtil.getNDayOn000000Ago(m,0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return targetTime;
    }

    public static long getTimeStample( int year,int month, int day,int hour, int minute,int second,int milli,TimeZone tz) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH,month-1);
        c.set(Calendar.DAY_OF_MONTH,day);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        c.set(Calendar.MILLISECOND, milli);
        c.setTimeZone(tz);
        return c.getTimeInMillis();
    }


    /**
     * 获取某天的最后时间
     * 由于夏令时、冬令时 的问题，某天的小时数可能是23或25，所以逻辑修改为取后一天毫秒数减1
     * @param year
     * @param month
     * @param day
     * @param tz
     * @return
     */
    public static long getLastTime( int year,int month, int day,TimeZone tz) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month-1);
        c.set(Calendar.DAY_OF_MONTH,day+1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.setTimeZone(tz==null?defaultZone:tz);
        return c.getTimeInMillis()-1;
    }

    //获取开始时间
    public static long getBeginTime( int year,int month, int day,TimeZone tz) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month-1);
        c.set(Calendar.DAY_OF_MONTH,day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.setTimeZone(tz==null?defaultZone:tz);
        return c.getTimeInMillis();
    }

}
