package com.danger.bean;

import org.springframework.context.ApplicationEvent;

/**
 * Created by xiaojie.Ma on 2018/9/12.
 */
public class DemoEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    //交互的信息
    private String msg;

    //
    public DemoEvent(Object source , String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
