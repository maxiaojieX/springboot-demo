package com.danger.event;

import com.danger.bean.EventBean;
import org.springframework.context.ApplicationEvent;

/**
 * Created by xiaojie.Ma on 2018/9/12.
 */
public class TestEvent extends ApplicationEvent {
    public TestEvent(EventBean eventBean) {
        super(eventBean);
    }
}
