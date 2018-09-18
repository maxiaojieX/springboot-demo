package com.danger.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by xiaojie.Ma on 2018/5/9.
 */
public class Danger {

    private Integer id;
    private String qq;
    private String password;
    private String other;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public static void main(String[] args) {
        Calendar ca = Calendar.getInstance();
        ca.setTimeZone(TimeZone.getTimeZone("Asia/Ulan_Bator"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String last = format.format(ca.getTime());
        try {
            System.out.println(last);
        } catch (Exception e) {
//            return null;
        }

    }
}
