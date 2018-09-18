package com.danger.event;

import com.danger.bean.DemoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by xiaojie.Ma on 2018/9/12.
 */
@Component
public class DemoPublisher {

    @Autowired
    ApplicationContext applicationContext;

    public void publisher(String msg) {
        //消息发布
        applicationContext.publishEvent(new DemoEvent(this, msg));
    }


}
