package com.danger.bean;

import org.springframework.context.ApplicationEvent;

/**
 * Created by xiaojie.Ma on 2018/9/12.
 */
public class EventBean  extends ApplicationEvent {

    private String ha;

    public EventBean(String source) {
        super(source);
        this.ha = source;
    }

    @Override
    public String getSource() {
        return ha;
    }

    public String getHa() {
        return ha;
    }

    public void setHa(String ha) {
        this.ha = ha;
    }

    @Override
    public String toString() {
        return "EventBean{" +
                "ha='" + ha + '\'' +
                '}';
    }
}
